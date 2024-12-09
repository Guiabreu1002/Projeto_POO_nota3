package br.com.virtual.market.Model;

import br.com.virtual.market.Contracts.ICliente;
import br.com.virtual.market.Enum.ETipoUsuario;

import java.util.ArrayList;
import java.util.Map;

public class Cliente extends Usuario implements ICliente {

    public Pedido pedido;
    public ArrayList<Map<String, Object>> historico;
    public Carrinho carrinho;

    public Cliente(String nome, String email, String pass, ETipoUsuario tipo) {
        super(nome, email, pass, tipo);
        this.carrinho = new Carrinho();
        this.historico = new ArrayList<>();
        this.pedido = new Pedido();

    }

    public Carrinho getCarrinho() {
        return carrinho;
    }
    @Override
    public void exibirCarrinho () {
        if (!carrinho.produtos.isEmpty()) {
            for (Produto item : carrinho.produtos) {
                System.out.println("\nProduto: " + item.getNome() + ", Preço: " + item.getPreco() + ", Quantidade: " + item.getQuantidade() + "\n");
            }
        }else{
            System.out.println("\nCarrinho vazio!\n");
        }
    }
    @Override
    public void exibirSuasInformacoes () {
        System.out.println("\nNome: " + super.nome + "\n" +
                "Email: " + super.email + "\n" +
                "Senha: " + super.pass + "\n");
    }
    @Override
    public void exibirHistoricoCompras () {
        if (!historico.isEmpty()) {
            System.out.println("\nHistorico de Compras!\n");
            for (Map<String, Object> print : historico) {
                System.out.println("\nProduto: " + print.get("nome") + ", Quantidade:  " + print.get("quantidade") + ", Preço: " + print.get("price") + ", Data: " + print.get("data") +"\n");
            }
        }else{
            System.out.println("\nVoce ainda não fez nenhuma compra!\n");
        }
    }

    @Override
    public void exibirNotaFiscal () {
        if (!pedido.notaFiscal.isEmpty() && pedido.notaFiscal != null) {
            for (String pedido : pedido.notaFiscal) {
                System.out.println("\n" + pedido + "\n");
            }
        }
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "carrinho=" + carrinho +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
