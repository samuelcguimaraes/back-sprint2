package br.com.rchlo.main.filtro;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.ListaDeProdutos;

public class FiltroDeProdutosPorCorComStreams {

    public static void main(String[] args) {
        var cor = Cor.VERMELHA;

        ListaDeProdutos.lista()
                .stream()
                .filter(produto -> cor.equals(produto.getCor()))
                .forEach(produto -> System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo()));

    }

}
