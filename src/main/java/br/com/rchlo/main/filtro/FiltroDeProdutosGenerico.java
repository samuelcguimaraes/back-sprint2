package br.com.rchlo.main.filtro;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;
import br.com.rchlo.main.filtro.criterio.*;

import java.math.BigDecimal;
import java.util.List;

public class FiltroDeProdutosGenerico {

    public static void main(String[] args) {
        List<Produto> produtos = ListaDeProdutos.lista();
        var cor = Cor.VERMELHA;
    
        FiltroDeProdutosGenerico filtroDeProdutosPorCor = new FiltroDeProdutosGenerico();

        //List<Produto> produtosFiltrados = filtroDeProdutosPorCor.filtraProdutos(new PorCor(produtos, Cor.VERMELHA));
        //List<Produto> produtosFiltrados = filtroDeProdutosPorCor.filtraProdutos(new PorCodigo(produtos,1L));
        List<Produto> produtosFiltrados = filtroDeProdutosPorCor.filtraProdutos(new PorFaixaPreco(produtos, new BigDecimal("30"), new BigDecimal("40")));
    
    
        for (Produto produto : produtosFiltrados) {
            System.out.println(produto.getNome() + " (cod. " + produto.getCodigo() + " - " + produto.getPesoEmGramas() + " g) custa R$ " + produto.getPrecoEfetivo());
        }

    }
    
    public List<Produto> filtraProdutos(final CriterioFiltro criterioFiltro) {
    
        return criterioFiltro.filtra();
    }
    
}
