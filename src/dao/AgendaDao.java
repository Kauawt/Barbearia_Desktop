package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import model.Agendamento;
import model.Usuario;
import view.PlaceholderTextField;
import model.Cliente;
import model.Servico;
/**
 * Classe responsável por realizar operações de acesso a dados relacionadas a agendamentos no banco de dados.
 * Implementa métodos para inserção, consulta, atualização e exclusão de dados de agendamentos.
 */

public class AgendaDao {

	private final String CADASTRAR_AGENDAMENTO = "insert into tbAgendamento(codUsuario,codCliente,codServico,precoServico,dataAgendamento,horaAtendimento) values (?,?,?,?,?,?)";
	private static final String ALTERAR_AGENDAMENTO = "UPDATE tbAgendamento SET codUsuario = ?, codCliente = ?, codServico = ?,  precoServico = ?, dataAgendamento = ?, horaAtendimento = ? " +
            "WHERE codAgendamento = ?";
	private static final String DELETAR_AGENDAMENTO = "DELETE FROM tbAgendamento WHERE codAgendamento = ?";	
    private static final String LISTAR_AGENDAMENTOS = "SELECT a.codAgendamento, a.dataAgendamento, a.horaAtendimento, " +
											            "c.codCliente, c.nomeCliente, c.cpfCliente, " +
											            "u.codUsuario, u.nomeUsuario, " +
											            "s.codServico, s.tipoServico, s.precoServico " +
											            "FROM tbAgendamento a " +
											            "INNER JOIN tbCliente c ON a.codCliente = c.codCliente " +
											            "INNER JOIN tbUsuario u ON a.codUsuario = u.codUsuario " +
											            "INNER JOIN tbServico s ON a.codServico = s.codServico";
    private static final String LISTAR_SERVICOS = "SELECT DISTINCT codServico, tipoServico, precoServico FROM tbServico";
    private static final String BUSCAR_COD_AGENDAMENTO = "SELECT codAgendamento FROM tbAgendamento WHERE codCliente = ? AND codUsuario = ? AND codServico = ? AND dataAgendamento = ? AND horaAtendimento = ?";
    private static final String BUSCAR_AGENDAMENTO_POR_ID = LISTAR_AGENDAMENTOS + " WHERE a.codAgendamento = ?";
    private static final String CONSULTAR_HORARIOS = "SELECT horaAtendimento FROM tbAgendamento WHERE codUsuario = ? AND dataAgendamento = ?";
	private Connection conexao = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;

	/**
	 * Cadastra um novo agendamento no banco de dados.
	 * @param agendamento O agendamento a ser cadastrado.
	 * @throws ExceptionDao Se ocorrer algum erro durante o cadastro.
	 */
	public void cadastrarAgendamento(Agendamento agendamento) throws ExceptionDao {
		try {
			String query = CADASTRAR_AGENDAMENTO;
			conexao = ModuloConexao.conector();
			preparedStatement = conexao.prepareStatement(query);
			preparedStatement.setInt(1, agendamento.getCodUsuario());
			preparedStatement.setInt(2, agendamento.getCodCliente());
			preparedStatement.setInt(3, agendamento.getCodServico());
			preparedStatement.setDouble(4, agendamento.getPrecoServico());
			preparedStatement.setString(5, agendamento.getDataAtendimento());
			preparedStatement.setString(6, agendamento.getHoraAtendimento());
			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(null, "Agendamento realizado com sucesso!!");
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao cadastrar o agendamento: " + e.getMessage());
		} finally {
			ModuloConexao.fecharConexao();
		}
	}

	/**
	 * Lista todos os agendamentos cadastrados no banco de dados.
	 * @return Uma lista de agendamentos.
	 */
	public static ArrayList<Agendamento> listarAgendamentos() {
	    String query = LISTAR_AGENDAMENTOS;
	    Connection conexao = ModuloConexao.conector();
	    ArrayList<Agendamento> agendamentos = new ArrayList<>();
	    try {
	        PreparedStatement pst = conexao.prepareStatement(query);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	        	int codAtendimento = rs.getInt("codAgendamento");
	        	Cliente cliente = new Cliente(rs.getInt("codCliente"), rs.getString("nomeCliente"), rs.getString("cpfCliente"));
	        	Servico servico = new Servico(rs.getInt("codServico"), rs.getString("tipoServico"), rs.getDouble("precoServico"));
	        	Usuario usuario = new Usuario(rs.getInt("codUsuario"), rs.getString("nomeUsuario"));
        	    LocalDate dataAtendimento = rs.getDate("dataAgendamento").toLocalDate();
        	    LocalTime horaAtendimento = rs.getTime("horaAtendimento").toLocalTime();
        	    Agendamento agendamento = new Agendamento(codAtendimento, servico, cliente, usuario, dataAtendimento, horaAtendimento);
        	    agendamentos.add(agendamento);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        agendamentos = null;
	    } finally {
	        ModuloConexao.fecharConexao();
	    }
	    return agendamentos;
	}
	/**
	 * Deleta um agendamento do banco de dados.
	 * @param codAgendamento O código do agendamento a ser cancelado.
	 * @throws ExceptionDao Se ocorrer algum erro durante a exclusão.
	 */
	 
    public void deletarAgendamento(int codAgendamento) throws ExceptionDao {
        try {
            String query = DELETAR_AGENDAMENTO;
            conexao = ModuloConexao.conector();
            preparedStatement = conexao.prepareStatement(query);
            preparedStatement.setInt(1, codAgendamento);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Agendamento cancelado com sucesso!!");
        } catch (SQLException e) {
            throw new ExceptionDao("Erro ao cancelar o agendamento: " + e.getMessage());
        } finally {
            ModuloConexao.fecharConexao();
        }
    }

    /**
     * Atualiza um agendamento no banco de dados.
     * @param agendamento   O agendamento atualizado.
     * @param codAgendamento O código do agendamento a ser atualizado.
     * @throws ExceptionDao Se ocorrer algum erro durante a atualização.
     */

	public void atualizarAgendamento(Agendamento agendamento, int codAgendamento) throws ExceptionDao {
	    try {
	        String query = ALTERAR_AGENDAMENTO;
	        conexao = ModuloConexao.conector();
	        preparedStatement = conexao.prepareStatement(query);
	        preparedStatement.setInt(1, agendamento.getCodUsuario());
	        preparedStatement.setInt(2, agendamento.getCodCliente());
	        preparedStatement.setInt(3, agendamento.getCodServico());
	        preparedStatement.setDouble(4, agendamento.getPrecoServico());
	        preparedStatement.setString(5, agendamento.getDataAtendimento());
	        preparedStatement.setString(6, agendamento.getHoraAtendimento());
	        preparedStatement.setInt(7, codAgendamento);
	        preparedStatement.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Agendamento atualizado com sucesso!!");
	    } catch (SQLException e) {
	        throw new ExceptionDao("Erro ao atualizar o agendamento: " + e);
	    } finally {
	        ModuloConexao.fecharConexao();
	    }
	}

	/**
	 * Lista todos os serviços cadastrados no banco de dados.
	 * @return Uma lista de serviços.
	 */
	public static ArrayList<Servico> listarServicos() {
	    String query = LISTAR_SERVICOS;
	    Connection conexao = ModuloConexao.conector();
	    ArrayList<Servico> servicos = new ArrayList<>();
	    
	    try {
	        PreparedStatement pst = conexao.prepareStatement(query);
	        ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int codServico = rs.getInt("codServico");
				String tipoServico = rs.getString("tipoServico");
				double precoServico = rs.getDouble("precoServico");
				Servico servico = new Servico(codServico, tipoServico, precoServico);
				servicos.add(servico);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ModuloConexao.fecharConexao();
		}

		return servicos;
	}
	
	/**
	 * Consulta um agendamento por ID.
	 * @param codAgendamento O ID do agendamento a ser consultado.
	 * @return O agendamento encontrado ou null se não encontrado.
	 * @throws SQLException Se ocorrer algum erro durante a consulta.
	 */
   public static Agendamento consultarAgendamentoPorId(int codAgendamento) throws SQLException {
        String query = BUSCAR_AGENDAMENTO_POR_ID;
        Connection conexao = ModuloConexao.conector();
        Agendamento agendamento = null;

        try {
            PreparedStatement pst = conexao.prepareStatement(query);
            pst.setInt(1, codAgendamento);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int codAtendimento = rs.getInt("codAgendamento");
                Cliente cliente = new Cliente(rs.getInt("codCliente"), rs.getString("nomeCliente"), rs.getString("cpfCliente"));
                Servico servico = new Servico(rs.getInt("codServico"), rs.getString("tipoServico"), rs.getDouble("precoServico"));
                Usuario usuario = new Usuario(rs.getInt("codUsuario"), rs.getString("nomeUsuario"));
                LocalDate dataAtendimento = rs.getDate("dataAgendamento").toLocalDate();
                LocalTime horaAtendimento = rs.getTime("horaAtendimento").toLocalTime();
                agendamento = new Agendamento(codAtendimento, servico, cliente, usuario, dataAtendimento, horaAtendimento);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao consultar o agendamento por ID: " + e.getMessage());
        } finally {
            ModuloConexao.fecharConexao();
        }

        return agendamento;
    }
    
   /**
    * Consulta os horários marcados para um determinado barbeiro em uma data específica.
    * @param codBarbeiro O código do barbeiro.
    * @param dataAgendamento A data dos agendamentos.
    * @return Uma lista de horários marcados para o barbeiro na data especificada.
    * @throws SQLException Se ocorrer algum erro durante a consulta ao banco de dados.
    */
    public ArrayList<LocalTime> consultarHorariosMarcados(int codBarbeiro, String dataAgendamento) throws SQLException {
        ArrayList<LocalTime> horariosMarcados = new ArrayList<>();
        Connection conexao = ModuloConexao.conector();
        
        try {
            PreparedStatement pst = conexao.prepareStatement(CONSULTAR_HORARIOS);
            pst.setInt(1, codBarbeiro);
            pst.setString(2, dataAgendamento);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                LocalTime horaAtendimento = rs.getTime("horaAtendimento").toLocalTime();
                horariosMarcados.add(horaAtendimento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao consultar os horários marcados para o barbeiro: " + e.getMessage());
        } finally {
            ModuloConexao.fecharConexao();
        }
        
        return horariosMarcados;
    }
    /**
     * Busca o código de um agendamento no banco de dados com base nos parâmetros fornecidos.
     * @param codCliente O código do cliente do agendamento.
     * @param codUsuario O código do usuário responsável pelo agendamento.
     * @param codServico O código do serviço realizado no agendamento.
     * @param dataAtendimento A data do agendamento no formato "dd/MM/yyyy".
     * @param horaAtendimento A hora do agendamento no formato "HH:mm".
     * @return O código do agendamento encontrado ou -1 se não encontrado.
     * @throws SQLException Se ocorrer algum erro durante a busca no banco de dados.
     */

    public static int buscarCodAgendamento(int codCliente, int codUsuario, int codServico, String dataAtendimento, String horaAtendimento) throws SQLException {
        int codAgendamento = -1;

        try (Connection conexao = ModuloConexao.conector();
             PreparedStatement pst = conexao.prepareStatement(BUSCAR_COD_AGENDAMENTO)) {

            pst.setInt(1, codCliente);
            pst.setInt(2, codUsuario);
            pst.setInt(3, codServico);
            pst.setString(4, dataAtendimento);
            pst.setString(5, horaAtendimento);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    codAgendamento = rs.getInt("codAgendamento");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar o código do agendamento: " + e.getMessage(), e);
        }

        return codAgendamento;
    }

}
