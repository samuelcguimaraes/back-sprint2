package br.com.rchlo.main.filtro.criterio;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class PorCor extends UmCriterio<Cor> {
	
	public PorCor(final List<Produto> produtos, final Cor cor) {
		super(produtos, cor);
	}
	
	@Override
	public List<Produto> filtra() {
		
		List<Produto> produtosFiltrados = new ArrayList<>();
		
		for (Produto produto : produtos) {
			if (criterio.equals(produto.getCor())) {
				produtosFiltrados.add(produto);
			}
		}
		
		return produtosFiltrados;
	}
}
