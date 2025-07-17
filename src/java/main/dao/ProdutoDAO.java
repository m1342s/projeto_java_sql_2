package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactoryProduto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Produto;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public Integer cadastrar(Produto produto) throws Exception {
		Connection connection=null;
		PreparedStatement stm=null;
		
		try {
			connection=ConnectionFactoryProduto.getConnection();
			String sql=getSqlInsert();
			stm=connection.prepareStatement(sql);
			parametrosInsert(stm,produto);
			return stm.executeUpdate();
			
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection,stm,null);
		}
	
		
	}

	

		
	


	private void parametrosInsert(PreparedStatement stm, Produto produto) throws SQLException {
		stm.setString(1,produto.getCodigo());
		stm.setString(2, produto.getNome());
		
	}







	private String getSqlInsert() {
		StringBuilder sb= new StringBuilder();
		sb.append("INSERT INTO PRODUTO_TABLE (ID,CODIGO,NOME) ");
		sb.append("VALUES (nextval('SQ_PRODUTO'),?,?)");
		return sb.toString();

	}







	@Override
	public Integer atualizar(Produto produto) throws Exception {
		Connection connection=null;
		PreparedStatement stm=null;
		
		try {
			connection=ConnectionFactoryProduto.getConnection();
			String sql=getSqlUpdate();
			stm=connection.prepareStatement(sql);
			parametrosUpdate(stm,produto);
			return stm.executeUpdate();
			
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection,stm,null);
		}
		
	
	}
	
	private String getSqlUpdate() {
		StringBuilder sb=new StringBuilder();
		sb.append("UPDATE PRODUTO_TABLE ");
		sb.append("SET NOME = ?, CODIGO = ? ");
		sb.append("WHERE ID = ?");
		return sb.toString();
	}

	private void parametrosUpdate(PreparedStatement stm, Produto produto) throws SQLException {
		stm.setString(1,produto.getNome());
		stm.setString(2,produto.getCodigo());
		stm.setLong(3,produto.getId());

		
	}



	



	@Override
	public Integer excluir(Produto produto) throws Exception {
		Connection connection=null;
		PreparedStatement stm=null;
		
		try {
			connection=ConnectionFactoryProduto.getConnection();
			String sql=getSqlDelete();
			stm=connection.prepareStatement(sql);
			parametrosDelete(stm,produto);
			return stm.executeUpdate();
			
			
			
			
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection,stm,null);
		}
	}

	private void parametrosDelete(PreparedStatement stm, Produto produto) throws SQLException {
		stm.setString(1,produto.getCodigo());
		
	}



	private String getSqlDelete() {
		StringBuilder sb=new StringBuilder();
		sb.append("DELETE FROM PRODUTO_TABLE ");
		sb.append("WHERE CODIGO = ?");
		return sb.toString();

	}



	@Override
	public Produto buscar(String codigo) throws Exception {
		Connection connection=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		Produto produto=null;
		
		try {
			connection=ConnectionFactoryProduto.getConnection();
			String sql=getSqlSelect();
			stm=connection.prepareStatement(sql);
			parametrosSelect(stm,codigo);
			rs=stm.executeQuery();
			
			if(rs.next()) {
				produto=new Produto();
				Long id=rs.getLong("ID");
				String nome=rs.getString("NOME");
				String cg=rs.getString("CODIGO");
				produto.setId(id);
				produto.setNome(nome);
				produto.setCodigo(cg);

				

				
				
			}
			
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection,stm,rs);
		}
		return produto;
		
	}

	private void parametrosSelect(PreparedStatement stm, String codigo) throws SQLException {
		stm.setString(1,codigo);
	
	}


	private String getSqlSelect() {
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT * FROM PRODUTO_TABLE ");
		sb.append("WHERE CODIGO = ?");
		return sb.toString();
	}



	@Override
	public List<Produto> buscarTodos() throws Exception {
		Connection connection=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		List<Produto> listaDeProdutos=new ArrayList<>();
		Produto produto=null;
		
		try {
			connection=ConnectionFactoryProduto.getConnection();
			String sql=getSqlSelectTodos();
			stm=connection.prepareStatement(sql);
			rs=stm.executeQuery();
			
			if(rs.next()) {
				produto=new Produto();
				Long id=rs.getLong("ID");
				String nome=rs.getString("NOME");
				String cg=rs.getString("CODIGO");
				produto.setId(id);
				produto.setNome(nome);
				produto.setCodigo(cg);
				listaDeProdutos.add(produto);

				

				
				
			}
			
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection,stm,rs);
		}
		return listaDeProdutos;
		
		
	}
	
	private String getSqlSelectTodos() {
		StringBuilder sb= new StringBuilder();
		sb.append("SELECT * FROM PRODUTO_TABLE");
		return sb.toString();
		
	}



	private void closeConnection(Connection connection,PreparedStatement stm,ResultSet rs ) {
		try{
			if(rs!=null && !rs.isClosed()) {
				rs.close();
			}
			if(stm!=null && !stm.isClosed()) {
				stm.close();
			}
			if(connection!=null && !connection.isClosed()) {
				connection.close();
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
}
