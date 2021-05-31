package br.com.rchlo.domain;

import java.math.BigDecimal;

public class Pagamento {

    private Long id;
    private BigDecimal valor;
    private DadosCartao dadosCartao;
    private StatusPagamento status;

    public Pagamento(BigDecimal valor, DadosCartao dadosCartao, StatusPagamento status) {
        this.valor = valor;
        this.dadosCartao = dadosCartao;
        this.status = status;
    }

    public Pagamento(Long id, BigDecimal valor, DadosCartao dadosCartao, StatusPagamento status) {
       this(valor, dadosCartao, status);
       this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public DadosCartao getDadosCartao() {
        return dadosCartao;
    }

    public StatusPagamento getStatus() {
        return status;
    }
}
