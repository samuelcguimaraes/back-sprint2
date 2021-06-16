package br.com.rchlo.main.filtro.criterio;

import br.com.rchlo.domain.Produto;

import java.util.List;

public abstract class DoisCriterios<T> implements CriterioFiltro {
	
	protected final List<Produto> produtos;
	protected final T criterio1;
	protected final T criterio2;
	
	protected DoisCriterios(final List<Produto> produtos, final T criterio1, final T criterio2) {
		this.produtos = produtos;
		this.criterio1 = criterio1;
		this.criterio2 = criterio2;
	}
	
	public abstract List<Produto> filtra();
}
