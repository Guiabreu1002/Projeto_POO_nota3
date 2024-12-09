package br.com.virtual.market.Model;

import br.com.virtual.market.Contracts.IVirtualMarket;
import br.com.virtual.market.Controller.UsuarioController;
import br.com.virtual.market.Enum.EFormaPagamento;
import br.com.virtual.market.Enum.EStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualMarket implements IVirtualMarket {

    public String nome;
    public String local;
    public String cnpj;
    public UsuarioController usuarioController;
    public List<Produto> estoque;

    public VirtualMarket (String nome, String local, String cnpj) {
        this.nome = nome;
        this.local = local;
        this.cnpj = cnpj;
        this.usuarioController = new UsuarioController();
        this.estoque = new ArrayList<>();
    }

    @Override
    public boolean venderProduto (Cliente cliente, EFormaPagamento forma) {
        try {
            EStatus statusPedido = EStatus.PENDENTE;
            if (!estoque.isEmpty()) {
                if (!cliente.getCarrinho().getProdutos().isEmpty()) {
                    cliente.getCarrinho().calcularTotal();

                    if (forma == EFormaPagamento.CARTAO || forma == EFormaPagamento.PIX || forma == EFormaPagamento.BOLETO) {
                        cliente.pedido = new Pedido(cliente.carrinho.getValorTotal(), forma, statusPedido);

                        for (Produto produtoEstoque : estoque) {
                            for (Produto produtoCarrinho : cliente.carrinho.getProdutos()) {
                                if (produtoEstoque.getId() == produtoCarrinho.getId()) {

                                    int novaQuantidade = (produtoEstoque.getQuantidade() - produtoCarrinho.getQuantidade());
                                    produtoEstoque.setQuantidade(novaQuantidade);

                                    Map<String, Object> historico = new HashMap<>();
                                    historico.put("nome", produtoCarrinho.getNome());
                                    historico.put("quantidade", produtoCarrinho.getQuantidade());
                                    historico.put("price", produtoCarrinho.getPreco());
                                    historico.put("data", produtoCarrinho.getValidade());

                                    cliente.historico.add(historico);
                                }
                            }
                        }
                        statusPedido = EStatus.SUCESSO;
                        String nota = String.format("""
                                ------NOTA FISCAL------
                                -----------------------
                                Comprador: %s
                                Valor total: %2.0f
                                Produtos: %s
                                Quantidade: %d
                                Pagamento: %s
                                Status: %s
                                Data: %s
                                -----------------------
                                -----------------------
                                """, cliente.getNome(), cliente.carrinho.getValorTotal(), cliente.historico.toString(), cliente.carrinho.quantidadeTotal, forma, statusPedido, cliente.pedido.dataPagamento);
                        cliente.pedido.emitirNotaFiscal(nota);
                        cliente.carrinho.limparCarrinho();
                        return true;
                    } else {
                        statusPedido = EStatus.FALHA;
                        System.out.println("\nForma de pagamento invalido!\n");
                    }
                }else{
                    statusPedido = EStatus.FALHA;
                    System.out.println("\nCarrinho vazio!\n");
                }
            }else{
                statusPedido = EStatus.FALHA;
                System.out.println("\nEstoque esta vazio!\n");
            }
            if (statusPedido == EStatus.FALHA) {
                System.out.println("\nSua compra não foi realizada, tente novamente!\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void pesquisarProduto (String nome) {
        try {
            if (!estoque.isEmpty()) {
                boolean encontrado = false;
                for (Produto produto : estoque) {
                    if (nome.equalsIgnoreCase(produto.getNome())) {
                        System.out.println("\nProduto encontrado!\n");
                        System.out.println("\nProduto: " + produto.getNome() + "\n" +
                                "Tipo: " + produto.getTipo() + "\n" +
                                "Descrição: " + produto.getDescricao() + "\n" +
                                "Preço: " + produto.getPreco() + "\n" +
                                "Quantidade em estoque: " + produto.getQuantidade() + "\n");
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("\nProduto não encontrado!\n");
                }
            }else{
                System.out.println("\nNosso estoque esta vazio\n");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Override
    public Produto retornarProduto (String busca) {
        try {
            if (!estoque.isEmpty()) {
                for (Produto produto : estoque) {
                    if (busca.equalsIgnoreCase(produto.getNome())) {
                        return produto;
                    }
                }
                System.out.println("\nProduto não encontrado\n");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    @Override
    public void listarProdutos () {
        try {
            if (!estoque.isEmpty()) {
                for (Produto produto : estoque) {
                    System.out.println(produto.toString() + "\n");
                }
            }else{
                System.out.println("\nO estoque esta vazio!\n");
            }
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}
