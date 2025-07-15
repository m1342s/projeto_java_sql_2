package testPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;


import org.junit.Test;

import dao.IProdutoDAO;
import dao.ProdutoDAO;
import domain.Produto;

public class ProdutoTest {
	
	private IProdutoDAO produtoDAO;
	
	@Test
	public void cadastrarProdutoTest() throws Exception {
		produtoDAO=new ProdutoDAO();
		
		Produto produto=new Produto();
		produto.setCodigo("01");
		produto.setNome("pão");
		Integer countCad=produtoDAO.cadastrar(produto);
		assertTrue(countCad==1);
		
		Produto produtoBancoDados=produtoDAO.buscar("01");
		assertNotNull(produtoBancoDados);
		assertEquals(produto.getCodigo(),produtoBancoDados.getCodigo());
		assertEquals(produto.getNome(),produtoBancoDados.getNome());
		
		Integer countDelete=produtoDAO.excluir(produtoBancoDados);
		assertTrue(countDelete==1);
		
	}
	@Test
	public void buscarProdutoTest() throws Exception {
		produtoDAO=new ProdutoDAO();
		
		Produto produto=new Produto();
		produto.setCodigo("01");
		produto.setNome("pão");
		Integer countCad=produtoDAO.cadastrar(produto);
		assertTrue(countCad==1);
		
		Produto produtoBancoDados=produtoDAO.buscar("01");
		assertNotNull(produtoBancoDados);
		assertEquals(produto.getCodigo(),produtoBancoDados.getCodigo());
		assertEquals(produto.getNome(),produtoBancoDados.getNome());
		
		Integer countDelete=produtoDAO.excluir(produtoBancoDados);
		assertTrue(countDelete==1);
		
	}
	@Test
	public void excluirProdutoTest() throws Exception {
		produtoDAO=new ProdutoDAO();
		
		Produto produto=new Produto();
		produto.setCodigo("01");
		produto.setNome("pão");
		Integer countCad=produtoDAO.cadastrar(produto);
		assertTrue(countCad==1);
		
		Produto produtoBancoDados=produtoDAO.buscar("01");
		assertNotNull(produtoBancoDados);
		assertEquals(produto.getCodigo(),produtoBancoDados.getCodigo());
		assertEquals(produto.getNome(),produtoBancoDados.getNome());
		
		Integer countDelete=produtoDAO.excluir(produtoBancoDados);
		assertTrue(countDelete==1);
		
	}
	@Test
	public void buscarTodosProdutosTest() throws Exception {
		produtoDAO=new ProdutoDAO();
		
		Produto produto=new Produto();
		produto.setCodigo("01");
		produto.setNome("pão");
		Integer countCad=produtoDAO.cadastrar(produto);
		assertTrue(countCad==1);
		
		Produto produto2=new Produto();
		produto.setCodigo("02");
		produto.setNome("manteiga");
		Integer countCad2=produtoDAO.cadastrar(produto2);
		assertTrue(countCad2==1);
		
		List<Produto>listaDeProdutos=produtoDAO.buscarTodos();
		assertNotNull(listaDeProdutos);
		assertEquals(2,listaDeProdutos.size());
		
		int countDelete=0;
		for(Produto pr:listaDeProdutos) {
			produtoDAO.excluir(pr);
			countDelete++;
			
		}
		assertEquals(listaDeProdutos.size(),countDelete);
		listaDeProdutos=produtoDAO.buscarTodos();
		assertEquals(listaDeProdutos.size(),0);
		
	}
	@Test
	public void atualizarProdutoTest() throws Exception {
		produtoDAO=new ProdutoDAO();
		
		Produto produto=new Produto();
		produto.setCodigo("01");
		produto.setNome("pão");
		Integer countCad=produtoDAO.cadastrar(produto);
		assertTrue(countCad==1);
		
		Produto produtoBancoDados=produtoDAO.buscar("01");
		assertNotNull(produtoBancoDados);
		assertEquals(produto.getCodigo(),produtoBancoDados.getCodigo());
		assertEquals(produto.getNome(),produtoBancoDados.getNome());
		
		
		
		produtoBancoDados.setCodigo("02");
		produto.setNome("bolacha");
		Integer countUpdate=produtoDAO.atualizar(produtoBancoDados);
		assertTrue(countUpdate==1);
		
		Produto produtoBancoDados1=produtoDAO.buscar("01");
		assertNull(produtoBancoDados1);
		
		Produto produtoBancoDados2=produtoDAO.buscar("02");
		assertNotNull(produtoBancoDados2);
		
		assertEquals(produtoBancoDados.getId(),produtoBancoDados2.getId());
		assertEquals(produtoBancoDados.getCodigo(),produtoBancoDados2.getCodigo());
		assertEquals(produtoBancoDados.getNome(),produtoBancoDados2.getNome());
		
		
		List<Produto>listaDeProdutos=produtoDAO.buscarTodos();
		
		for(Produto pr:listaDeProdutos) {
			produtoDAO.excluir(pr);			
		}
		
		
	}
	
}
