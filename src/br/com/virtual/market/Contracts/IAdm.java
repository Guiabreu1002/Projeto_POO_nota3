package br.com.virtual.market.Contracts;

import br.com.virtual.market.Enum.ETipoProdutos;
import br.com.virtual.market.Model.Produto;

import java.util.List;
import java.util.Map;

public interface IAdm {

    Produto criarProdutoPerecivel (Map<String, Object> dados);

    Produto criarProdutoNaoPerecivel (Map<String, Object> dados);

    void removerProduto (Produto produto, List<Produto> lista);

    void editarProduto (Produto produto, List<Produto> lista, String nNome, double nPreco, String nDescribe, ETipoProdutos nTipo, int nQuantidade);

}
