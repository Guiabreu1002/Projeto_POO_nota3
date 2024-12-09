package br.com.virtual.market.Model;

import br.com.virtual.market.Enum.ETipoProdutos;

import java.time.LocalDate;
import java.util.UUID;

public class ProdutosPerecivel extends Produto{


    public ProdutosPerecivel(String nome, double preco, String descricao, ETipoProdutos tipo, int quantidade) {
        super(nome, preco, descricao, tipo, quantidade);
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public double getPreco() {
        return super.getPreco();
    }

    @Override
    public String getDescricao() {
        return super.getDescricao();
    }

    @Override
    public ETipoProdutos getTipo() {
        return super.getTipo();
    }

    @Override
    public int getQuantidade() {
        return super.getQuantidade();
    }

    @Override
    public LocalDate getValidade() {
        return super.getValidade();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
