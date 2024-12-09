package br.com.virtual.market.Contracts;

import br.com.virtual.market.Enum.EFormaPagamento;
import br.com.virtual.market.Enum.ETipoProdutos;

public interface IVirtualMarketView {

    int inputOp ();

    String inputNome ();

    String inputEmail ();

    String inputPass ();

    int inputQuantidade ();

    String inputNomeProduto ();

    double inputPreco ();

    String inputDescricao ();

    ETipoProdutos inputTipoProduto();

    EFormaPagamento inputFormaPagamento ();

}
