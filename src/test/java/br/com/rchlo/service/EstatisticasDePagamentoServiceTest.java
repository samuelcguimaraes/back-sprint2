package br.com.rchlo.service;

import br.com.rchlo.dao.PagamentoDao;
import br.com.rchlo.domain.StatusPagamento;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

class EstatisticasDePagamentoServiceTest {

    @Test
    void deveConsiderarMaiorPagamentoConfirmado() {

        var pagamentoDao = new PagamentoDao();
        var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);

        EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calcula();

        BigDecimal maiorPagamentoConfirmado = estatisticasDePagamento.getMaiorPagamentoConfirmado();
        Assertions.assertThat(maiorPagamentoConfirmado).isEqualTo(new BigDecimal("200.00"));
    }

    @Test
    void deveConsiderarQuantidadeDePagamentoPorStatus() {

        var pagamentoDao = new PagamentoDao();
        var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);

        EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calcula();

        Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus = estatisticasDePagamento.getQuantidadeDePagamentoPorStatus();
        Assertions.assertThat(quantidadeDePagamentoPorStatus)
                .containsEntry(StatusPagamento.CRIADO, 2L)
                .containsEntry(StatusPagamento.CONFIRMADO, 1L)
                .containsEntry(StatusPagamento.CANCELADO, 1L);
    }

}