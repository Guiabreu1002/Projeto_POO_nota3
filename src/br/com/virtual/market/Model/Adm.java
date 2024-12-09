package br.com.virtual.market.Model;

import br.com.virtual.market.Contracts.IAdm;
import br.com.virtual.market.Enum.ETipoProdutos;
import br.com.virtual.market.Enum.ETipoUsuario;

import java.util.List;
import java.util.Map;

public class Adm extends Usuario implements IAdm {

    public Adm(String nome, String email, String pass, ETipoUsuario tipo) {
        super(nome, email, pass, tipo);
    }

    @Override
    public Produto criarProdutoPerecivel(Map<String, Object> dados) {
        ProdutosPerecivel produto = new ProdutosPerecivel(
                dados.get("nome").toString(),
                (double) dados.get("preco"),
                dados.get("describe").toString(),
                (ETipoProdutos) dados.get("tipo"),
                (int) dados.get("quantidade")
        );
        return produto;
    }

    @Override
    public Produto criarProdutoNaoPerecivel(Map<String, Object> dados) {
        ProdutosNaoPerecivel produto = new ProdutosNaoPerecivel(
                dados.get("nome").toString(),
                (double) dados.get("preco"),
                dados.get("describe").toString(),
                (ETipoProdutos) dados.get("tipo"),
                (int) dados.get("quantidade")
        );
        return produto;
    }

    @Override
    public void removerProduto(Produto produto, List<Produto> lista) {
        if (!lista.isEmpty()) {
            for (Produto busca : lista) {
                if (busca.getNome().equalsIgnoreCase(produto.getNome())) {
                    lista.remove(produto);
                    System.out.println("\nProduto removido com sucesso!\n");
                    break;
                }
            }
        }else{
            System.out.println("\nO estoque esta vazio!\n");
        }
    }

    @Override
    public void editarProduto(Produto produto, List<Produto> lista, String nNome, double nPreco, String nDescribe, ETipoProdutos nTipo, int nQuantidade) {
        if (!lista.isEmpty()) {
            for (Produto prod : lista) {
                if (prod.getId() == produto.getId()) {
                    prod.setNome(nNome);
                    prod.setPreco(nPreco);
                    prod.setDescricao(nDescribe);
                    prod.setTipo(nTipo);
                    prod.setQuantidade(nQuantidade);
                    System.out.println("\nProduto Editado com sucesso!\n");
                    break;
                }
            }
        }else{
            System.out.println("\nO estoque esta vazio!\n");
        }
    }
}
