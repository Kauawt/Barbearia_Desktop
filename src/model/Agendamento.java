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

	// Construtor vazio
	public Agendamento() {}

	// Construtor utilizado no método formatarDadosAgendamento
	public Agendamento(int codUsuario, int codCliente, int codServico, double precoServico, String dataAtendimento, String horaAtendimento) {
	    this.codUsuario = codUsuario;
	    this.codCliente = codCliente;
	    this.codServico = codServico;
	    this.precoServico = precoServico;
	    this.dataAtendimento = dataAtendimento;
	    this.horaAtendimento = horaAtendimento;
	}

	// Construtor usado no ListarAgendamento
    public Agendamento(int codAgendamento, Servico servico, Cliente cliente, Usuario usuario, LocalDate dataAtendimento, LocalTime horaAtendimento) {
        this.codAgendamento = codAgendamento;
        this.servico = servico;
        this.cliente = cliente;
        this.usuario = usuario;
        this.dataAtendimento = dataAtendimento.toString();
        this.horaAtendimento = horaAtendimento.toString();
    }
   
	 public Agendamento(int codAgendamento, int codCliente, int codUsuario, int codServico, double precoServico, String dataAtendimento, String horaAtendimento) {
	        this.codAgendamento = codAgendamento;
	        this.codCliente = codCliente;
	        this.codUsuario = codUsuario;
	        this.codServico = codServico;
	        this.precoServico = precoServico;
	        this.dataAtendimento = dataAtendimento;
	        this.horaAtendimento = horaAtendimento;
	    }
	 

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
	
	public void cadastrarAgendamento(Agendamento agendamento) throws ExceptionDao {
		
		new AgendaDao().cadastrarAgendamento(agendamento);
	}




}
