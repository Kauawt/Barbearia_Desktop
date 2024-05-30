package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import dao.AgendaDao;
import dao.ExceptionDao;
import dao.UsuarioDao;

public class Agendamento {
	private int codAtendimento;
	private Servico servico; // Objeto representando o serviço
    private Cliente cliente; // Objeto representando o cliente
    private Usuario usuario; // Objeto representando o usuário
    private double valorTotal;
    private LocalDate dataAtendimento; // Objeto representando a data do atendimento
    private LocalTime horaAtendimento; // Objeto representando o horário do atendimento

    public Agendamento(int codAtendimento, Servico servico, Cliente cliente, Usuario usuario, double valorTotal,
                       LocalDate dataAtendimento, LocalTime horaAtendimento) {
        this.codAtendimento = codAtendimento;
        this.servico = servico;
        this.cliente = cliente;
        this.usuario = usuario;
        this.valorTotal = valorTotal;
        this.dataAtendimento = dataAtendimento;
        this.horaAtendimento = horaAtendimento;
        JOptionPane.showMessageDialog(null, "Agendamento realizado com Sucesso");
	}
	 
	public int getCodAtendimento() {
		return codAtendimento;
	}
	public void setCodAtendimento(int codAtendimento) {
		this.codAtendimento = codAtendimento;
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
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public LocalDate getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(LocalDate dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public LocalTime getHoraAtendimento() {
		return horaAtendimento;
	}

	public void setHoraAtendimento(LocalTime horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}

	public void cadastrarAgendamento(Agendamento agendamento) throws ExceptionDao {
		new AgendaDao().cadastrarAgendamento(agendamento);
	}

}
