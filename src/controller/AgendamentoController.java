package controller;

import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;

import controller.helper.AgendaHelper;
import dao.AgendaDao;
import dao.ExceptionDao;
import dao.ServicoDao;
import dao.UsuarioDao;
import model.Agendamento;
import model.Servico;
import view.TelaAgendamento;
import view.TelaAgendamentoPanel;

public class AgendamentoController {
	private final AgendaHelper helper;
	private TelaAgendamentoPanel view;
	private AgendaDao agendaDao;
	private UsuarioDao usuarioDao;
	

	public AgendamentoController(TelaAgendamentoPanel telaAgendamentoPanel) {
	    this.view = telaAgendamentoPanel;
	    this.helper = new AgendaHelper(telaAgendamentoPanel);
	    this.agendaDao = new AgendaDao();
	    this.usuarioDao = new UsuarioDao();
	}
	
	/**
     * Atualiza a lista de serviços disponíveis na view.
     * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
     */
	public void atualizaServico() throws ExceptionDao {
	    ServicoDao servicoDao = new ServicoDao();
	    ArrayList<Servico> servicos = servicoDao.selectAll();
	    helper.preencherServicos(servicos);
	}
	
	/**
     * Atualiza o valor do serviço selecionado na view, tornando mais prático a seleção.
     */
	public void atualizaValor() {
		Servico servico = helper.obterServico();
		helper.setarValor(servico.getPrecoServico());
	}
	
	/**
     * Cancela um agendamento, chamando o AgendaDao.
     * 
     * @param codCliente O código do cliente do agendamento.
     * @param dataAgendamento A data do agendamento.
     * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
     */
	public void cancelarAgendamento(int codCliente, String dataAgendamento) throws ExceptionDao {
	    try {
	        agendaDao.deletarAgendamento(codCliente, dataAgendamento);
	    } catch (ExceptionDao e) {  
	        e.printStackTrace();
	    }
	}
	/**
     * Busca o código de um usuário pelo nome, método para converter os campos retornados da View em Inserts no Banco de Dados.
     * @param nomeUsuario O nome do usuário.
     * @return O código do usuário, ou -1 se não encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
	public int buscarCodigoUsuarioPorNome(String nomeUsuario) throws SQLException {
        return usuarioDao.buscarCodigoUsuarioPorNome(nomeUsuario);
    }	
	
	/**
     * Formata os dados vindos da View TelaAgendamento e valida os campos..
     * @param servico O serviço do agendamento.
     * @param cpfCliente O CPF do cliente.
     * @param nomeUsuario O nome do usuário.
     * @param dataAtendimento A data do atendimento.
     * @param horaAtendimento A hora do atendimento.
     * @param precoServico O preço do serviço.
     * @return O objeto Agendamento formatado, ou null se ocorrer um erro.
     * @throws ParseException Se ocorrer um erro ao converter os dados.
     * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
	public Agendamento formatarDadosAgendamento(Servico servico, String cpfCliente, String nomeUsuario,
	        String dataAtendimento, String horaAtendimento, String precoServico) throws ParseException, ExceptionDao, SQLException {

	    int codUsuario = usuarioDao.buscarCodigoUsuarioPorNome(nomeUsuario);
	    if (codUsuario == -1) {
	        JOptionPane.showMessageDialog(null, "O código do usuário não pôde ser encontrado para o nome de usuário fornecido.", "Aviso", JOptionPane.WARNING_MESSAGE);
	        return null; 
	    }

	    int codServico = ServicoDao.buscarCodigoServicoPorNome(servico.getTipoServico());
	    if (codServico == -1) {
	        JOptionPane.showMessageDialog(null, "O código do serviço não pôde ser encontrado para o tipo de serviço fornecido.", "Aviso", JOptionPane.WARNING_MESSAGE);
	        return null; 
	    }

	    int codCliente = ClienteController.buscarCodigoClientePorCPF(cpfCliente);
	    if (codCliente == -1) {
	        JOptionPane.showMessageDialog(null, "O código do cliente não pôde ser encontrado para o CPF fornecido.", "Aviso", JOptionPane.WARNING_MESSAGE);
	        return null;
	    }

	    // Validação do preço do serviço
	    float precoServicoFloat;
	    try {
	        precoServicoFloat = Float.parseFloat(precoServico);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Preço do serviço inválido: " + precoServico, "Aviso", JOptionPane.WARNING_MESSAGE);
	        return null; 
	    }

	    String dataAgendamento = dataAtendimento;
	    String horaAgendamento = horaAtendimento;

	    Agendamento agendamento = new Agendamento(codUsuario, codCliente, codServico, String.valueOf(precoServicoFloat), dataAgendamento, horaAgendamento);
	    agendamento.setCodUsuario(codUsuario);
	    agendamento.setCodCliente(codCliente);
	    agendamento.setCodServico(codServico);
	    agendamento.setPrecoServico(String.valueOf(precoServicoFloat));
	    agendamento.setDataAtendimento(dataAgendamento);
	    agendamento.setHoraAtendimento(horaAgendamento);
	    return agendamento;
	}

	/**
     *	Cadastrar um agendamento, chamando o método formatarDadosAgendamento com os atributos corretos para INSERT no Banco de dados.
     */
	public void cadastrarAgendamento(Servico servico, String cpfCliente, String nomeUsuario,
	        String dataAtendimento, String horaAtendimento, String precoServico) throws ParseException, ExceptionDao, SQLException {
		
	    Agendamento agendamento = formatarDadosAgendamento(servico, cpfCliente, nomeUsuario, dataAtendimento, horaAtendimento, precoServico);

	    // Chame o método cadastrarAgendamento do AgendamentoDao
	    agendaDao.cadastrarAgendamento(agendamento);
	}
	/**
     * Atualiza um agendamento, chamando o método formatarDadosAgendamento com os atributos corretos para UPDATE no Banco de dados.
     */
	public void atualizarAgendamento(Servico servico, String cpfCliente, String nomeUsuario,
	        String dataAtendimento, String horaAtendimento, String precoServico) throws ParseException, ExceptionDao, SQLException {

	    Agendamento agendamento = formatarDadosAgendamento(servico, cpfCliente, nomeUsuario, dataAtendimento, horaAtendimento, precoServico);

	    // Chame o método atualizarAgendamento do AgendamentoDao
	    agendaDao.atualizarAgendamento(agendamento);
	}


}
