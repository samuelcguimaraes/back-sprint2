package br.com.rchlo.main.filtro;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FiltroDeProdutosPorCor {

    public static void main(String[] args) {
        List<Produto> produtos = ListaDeProdutos.lista();
        var cor = Cor.VERMELHA;
    
        FiltroDeProdutosPorCor filtroDeProdutosPorCor = new FiltroDeProdutosPorCor();

        List<Produto> produtosFiltrados = filtroDeProdutosPorCor.filtraProdutos(produtos, cor);
    
        for (Produto produto : produtosFiltrados) {
            System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo());
        }

    }
    
    public List<Produto> filtraProdutos(final List<Produto> produtos, final Cor cor) {
    
        List<Produto> produtosFiltrados = new ArrayList<>();
        
        for (Produto produto : produtos) {
            if (cor.equals(produto.getCor())) {
                produtosFiltrados.add(produto);
            }
        }
    
        /*if (produtosFiltrados.isEmpty()) {
            throw new NoSuchElementException("Não foi encontrado nenhum produto com a cor " + cor.name());
        }*/
        
        return produtosFiltrados;
    }
    
}
