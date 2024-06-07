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
	private static final String ALTERAR_USUARIO = "UPDATE tbUsuario set nomeUsuario = ?, cpfUsuario = ?, dataNascimentoUsuario = ?, salarioUsuario = ?, emailUsuario = ?, senhaUsuario = ?, perfilUsuario = ?, statusUsuario = ? where cpfUsuario = ?";
	private static final String DELETAR_USUARIO = "UPDATE tbUsuario set statusUsuario = 'Inativo' where codUsuario = ?";
	private static final String LISTAR_BARBEIRO = "SELECT codUsuario, nomeUsuario FROM tbUsuario";
	private final String CONSULTAR_USUARIO_POR_NOME = "SELECT * FROM tbUsuario WHERE nomeUsuario = ?";
	private final String CONSULTAR_USUARIO_POR_ID = "SELECT * FROM tbUsuario WHERE codUsuario = ?";
	private Connection conexao = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;

	public void cadastrarUsuario(Usuario usuario) throws ExceptionDao {
		try {
			String query = CADASTRAR_USUARIO;
			conexao = ModuloConexao.conector(); // abre conexao
			preparedStatement = conexao.prepareStatement(query); // passa o comando sql como argumento
			preparedStatement.setString(1, usuario.getNomeUsuario());
			preparedStatement.setString(2, usuario.getCpfUsuario());
			preparedStatement.setString(3, usuario.converteDataTelaBanco(usuario.getDataNascimentoUsuario()));
			preparedStatement.setDouble(4, usuario.getSalarioUsuario());
			preparedStatement.setString(5, usuario.getEmailUsuario());
			preparedStatement.setString(6, usuario.getSenhaUsuario());
			preparedStatement.setString(7, usuario.getPerfilUsuario());
			preparedStatement.setString(8, usuario.getStatusUsuario());
			preparedStatement.executeUpdate(); // atualiza o banco de dados
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao Cadastrar o Usuario: " + e);
		} finally {
			ModuloConexao.fecharConexao();
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
		}finally {
			ModuloConexao.fecharConexao();
		}
		return usuarios;
	}
	
	public ArrayList<Usuario> selectAllUsuarios() throws ExceptionDao {
        String query = LISTAR_BARBEIRO;
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (Connection conexao = ModuloConexao.conector();
             PreparedStatement pst = conexao.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCodUsuario(rs.getInt("codUsuario"));
                usuario.setNomeUsuario(rs.getString("nomeUsuario"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new ExceptionDao("Erro ao buscar todos os usuários: " + e);
        }
        return usuarios;
    }


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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ModuloConexao.fecharConexao();
		}
		if (usuario == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível encontrar este usuário ", "",
					JOptionPane.WARNING_MESSAGE);
			throw new ExceptionDao("Não foi possivel localizar o cliente selecionado");
		}
		return usuario;
	}

	public void alterarUsuario(String cpfUsuario, Usuario usuario) throws ExceptionDao {
		try {
			String query = ALTERAR_USUARIO;
			conexao = ModuloConexao.conector(); // abre conexao
			preparedStatement = conexao.prepareStatement(query); // passa o comando sql como argumento
			preparedStatement.setString(1, usuario.getNomeUsuario());
			preparedStatement.setString(2, usuario.getCpfUsuario());
			preparedStatement.setString(3, usuario.converteDataTelaBanco(usuario.getDataNascimentoUsuario()));
			preparedStatement.setDouble(4, usuario.getSalarioUsuario());
			preparedStatement.setString(5, usuario.getEmailUsuario());
			preparedStatement.setString(6, usuario.getSenhaUsuario());
			preparedStatement.setString(7, usuario.getPerfilUsuario());
			preparedStatement.setString(8, usuario.getStatusUsuario());
			preparedStatement.setString(9, cpfUsuario);
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
			preparedStatement.setInt(1, codUsuario);
			preparedStatement.executeUpdate(); // atualiza o banco de dados

			JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao alterar o Usuario: " + e);
		} finally {
			ModuloConexao.fecharConexao();
		}
	}

	public int buscarCodigoUsuarioPorNome(String nomeUsuario) throws SQLException {
	    int codigoUsuario = -1; // Valor padrão para indicar que não foi encontrado

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

    
    public Usuario buscarUsuarioPorId(int codUsuario) throws SQLException {
        Usuario usuario = null;
        try (Connection conexao = ModuloConexao.conector();
             PreparedStatement preparedStatement = conexao.prepareStatement(CONSULTAR_USUARIO_POR_ID)) {
            preparedStatement.setInt(1, codUsuario);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setCodUsuario(rs.getInt("codUsuario"));
                usuario.setNomeUsuario(rs.getString("nomeUsuario"));
                // Preencha outros campos conforme necessário
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar usuário por ID: " + e.getMessage());
        }
        return usuario;
    }
}
