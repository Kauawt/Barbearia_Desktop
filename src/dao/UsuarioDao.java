package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Cliente;
import model.Usuario;
import dao.ExceptionDao;

public class UsuarioDao {
	
	private final String CADASTRAR_USUARIO = "insert into tbUsuario(nomeUsuario,cpfUsuario,dataNascimentoUsuario,salarioUsuario,emailUsuario,senhaUsuario,perfilUsuario,statusUsuario) values (?,?,?,?,?,?,?,?)";
	
	private static final String LISTAR_USUARIOS = "select * from tbUsuario";
	private static final String CONSULTAR_USUARIO_BY_CPF = "select * from tbUsuario where cpfUsuario = ?";
	private static final String ALTERAR_USUARIO = "UPDATE tbUsuario set nomeUsuario = ?, cpfUsuario = ?, dataNascimentoUsuario = ?, salarioUsuario = ? emailUsuario = ?, senhaUsuario = ?, perfilUsuario = ?, statusUsuario = ? where codUsuario = ?";
	private static final String DELETAR_USUARIO = "UPDATE tbUsuario set statusUsuario = 'INATIVO' where codUsuario = ?";
	
	private Connection conexao = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;
	
	public void cadastrarUsuario(Usuario usuario) throws ExceptionDao {
		try {
			String query = CADASTRAR_USUARIO;
			conexao = ModuloConexao.conector(); // abre conexao
			preparedStatement = conexao.prepareStatement(query); // passa o comando sql como argumento
			preparedStatement.setString(2, usuario.getNomeUsuario());
			preparedStatement.setString(3, usuario.getCpfUsuario());
			preparedStatement.setDate(4, new Date(usuario.getDataNascimentoUsuario().getTime())); // a data do java e do sql são diferentes, por isso é necessário criar uma nova variável com os padrões sql e posteriormente utilizar getTIme para converter em um tipo long
			preparedStatement.setDouble(5, usuario.getSalarioUsuario());
			preparedStatement.setString(6, usuario.getEmailUsuario());
			preparedStatement.setString(7, usuario.getSenhaUsuario());
			preparedStatement.setString(8, usuario.getPerfilUsuario());
			preparedStatement.setString(9, usuario.getStatusUsuario());
			preparedStatement.executeUpdate(); // atualiza o banco de dados
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao Cadastrar o Usuario: " + e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
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
	public static ArrayList<Usuario> listarUsuarios() {
		String query = LISTAR_USUARIOS;
		Connection conexao = ModuloConexao.conector();
		
		ArrayList<Usuario> usuarios = new ArrayList<>();
		
		try {
			Statement pst = conexao.createStatement();
			conexao.prepareStatement(query);
			rs = pst.executeQuery(query);
			
			while(rs.next()) {
				usuarios.add(new Usuario(rs.getInt("codUsuario"), 
						rs.getString("nomeUsuario"), 
						rs.getString("cpfUsuario"),
						rs.getString("dataNascimentoUsuario"), 
						rs.getDouble("salarioUsuario"), 
						rs.getString("emailUsuario"), 
						rs.getString("perfilUsuario"), 
						rs.getString("statusUsuario")));
			};
			
		} catch (SQLException e) {
			e.printStackTrace();
			usuarios = null;
		}
		return usuarios;
	}
	
	public static Usuario consultarUsuarioByCPF(String cpfUsuario) throws ExceptionDao {
		String query = CONSULTAR_USUARIO_BY_CPF;
		Connection conexao = ModuloConexao.conector();
		Usuario usuario = null;

		try {
			preparedStatement = conexao.prepareStatement(query);
			rs = preparedStatement.executeQuery(query);
			
			int i = 1;
			preparedStatement.setString(i++, cpfUsuario);
			
			while(rs.next()) {
				usuario = new Usuario(rs.getInt("codUsuario"), 
							rs.getString("nomeUsuario"), 
							rs.getString("cpfUsuario"),
							rs.getString("dataNascimentoUsuario"), 
							rs.getDouble("salarioUsuario"), 
							rs.getString("emailUsuario"), 
							rs.getString("perfilUsuario"), 
							rs.getString("statusUsuario"));
			};
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ModuloConexao.fecharConexao();
		}
		if(usuario == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível encontrar este usuário ", "", JOptionPane.WARNING_MESSAGE);
			throw new ExceptionDao("Não foi possivel localizar o cliente selecionado");
		}
		return usuario;
	}
	public void alterarUsuario(int codUsuario, Usuario usuario) throws ExceptionDao {
		try {
			String query = ALTERAR_USUARIO;
			conexao = ModuloConexao.conector(); // abre conexao
			preparedStatement = conexao.prepareStatement(query); // passa o comando sql como argumento
			preparedStatement.setString(2, usuario.getNomeUsuario());
			preparedStatement.setString(3, usuario.getCpfUsuario());
			preparedStatement.setDate(4, new Date(usuario.getDataNascimentoUsuario().getTime())); // a data do java e do sql são diferentes, por isso é necessário criar uma nova variável com os padrões sql e posteriormente utilizar getTIme para converter em um tipo long
			preparedStatement.setDouble(5, usuario.getSalarioUsuario());
			preparedStatement.setString(6, usuario.getEmailUsuario());
			preparedStatement.setString(7, usuario.getSenhaUsuario());
			preparedStatement.setString(8, usuario.getPerfilUsuario());
			preparedStatement.setString(9, usuario.getStatusUsuario());
			preparedStatement.executeUpdate(); // atualiza o banco de dados
			
			JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao alterar o Usuario: " + e);
		} finally {
			ModuloConexao.fecharConexao();
		}
	}
	public void deletarUsuario(int codUsuario) throws ExceptionDao {
		try {
			String query = DELETAR_USUARIO;
			conexao = ModuloConexao.conector(); // abre conexao
			preparedStatement = conexao.prepareStatement(query); // passa o comando sql como argumento
			preparedStatement.setInt(9, codUsuario);
			preparedStatement.executeUpdate(); // atualiza o banco de dados
			
			JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao alterar o Usuario: " + e);
		} finally {
			ModuloConexao.fecharConexao();
		}
	}
	
}
