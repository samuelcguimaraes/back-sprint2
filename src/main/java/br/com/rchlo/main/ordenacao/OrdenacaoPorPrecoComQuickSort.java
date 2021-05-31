package br.com.rchlo.main.ordenacao;

import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;

public class OrdenacaoPorPrecoComQuickSort {

    public static void main(String[] args) {
        var ordenacaoPorPrecoComQuickSort = new OrdenacaoPorPrecoComQuickSort();

        Produto[] produtos = ListaDeProdutos.todos();

        ordenacaoPorPrecoComQuickSort.ordena(produtos);

        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo());
        }

    }

    public void ordena(Produto[] produtos) {
        ordena(produtos, 0, produtos.length);
    }

    private void ordena(Produto[] produtos, int de, int ate) {
        int elementos = ate - de;
        if (elementos > 1) {
            int posicaoDoPivo = particiona(produtos, ate);
            ordena(produtos, de, posicaoDoPivo);
            ordena(produtos, posicaoDoPivo + 1, ate);
        }
    }

    private int particiona(Produto[] produtos, int termino) {
        int menoresEncontrados = 0;

        Produto pivo = produtos[termino - 1];
        for(int analisando = 0; analisando < termino - 1; analisando++) {
            Produto atual = produtos[analisando];
            if(atual.getPrecoEfetivo().compareTo(pivo.getPrecoEfetivo()) <= 0) {
                troca(produtos, analisando, menoresEncontrados);
                menoresEncontrados++;
            }
        }
        troca(produtos, termino - 1, menoresEncontrados);
        return menoresEncontrados;
    }

    private void troca(Produto[] produtos, int de, int para) {
        Produto produto1 = produtos[de];
        Produto produto2 = produtos[para];
        produtos[para] = produto1;
        produtos[de] = produto2;
    }

}