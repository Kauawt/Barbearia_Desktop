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
import javax.swing.JOptionPane;
import model.Agendamento;
import model.Usuario;
import model.Cliente;
import model.Servico;



public class AgendaDao {

	private final String CADASTRAR_AGENDAMENTO = "insert into tbAgendamento(codAgendamento,codUsuario,codCliente,codServico,precoServico,dataAgendamento,horaAtendimento) values (?,?,?,?,?,?)";
	private static final String LISTAR_AGENDAMENTOS = "select * from tbAgendamento";
	private Connection conexao = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;
	
	public void cadastrarAgendamento(Agendamento agendamento) throws ExceptionDao {
		
		try {
			String query = CADASTRAR_AGENDAMENTO;
			conexao = ModuloConexao.conector(); // abre conexao
			preparedStatement  = conexao.prepareStatement(query); // passa o comando sql como argumento
			preparedStatement .setInt(1, agendamento.getCodAtendimento());  // atribui o valor do model a linha SQL
			preparedStatement .setInt(2, agendamento.getUsuario().getCodUsuario()); // 
			preparedStatement .setInt(3, agendamento.getCliente().getCodCliente());
			preparedStatement .setInt(4, agendamento.getServico().getCodServico());
			preparedStatement .setDouble(5, agendamento.getServico().getPrecoServico());
			preparedStatement .setDate(6, java.sql.Date.valueOf(agendamento.getDataAtendimento())); //
			preparedStatement .setTime(7, java.sql.Time.valueOf(agendamento.getHoraAtendimento())); // 
            preparedStatement .executeUpdate();
			JOptionPane.showMessageDialog(null, "Agendamento Realizado com Sucesso!!" );
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao Cadastrar o Usuario: " + e);
		} finally {
			ModuloConexao.fecharConexao();
		}
	}
	
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