package dao;

import java.sql.*;

public class ModuloConexao {
	
	public static Connection conector() {
		java.sql.Connection conexao = null;
		String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url="jdbc:sqlserver://localhost:1433;databasename=dbBarbearia";
		String user ="sa";
		String password="admin123";
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url,user,password);
			return conexao;
		}catch(Exception e ){
			return null;
		}
	}

}
