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



public class AgendaDao {

	private final String CADASTRAR_AGENDAMENTO = "insert into tbAgendamento(codUsuario,codCliente,codServico,precoServico,dataAgendamento,horaAtendimento) values (?,?,?,?,?,?)";
	private static final String ALTERAR_AGENDAMENTO = "UPDATE tbAgendamento SET codServico = ?, precoServico = ?, dataAgendamento = ?, horaAtendimento = ? WHERE codCliente = ?";
	private static final String DELETAR_AGENDAMENTO = "DELETE FROM tbAgendamento WHERE cpfCliente = ? AND dataAgendamento = ?";
	private static final String LISTAR_AGENDAMENTOS = "select * from tbAgendamento";
    private final String BUSCAR_AGENDAMENTO_POR_ID = "select * from tbAgendamento where codAgendamento = ?";
    private static final String CONSULTAR_HORARIOS_MARCADOS = "SELECT horaAtendimento FROM tbAgendamento WHERE dataAgendamento = ?";
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

	        // Atribuir os IDs diretamente, sem verificar se o objeto é nulo
	        preparedStatement.setInt(1, agendamento.getCodUsuario());
	        preparedStatement.setInt(2, agendamento.getCodCliente());
	        preparedStatement.setInt(3, agendamento.getCodServico());
	        preparedStatement.setFloat(4, Float.parseFloat(agendamento.getPrecoServico()));
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
	    String query = "SELECT a.codAgendamento, a.dataAgendamento, a.horaAtendimento, " +
	               "c.codCliente, c.nomeCliente, c.cpfCliente, " +
	               "u.codUsuario, u.nomeUsuario, " +
	               "s.codServico, s.tipoServico, s.precoServico " +
	               "FROM tbAgendamento a " +
	               "INNER JOIN tbCliente c ON a.codCliente = c.codCliente " +
	               "INNER JOIN tbUsuario u ON a.codUsuario = u.codUsuario " +
	               "INNER JOIN tbServico s ON a.codServico = s.codServico";
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
     * Atualiza um agendamento no banco de dados.
     * @param agendamento O agendamento atualizado.
     * @throws ExceptionDao Se ocorrer algum erro durante a atualização.
     */
	public void atualizarAgendamento(Agendamento agendamento) throws ExceptionDao {
	    try {
	        String query = ALTERAR_AGENDAMENTO;
	        conexao = ModuloConexao.conector();
	        preparedStatement = conexao.prepareStatement(query);
	        preparedStatement.setInt(1, agendamento.getCodServico());
	        preparedStatement.setFloat(2, Float.parseFloat(agendamento.getPrecoServico()));
	        preparedStatement.setString(3, agendamento.getDataAtendimento());
	        preparedStatement.setString(4, agendamento.getHoraAtendimento());
	        preparedStatement.setInt(5, agendamento.getCodCliente());
	        preparedStatement.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Agendamento atualizado com sucesso!!");
	    } catch (SQLException e) {
	        throw new ExceptionDao("Erro ao atualizar o agendamento: " + e.getMessage());
	    } finally {
	        ModuloConexao.fecharConexao();
	    }
	}
	
	 /**
     * Deleta um agendamento do banco de dados.
     * @param codCliente      O código do cliente do agendamento.
     * @param dataAgendamento A data do agendamento a ser cancelado.
     * @throws ExceptionDao   Se ocorrer algum erro durante a exclusão.
     */

	public void deletarAgendamento(int codCliente, String dataAgendamento) throws ExceptionDao {
	    try {
	        String query = "DELETE FROM tbAgendamento WHERE codCliente = ? AND dataAgendamento = ?";
	        conexao = ModuloConexao.conector();
	        preparedStatement = conexao.prepareStatement(query);
	        preparedStatement.setInt(1, codCliente);
	        preparedStatement.setString(2, dataAgendamento);
	        preparedStatement.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Agendamento cancelado com sucesso!!");
	    } catch (SQLException e) {
	        throw new ExceptionDao("Erro ao cancelar o agendamento: " + e);
	    } finally {
	        ModuloConexao.fecharConexao();
	    }
	}
	/**
     * Lista todos os serviços cadastrados no banco de dados.
     * @return Uma lista de serviços.
     */
	public static ArrayList<Servico> listarServicos() {
	    String query = "SELECT DISTINCT codServico, tipoServico, precoServico FROM tbServico";
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
	
}
