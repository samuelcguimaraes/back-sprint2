package br.com.rchlo.main.filtro;

import br.com.rchlo.domain.ListaDeProdutos;

import java.math.BigDecimal;

public class FiltroDeProdutosPorFaixaDeValoresComStreams {

    public static void main(String[] args) {
        BigDecimal precoMinimo = new BigDecimal("30.00");
        BigDecimal precoMaximo = new BigDecimal("50.00");
        System.out.println("Filtra por faixa de preço");
        ListaDeProdutos.lista()
                .stream()
                .filter(produto -> produto.getPrecoEfetivo().compareTo(precoMinimo) >= 0 && produto.getPrecoEfetivo().compareTo(precoMaximo) <= 0)
                .forEach(produto -> System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo()));

        int pesoMinimo = 110;
        int pesoMaximo = 130;
        System.out.println("\nFiltra por faixa de peso (g)");
        ListaDeProdutos.lista()
                .stream()
                .filter(produto -> produto.getPesoEmGramas() >= pesoMinimo && produto.getPesoEmGramas() <= pesoMaximo)
                .forEach(produto -> System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo()));

    }

}
