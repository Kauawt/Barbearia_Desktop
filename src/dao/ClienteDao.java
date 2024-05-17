package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cliente;

public class ClienteDao {
	
	public void cadastrarCliente(Cliente cliente) throws ExceptionDao {
		String sql = "insert into tbCliente(codCliente,nomeCliente,enderecoCliente,telefoneCliente,cpfCliente,statusCliente) values (?,?,?,?,?,?)";
		Connection conexao = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
	
		try {
			
			conexao = ModuloConexao.conector(); // abre conexao
			pst = conexao.prepareStatement(sql); // passa o comando sql como argumento
			pst.setInt(1, cliente.getCodCliente()); // atribui o valor do model a linha SQL
			pst.setString(2, cliente.getNomeCliente());
			pst.setString(3, cliente.getEnderecoCliente());
			pst.setString(4, cliente.getTelefoneCliente());
			pst.setString(5, cliente.getCpfCliente());
			pst.setString(6, cliente.getStatusCliente());
			pst.executeUpdate(); // atualiza o banco de dados
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao Cadastrar o Cliente: " + e);
		} finally {
			try {
				if (pst != null) {
					pst.close();
				} // fecha conexão com banco
			} catch (SQLException e) {
				throw new ExceptionDao("Erro ao fechar o Statement" + e);
			}
			try {
				if (conexao != null) {
					conexao.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDao("Erro ao fechar a conexão: " + e);
			}
		}
	}
}
