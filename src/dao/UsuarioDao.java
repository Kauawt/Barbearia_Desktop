package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Criptografia;
import model.Cliente;
import model.Usuario;
import dao.ExceptionDao;
/**
 * Classe responsável por realizar operações de acesso a dados relacionadas a usuários no banco de dados.
 * Implementa métodos para inserção, consulta, atualização e exclusão de dados de usuários.
 */
public class UsuarioDao {

	private final String CADASTRAR_USUARIO = "insert into tbUsuario(nomeUsuario,cpfUsuario,dataNascimentoUsuario,salarioUsuario,emailUsuario,senhaUsuario,perfilUsuario,statusUsuario) values (?,?,?,?,?,?,?,?)";

	private static final String LISTAR_USUARIOS = "select * from tbUsuario where statusUsuario = 'Ativo'";
	private static final String CONSULTAR_USUARIO_BY_CPF = "select * from tbUsuario where cpfUsuario = ?";
	private static final String ALTERAR_USUARIO = "UPDATE tbUsuario set nomeUsuario = ?, cpfUsuario = ?, dataNascimentoUsuario = ?, salarioUsuario = ?, emailUsuario = ?, senhaUsuario = ?, perfilUsuario = ?, statusUsuario = ? where codUsuario = ?";
	private static final String DELETAR_USUARIO = "UPDATE tbUsuario set statusUsuario = 'Inativo' where codUsuario = ?";
	private static final String LISTAR_BARBEIRO = "SELECT codUsuario, nomeUsuario FROM tbUsuario";
	private final static String CONSULTAR_USUARIO_POR_NOME = "SELECT * FROM tbUsuario WHERE nomeUsuario = ?";
	private final static String CONSULTAR_USUARIO_POR_ID = "SELECT * FROM tbUsuario WHERE codUsuario = ?";
	private Connection conexao = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;

	/**
     * Método para cadastrar um usuário na base de dados.
     * 
     * @param usuario O objeto Usuario a ser cadastrado.
     * @throws ExceptionDao Se ocorrer um erro durante a execução da operação de cadastro.
     */
	public void cadastrarUsuario(Usuario usuario) throws ExceptionDao {
		try {
			String query = CADASTRAR_USUARIO;
			conexao = ModuloConexao.conector(); // abre conexao
			preparedStatement = conexao.prepareStatement(query); // passa o comando sql como argumento
			Criptografia criptografia = new Criptografia(usuario.getSenhaUsuario(), Criptografia.MD5);

			preparedStatement.setString(1, usuario.getNomeUsuario());
			preparedStatement.setString(2, usuario.getCpfUsuario());
			preparedStatement.setString(3, usuario.converteDataTelaBanco(usuario.getDataNascimentoUsuario()));
			preparedStatement.setDouble(4, usuario.getSalarioUsuario());
			preparedStatement.setString(5, usuario.getEmailUsuario());
			preparedStatement.setString(6, criptografia.criptografar().toString());
			preparedStatement.setString(7, usuario.getPerfilUsuario());
			preparedStatement.setString(8, usuario.getStatusUsuario());
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao Cadastrar o Usuario: " + e);
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				System.err.println("Erro ao fechar o PreparedStatement: " + e);
			}
		}
		ModuloConexao.fecharConexao();

	}
	/**
     * Método para listar todos os usuários cadastrados no sistema.
     * 
     * @return Uma lista contendo todos os usuários cadastrados.
     */
	public static ArrayList<Usuario> listarUsuarios() {
		String query = LISTAR_USUARIOS;
		Connection conexao = ModuloConexao.conector();

		ArrayList<Usuario> usuarios = new ArrayList<>();

		try {
			Statement pst = conexao.createStatement();
			conexao.prepareStatement(query);
			rs = pst.executeQuery(query);

			while (rs.next()) {
				usuarios.add(new Usuario(rs.getInt("codUsuario"), rs.getString("nomeUsuario"),
						rs.getString("cpfUsuario"), rs.getString("dataNascimentoUsuario"),
						rs.getDouble("salarioUsuario"), rs.getString("emailUsuario"), rs.getString("perfilUsuario"),
						rs.getString("statusUsuario")));
			}
			;

		} catch (SQLException e) {
			e.printStackTrace();
			usuarios = null;
		} finally {
			ModuloConexao.fecharConexao();
		}
		return usuarios;
	}
	/**
     * Método para buscar todos os usuários do sistema.
     * 
     * @return Uma lista de objetos Usuario contendo todos os usuários cadastrados.
     * @throws ExceptionDao Se ocorrer um erro durante a execução da operação de busca.
     */

	public ArrayList<Usuario> selectAllUsuarios() throws SQLException {
	    ArrayList<Usuario> usuarios = new ArrayList<>();
	    String sql = "SELECT codUsuario, nomeUsuario FROM tbUsuario";

	    try (Connection conexao = ModuloConexao.conector();
	         PreparedStatement stmt = conexao.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Usuario usuario = new Usuario();
	            usuario.setCodUsuario(rs.getInt("codUsuario"));
	            usuario.setNomeUsuario(rs.getString("nomeUsuario"));
	            usuarios.add(usuario);
	        }
	    } catch (SQLException e) {
	        throw new SQLException("Erro ao buscar todos os usuários: " + e.getMessage());
	    }

	    return usuarios;
	}

	/**
     * Método para consultar um usuário pelo CPF.
     * 
     * @param cpfUsuario O CPF do usuário a ser consultado.
     * @return O objeto Usuario correspondente ao CPF fornecido.
     * @throws ExceptionDao Se ocorrer um erro durante a execução da operação de consulta.
     */
	public static Usuario consultarUsuarioByCPF(String cpfUsuario) throws ExceptionDao {
		String query = CONSULTAR_USUARIO_BY_CPF;
		Connection conexao = ModuloConexao.conector();
		Usuario usuario = null;

		try {
			preparedStatement = conexao.prepareStatement(query);

			int i = 1;
			preparedStatement.setString(i++, cpfUsuario);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				usuario = new Usuario(rs.getInt("codUsuario"), rs.getString("nomeUsuario"), rs.getString("cpfUsuario"),
						rs.getString("dataNascimentoUsuario"), rs.getDouble("salarioUsuario"),
						rs.getString("emailUsuario"), rs.getString("senhaUsuario"), rs.getString("perfilUsuario"),
						rs.getString("statusUsuario"));
			}
			;
			System.out.println(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ModuloConexao.fecharConexao();
		}
		if (usuario == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível encontrar este usuário ", "",
					JOptionPane.WARNING_MESSAGE);
			throw new ExceptionDao("Não foi possivel localizar o usuário selecionado");
		}
		return usuario;
	}
	/**
     * Método para alterar um usuário na base de dados.
     * 
     * @param codUsuario O código do usuário a ser alterado.
     * @param usuario O objeto Usuario com os novos dados a serem atualizados.
     * @throws ExceptionDao Se ocorrer um erro durante a execução da operação de alteração.
     */
	public void alterarUsuario(int codUsuario, Usuario usuario) throws ExceptionDao {
		try {
			String query = ALTERAR_USUARIO;
			conexao = ModuloConexao.conector(); // abre conexao
			preparedStatement = conexao.prepareStatement(query); // passa o comando sql como argumento
			Criptografia criptografia = new Criptografia(usuario.getSenhaUsuario(), Criptografia.MD5);

			preparedStatement.setString(1, usuario.getNomeUsuario());
			preparedStatement.setString(2, usuario.getCpfUsuario());
			preparedStatement.setString(3, usuario.converteDataTelaBanco(usuario.getDataNascimentoUsuario()));
			preparedStatement.setDouble(4, usuario.getSalarioUsuario());
			preparedStatement.setString(5, usuario.getEmailUsuario());
			preparedStatement.setString(6, criptografia.criptografar().toString());
			preparedStatement.setString(7, usuario.getPerfilUsuario());
			preparedStatement.setString(8, usuario.getStatusUsuario());
			preparedStatement.setInt(9, usuario.getCodUsuario());
			preparedStatement.executeUpdate(); // atualiza o banco de dados
			System.out.println(usuario);
			JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao alterar o Usuario: " + e);
		} finally {
			ModuloConexao.fecharConexao();
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				System.err.println("Erro ao fechar o PreparedStatement: " + e);
			}
		}
	}
	   /**
     * Método para deletar um usuário da base de dados.
     * 
     * @param codUsuario O código do usuário a ser deletado.
     * @throws ExceptionDao Se ocorrer um erro durante a execução da operação de exclusão.
     */
	public void deletarUsuario(int codUsuario) throws ExceptionDao {
		try {
			String query = DELETAR_USUARIO;
			conexao = ModuloConexao.conector(); // abre conexao
			preparedStatement = conexao.prepareStatement(query); // passa o comando sql como argumento
			preparedStatement.setInt(1, codUsuario);
			preparedStatement.executeUpdate(); // atualiza o banco de dados

			JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao alterar o Usuario: " + e);
		} finally {
			ModuloConexao.fecharConexao();
		}
	} 
	/**
     * Método para buscar o código de um usuário pelo seu nome.
     * 
     * @param nomeUsuario O nome do usuário a ser buscado.
     * @return O código do usuário, ou -1 se não for encontrado.
     * @throws SQLException Se ocorrer um erro durante a execução da operação de busca.
     */

	public static int buscarCodigoUsuarioPorNome(String nomeUsuario) throws SQLException {
	    int codigoUsuario = -1; 

		try (Connection conexao = ModuloConexao.conector();
				PreparedStatement preparedStatement = conexao.prepareStatement(CONSULTAR_USUARIO_POR_NOME)) {
			preparedStatement.setString(1, nomeUsuario);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				codigoUsuario = rs.getInt("codUsuario");
			}
		} catch (SQLException e) {
			throw new SQLException("Erro ao buscar código do usuário por nome: " + e.getMessage());
		}

		return codigoUsuario;
	}
	   /**
     * Método para buscar um usuário pelo seu ID.
     * 
     * @param codUsuario O ID do usuário a ser buscado.
     * @return O objeto Usuario correspondente ao ID fornecido.
     * @throws SQLException Se ocorrer um erro durante a execução da operação de busca.
 */
	public static Usuario buscarUsuarioPorId(int codUsuario) throws SQLException {
		Usuario usuario = null;
		try (Connection conexao = ModuloConexao.conector();
				PreparedStatement preparedStatement = conexao.prepareStatement(CONSULTAR_USUARIO_POR_ID)) {
			preparedStatement.setInt(1, codUsuario);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setCodUsuario(rs.getInt("codUsuario"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
			}
		} catch (SQLException e) {
			throw new SQLException("Erro ao buscar usuário por ID: " + e.getMessage());
		}
		return usuario;
	}
}
