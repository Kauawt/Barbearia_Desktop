package controller;

import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;

import controller.helper.AgendaHelper;
import dao.AgendaDao;
import dao.ExceptionDao;
import dao.ServicoDao;
import dao.UsuarioDao;
import model.Agendamento;
import model.Servico;
import view.TelaAgendamentoPanel;


/**
 * Controlador responsável pela lógica de agendamento de serviços.
 * Coordena a interação entre a interface de usuário, os dados do sistema e a lógica de negócios.
 */
public class AgendamentoController {
	private final AgendaHelper helper;
	private TelaAgendamentoPanel view;
	private AgendaDao agendaDao;
	private UsuarioDao usuarioDao;
	 private final int TEMPO_PADRAO_CORTE = 30;
	

	 /**
	     * Construtor da classe AgendamentoController.
	     *
	     * @param telaAgendamentoPanel O painel de tela associado a este controlador.
	     */
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
	    if (servico == null) {
	    } else {
	        helper.setarValor(servico.getPrecoServico());
	    }
	}
	
	
	/**
	 * Cancela um agendamento existente através do código de agendamento fornecido.
	 * Chama o método deletarAgendamento do AgendaDao para realizar a operação de exclusão.
	 * @param codAgendamento O código do agendamento a ser cancelado.
	 * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados durante a exclusão.
	 */
	public void cancelarAgendamento(int codAgendamento) throws ExceptionDao {
	    try {
	        agendaDao.deletarAgendamento(codAgendamento);
	    } catch (ExceptionDao e) {  
	        e.printStackTrace();
	    }}
	
	/**
	 * Cadastra um novo agendamento com os dados fornecidos da View TelaAgendamentoPanel.
	 * Cria um objeto Agendamento com os parâmetros especificados e chama o método cadastrarAgendamento
	 * do AgendaDao para persistir o agendamento no banco de dados.
	 * @param codUsuario O código do usuário responsável pelo agendamento.
	 * @param codCliente O código do cliente associado ao agendamento.
	 * @param codServico O código do serviço a ser realizado no agendamento.
	 * @param precoServico O preço do serviço agendado.
	 * @param dataAtendimento A data do agendamento no formato "dd/MM/yyyy".
	 * @param horaAtendimento A hora do agendamento no formato "HH:mm".
	 * @throws SQLException Se ocorrer um erro SQL ao acessar o banco de dados.
	 * @throws ExceptionDao Se ocorrer um erro geral ao acessar o banco de dados durante o cadastro.
	 */
    public void cadastrarAgendamento(int codUsuario, int codCliente, int codServico, double precoServico, String dataAtendimento, String horaAtendimento) throws SQLException, ExceptionDao {
    	Agendamento agendamento = new Agendamento(codUsuario, codCliente, codServico, precoServico, dataAtendimento, horaAtendimento);
        agendaDao.cadastrarAgendamento(agendamento);
    }
    /**
     * Atualiza um agendamento existente com os novos dados fornecidos.
     * Cria um objeto Agendamento com os parâmetros especificados e chama o método atualizarAgendamento
     * do AgendaDao para persistir as alterações no banco de dados.
     * Exibe uma mensagem de sucesso após a atualização.
     * @param codUsuario O código do usuário responsável pelo agendamento.
     * @param codCliente O código do cliente associado ao agendamento.
     * @param codServico O código do serviço a ser realizado no agendamento.
     * @param precoServico O preço do serviço agendado.
     * @param dataAtendimento A data do agendamento no formato "dd/MM/yyyy".
     * @param horaAtendimento A hora do agendamento no formato "HH:mm".
     * @param codAgendamento O código do agendamento a ser atualizado.
     * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados durante a atualização.
     * @throws SQLException Se ocorrer um erro SQL ao acessar o banco de dados.
     */
    public void atualizarAgendamento(int codUsuario, int codCliente, int codServico, double precoServico, String dataAtendimento, String horaAtendimento, int codAgendamento) throws ExceptionDao, SQLException {
    	Agendamento agendamento = new Agendamento(codUsuario, codCliente, codServico, precoServico, dataAtendimento, horaAtendimento);

		agendaDao.atualizarAgendamento(agendamento, codAgendamento);
    }

    /**
     * Retorna a carga horária disponível para um barbeiro em um determinado dia da semana, exceto domingo (de acordo com a Regra de Negócio definida).
     *
     * @param data A data para a qual se deseja verificar a carga horária.
     * @return ArrayList contendo os horários disponíveis para agendamento.
     */
    private ArrayList<LocalTime> cargaHorariaBarbeiro(LocalDate data) {
        ArrayList<LocalTime> cargaHoraria = new ArrayList<>();
        LocalTime horarioInicio = LocalTime.of(8, 0); // Horário de início da jornada
        LocalTime horarioFim = LocalTime.of(19, 0); // Horário de término da jornada
        DayOfWeek diaDaSemana = data.getDayOfWeek();
        if (diaDaSemana != DayOfWeek.SUNDAY) {        // Verifica se é de segunda a sábado
            while (horarioInicio.isBefore(horarioFim)) {
                cargaHoraria.add(horarioInicio);
                horarioInicio = horarioInicio.plusMinutes(30); // 30 minutos padrão para o tempo do corte
            }}
        return cargaHoraria;
        }
    /**
     * Retorna uma lista de horários disponíveis para agendamento de um barbeiro em uma data específica.
     * @param codBarbeiro ID do barbeiro para consulta dos horários disponíveis.
     * @param dataAgendamento Data para a qual se deseja verificar os horários disponíveis no formato "dd/MM/yyyy".
     * @return ArrayList contendo os horários disponíveis para agendamento.
     */
    public ArrayList<LocalTime> obterHorariosDisponiveis(int codBarbeiro, String dataAgendamento) {
        try {
            ArrayList<LocalTime> horariosMarcados = null;
            try {
                horariosMarcados = agendaDao.consultarHorariosMarcados(codBarbeiro, dataAgendamento);
            } catch (ClassCastException e) {
                JOptionPane.showMessageDialog(null, "Erro ao consultar horários marcados: formato de dados inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                return new ArrayList<>();}
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataAgendamentoFormatada = LocalDate.parse(dataAgendamento, formatter);
            if (dataAgendamentoFormatada.getDayOfWeek() == DayOfWeek.SUNDAY) {
                JOptionPane.showMessageDialog(null, "Não é possível agendar no Domingo.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return new ArrayList<>();}
            ArrayList<LocalTime> cargaHoraria = cargaHorariaBarbeiro(dataAgendamentoFormatada);
            Set<LocalTime> horariosMarcadosSet = new HashSet<>(horariosMarcados);
            ArrayList<LocalTime> horariosDisponiveis = new ArrayList<>();
            LocalTime horarioAtual = LocalTime.now();
            LocalDate hoje = LocalDate.now();
            if (dataAgendamentoFormatada.isBefore(hoje)) {
                JOptionPane.showMessageDialog(null, "Data de agendamento não pode ser anterior ao dia de hoje.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return new ArrayList<>();}
            if (dataAgendamentoFormatada.equals(hoje)) {
                LocalTime horarioInicioExpediente = horarioAtual.plusMinutes(30 - (horarioAtual.getMinute() % 30));
                for (LocalTime horario : cargaHoraria) {
                    if (horario.isAfter(horarioAtual) && horario.isAfter(horarioInicioExpediente) && !horariosMarcadosSet.contains(horario)) {
                        horariosDisponiveis.add(horario);
                    }}
            } else {
                for (LocalTime horario : cargaHoraria) {
                    if (!horariosMarcadosSet.contains(horario)) {
                        horariosDisponiveis.add(horario);
                    }}}
            if (horariosDisponiveis.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não possuem mais horários disponíveis para hoje.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            return horariosDisponiveis;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return new ArrayList<>();
        }}
}