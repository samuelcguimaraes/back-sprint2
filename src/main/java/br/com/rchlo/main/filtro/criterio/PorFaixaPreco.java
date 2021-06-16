package br.com.rchlo.main.filtro.criterio;

import br.com.rchlo.domain.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PorFaixaPreco extends DoisCriterios<BigDecimal> {
	
	public PorFaixaPreco(final List<Produto> produtos,
	                     final BigDecimal minimo, final BigDecimal maximo) {
		super(produtos, minimo, maximo);
	}
	
	@Override
	public List<Produto> filtra() {
		
		List<Produto> produtosFiltrados = new ArrayList<>();
		for (Produto produto : produtos) {
			if (produto.getPrecoEfetivo().compareTo(criterio1) >= 0 &&
			    produto.getPrecoEfetivo().compareTo(criterio2) <= 0) {
				produtosFiltrados.add(produto);
			}
		}
		
		return produtosFiltrados;
	}
}
