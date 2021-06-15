package br.com.rchlo.main.busca;

import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BuscaBinariaPorCodigoComApiDeCollectionsTest {
	
	private BuscaBinariaPorCodigoComApiDeCollections buscaBinariaPorCodigo;
	private List<Produto> produtos;
	
	@BeforeEach
	void setUp() {
		buscaBinariaPorCodigo = new BuscaBinariaPorCodigoComApiDeCollections();
		//produtos = ListaDeProdutos.lista();
		produtos = constroiListaDeProdutos();
	}
	
	@Test
	void deveriaEncontrarProdutoExistenteNaLista() {
		
		final Produto produto = new Produto(5L);
		
		final int indice = buscaBinariaPorCodigo.executa(produtos, produto);
		
		assertEquals(2, indice);
	}
	
	@Test
	void deveriaLancarExcecaoAoNaoEncontrarProdutoNaLista() {
		
		final Produto produto = new Produto(2L);
		
		final NoSuchElementException thrown =
				assertThrows(NoSuchElementException.class,
				             () -> buscaBinariaPorCodigo.executa(produtos, produto),
				             "Era esperado que a busca lancasse uma exceção por não encontrar o produto na lista, mas isso não ocorreu.");
		
		assertTrue(thrown.getMessage().contains("O produto buscado não foi encontrado: "));
	}
	
	@Test
	void deveriaLancarExcecaoQuandoAListaEstiverVazia() {
		
		final Produto produto = new Produto(2L);
		final List<Produto> produtos = new ArrayList<>();
		
		final NoSuchElementException thrown =
				assertThrows(NoSuchElementException.class,
				             () -> buscaBinariaPorCodigo.executa(produtos, produto),
				             "Era esperado que a busca lancasse uma exceção por não encontrar o produto na lista, mas isso não ocorreu.");
		
		assertTrue(thrown.getMessage().contains("O produto buscado não foi encontrado: "));
	}
	
	private List<Produto> constroiListaDeProdutos() {
		return Arrays.asList(
				new Produto(5L),
				new Produto(1L),
				new Produto(10L),
				new Produto(3L)
		);
	}
}