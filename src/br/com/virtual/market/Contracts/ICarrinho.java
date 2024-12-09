package br.com.virtual.market.Contracts;

import br.com.virtual.market.Model.Produto;

import java.util.List;

public interface ICarrinho {

    boolean adicionarProduto(Produto produto);

    boolean removerProduto(String busca);

    void calcularTotal();

    List<Produto>listarProdutos();

    void limparCarrinho();

    boolean verivicarCarrinho ();
}
