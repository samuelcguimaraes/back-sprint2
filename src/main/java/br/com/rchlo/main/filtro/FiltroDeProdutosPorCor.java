package br.com.rchlo.main.filtro;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class FiltroDeProdutosPorCor {

    public static void main(String[] args) {
        List<Produto> produtos = ListaDeProdutos.lista();
        var cor = Cor.VERMELHA;

        List<Produto> produtosFiltrados = new ArrayList<>();
        for (Produto produto : produtos) {
            if (cor.equals(produto.getCor())) {
                produtosFiltrados.add(produto);
            }
        }

        for (Produto produto : produtosFiltrados) {
            System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo());
        }

    }

}