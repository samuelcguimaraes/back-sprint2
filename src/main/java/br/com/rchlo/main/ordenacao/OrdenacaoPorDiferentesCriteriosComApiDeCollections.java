package br.com.rchlo.main.ordenacao;

import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;

import java.util.Comparator;
import java.util.List;

public class OrdenacaoPorDiferentesCriteriosComApiDeCollections {

    public static void main(String[] args) {
        List<Produto> produtos = ListaDeProdutos.lista();

        produtos.sort(new Comparator<Produto>() {
            @Override
            public int compare(Produto produto1, Produto produto2) {
                return Long.compare(produto1.getCodigo(), produto2.getCodigo());
            }
        });
        produtos.sort((produto1, produto2) -> Long.compare(produto1.getCodigo(), produto2.getCodigo()));
        produtos.sort(Comparator.comparingLong(Produto::getCodigo));

        System.out.println("Ordenação por código.");
        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo());
        }

        produtos.sort(new Comparator<Produto>() {
            @Override
            public int compare(Produto produto1, Produto produto2) {
                return produto1.getNome().compareTo(produto2.getNome());
            }
        });
        produtos.sort((produto1, produto2) -> produto1.getNome().compareTo(produto2.getNome()));
        produtos.sort(Comparator.comparing(Produto::getNome));

        System.out.println("\nOrdenação por nome.");
        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo());
        }

        produtos.sort(new Comparator<Produto>() {
            @Override
            public int compare(Produto produto1, Produto produto2) {
                return Integer.compare(produto1.getPesoEmGramas(), produto2.getPesoEmGramas());
            }
        });
        produtos.sort((produto1, produto2) -> Integer.compare(produto1.getPesoEmGramas(), produto2.getPesoEmGramas()));
        produtos.sort(Comparator.comparing(Produto::getPesoEmGramas));

        System.out.println("\nOrdenação por peso (g).");
        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo());
        }

        produtos.sort(new Comparator<Produto>() {
            @Override
            public int compare(Produto produto1, Produto produto2) {
                return produto1.getPrecoEfetivo().compareTo(produto2.getPrecoEfetivo());
            }
        });
        produtos.sort((produto1, produto2) -> produto1.getPrecoEfetivo().compareTo(produto2.getPrecoEfetivo()
        ));
        produtos.sort(Comparator.comparing(Produto::getPrecoEfetivo));

        System.out.println("\nOrdenação por preço.");
        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo());
        }

    }

}
