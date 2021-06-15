package br.com.rchlo.main.filtro;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FiltroDeProdutosPorCorTest {
	
	private FiltroDeProdutosPorCor filtroDeProdutosPorCor;
	private List<Produto> produtos;
	
	@BeforeEach
	void setUp() {
		filtroDeProdutosPorCor = new FiltroDeProdutosPorCor();
		//produtos = ListaDeProdutos.lista();
		produtos = constroiListaDeProdutos();
	}
	
	@Test
	void deveriaFiltrarUnicoProdutoVerdeExistenteNaLista() {
		
		final List<Produto> produtosFiltrados = filtroDeProdutosPorCor.filtraProdutos(this.produtos, Cor.VERDE);
		
		assertEquals(1, produtosFiltrados.size());
	}
	
	@Test
	void deveriaFiltrarOsTresProdutosPretosExistentesNaLista() {
		
		final List<Produto> produtosFiltrados = filtroDeProdutosPorCor.filtraProdutos(this.produtos, Cor.PRETA);
		
		assertEquals(3, produtosFiltrados.size());
	}
	
	@Test
	void deveriaRetornarListaVaziaQuandoNaoHouverProdutoDaCorProcurada() {
		
		final List<Produto> produtosFiltrados = filtroDeProdutosPorCor.filtraProdutos(this.produtos, Cor.CINZA);
		
		assertEquals(0, produtosFiltrados.size());
	}
	
	@Test
	void deveriaRetornarListaVaziaQuandoAListaDeProdutosEstiverVazia() {
		
		produtos = new ArrayList<>();
		
		final List<Produto> produtosFiltrados = filtroDeProdutosPorCor.filtraProdutos(this.produtos, Cor.PRETA);
		
		assertEquals(0, produtosFiltrados.size());
	}
	
	private List<Produto> constroiListaDeProdutos() {
		return Arrays.asList(
				new Produto(Cor.PRETA),
				new Produto(Cor.PRETA),
				new Produto(Cor.VERMELHA),
				new Produto(Cor.BRANCA),
				new Produto(Cor.VERMELHA),
				new Produto(Cor.PRETA),
				new Produto(Cor.VERDE)
		);
	}
}