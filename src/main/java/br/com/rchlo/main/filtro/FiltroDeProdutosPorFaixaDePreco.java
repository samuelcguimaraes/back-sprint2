package br.com.rchlo.main.filtro;

import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FiltroDeProdutosPorFaixaDePreco {

    public static void main(String[] args) {
        List<Produto> lis = ListaDeProdutos.lista();

        BigDecimal mi = new BigDecimal("30.00");
        BigDecimal ma = new BigDecimal("50.00");

        List<Produto> lisFi = new ArrayList<>();
        for (Produto x : lis) {
            if ((x.getPrecoDesconto() != null ? x.getPrecoDesconto() : x.getPreco()).compareTo(mi) >= 0 &&
                    (x.getPrecoDesconto() != null ? x.getPrecoDesconto() : x.getPreco()).compareTo(ma) <= 0) {
                lisFi.add(x);
            }
        }

        for (Produto x : lisFi) {
            System.out.println(x.getNome() + " (cod. " + x.getCodigo() + " - " + x.getPesoEmGramas() + " g) custa R$ " + x.getPrecoEfetivo());
        }

    }

}
