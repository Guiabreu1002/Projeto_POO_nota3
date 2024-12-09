package br.com.virtual.market.Model;

import br.com.virtual.market.Enum.EFormaPagamento;
import br.com.virtual.market.Enum.EStatus;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Pagamento {

    private UUID id;
    private double valor;
    protected LocalDate dataPagamento;
    private EFormaPagamento formaPagamento;
    private EStatus status;

    public Pagamento () {}

    public Pagamento (double valor, EFormaPagamento formaPagamento, EStatus status) {
        this.id = UUID.randomUUID();
        this.dataPagamento = LocalDate.now();
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.status = status;
    }
}
