package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Servico;
/**
 * Classe responsável por realizar operações de acesso a dados relacionadas a serviços no banco de dados.
 * Implementa métodos para inserção, consulta, atualização e exclusão de dados de serviços.
 */
public class ServicoDao {
private final String CADASTRAR_SERVICO = "insert into tbServico(tipoServico,descricaoServico,precoServico,duracaoServico,statusServico) values (?,?,?,?,?)";

	private static final String LISTAR_SERVICOS = "select * from tbServico where statusServico = 'Ativo'";
	private static final String CONSULTAR_SERVICOS_POR_COD = "select * from tbServico where codServico = ?";
	private static final String ALTERAR_SERVICO = "UPDATE tbServico set tipoServico = ?, descricaoServico = ?, precoServico = ?, duracaoServico = ?, statusServico = ? where codServico = ?";
	private static final String DELETAR_SERVICO = "UPDATE tbServico set statusServico = 'Inativo' where codServico = ?";

	private Connection conexao = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;
	private static final String CONSULTAR_SERVICO_POR_NOME = "select codServico from tbServico where tipoServico = ?";
	
	 /**
     * Método para cadastrar um novo serviço no banco de dados.
     * 
     * @param servico O objeto Servico contendo os dados do serviço a ser cadastrado.
     * @throws ExceptionDao Se ocorrer um erro durante a execução da operação de cadastro.
     */
	public void cadastrarServico(Servico servico) throws ExceptionDao {
		try {
			String query = CADASTRAR_SERVICO;
			conexao = ModuloConexao.conector(); // abre conexao
			pst = conexao.prepareStatement(query); // passa o comando sql como argumento
			pst.setString(1, servico.getTipoServico());
			pst.setString(2, servico.getDescricaoServico());
			pst.setDouble(3, servico.getPrecoServico());
			pst.setDouble(4, servico.getDuracaoServico());
			pst.setString(5, servico.getStatusServico());
			pst.executeUpdate(); // atualiza o banco de dados
			JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!");
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao Cadastrar o Serviço: " + e);
		} finally {
			ModuloConexao.fecharConexao();
		}
	}
	 /**
     * Método para listar todos os serviços cadastrados no banco de dados.
     * 
     * @return Uma lista contendo todos os serviços cadastrados.
     */

	public static ArrayList<Servico> listarServicos() {
		String query = LISTAR_SERVICOS;
		Connection conexao = ModuloConexao.conector();
		ArrayList<Servico> servicos = new ArrayList<>();

		try {
			Statement pst = conexao.createStatement();
			conexao.prepareStatement(query);
			rs = pst.executeQuery(query);

			while (rs.next()) {
				servicos.add(new Servico(rs.getInt("codServico"), rs.getString("tipoServico"),
						rs.getString("descricaoServico"), rs.getDouble("precoServico"), rs.getDouble("duracaoServico"),
						rs.getString("statusServico")));
			}
			;

		} catch (SQLException e) {
			e.printStackTrace();
			servicos = null;
		} finally {
			ModuloConexao.fecharConexao();
		}
		return servicos;
	}

	/**
     * Método para consultar um serviço no banco de dados pelo seu código.
     * 
     * @param codServico O código do serviço a ser consultado.
     * @return O objeto Servico correspondente ao código fornecido, ou null se não for encontrado.
     * @throws ExceptionDao Se ocorrer um erro durante a execução da operação de consulta.
     */
	public static Servico consultarServicoPorCOD(int codServico) throws ExceptionDao {
		String query = CONSULTAR_SERVICOS_POR_COD;
		Connection conexao = ModuloConexao.conector();
		Servico servico = null;

		try {
			pst = conexao.prepareStatement(query);

			int i = 1;
			pst.setInt(i++, codServico);
			rs = pst.executeQuery();
			while (rs.next()) {
				servico = new Servico(rs.getInt("codServico"), rs.getString("tipoServico"),
						rs.getString("descricaoServico"), rs.getDouble("precoServico"), rs.getDouble("duracaoServico"),
						rs.getString("statusServico"));
			}
			;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ModuloConexao.fecharConexao();
		}
		return servico;
	}
	 /**
     * Método para alterar os dados de um serviço no banco de dados.
     * 
     * @param codServico O código do serviço a ser alterado.
     * @param servico O objeto Servico contendo os novos dados do serviço.
     * @throws ExceptionDao Se ocorrer um erro durante a execução da operação de alteração.
     */
	public void alterarServico(int codServico, Servico servico) throws ExceptionDao {
		try {
			String query = ALTERAR_SERVICO;
			conexao = ModuloConexao.conector(); // abre conexao
			pst = conexao.prepareStatement(query); // passa o comando sql como argumento
			pst.setString(1, servico.getTipoServico());
			pst.setString(2, servico.getDescricaoServico());
			pst.setDouble(3, servico.getPrecoServico());
			pst.setDouble(4, servico.getDuracaoServico());
			pst.setString(5, servico.getStatusServico());
			pst.setInt(6,servico.getCodServico());
			pst.executeUpdate(); // atualiza o banco de dados
			JOptionPane.showMessageDialog(null, "Serviço alterado com sucesso!");
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao alterar o Serviço: " + e);
		} finally {
			ModuloConexao.fecharConexao();
		}
	}
	/**
     * Método para deletar um serviço do banco de dados.
     * 
     * @param codServico O código do serviço a ser deletado.
     * @throws ExceptionDao Se ocorrer um erro durante a execução da operação de exclusão.
     */
	public void deletarServico(int codServico) throws ExceptionDao {
		try {
			String query = DELETAR_SERVICO;
			conexao = ModuloConexao.conector(); // abre conexao
			pst = conexao.prepareStatement(query); // passa o comando sql como argumento
			pst.setInt(1, codServico);
			pst.executeUpdate(); // atualiza o banco de dados

			JOptionPane.showMessageDialog(null, "Serviço deletado com sucesso!");
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao alterar o Serviço: " + e);
		} finally {
			ModuloConexao.fecharConexao();
		}
	}
	  /**
     * Método para buscar todos os serviços cadastrados no banco de dados.
     * 
     * @return Uma lista contendo todos os serviços cadastrados.
     * @throws ExceptionDao Se ocorrer um erro durante a execução da operação de busca.
     */
	 public ArrayList<Servico> selectAll() throws ExceptionDao {
	        String sql = "SELECT codServico, tipoServico, precoServico FROM tbServico";
	        ArrayList<Servico> servicos = new ArrayList<>();

	        try (Connection conexao = ModuloConexao.conector();
	             PreparedStatement pst = conexao.prepareStatement(sql);
	             ResultSet rs = pst.executeQuery()) {

	            while (rs.next()) {
	                Servico servico = new Servico(
	                    rs.getInt("codServico"),
	                    rs.getString("tipoServico"),
	                    rs.getDouble("precoServico")
	                );
	                servicos.add(servico);
	            }
	        } catch (SQLException e) {
	            throw new ExceptionDao("Erro ao buscar todos os serviços: " + e);
	        }
	        return servicos;
	    }
	 /**
	     * Método para buscar o código de um serviço pelo seu nome.
	     * 
	     * @param nomeServico O nome do serviço a ser buscado.
	     * @return O código do serviço, ou -1 se não for encontrado.
	     * @throws ExceptionDao Se ocorrer um erro durante a execução da operação de busca.
	     */
	 public static int buscarCodigoServicoPorNome(String nomeServico) throws ExceptionDao {
	        int codServico = -1;
	        String query = CONSULTAR_SERVICO_POR_NOME;

	        try (Connection conexao = ModuloConexao.conector();
	             PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
	            preparedStatement.setString(1, nomeServico);
	            ResultSet rs = preparedStatement.executeQuery();
	            
	            if (rs.next()) {
	                codServico = rs.getInt("codServico");
	            }
	        } catch (SQLException e) {
	            throw new ExceptionDao("Erro ao buscar código do serviço por nome: " + e.getMessage());
	        }
	        
	        return codServico;
	    }
}
