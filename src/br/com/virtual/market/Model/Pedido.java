package br.com.virtual.market.Model;

import br.com.virtual.market.Enum.EFormaPagamento;
import br.com.virtual.market.Enum.EStatus;

import java.util.ArrayList;
import java.util.List;

public class Pedido extends Pagamento{

    protected List<String> notaFiscal;

    public Pedido () {}

    public Pedido(double valor, EFormaPagamento formaPagamento, EStatus status) {
        super(valor, formaPagamento, status);
        this.notaFiscal = new ArrayList<>();
    }

    public void emitirNotaFiscal (String notaFiscal) {
        String nota = notaFiscal;
        this.notaFiscal.add(nota);
    }

}
