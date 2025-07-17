package testPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.ClienteDAO;
import dao.IProdutoDAO;
import dao.ProdutoDAO;
import domain.Cliente;
import domain.Produto;

public class ProdutoTest {
	
	
	@Before
	public void setup() throws Exception {

	  ProdutoDAO ProdutoDAO = new ProdutoDAO();

	  List<Produto> listaDeProdutos = ProdutoDAO.buscarTodos();

	  for (Produto produto : listaDeProdutos) {

	    ProdutoDAO.excluir(produto);

	  }

	}
	
	private IProdutoDAO produtoDAO;
	
	@Test
	public void cadastrarProdutoTest() throws Exception {
		produtoDAO=new ProdutoDAO();
		
		Produto produto= new Produto();
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
		produto2.setCodigo("02");
		produto2.setNome("manteiga");
		Integer countCad2=produtoDAO.cadastrar(produto2);
		assertTrue(countCad2==1);
		
		List<Produto> listaDeProdutos=produtoDAO.buscarTodos();
		assertNotNull(listaDeProdutos);
		assertEquals(2,listaDeProdutos.size());
		
		int countDelete=0;
		for(Produto pr : listaDeProdutos) {
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
		
		Produto produtoBD=produtoDAO.buscar("01");
		assertNotNull(produtoBD);
		assertEquals(produto.getCodigo(),produtoBD.getCodigo());
		assertEquals(produto.getNome(),produtoBD.getNome());
		
		produtoBD.setCodigo("02");
		produtoBD.setNome("manteiga");
		Integer countUpdate=produtoDAO.atualizar(produtoBD);
		assertTrue(countUpdate==1);
		
		Produto produtoBD1=produtoDAO.buscar("01");
		assertNull(produtoBD1);
		
		Produto produtoBD2=produtoDAO.buscar("02");
		assertNotNull(produtoBD2);

		assertEquals(produtoBD.getId(),produtoBD2.getId());
		assertEquals(produtoBD.getCodigo(),produtoBD2.getCodigo());
		assertEquals(produtoBD.getNome(),produtoBD2.getNome());
		
		List<Produto>listaDeProdutos=produtoDAO.buscarTodos();
		for(Produto pr : listaDeProdutos) {
			produtoDAO.excluir(pr);
		}
		
}
}
