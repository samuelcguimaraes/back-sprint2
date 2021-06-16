package br.com.rchlo.main.filtro.criterio;

import br.com.rchlo.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class PorCodigo extends UmCriterio<Long> {
	
	public PorCodigo(final List<Produto> produtos, final long codigo) {
		super(produtos, codigo);
	}
	
	@Override
	public List<Produto> filtra() {
		
		List<Produto> produtosFiltrados = new ArrayList<>();
		
		for (Produto produto : produtos) {
			if (criterio.equals(produto.getCodigo())) {
				produtosFiltrados.add(produto);
			}
		}
		
		return produtosFiltrados;
	}
}
