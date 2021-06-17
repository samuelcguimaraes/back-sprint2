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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	
	@Test
	void deveConsiderarQueNaoHaNenhumPagamento() {
		
		Mockito.when(pagamentoDao.obtemTodos()).thenReturn(new ArrayList<>());
		var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);
		
		EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calcula();
		
		BigDecimal maiorPagamentoConfirmado = estatisticasDePagamento.getMaiorPagamentoConfirmado();
		Assertions.assertThat(maiorPagamentoConfirmado).isEqualTo(BigDecimal.ZERO);
		
		Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus = estatisticasDePagamento.getQuantidadeDePagamentoPorStatus();
		Assertions.assertThat(quantidadeDePagamentoPorStatus.isEmpty()).isTrue();
	}
	
	@Test
	void deveConsiderarQuantidadeDePagamentoPorStatusSemCriado() {
		
		Mockito.when(pagamentoDao.obtemTodos()).thenReturn(constroiListaDePagamentosSemStatusCriado());
		var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);
		
		EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calcula();
		
		BigDecimal maiorPagamentoConfirmado = estatisticasDePagamento.getMaiorPagamentoConfirmado();
		Assertions.assertThat(maiorPagamentoConfirmado).isEqualTo(new BigDecimal("200.00"));
		
		Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus = estatisticasDePagamento.getQuantidadeDePagamentoPorStatus();
		Assertions.assertThat(quantidadeDePagamentoPorStatus)
		          .containsEntry(StatusPagamento.CONFIRMADO, 1L)
		          .containsEntry(StatusPagamento.CANCELADO, 1L);
	}
	
	@Test
	void deveConsiderarQuantidadeDePagamentoPorStatusSemConfirmado() {
		
		Mockito.when(pagamentoDao.obtemTodos()).thenReturn(constroiListaDePagamentosSemStatusConfirmado());
		var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);
		
		EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calcula();
		
		BigDecimal maiorPagamentoConfirmado = estatisticasDePagamento.getMaiorPagamentoConfirmado();
		Assertions.assertThat(maiorPagamentoConfirmado).isEqualTo(BigDecimal.ZERO);
		
		Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus = estatisticasDePagamento.getQuantidadeDePagamentoPorStatus();
		Assertions.assertThat(quantidadeDePagamentoPorStatus)
		          .containsEntry(StatusPagamento.CRIADO, 2L)
		          .containsEntry(StatusPagamento.CANCELADO, 1L);
	}
	
	@Test
	void deveConsiderarQuantidadeDePagamentoPorStatusSemCancelado() {
		
		Mockito.when(pagamentoDao.obtemTodos()).thenReturn(constroiListaDePagamentosSemStatusCancelado());
		var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);
		
		EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calcula();
		
		BigDecimal maiorPagamentoConfirmado = estatisticasDePagamento.getMaiorPagamentoConfirmado();
		Assertions.assertThat(maiorPagamentoConfirmado).isEqualTo(new BigDecimal("200.00"));
		
		Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus = estatisticasDePagamento.getQuantidadeDePagamentoPorStatus();
		Assertions.assertThat(quantidadeDePagamentoPorStatus)
		          .containsEntry(StatusPagamento.CRIADO, 2L)
		          .containsEntry(StatusPagamento.CONFIRMADO, 1L);
	}
	
	private List<Pagamento> constroiListaDePagamentos() {
		
		final DadosCartao dadosCartao = new DadosCartao("Fulano", "123456", YearMonth.now(), "1234");
		
		return Arrays.asList(
				new Pagamento(5L, new BigDecimal("50.00"), dadosCartao, StatusPagamento.CRIADO),
				new Pagamento(1L, new BigDecimal("200.00"), dadosCartao, StatusPagamento.CONFIRMADO),
				new Pagamento(7L, new BigDecimal("100.00"), dadosCartao, StatusPagamento.CRIADO),
				new Pagamento(3L, new BigDecimal("250.00"), dadosCartao, StatusPagamento.CANCELADO)
		);
	}
	
	private List<Pagamento> constroiListaDePagamentosSemStatusCriado() {
		
		final DadosCartao dadosCartao = new DadosCartao("Fulano", "123456", YearMonth.now(), "1234");
		
		return Arrays.asList(
				new Pagamento(1L, new BigDecimal("200.00"), dadosCartao, StatusPagamento.CONFIRMADO),
				new Pagamento(7L, new BigDecimal("100.00"), dadosCartao, StatusPagamento.CRIADO),
				new Pagamento(3L, new BigDecimal("250.00"), dadosCartao, StatusPagamento.CANCELADO)
		);
	}
	
	private List<Pagamento> constroiListaDePagamentosSemStatusConfirmado() {
		
		final DadosCartao dadosCartao = new DadosCartao("Fulano", "123456", YearMonth.now(), "1234");
		
		return Arrays.asList(
				new Pagamento(5L, new BigDecimal("50.00"), dadosCartao, StatusPagamento.CRIADO),
				new Pagamento(7L, new BigDecimal("100.00"), dadosCartao, StatusPagamento.CRIADO),
				new Pagamento(3L, new BigDecimal("250.00"), dadosCartao, StatusPagamento.CANCELADO)
		);
	}
	
	private List<Pagamento> constroiListaDePagamentosSemStatusCancelado() {
		
		final DadosCartao dadosCartao = new DadosCartao("Fulano", "123456", YearMonth.now(), "1234");
		
		return Arrays.asList(
				new Pagamento(5L, new BigDecimal("50.00"), dadosCartao, StatusPagamento.CRIADO),
				new Pagamento(1L, new BigDecimal("200.00"), dadosCartao, StatusPagamento.CONFIRMADO),
				new Pagamento(7L, new BigDecimal("100.00"), dadosCartao, StatusPagamento.CRIADO)
		);
	}
	
	private BigDecimal obtemMaiorPagamentoConfirmadoDaListaDePagamentos(List<Pagamento> pagamentos) {
		
		return pagamentos.stream()
		                 .filter(pagamento -> StatusPagamento.CONFIRMADO.equals(pagamento.getStatus()))
		                 .map(Pagamento::getValor)
		                 .max(BigDecimal::compareTo)
		                 .orElse(BigDecimal.ZERO);
	}
	
	private Map<StatusPagamento, Long> obtemQuantidadeDePagamentoPorStatusDaListaDePagamentos(
			final List<Pagamento> pagamentos) {
		
		return pagamentos.stream()
		                 .collect(Collectors.groupingBy(Pagamento::getStatus, Collectors.counting()));
	}
	
	@Test
	void deveConsiderarMaiorPagamentoConfirmadoRefatorado() {
		
		Mockito.when(pagamentoDao.obtemMaiorPagamentoConfirmado()).thenReturn(
				obtemMaiorPagamentoConfirmadoDaListaDePagamentos(constroiListaDePagamentos()));
		Mockito.when(pagamentoDao.obtemQuantidadeDePagamentoPorStatus()).thenReturn(
				obtemQuantidadeDePagamentoPorStatusDaListaDePagamentos(constroiListaDePagamentos()));
		var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);
		
		EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calculaRefatorado();
		
		BigDecimal maiorPagamentoConfirmado = estatisticasDePagamento.getMaiorPagamentoConfirmado();
		Assertions.assertThat(maiorPagamentoConfirmado).isEqualTo(new BigDecimal("200.00"));
	}
	
	@Test
	void deveConsiderarQuantidadeDePagamentoPorStatusRefatorado() {
		
		Mockito.when(pagamentoDao.obtemMaiorPagamentoConfirmado()).thenReturn(
				obtemMaiorPagamentoConfirmadoDaListaDePagamentos(constroiListaDePagamentos()));
		Mockito.when(pagamentoDao.obtemQuantidadeDePagamentoPorStatus()).thenReturn(
				obtemQuantidadeDePagamentoPorStatusDaListaDePagamentos(constroiListaDePagamentos()));
		var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);
		
		EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calculaRefatorado();
		
		Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus = estatisticasDePagamento.getQuantidadeDePagamentoPorStatus();
		Assertions.assertThat(quantidadeDePagamentoPorStatus)
		          .containsEntry(StatusPagamento.CRIADO, 2L)
		          .containsEntry(StatusPagamento.CONFIRMADO, 1L)
		          .containsEntry(StatusPagamento.CANCELADO, 1L);
	}
	
	@Test
	void deveConsiderarQueNaoHaNenhumPagamentoRefatorado() {
		
		Mockito.when(pagamentoDao.obtemMaiorPagamentoConfirmado()).thenReturn(
				obtemMaiorPagamentoConfirmadoDaListaDePagamentos(new ArrayList<>()));
		Mockito.when(pagamentoDao.obtemQuantidadeDePagamentoPorStatus()).thenReturn(
				obtemQuantidadeDePagamentoPorStatusDaListaDePagamentos(new ArrayList<>()));
		var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);
		
		EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calculaRefatorado();
		
		BigDecimal maiorPagamentoConfirmado = estatisticasDePagamento.getMaiorPagamentoConfirmado();
		Assertions.assertThat(maiorPagamentoConfirmado).isEqualTo(BigDecimal.ZERO);
		
		Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus = estatisticasDePagamento.getQuantidadeDePagamentoPorStatus();
		Assertions.assertThat(quantidadeDePagamentoPorStatus.isEmpty()).isTrue();
	}
	
	@Test
	void deveConsiderarQuantidadeDePagamentoPorStatusSemCriadoRefatorado() {
		
		Mockito.when(pagamentoDao.obtemMaiorPagamentoConfirmado()).thenReturn(
				obtemMaiorPagamentoConfirmadoDaListaDePagamentos(constroiListaDePagamentosSemStatusCriado()));
		Mockito.when(pagamentoDao.obtemQuantidadeDePagamentoPorStatus()).thenReturn(
				obtemQuantidadeDePagamentoPorStatusDaListaDePagamentos(constroiListaDePagamentosSemStatusCriado()));
		var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);
		
		EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calculaRefatorado();
		
		BigDecimal maiorPagamentoConfirmado = estatisticasDePagamento.getMaiorPagamentoConfirmado();
		Assertions.assertThat(maiorPagamentoConfirmado).isEqualTo(new BigDecimal("200.00"));
		
		Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus = estatisticasDePagamento.getQuantidadeDePagamentoPorStatus();
		Assertions.assertThat(quantidadeDePagamentoPorStatus)
		          .containsEntry(StatusPagamento.CONFIRMADO, 1L)
		          .containsEntry(StatusPagamento.CANCELADO, 1L);
	}
	
	@Test
	void deveConsiderarQuantidadeDePagamentoPorStatusSemConfirmadoRefatorado() {
		
		Mockito.when(pagamentoDao.obtemMaiorPagamentoConfirmado()).thenReturn(
				obtemMaiorPagamentoConfirmadoDaListaDePagamentos(constroiListaDePagamentosSemStatusConfirmado()));
		Mockito.when(pagamentoDao.obtemQuantidadeDePagamentoPorStatus()).thenReturn(
				obtemQuantidadeDePagamentoPorStatusDaListaDePagamentos(constroiListaDePagamentosSemStatusConfirmado()));
		var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);
		
		EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calculaRefatorado();
		
		BigDecimal maiorPagamentoConfirmado = estatisticasDePagamento.getMaiorPagamentoConfirmado();
		Assertions.assertThat(maiorPagamentoConfirmado).isEqualTo(BigDecimal.ZERO);
		
		Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus = estatisticasDePagamento.getQuantidadeDePagamentoPorStatus();
		Assertions.assertThat(quantidadeDePagamentoPorStatus)
		          .containsEntry(StatusPagamento.CRIADO, 2L)
		          .containsEntry(StatusPagamento.CANCELADO, 1L);
	}
	
	@Test
	void deveConsiderarQuantidadeDePagamentoPorStatusSemCanceladoRefatorado() {
		
		Mockito.when(pagamentoDao.obtemMaiorPagamentoConfirmado()).thenReturn(
				obtemMaiorPagamentoConfirmadoDaListaDePagamentos(constroiListaDePagamentosSemStatusCancelado()));
		Mockito.when(pagamentoDao.obtemQuantidadeDePagamentoPorStatus()).thenReturn(
				obtemQuantidadeDePagamentoPorStatusDaListaDePagamentos(constroiListaDePagamentosSemStatusCancelado()));
		var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);
		
		EstatisticasDePagamento estatisticasDePagamento = estatisticasDePagamentoService.calculaRefatorado();
		
		BigDecimal maiorPagamentoConfirmado = estatisticasDePagamento.getMaiorPagamentoConfirmado();
		Assertions.assertThat(maiorPagamentoConfirmado).isEqualTo(new BigDecimal("200.00"));
		
		Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus = estatisticasDePagamento.getQuantidadeDePagamentoPorStatus();
		Assertions.assertThat(quantidadeDePagamentoPorStatus)
		          .containsEntry(StatusPagamento.CRIADO, 2L)
		          .containsEntry(StatusPagamento.CONFIRMADO, 1L);
	}
}