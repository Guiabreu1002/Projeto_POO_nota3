package br.com.virtual.market.Contracts;

import br.com.virtual.market.Enum.EFormaPagamento;
import br.com.virtual.market.Model.Cliente;
import br.com.virtual.market.Model.Produto;

public interface IVirtualMarket {

    boolean venderProduto (Cliente cliente, EFormaPagamento forma);

    void pesquisarProduto (String nome);

    Produto retornarProduto (String busca);

    void listarProdutos ();

}
