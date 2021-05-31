package br.com.rchlo.main.ordenacao;

import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;

public class OrdenacaoPorPrecoComMergeSort {

    public static void main(String[] args) {
        var mergeSort = new OrdenacaoPorPrecoComMergeSort();

        Produto[] produtos = ListaDeProdutos.todos();

        mergeSort.ordena(produtos);

        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo());
        }

    }

    public void ordena(Produto[] produtos) {
        ordena(produtos, 0, produtos.length);
    }

    private void ordena(Produto[] produtos, int inicio, int termino) {
        int quantidade = termino - inicio;
        if (quantidade > 1) {
            int meio = (inicio + termino) / 2;
            ordena(produtos, inicio, meio);
            ordena(produtos, meio, termino);
            intercala(produtos, inicio, meio, termino);
        }
    }


    private void intercala(Produto[] produtos, int inicio, int meio, int termino) {

        int total = termino - inicio;
        Produto[] resultado = new Produto[total];

        int atual = 0;
        int atual1 = inicio;
        int atual2 = meio;

        while (atual1 < meio && atual2 < termino) {
            Produto produto1 = produtos[atual1];
            Produto produto2 = produtos[atual2];
            if (produto1.getPrecoEfetivo().compareTo(produto2.getPrecoEfetivo()) < 0) {
                resultado[atual] = produto1;
                atual1++;
            } else {
                resultado[atual] = produto2;
                atual2++;
            }
            atual++;
        }

        while (atual1 < meio) {
            resultado[atual] = produtos[atual1];
            atual1++;
            atual++;
        }

        while (atual2 < termino) {
            resultado[atual] = produtos[atual2];
            atual2++;
            atual++;
        }

        for (int contador = 0; contador < atual; contador++) {
            produtos[inicio + contador] = resultado[contador];
        }
    }

}
