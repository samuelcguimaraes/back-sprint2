package br.com.rchlo.main.ordenacao;

import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;

import java.util.Collections;
import java.util.List;

public class OrdenacaoPorCodigoComApiDeCollections {

    public static void main(String[] args) {
        List<Produto> produtos = ListaDeProdutos.lista();

        Collections.sort(produtos);

        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo());
        }
    }

}
