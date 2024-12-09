package br.com.virtual.market.Model;

import br.com.virtual.market.Contracts.ICarrinho;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Carrinho implements ICarrinho {

    private UUID idCarrinho;
    protected List<Produto> produtos;
    protected double valorTotal;
    protected int quantidadeTotal;

    public Carrinho () {
        this.idCarrinho = UUID.randomUUID();
        this.produtos = new ArrayList<>();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public UUID getIdCarrinho() {
        return idCarrinho;
    }

    @Override
    public boolean adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("\nProduto adicionado com sucesso!\n");
        return true;
    }

    @Override
    public boolean removerProduto(String busca) {
        if (!produtos.isEmpty()) {
            for (Produto produto : produtos) {
                if (busca.equalsIgnoreCase(produto.getNome())) {
                    produtos.remove(produto);
                    return true;
                }
            }
            System.out.println("\nEsse produto não esta no carrinho!\n");
        }
        return false;
    }

    @Override
    public void calcularTotal() {
        try {
            this.valorTotal = 0;
            this.quantidadeTotal = 0;
            if (!produtos.isEmpty()) {
                for (Produto produto : produtos) {
                    double valorTotal = produto.getQuantidade() * produto.getPreco();
                    this.valorTotal += valorTotal;
                    this.quantidadeTotal += produto.getQuantidade();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Produto> listarProdutos() {
        return List.of();
    }

    @Override
    public void limparCarrinho() {
        if (!produtos.isEmpty()) {
            produtos.clear();
        }
    }

    @Override
    public boolean verivicarCarrinho() {
        if (!produtos.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "idCarrinho=" + idCarrinho +
                ", produtos=" + produtos +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
