package br.com.rchlo.dao;

import br.com.rchlo.domain.DadosCartao;
import br.com.rchlo.domain.Pagamento;
import br.com.rchlo.domain.StatusPagamento;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagamentoDao {
	
	public List<Pagamento> obtemTodos() {
		List<Pagamento> todos = new ArrayList<>();
		
		String query = "select id, valor, nome_cliente, numero_cartao, expiracao_cartao, codigo_verificacao_cartao, status from pagamento";
		
		ConnectionFactory.init();
		try (Connection connection = ConnectionFactory.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				BigDecimal valor = resultSet.getBigDecimal("valor");
				
				String nomeCliente = resultSet.getString("nome_cliente");
				String numeroCartao = resultSet.getString("numero_cartao");
				YearMonth expiracaoCartao = YearMonth.parse(resultSet.getString("expiracao_cartao"));
				String codigoVerificacaoCartao = resultSet.getString("codigo_verificacao_cartao");
				
				var dadosCartao = new DadosCartao(nomeCliente, numeroCartao, expiracaoCartao, codigoVerificacaoCartao);
				
				var status = StatusPagamento.valueOf(resultSet.getString("status"));
				
				var pagamento = new Pagamento(id, valor, dadosCartao, status);
				
				todos.add(pagamento);
				
			}
		} catch (SQLException ex) {
			throw new IllegalStateException("Erro ao buscar todos os pagamentos", ex);
		}
		
		return todos;
	}
	
	public BigDecimal obtemMaiorPagamentoConfirmado() {
		
		BigDecimal maiorPagamentoConfirmado;
		
		String query = "select max(valor) " +
		               "from pagamento " +
		               "where status = 'CONFIRMADO'";
		
		ConnectionFactory.init();
		try (Connection connection = ConnectionFactory.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			maiorPagamentoConfirmado = resultSet.getBigDecimal(1);
		} catch (SQLException ex) {
			throw new IllegalStateException("Erro ao buscar o maior pagamento confirmado", ex);
		}
		
		return maiorPagamentoConfirmado;
	}
	
	public Map<StatusPagamento, Long> obtemQuantidadeDePagamentoPorStatus() {
		
		Map<StatusPagamento, Long> pagamentosPorStatus = new HashMap<>();
		
		String query = "select status, count(*) as quantidade " +
		               "from pagamento " +
		               "group by status";
		
		ConnectionFactory.init();
		try (Connection connection = ConnectionFactory.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				var nomeStatus = resultSet.getString("status");
				StatusPagamento statusPagamento = StatusPagamento.valueOf(nomeStatus);
				
				long quantidade = resultSet.getLong("quantidade");
				
				pagamentosPorStatus.put(statusPagamento, quantidade);
			}
		} catch (SQLException ex) {
			throw new IllegalStateException("Erro ao buscar a quantidade de pagamentos por status", ex);
		}
		
		return pagamentosPorStatus;
	}
	
}
