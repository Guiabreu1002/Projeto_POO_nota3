package br.com.virtual.market.Model;

import br.com.virtual.market.Enum.ETipoProdutos;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Produto {

    protected UUID id;
    protected String nome;
    protected double preco;
    protected String descricao;
    protected ETipoProdutos tipo;
    protected int quantidade;
    protected LocalDate validade;

    public Produto (String nome, double preco, String descricao, ETipoProdutos tipo, int quantidade) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.validade = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public ETipoProdutos getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipo(ETipoProdutos tipo) {
        this.tipo = tipo;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return String.format("Produto: %s, Preço: %2.0f, Quantidade: %d", this.nome, this.preco, this.quantidade);
    }
}
