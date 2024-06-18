package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.AgendaDao;
import dao.ClienteDao;
import dao.ExceptionDao;
import dao.UsuarioDao;
/**
 * Classe que representa um agendamento de serviço.
 * Armazena informações sobre o agendamento, incluindo o serviço, cliente, usuário responsável,
 * data e hora do atendimento, código do serviço, código do cliente, código do usuário e preço do serviço.
 */
public class Agendamento {
	private int codAgendamento;
	private Servico servico; // Objeto representando o serviço
    private Cliente cliente; // Objeto representando o cliente
    private Usuario usuario; // Objeto representando o usuário
    private String dataAtendimento; // Objeto representando a data do atendimento
    private String horaAtendimento; // Objeto representando o horário do atendimento
	private int codServico;
	private int codCliente;
	private int codUsuario;
	private double precoServico;

	/**
     * Construtor vazio da classe Agendamento.
     */
	public Agendamento() {}

	/**
     * Construtor utilizado para formatar dados de agendamento.
     * Usado no método formatarDadosAgendamento.
     *
     * @param codUsuario Código do usuário responsável pelo agendamento
     * @param codCliente Código do cliente agendado
     * @param codServico Código do serviço agendado
     * @param precoServico Preço do serviço agendado
     * @param dataAtendimento Data do atendimento
     * @param horaAtendimento Hora do atendimento
     */
	public Agendamento(int codUsuario, int codCliente, int codServico, double precoServico, String dataAtendimento, String horaAtendimento) {
	    this.codUsuario = codUsuario;
	    this.codCliente = codCliente;
	    this.codServico = codServico;
	    this.precoServico = precoServico;
	    this.dataAtendimento = dataAtendimento;
	    this.horaAtendimento = horaAtendimento;
	}

	/**
     * Construtor utilizado para listar agendamentos.
     * Usado no método ListarAgendamento.
     *
     * @param codAgendamento Identificador único do agendamento
     * @param servico Objeto representando o serviço agendado
     * @param cliente Objeto representando o cliente agendado
     * @param usuario Objeto representando o usuário responsável pelo agendamento
     * @param dataAtendimento Data do atendimento
     * @param horaAtendimento Hora do atendimento
     */
    public Agendamento(int codAgendamento, Servico servico, Cliente cliente, Usuario usuario, LocalDate dataAtendimento, LocalTime horaAtendimento) {
        this.codAgendamento = codAgendamento;
        this.servico = servico;
        this.cliente = cliente;
        this.usuario = usuario;
        this.dataAtendimento = dataAtendimento.toString();
        this.horaAtendimento = horaAtendimento.toString();
    }
    /**
     * Construtor para a Tela Consulta Agendamento.
     * @param codAgendamento
     * @param codCliente
     * @param codUsuario
     * @param codServico
     * @param precoServico
     * @param dataAtendimento
     * @param horaAtendimento
     */
	 public Agendamento(int codAgendamento, int codCliente, int codUsuario, int codServico, double precoServico, String dataAtendimento, String horaAtendimento) {
	        this.codAgendamento = codAgendamento;
	        this.codCliente = codCliente;
	        this.codUsuario = codUsuario;
	        this.codServico = codServico;
	        this.precoServico = precoServico;
	        this.dataAtendimento = dataAtendimento;
	        this.horaAtendimento = horaAtendimento;
	    }
	 
	 /**
     * Retorna uma representação em string do objeto Agendamento.
     * @return Uma string contendo informações detalhadas sobre o agendamento.
     */
	@Override
	public String toString() {
		return "Agendamento [codAgendamento=" + codAgendamento + ", servico=" + servico + ", cliente=" + cliente
				+ ", usuario=" + usuario + ", dataAtendimento=" + dataAtendimento + ", horaAtendimento="
				+ horaAtendimento + ", codServico=" + codServico + ", codCliente=" + codCliente + ", codUsuario="
				+ codUsuario + ", precoServico=" + precoServico + "]";
	}

	public double getPrecoServico() {
		return precoServico;
	}

	public void setPrecoServico(double precoServico) {
		this.precoServico = precoServico;
	}
	
	public int getCodServico() {
		return codServico;
	}

	public void setCodServico(int codServico) {
		this.codServico = codServico;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario2) {
		this.codUsuario = codUsuario2;
	}

	public int getCodAgendamento() {
		return codAgendamento;
	}
	public void setCodAgendamento(int codAgendamento) {
		this.codAgendamento = codAgendamento;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public String getNomeCliente() {
		return cliente.getNomeCliente();
	}
	
	public String getCpfCliente() {
		return cliente.getCpfCliente();
	}
	
	public String getNomeUsuario() {
        return usuario.getNomeUsuario(); 
    }
	public String getHoraAtendimento() {
		return horaAtendimento;
	}
	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
		
	}
	public String getDataAtendimento() {
		return dataAtendimento;
	}
	public void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}
	/**
	 * Método para cadastrar um novo agendamento utilizando o AgendaDao.
	 * @param agendamento Objeto do tipo Agendamento contendo as informações do agendamento a ser cadastrado
	 * @throws ExceptionDao Exceção lançada em caso de erro ao acessar ou manipular os dados no banco
	 */
	public void cadastrarAgendamento(Agendamento agendamento) throws ExceptionDao {
		
		new AgendaDao().cadastrarAgendamento(agendamento);
	}




}
