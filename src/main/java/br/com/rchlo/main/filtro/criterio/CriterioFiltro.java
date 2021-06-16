package br.com.rchlo.main.filtro.criterio;

import br.com.rchlo.domain.Produto;

import java.util.List;

public interface CriterioFiltro {
	
	List<Produto> filtra();
}
