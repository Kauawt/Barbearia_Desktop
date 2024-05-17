package dao;

import java.sql.*;

public class ModuloConexao {
	private static java.sql.Connection conexao = null;
	public static Connection conector() {
		
		String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url="jdbc:sqlserver://localhost:1433;databasename=dbBarbearia";
		String user ="sa";
		String password="Admin123";
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url,user,password);
			return conexao;
		}catch(Exception e ){
			return null;
		}
	}
	
	public static void fecharConexao() {
		
		try {
			if(conexao!=null && !conexao.isClosed()) {
				conexao.close();
			}
		}catch(SQLException e) {
			System.out.println("Erro ao encerrar a conexão: " + e.getMessage());
		}finally {
			conexao = null;
		}
	}

}
