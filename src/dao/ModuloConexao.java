package dao;

import java.sql.*;
/**
 * Classe responsável por estabelecer a conexão com o banco de dados.
 * Gerencia a conexão com o banco de dados e fornece métodos para abrir, fechar e configurar a conexão.
 */
public class ModuloConexao {
	
	private static Connection conexao = null;
	/**
	 * Método para obter uma conexão com o banco de dados.
	 * 
	 * @return A conexão com o banco de dados, ou null se ocorrer algum erro.
	 */
	public static Connection conector() {
		Connection conexao = null;
		String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url="jdbc:sqlserver://localhost:1433;databaseName=dbBarbearia";
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
	/**
	 * Método para fechar a conexão com o banco de dados.
	 */
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
