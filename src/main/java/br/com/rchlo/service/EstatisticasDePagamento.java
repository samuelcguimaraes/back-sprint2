package br.com.rchlo.service;

import br.com.rchlo.domain.StatusPagamento;

import java.math.BigDecimal;
import java.util.Map;

public class EstatisticasDePagamento {

    private BigDecimal maiorPagamentoConfirmado;
    private Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus;

    public EstatisticasDePagamento(BigDecimal maiorPagamentoConfirmado, Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus) {
        this.maiorPagamentoConfirmado = maiorPagamentoConfirmado;
        this.quantidadeDePagamentoPorStatus = quantidadeDePagamentoPorStatus;
    }

    public BigDecimal getMaiorPagamentoConfirmado() {
        return maiorPagamentoConfirmado;
    }

    public Map<StatusPagamento, Long> getQuantidadeDePagamentoPorStatus() {
        return quantidadeDePagamentoPorStatus;
    }
}
