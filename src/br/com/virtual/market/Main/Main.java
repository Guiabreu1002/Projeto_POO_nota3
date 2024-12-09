package br.com.virtual.market.Main;

import br.com.virtual.market.Enum.*;
import br.com.virtual.market.Model.*;
import br.com.virtual.market.View.VirtualMarketView;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //ADM E USUARIO INSTACIADOS PARA FACILITAR OS TESTES
        Adm adm = new Adm("Guilherme", "gui@gmail.com", "Gui@12345", ETipoUsuario.ADM);
        Cliente caze = new Cliente("caze", "caze@gmail.com", "Caze@12345", ETipoUsuario.CLIENTE);

        VirtualMarket virtualMarket = new VirtualMarket("VIRTUAL MARKET", "Marizopolis", "4564d56465");
        VirtualMarketView view = new VirtualMarketView();

        ProdutosPerecivel produtosPerecivel1 = new ProdutosPerecivel("arroz", 10, "arroz", ETipoProdutos.PERECIVEL, 10);
        ProdutosPerecivel produtosPerecivel2 = new ProdutosPerecivel("feijao", 8.10, "feijao", ETipoProdutos.PERECIVEL, 10);

        virtualMarket.estoque.add(produtosPerecivel1);
        virtualMarket.estoque.add(produtosPerecivel2);

        virtualMarket.usuarioController.dao.listarAdm().add(adm);
        virtualMarket.usuarioController.dao.listarClientes().add(caze);

        System.out.println("---------Seja Bem vindo ao VirtualMarket---------\n");
        int op = 0;
        while (op != 3) {
            System.out.println("""
                    1-Fazer Login
                    2-Registrar
                    3-Sair
                    """);
            op = view.inputOp();
            switch (op) {
                case 1:
                    String emaillog = view.inputEmail();
                    String passlog = view.inputPass();
                    if (virtualMarket.usuarioController.autenticarCliente(emaillog, passlog, ETipoUsuario.CLIENTE)) {
                        //MENU DOS CLIENTES
                        System.out.println("\nLogado!\n");
                        while (op != 11) {
                            System.out.println("1-Realizar Pagamento\n" +
                                    "2-Adicionar Produto ao carrinho\n" +
                                    "3-Remover Produto do carrinho\n" +
                                    "4-Pesquisar Produto\n" +
                                    "5-Ver Produtos em estoque\n" +
                                    "6-Ver suas informações\n" +
                                    "7-Ver seu carrinho\n" +
                                    "8-Atualizar suas informações\n" +
                                    "9-Ver historico de compras\n" +
                                    "10-Ver Notas Fiscais\n" +
                                    "11-Sair\n");
                            op = view.inputOp();
                            switch (op) {
                                case 1:

                                    Cliente clientePag = virtualMarket.usuarioController.retornarCliente(emaillog, passlog);
                                    if (clientePag.carrinho.verivicarCarrinho()) {
                                        EFormaPagamento forma = view.inputFormaPagamento();
                                        if(virtualMarket.venderProduto(clientePag, forma)) {
                                            System.out.println("\nPagamento realizado com sucesso!\n");
                                        }
                                    }else{
                                        System.out.println("\nCarrinho vazio!\n");
                                    }
                                    break;
                                case 2:
                                    String busca = view.inputNomeProduto();
                                    int quantidade = view.inputQuantidade();

                                    Produto prod = virtualMarket.retornarProduto(busca);
                                    if (prod == null) {
                                        System.out.println("\nProduto não existe!\n");
                                    } else {
                                        Map<String, Object> psProd = new HashMap<>();

                                        psProd.put("id", prod.getId());
                                        psProd.put("nome", prod.getNome());
                                        psProd.put("preco", prod.getPreco());
                                        psProd.put("describe", prod.getDescricao());
                                        psProd.put("tipo", prod.getTipo());
                                        psProd.put("quantidade", quantidade);

                                        if (!busca.equalsIgnoreCase(prod.getNome())) {
                                            System.out.println("\nProduto não encontrado para adicionar!\n");
                                        } else if (prod.getQuantidade() < quantidade) {
                                            System.out.println("\nA quantidade de produtos requirida excede a quantidade de produtos no estoque!\n");
                                        } else if (quantidade <= 0) {
                                            System.out.println("\nQuantidade menor ou igual a zero!\n");
                                        } else if (prod.getTipo() == ETipoProdutos.PERECIVEL){

                                            Produto carr = new ProdutosPerecivel(
                                                    psProd.get("nome").toString(),
                                                    (double) psProd.get("preco"),
                                                    psProd.get("describe").toString(),
                                                    (ETipoProdutos) psProd.get("tipo"),
                                                    (int) psProd.get("quantidade")
                                            );
                                            carr.setId(prod.getId());
                                            Cliente cliente = virtualMarket.usuarioController.retornarCliente(emaillog, passlog);
                                            cliente.getCarrinho().adicionarProduto(carr);

                                        } else if (prod.getTipo() == ETipoProdutos.NAO_PERECIVEL) {

                                            Produto carr = new ProdutosNaoPerecivel(
                                                    psProd.get("nome").toString(),
                                                    (double) psProd.get("preco"),
                                                    psProd.get("describe").toString(),
                                                    (ETipoProdutos) psProd.get("tipo"),
                                                    (int) psProd.get("quantidade")
                                            );
                                            carr.setId(prod.getId());
                                            Cliente cliente = virtualMarket.usuarioController.retornarCliente(emaillog, passlog);
                                            cliente.getCarrinho().adicionarProduto(carr);
                                        } else {
                                            System.out.println("\nError ao adicionar\n");
                                        }
                                    }

                                    break;
                                case 3:
                                    String removerProd = view.inputNomeProduto();
                                    Cliente clienteRemove = virtualMarket.usuarioController.retornarCliente(emaillog, passlog);
                                    clienteRemove.carrinho.removerProduto(removerProd);
                                    break;
                                case 4:
                                    String nomeProdPesq = view.inputNomeProduto();
                                    virtualMarket.pesquisarProduto(nomeProdPesq);
                                    break;
                                case 5:
                                    virtualMarket.listarProdutos();
                                    break;
                                case 6:
                                    Cliente user = virtualMarket.usuarioController.retornarCliente(emaillog, passlog);
                                    user.exibirSuasInformacoes();
                                    break;
                                case 7:
                                    Cliente cliente = virtualMarket.usuarioController.retornarCliente(emaillog, passlog);
                                    cliente.exibirCarrinho();
                                    break;
                                case 8:
                                    String nome = view.inputNome();
                                    String email = view.inputEmail();
                                    String pass = view.inputPass();

                                    Map<String, Object> dados = new HashMap<>();

                                    dados.put("nome", nome);
                                    dados.put("email", email);
                                    dados.put("senha", pass);
                                    dados.put("tipo", ETipoUsuario.CLIENTE);

                                    if (virtualMarket.usuarioController.validarEmail(email) && virtualMarket.usuarioController.validarPass(pass)) {
                                        virtualMarket.usuarioController.atualizarDados(emaillog, passlog, dados);
                                        System.out.println("\nInformações atualizadas com sucesso, faça login novamente!\n");
                                        op = 11;
                                    }else{
                                        System.out.print("\nNão foi possivel mudar seus dados\n");
                                    }
                                    break;
                                case 9:
                                    Cliente historico = virtualMarket.usuarioController.retornarCliente(emaillog, passlog);
                                    historico.exibirHistoricoCompras();
                                    break;
                                case 10:
                                    try {
                                        Cliente notaFiscal = virtualMarket.usuarioController.retornarCliente(emaillog, passlog);
                                        notaFiscal.exibirNotaFiscal();
                                    } catch (Exception e) {
                                        System.out.println("\nVoce ainda não tem notas fiscais!\n");
                                    }
                                    break;
                                case 11:
                                    System.out.println("\nlogout!\n");
                                    break;
                                default:
                                    System.out.println("\nEssa opção não existe!\n");
                            }
                        }
                        //FIM DO MENU DOS CLIENTES
                    } else if (virtualMarket.usuarioController.autenticarAdm(emaillog, passlog, ETipoUsuario.ADM)) {
                        //MENU DOS ADM
                        System.out.println("\nLogado!\n");
                        while (op != 10) {
                            System.out.println("1-Adicionar Produto ao Estoque\n" +
                                    "2-Remover Produto do Estoque\n" +
                                    "3-Buscar Produto\n" +
                                    "4-Listar CLientes\n" +
                                    "5-Listar Produtos\n" +
                                    "6-Editar produto\n" +
                                    "7-Buscar Cliente\n" +
                                    "8-Remover Cliente\n" +
                                    "9-Cadastrar Adm\n" +
                                    "10-Sair\n");
                            op = view.inputOp();
                            switch (op) {
                                case 1:
                                    System.out.println("\nQue tipo de produto voce quer adicionar ao estoque?\n" +
                                            "\n1-Produto Perecivel\n" +
                                            "2-Produto não Perecivel\n");
                                    op = view.inputOp();
                                    if (op == 1) {

                                        String nomeProd = view.inputNomeProduto();
                                        double preco = view.inputPreco();
                                        String describe = view.inputDescricao();
                                        int quantidade = view.inputQuantidade();

                                        Produto validacao = virtualMarket.retornarProduto(nomeProd);

                                        if (validacao != null) {
                                            System.out.println("\nEsse produto ja existe!" + nomeProd + "\n");
                                        } else {
                                            Map<String, Object> dados = new HashMap<>();

                                            dados.put("nome", nomeProd);
                                            dados.put("preco", preco);
                                            dados.put("describe", describe);
                                            dados.put("tipo", ETipoProdutos.PERECIVEL);
                                            dados.put("quantidade", quantidade);

                                            Produto produto = adm.criarProdutoPerecivel(dados);

                                            if (virtualMarket.usuarioController.validarProduto(produto)) {
                                                System.out.println("\nProduto adicionado com sucesso\n");
                                                virtualMarket.estoque.add(produto);
                                            } else {
                                                System.out.println("\nProduto não foi adicionado!\n");
                                            }
                                        }
                                    } else if (op == 2) {

                                        String nomeProd = view.inputNomeProduto();
                                        double preco = view.inputPreco();
                                        String describe = view.inputDescricao();
                                        int quantidade = view.inputQuantidade();

                                        Produto validacao = virtualMarket.retornarProduto(nomeProd);

                                        if (validacao != null) {
                                            System.out.println("\nEsse produto ja existe!" + nomeProd + "\n");
                                        } else {
                                            Map<String, Object> dados = new HashMap<>();

                                            dados.put("nome", nomeProd);
                                            dados.put("preco", preco);
                                            dados.put("describe", describe);
                                            dados.put("tipo", ETipoProdutos.NAO_PERECIVEL);
                                            dados.put("quantidade", quantidade);

                                            Produto produto = adm.criarProdutoNaoPerecivel(dados);

                                            if (virtualMarket.usuarioController.validarProduto(produto)) {
                                                System.out.println("Produto adicionado com sucesso");
                                                virtualMarket.estoque.add(produto);
                                            } else {
                                                System.out.println("\nProduto não foi adicionado!\n");
                                            }
                                        }
                                    }else{
                                        System.out.println("Opção invalida");
                                    }
                                    break;
                                case 2:
                                    String buscaProdRemover = view.inputNomeProduto();
                                    Produto produto = virtualMarket.retornarProduto(buscaProdRemover);
                                    if (produto == null) {
                                        System.out.println("\nEsse produto não existe!\n");
                                    } else {
                                        adm.removerProduto(produto, virtualMarket.estoque);
                                    }
                                    break;
                                case 3:
                                    String nomeProdPesq = view.inputNomeProduto();
                                    virtualMarket.pesquisarProduto(nomeProdPesq);
                                    break;
                                case 4:
                                    if (!virtualMarket.usuarioController.dao.listarClientes().isEmpty()) {
                                        System.out.println("\n" + virtualMarket.usuarioController.dao.listarClientes().toString() + "\n");
                                    }else{
                                        System.out.println("\nBanco de dados vazio!\n");
                                    }
                                    break;
                                case 5:
                                    virtualMarket.listarProdutos();
                                    break;
                                case 6:
                                    String buscarProdEditar = view.inputNomeProduto();
                                    Produto prodEditar = virtualMarket.retornarProduto(buscarProdEditar);

                                    if (virtualMarket.estoque.contains(prodEditar)) {
                                        System.out.println("\nDigite os novos atributos do produto.\n");
                                        String novoNome = view.inputNomeProduto();
                                        double novoPreco = view.inputPreco();
                                        String novaDescribe = view.inputDescricao();
                                        ETipoProdutos novoTipo = view.inputTipoProduto();
                                        int novaQuantidade = view.inputQuantidade();

                                        adm.editarProduto(prodEditar, virtualMarket.estoque, novoNome, novoPreco, novaDescribe, novoTipo, novaQuantidade);
                                    }else{
                                        System.out.println("\nNão tem esse produto no estoque!\n");
                                    }
                                    break;
                                case 7:
                                    String clienteBusca = view.inputEmail();
                                    Cliente encontrado = virtualMarket.usuarioController.buscarPorEmail(clienteBusca);
                                    if (encontrado != null) {
                                        System.out.println(encontrado.toString() + "\n");
                                    }else{
                                        System.out.println("\nUsuario não encontrado!\n");
                                    }
                                    break;
                                case 8:
                                    String clienteRemover = view.inputEmail();
                                    Cliente cliente = virtualMarket.usuarioController.buscarPorEmail(clienteRemover);
                                    if (cliente != null) {
                                        virtualMarket.usuarioController.dao.removerCliente(cliente);
                                    }else{
                                        System.out.println("\nUsuario não encontrado!\n");
                                    }
                                    break;
                                case 9:
                                    Map<String, Object> user = new HashMap<>();
                                    String nome = view.inputNome();
                                    String email = view.inputEmail();
                                    String senha = view.inputPass();

                                    user.put("nome", nome);
                                    user.put("email", email);
                                    user.put("senha", senha);
                                    user.put("tipo", ETipoUsuario.ADM);

                                    virtualMarket.usuarioController.inserirAdm(user);
                                    break;
                                case 10:
                                    System.out.println("\nLogout!\n");
                                    break;
                                default:
                                    System.out.println("\nOpção não existe!\n");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("\nEmail ou senha errado\n");
                    }
                    break;
                case 2:
                    Map<String, Object> user = new HashMap<>();
                    String nome = view.inputNome();
                    String email = view.inputEmail();
                    String senha = view.inputPass();

                    user.put("nome", nome);
                    user.put("email", email);
                    user.put("senha", senha);
                    user.put("tipo", ETipoUsuario.CLIENTE);

                    virtualMarket.usuarioController.inserirCliente(user);

                    break;
                case 3:
                    break;
                default:
                    System.out.println("\nOpção não existe!\n");
                    break;
            }
        }
    }
}