package br.com.rchlo.main.relatorio;

import br.com.rchlo.dao.PagamentoDao;
import br.com.rchlo.domain.StatusPagamento;
import br.com.rchlo.service.EstatisticasDePagamento;
import br.com.rchlo.service.EstatisticasDePagamentoService;

import java.util.Map;

public class RelatorioDePagamentos {

    public static void main(String[] args) {
        var pagamentoDao = new PagamentoDao();
        var estatisticasDePagamentoService = new EstatisticasDePagamentoService(pagamentoDao);

        EstatisticasDePagamento estatisticas = estatisticasDePagamentoService.calcula();
    
        System.out.println("Maior pagamento confirmado: " + estatisticas.getMaiorPagamentoConfirmado());
        Map<StatusPagamento, Long> quantidadeDePagamentoPorStatus = estatisticas.getQuantidadeDePagamentoPorStatus();
        for (StatusPagamento statusPagamento : quantidadeDePagamentoPorStatus.keySet()) {
            System.out.println("Quantidade de pagamentos com status '" + statusPagamento + "':  " + quantidadeDePagamentoPorStatus.get(statusPagamento));
        }
        System.out.println();
        
        estatisticas = estatisticasDePagamentoService.calculaRefatorado();
    
        System.out.println("Maior pagamento confirmado: " + estatisticas.getMaiorPagamentoConfirmado());
        quantidadeDePagamentoPorStatus = estatisticas.getQuantidadeDePagamentoPorStatus();
        for (StatusPagamento statusPagamento : quantidadeDePagamentoPorStatus.keySet()) {
            System.out.println("Quantidade de pagamentos com status '" + statusPagamento + "':  " + quantidadeDePagamentoPorStatus.get(statusPagamento));
        }
    
    }

}
