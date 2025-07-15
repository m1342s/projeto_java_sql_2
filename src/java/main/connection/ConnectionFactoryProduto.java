package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryProduto {
	
	private static Connection connection;
	
	private ConnectionFactoryProduto(Connection connection) {
		
	}
	
	public static Connection getConnection() throws SQLException{
		
		if(connection==null) {
			connection=initConnection();
		} else if(connection != null && connection.isClosed()) {
			connection=initConnection();

		}
		return connection;
	}
	private static Connection initConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/produto","postgres","1256");
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
}
