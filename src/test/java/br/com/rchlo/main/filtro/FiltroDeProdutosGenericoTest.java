package br.com.rchlo.main.filtro;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.Produto;
import br.com.rchlo.domain.Tamanho;
import br.com.rchlo.main.filtro.criterio.PorCodigo;
import br.com.rchlo.main.filtro.criterio.PorCor;
import br.com.rchlo.main.filtro.criterio.PorFaixaPreco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FiltroDeProdutosGenericoTest {
	
	private FiltroDeProdutosGenerico filtroDeProdutos;
	private List<Produto> produtos;
	
	@BeforeEach
	void setUp() {
		filtroDeProdutos = new FiltroDeProdutosGenerico();
		produtos = constroiListaDeProdutos();
	}
	
	@Test
	void deveriaFiltrarPorCorUmProduto() {
		
		final List<Produto> produtosFiltrados = filtroDeProdutos.filtraProdutos(new PorCor(produtos, Cor.ROSA));
		
		assertEquals(1, produtosFiltrados.size());
	}
	
	@Test
	void deveriaFiltrarPorCorMaisDeUmProduto() {
		
		final List<Produto> produtosFiltrados = filtroDeProdutos.filtraProdutos(new PorCor(produtos, Cor.PRETA));
		
		assertEquals(2, produtosFiltrados.size());
	}
	
	@Test
	void deveriaRetornarListaVaziaAoFiltrarPorCorInexistente() {
		
		final List<Produto> produtosFiltrados = filtroDeProdutos.filtraProdutos(new PorCor(produtos, Cor.CINZA));
		
		assertEquals(0, produtosFiltrados.size());
	}
	
	@Test
	void deveriaRetornarListaVaziaNoFiltroPorCorQuandoAListaDeProdutosEstiverVazia() {
		
		final List<Produto> produtos = new ArrayList<>();
		final List<Produto> produtosFiltrados = filtroDeProdutos.filtraProdutos(new PorCor(produtos, Cor.PRETA));
		
		assertEquals(0, produtosFiltrados.size());
	}
	
	@Test
	void deveriaFiltrarPorCodigoUmProduto() {
		
		final List<Produto> produtosFiltrados = filtroDeProdutos.filtraProdutos(new PorCodigo(produtos, 9L));
		
		assertEquals(1, produtosFiltrados.size());
	}
	
	@Test
	void deveriaFiltrarPorCodigoMaisDeUmProduto() {
		
		final List<Produto> produtosFiltrados = filtroDeProdutos.filtraProdutos(new PorCodigo(produtos, 10L));
		
		assertEquals(2, produtosFiltrados.size());
	}
	
	@Test
	void deveriaRetornarListaVaziaAoFiltrarPorCodigoInexistente() {
		
		final List<Produto> produtosFiltrados = filtroDeProdutos.filtraProdutos(new PorCodigo(produtos, 15L));
		
		assertEquals(0, produtosFiltrados.size());
	}
	
	@Test
	void deveriaRetornarListaVaziaNoFiltroPorCodigoQuandoAListaDeProdutosEstiverVazia() {
		
		final List<Produto> produtos = new ArrayList<>();
		final List<Produto> produtosFiltrados = filtroDeProdutos.filtraProdutos(new PorCodigo(produtos, 10L));
		
		assertEquals(0, produtosFiltrados.size());
	}
	
	@Test
	void deveriaFiltrarPorFaixaPrecoUmProduto() {
		
		final List<Produto> produtosFiltrados = filtroDeProdutos.filtraProdutos(
				new PorFaixaPreco(produtos,
				                  new BigDecimal("15"),
				                  new BigDecimal("20")));
		
		assertEquals(1, produtosFiltrados.size());
	}
	
	@Test
	void deveriaFiltrarPorFaixaPrecoMaisDeUmProduto() {
		
		final List<Produto> produtosFiltrados = filtroDeProdutos.filtraProdutos(
				new PorFaixaPreco(produtos,
				                  new BigDecimal("30"),
				                  new BigDecimal("80")));
		
		assertEquals(4, produtosFiltrados.size());
	}
	
	@Test
	void deveriaRetornarListaVaziaNoFiltroPorFaixaProdutoQuandoAListaDeProdutosEstiverVazia() {
		
		final List<Produto> produtos = new ArrayList<>();
		final List<Produto> produtosFiltrados = filtroDeProdutos.filtraProdutos(
				new PorFaixaPreco(produtos,
				                  new BigDecimal("30"),
				                  new BigDecimal("80")));
		
		assertEquals(0, produtosFiltrados.size());
	}
	
	private List<Produto> constroiListaDeProdutos() {
		return Arrays.asList(
				new Produto(5L,
				            "Produto 5",
				            "Produto 5",
				            "produto-5",
				            "marca 5",
				            new BigDecimal("100"),
				            new BigDecimal("90"),
				            Cor.PRETA,
				            Arrays.asList(Tamanho.PEQUENO, Tamanho.EXTRA_GRANDE),
				            100,
				            ""),
				new Produto(1L,
				            "Produto 1",
				            "Produto 1",
				            "produto-1",
				            "marca 1",
				            new BigDecimal("15.85"),
				            null,
				            Cor.PRETA,
				            Arrays.asList(Tamanho.PEQUENO, Tamanho.MEDIO, Tamanho.GRANDE),
				            50,
				            ""),
				new Produto(9L,
				            "Produto 9",
				            "Produto 9",
				            "produto-9",
				            "marca 9",
				            new BigDecimal("75.5"),
				            new BigDecimal("70.5"),
				            Cor.VERMELHA,
				            Arrays.asList(Tamanho.GRANDE, Tamanho.EXTRA_GRANDE),
				            199,
				            ""),
				new Produto(10L,
				            "Produto 10",
				            "Produto 10",
				            "produto-10",
				            "marca 10",
				            new BigDecimal("49.99"),
				            null,
				            Cor.VERMELHA, Arrays.asList(Tamanho.GRANDE, Tamanho.EXTRA_GRANDE),
				            250,
				            ""),
				new Produto(10L,
				            "Produto 10",
				            "Produto 10",
				            "produto-10",
				            "marca 10",
				            new BigDecimal("49.99"),
				            null,
				            Cor.VERMELHA, Arrays.asList(Tamanho.GRANDE, Tamanho.EXTRA_GRANDE),
				            250,
				            ""),
				new Produto(7L,
				            "Produto 7",
				            "Produto 7",
				            "produto-7",
				            "marca 7",
				            new BigDecimal("35.9"),
				            null,
				            Cor.ROSA, Arrays.asList(Tamanho.PEQUENO, Tamanho.MEDIO),
				            135,
				            "")
		);
	}
}