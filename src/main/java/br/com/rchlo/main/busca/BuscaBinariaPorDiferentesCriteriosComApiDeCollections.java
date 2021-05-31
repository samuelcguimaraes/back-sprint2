package br.com.rchlo.main.busca;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BuscaBinariaPorDiferentesCriteriosComApiDeCollections {

    public static void main(String[] args) {
        var busca = new BuscaBinariaPorDiferentesCriteriosComApiDeCollections();

        List<Produto> produtos = ListaDeProdutos.lista();

        try {

            var produtoASerBuscadoPorCodigo = new Produto(7L);
            int indiceEncontradoPorCodigo = busca.executa(produtos, produtoASerBuscadoPorCodigo, Comparator.comparing(Produto::getCodigo));

            Produto produtoEncontradoPorCodigo = produtos.get(indiceEncontradoPorCodigo);

            System.out.println("\nÍndice encontrado por código: " + indiceEncontradoPorCodigo);
            System.out.println(produtoEncontradoPorCodigo.getNome() + " (cod. " + produtoEncontradoPorCodigo.getCodigo() + " - " + produtoEncontradoPorCodigo.getPesoEmGramas() + " g) custa R$ " + produtoEncontradoPorCodigo.getPrecoEfetivo());

        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }

        try {

            var produtoASerBuscadoPorCor = new Produto(Cor.VERMELHA);
            int indiceEncontradoPorCor = busca.executa(produtos, produtoASerBuscadoPorCor, Comparator.comparing(Produto::getCor));

            Produto produtoEncontradoPorCor = produtos.get(indiceEncontradoPorCor);

            System.out.println("\nÍndice encontrado por cor: " + indiceEncontradoPorCor);
            System.out.println(produtoEncontradoPorCor.getNome() + " (cod. " + produtoEncontradoPorCor.getCodigo() + " - " + produtoEncontradoPorCor.getPesoEmGramas() + " g) custa R$ " + produtoEncontradoPorCor.getPrecoEfetivo());

        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private int executa(List<Produto> produtos, Produto produtoASerBuscado, Comparator<Produto> comparador) {
        Collections.sort(produtos, comparador);

        int indiceEncontrado = Collections.binarySearch(produtos, produtoASerBuscado, comparador);
        if (indiceEncontrado < 0) {
            throw  new NoSuchElementException("O produto buscado não foi encontrado: " + produtoASerBuscado);
        }
        return indiceEncontrado;
    }

}
