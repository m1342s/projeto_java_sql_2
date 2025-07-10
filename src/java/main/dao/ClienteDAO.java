package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import domain.Cliente;

public class ClienteDAO implements IClienteDAO {
	
	@Before
	public void setup() throws Exception {

	  ClienteDAO clienteDAO = new ClienteDAO();

	  List<Cliente> clientes = clienteDAO.buscarTodos();

	  for (Cliente cliente : clientes) {

	    clienteDAO.excluir(cliente);

	  }

	}

	@Override
	public Integer cadastrar(Cliente cliente) throws Exception {
		Connection connection=null;
		PreparedStatement stm=null;
		try {
			connection=ConnectionFactory.getConnection();
			String sql="INSERT INTO TB_CLIENTE_2 (ID, CODIGO, NOME) VALUES (nextval('SQ_CLIENTE_2'),?,?)";
			stm=connection.prepareStatement(sql);
			stm.setString(1,cliente.getCodigo());
			stm.setString(2,cliente.getNome());
			return stm.executeUpdate();

		} catch(Exception e ) {
			throw e;
		} finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
		
	}

	@Override
	public Cliente consultar(String codigo) throws Exception {
		Connection connection=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		Cliente cliente=null;
		try {
			connection=ConnectionFactory.getConnection();
			String sql="select * from tb_cliente_2 where codigo = ?";
			stm=connection.prepareStatement(sql);
			stm.setString(1,codigo);
			
			rs=stm.executeQuery();
			
			if(rs.next()) {
				cliente= new Cliente();
				cliente.setId(rs.getLong("id"));
				cliente.setCodigo(rs.getString("codigo"));
				cliente.setNome(rs.getString("nome"));
				


			}
			return cliente;

		} catch(Exception e ) {
			throw e;
		} finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Integer excluir(Cliente cliente) throws Exception {
		Connection connection=null;
		PreparedStatement stm=null;
		try {
			connection=ConnectionFactory.getConnection();
			String sql="DELETE FROM TB_CLIENTE_2 WHERE CODIGO = ?";
			stm=connection.prepareStatement(sql);
			stm.setString(1,cliente.getCodigo());
			return stm.executeUpdate();

		} catch(Exception e ) {
			throw e;
		} finally {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}
	
	public List<Cliente> buscarTodos() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Cliente> listaClientes= new ArrayList<>();
		Cliente cliente= new Cliente();
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_CLIENTE_2 ";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getCodigo());
			rs=stm.executeQuery();
			
			while(rs.next()) {
				cliente=new Cliente();
				Long id=rs.getLong("ID");
				String codigo=rs.getString("CODIGO");
				String nome=rs.getString("NOME");
				cliente.setId(id);
				cliente.setNome(nome);
				cliente.setCodigo(codigo);
				listaClientes.add(cliente);

				

				
			}
		} catch(Exception e) {
			throw e;
		} finally {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
		return listaClientes;
	}
    

public Integer atualizar(Cliente cliente) throws Exception {
	Connection connection = null;
	PreparedStatement stm = null;
	
	try {
		connection = ConnectionFactory.getConnection();
		String sql = getSQLUpdate();
		stm = connection.prepareStatement(sql);
		adicionarParametrosUpdate(stm,cliente);
		return stm.executeUpdate();
	} catch(Exception e) {
		throw e;
	} finally {
		if (stm != null && !stm.isClosed()) {
			stm.close();
		}
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
}

private void adicionarParametrosUpdate(PreparedStatement stm, Cliente cliente) throws SQLException {
	stm.setString(1,cliente.getNome());
	stm.setString(2,cliente.getCodigo());
	stm.setLong(3,cliente.getId());

}

private String getSQLUpdate() {
	StringBuilder sb=new StringBuilder();
	sb.append("UPDATE TB_CLIENTE_2 ");
	sb.append("SET NOME = ?, CODIGO = ?");
	sb.append("WHERE ID = ?");
	return sb.toString();

	
}
}
	
