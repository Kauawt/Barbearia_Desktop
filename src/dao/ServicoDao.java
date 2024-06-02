package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Servico;

public class ServicoDao {

	private final String CADASTRAR_SERVICO = "insert into tbServico(tipoServico,descricaoServico,precoServico,duracaoServico,statusServico) values (?,?,?,?,?)";

	private static final String LISTAR_SERVICOS = "select * from tbServico";
	private static final String CONSULTAR_SERVICOS_POR_COD = "select * from tbServico where codServico = ?";
	private static final String ALTERAR_SERVICO = "UPDATE tbServico set tipoServico = ?, descricaoServico = ?, precoServico = ?, duracaoServico = ?, statusServico = ? where codServico = ?";
	private static final String DELETAR_SERVICO = "UPDATE tbServico set statusServico = 'Inativo' where codServico = ?";

	private Connection conexao = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

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
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao Cadastrar o Serviço: " + e);
		} finally {
			ModuloConexao.fecharConexao();
		}
	}

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

	public ArrayList<Servico> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
