package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cliente;
import model.Usuario;

public class UsuarioDao {
	public void cadastrarUsuario(Usuario usuario) throws ExceptionDao {
		String sql = "insert into tbUsuario(codUsuario,nomeUsuario,cpfUsuario,dataNascimentoUsuario,salarioUsuario,userUsuario,senhaUsuario,perfilUsuario) values (?,?,?,?,?,?,?,?)";
		Connection conexao = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conexao = ModuloConexao.conector(); // abre conexao
			pst = conexao.prepareStatement(sql); // passa o comando sql como argumento
			pst.setInt(1, usuario.getCodUsuario()); // atribui o valor do model a linha SQL
			pst.setString(2, usuario.getNomeUsuario());
			pst.setString(3, usuario.getCpfUsuario());
			pst.setString(4, usuario.getDataNascimentoUsuario());
			pst.setDouble(5, usuario.getSalarioUsuario());
			pst.setString(6, usuario.getUserUsuario());
			pst.setString(7, usuario.getSenhaUsuario());
			pst.setString(8, usuario.getPerfilUsuario());
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
