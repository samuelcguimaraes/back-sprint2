package br.com.rchlo.service;

import br.com.rchlo.dao.PagamentoDao;
import br.com.rchlo.domain.DadosCartao;
import br.com.rchlo.domain.Pagamento;
import br.com.rchlo.domain.StatusPagamento;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class EstatisticasDePagamentoServiceTest {
    
    @Mock
    private PagamentoDao pagamentoDao;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsiderarMaiorPagamentoConfirmado() {

        //var pagamentoDao = new PagamentoDao();
        Mockito.when(pagamentoDao.obtemTodos()).thenReturn(constroiListaDePagamentos());
        var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);
        
        EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calcula();

        BigDecimal maiorPagamentoConfirmado = estatisticasDePagamento.getMaiorPagamentoConfirmado();
        Assertions.assertThat(maiorPagamentoConfirmado).isEqualTo(new BigDecimal("200.00"));
    }

    @Test
    void deveConsiderarQuantidadeDePagamentoPorStatus() {

        //var pagamentoDao = new PagamentoDao();
        Mockito.when(pagamentoDao.obtemTodos()).thenReturn(constroiListaDePagamentos());
        var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);

        EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calcula();

        Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus = estatisticasDePagamento.getQuantidadeDePagamentoPorStatus();
        Assertions.assertThat(quantidadeDePagamentoPorStatus)
                .containsEntry(StatusPagamento.CRIADO, 2L)
                .containsEntry(StatusPagamento.CONFIRMADO, 1L)
                .containsEntry(StatusPagamento.CANCELADO, 1L);
    }
    
    private List<Pagamento> constroiListaDePagamentos() {
        
        final DadosCartao dadosCartao = new DadosCartao("Fulano", "123456", YearMonth.now(), "1234");
    
        List<Pagamento> todosOsPagamentos = Arrays.asList(
                new Pagamento(5L, new BigDecimal("50.00"), dadosCartao, StatusPagamento.CRIADO),
                new Pagamento(1L, new BigDecimal("200.00"), dadosCartao, StatusPagamento.CONFIRMADO),
                new Pagamento(7L, new BigDecimal("100.00"), dadosCartao, StatusPagamento.CRIADO),
                new Pagamento(3L, new BigDecimal("250.00"), dadosCartao, StatusPagamento.CANCELADO)
        );
    
        return todosOsPagamentos;
    }

}