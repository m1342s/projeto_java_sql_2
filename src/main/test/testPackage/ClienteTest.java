package testPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.ClienteDAO;
import dao.IClienteDAO;
import domain.Cliente;

public class ClienteTest {
	
	@Before
	public void setup() throws Exception {

	  ClienteDAO clienteDAO = new ClienteDAO();

	  List<Cliente> clientes = clienteDAO.buscarTodos();

	  for (Cliente cliente : clientes) {

	    clienteDAO.excluir(cliente);

	  }

	}
	
	private IClienteDAO clienteDAO;
	
	
	
	@Test
	public void cadastrarTest() throws Exception {
		IClienteDAO dao=new ClienteDAO();
		Cliente cliente=new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Rodrigo Pires");
		
		Integer qtd=dao.cadastrar(cliente);
		assertTrue(qtd==1);
		
		Cliente clienteBD=dao.consultar(cliente.getCodigo());
		
		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());

		assertEquals(cliente.getCodigo(),clienteBD.getCodigo());
		assertEquals(cliente.getNome(),clienteBD.getNome());
		
		Integer qtdDelete=dao.excluir(cliente);
		assertNotNull(qtdDelete);
		
		dao.cadastrar(cliente);
		
		
	}
	
	@Test
	public void excluirTest() throws Exception {
		IClienteDAO dao=new ClienteDAO();
		Cliente cliente=new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Rodrigo Pires");
		
		Integer countCad=dao.cadastrar(cliente);
		assertTrue(countCad==1);
		
		Cliente clienteBancoDados= dao.consultar("01");
		assertNotNull(clienteBancoDados);
		
		assertEquals(cliente.getCodigo(),clienteBancoDados.getCodigo());
		assertEquals(cliente.getNome(),clienteBancoDados.getNome());
		
		Integer countDelete=dao.excluir(clienteBancoDados);
		assertTrue(countDelete==1);
		
	}
	
	@Test
	public void buscarTest() throws Exception {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente=new Cliente();
		
		cliente.setCodigo("01");
		cliente.setNome("Rodrigo Pires");
		Integer countCad=clienteDAO.cadastrar(cliente);
		assertTrue(countCad==1);
		
		Cliente clienteBD=clienteDAO.consultar("01");
		assertNotNull(clienteBD);
		
		assertEquals(cliente.getCodigo(),clienteBD.getCodigo());
		assertEquals(cliente.getNome(),clienteBD.getNome());
		
		Integer countDelete=clienteDAO.excluir(clienteBD);
		assertTrue(countDelete==1);
		
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		 clienteDAO = new ClienteDAO();
		Cliente cliente=new Cliente();
		
		cliente.setCodigo("01");
		cliente.setNome("Rodrigo Pires");
		Integer countCad=clienteDAO.cadastrar(cliente);
		assertTrue(countCad==1);
		
		Cliente clientes= new Cliente();
		clientes.setCodigo("02");
		clientes.setNome("Teste2");
		Integer countCad2=clienteDAO.cadastrar(clientes);
		assertTrue(countCad2==1);
		
		List<Cliente> listaClientes=clienteDAO.buscarTodos();
		assertNotNull(listaClientes);
		assertEquals(2,listaClientes.size());
		
		int countDelete=0;
		for(Cliente c:listaClientes) {
			clienteDAO.excluir(c);
			countDelete++;
			
		}
		assertEquals(listaClientes.size(),countDelete);
		
		listaClientes=clienteDAO.buscarTodos();
		assertEquals(listaClientes.size(),0);

		

	}
	
	@Test
	public void atualizarTest() throws Exception {
		clienteDAO = new ClienteDAO();
		Cliente cliente=new Cliente();
		
		cliente.setCodigo("01");
		cliente.setNome("Rodrigo Pires");
		Integer countCad=clienteDAO.cadastrar(cliente);
		assertTrue(countCad==1);
		
		Cliente clienteBancoDados=clienteDAO.consultar("01");
		assertNotNull(clienteBancoDados);
		assertEquals(cliente.getCodigo(),clienteBancoDados.getCodigo());
		assertEquals(cliente.getNome(),clienteBancoDados.getNome());
		
		cliente.setCodigo("02");
		cliente.setNome("Outro Teste");
		Integer countAtualizar=clienteDAO.atualizar(cliente);
		assertTrue(countAtualizar==1);
		
		Cliente clienteBD1=clienteDAO.consultar("01");
		assertNull(clienteBD1);
		
		Cliente clienteBancoDados2=clienteDAO.consultar("02");
		assertNotNull(clienteBancoDados2);
		assertEquals(clienteBancoDados.getId(),clienteBancoDados2.getId());
		assertEquals(clienteBancoDados.getCodigo(),clienteBancoDados2.getCodigo());
		assertEquals(clienteBancoDados.getNome(),clienteBancoDados2.getNome());
		
		List<Cliente>listaClientes=clienteDAO.buscarTodos();
		for(Cliente c:listaClientes) {
			clienteDAO.excluir(c);
			
		}


	}
	
	
	
	

}
