package br.com.rchlo.main.filtro.criterio;

import br.com.rchlo.domain.Produto;

import java.util.List;

public abstract class UmCriterio<T> implements CriterioFiltro {
	
	protected final List<Produto> produtos;
	protected final T criterio;
	
	protected UmCriterio(final List<Produto> produtos, final T criterio) {
		this.produtos = produtos;
		this.criterio = criterio;
	}
	
	public abstract List<Produto> filtra();
}
