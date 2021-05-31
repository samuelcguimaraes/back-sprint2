package br.com.rchlo.main.filtro;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Tamanho;

public class FiltroDeProdutosPorDiferentesCriteriosComStreams {

    public static void main(String[] args) {

        Cor corASerBuscada = Cor.VERMELHA;
        System.out.println("Filtro por cor: " + corASerBuscada);
        ListaDeProdutos.lista()
                .stream()
                .filter(produto -> corASerBuscada.equals(produto.getCor()))
                .forEach(produto -> System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo()));

        Tamanho tamanhoASerBuscado = Tamanho.EXTRA_GRANDE;
        System.out.println("\nFiltro por tamanho: " + tamanhoASerBuscado);
        ListaDeProdutos.lista()
                .stream()
                .filter(produto -> produto.getTamanhosDisponiveis().contains(tamanhoASerBuscado))
                .forEach(produto -> System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo()));

        String trechoDoNomeASerBuscado = "Moletom";
        System.out.println("\nFiltro por trecho do nome: " + trechoDoNomeASerBuscado);
        ListaDeProdutos.lista()
                .stream()
                .filter(produto -> produto.getNome().contains(trechoDoNomeASerBuscado))
                .forEach(produto -> System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo()));

        System.out.println("\nFiltra se há desconto");
        ListaDeProdutos.lista()
                .stream()
                .filter(produto -> produto.temDesconto())
                .forEach(produto -> System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo()));

    }

}
