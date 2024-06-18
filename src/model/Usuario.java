package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JOptionPane;

import dao.ExceptionDao;
import dao.UsuarioDao;
/**
 * A classe Usuario representa um usuário do sistema, com atributos como código, nome, CPF,
 * data de nascimento, salário, email, senha, perfil e status.
 */
public class Usuario {
	protected int codUsuario;
	private String nomeUsuario;
	protected String cpfUsuario;
	private String dataNascimentoUsuario;
	private double salarioUsuario;
	private String emailUsuario;
	private String senhaUsuario;
	private String perfilUsuario;
	private String statusUsuario;
	/**
     * Construtor para criar um novo usuário com todos os atributos.
     * @param nomeUsuario Nome do usuário
     * @param cpfUsuario CPF do usuário
     * @param dataNascimentoUsuario Data de nascimento do usuário
     * @param salarioUsuario Salário do usuário
     * @param emailUsuario Email do usuário
     * @param senhaUsuario Senha do usuário
     * @param perfilUsuario Perfil do usuário
     * @param statusUsuario Status do usuário
     */
	public Usuario(String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario, double salarioUsuario,
			String emailUsuario, String senhaUsuario, String perfilUsuario, String statusUsuario) {

		this.nomeUsuario = nomeUsuario;
		this.cpfUsuario = cpfUsuario;
		this.dataNascimentoUsuario = dataNascimentoUsuario;
		this.salarioUsuario = salarioUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
		this.perfilUsuario = perfilUsuario;
		this.statusUsuario = statusUsuario;

	}
	/**
     * Construtor para criar um usuário com todos os atributos, incluindo o código.
     * @param codUsuario Código do usuário
     * @param nomeUsuario Nome do usuário
     * @param cpfUsuario CPF do usuário
     * @param dataNascimentoUsuario Data de nascimento do usuário
     * @param salarioUsuario Salário do usuário
     * @param emailUsuario Email do usuário
     * @param perfilUsuario Perfil do usuário
     * @param statusUsuario Status do usuário
     */
	public Usuario(int codUsuario, String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario,
			double salarioUsuario, String emailUsuario, String perfilUsuario, String statusUsuario) {

		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.cpfUsuario = cpfUsuario;
		this.dataNascimentoUsuario = dataNascimentoUsuario;
		this.salarioUsuario = salarioUsuario;
		this.emailUsuario = emailUsuario;
		this.perfilUsuario = perfilUsuario;
		this.statusUsuario = statusUsuario;
	}
	 /**
     * Construtor para criar um usuário com todos os atributos, incluindo o código e a senha.
     * @param codUsuario Código do usuário
     * @param nomeUsuario Nome do usuário
     * @param cpfUsuario CPF do usuário
     * @param dataNascimentoUsuario Data de nascimento do usuário
     * @param salarioUsuario Salário do usuário
     * @param emailUsuario Email do usuário
     * @param senhaUsuario Senha do usuário
     * @param perfilUsuario Perfil do usuário
     * @param statusUsuario Status do usuário
     */
	public Usuario(int codUsuario, String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario,
			double salarioUsuario, String emailUsuario, String senhaUsuario, String perfilUsuario,
			String statusUsuario) {

		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.cpfUsuario = cpfUsuario;
		this.dataNascimentoUsuario = dataNascimentoUsuario;
		this.salarioUsuario = salarioUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
		this.perfilUsuario = perfilUsuario;
		this.statusUsuario = statusUsuario;
	}
	 /**
     * Construtor para criar um usuário apenas com código e nome.
     * @param codUsuario Código do usuário
     * @param nomeUsuario Nome do usuário
     */
	public Usuario(int codUsuario, String nomeUsuario) {
	    this.codUsuario = codUsuario;
	    this.nomeUsuario = nomeUsuario;
	}
	/**
     * Construtor vazio.
     */
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}
	

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getDataNascimentoUsuario() {
		return dataNascimentoUsuario;
	}

	public void setDataNascimentoUsuario(String dataNascimentoUsuario) {
		this.dataNascimentoUsuario = dataNascimentoUsuario;
	}

	public double getSalarioUsuario() {
		return salarioUsuario;
	}

	public void setSalarioUsuario(double salarioUsuario) {
		this.salarioUsuario = salarioUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(String perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public String getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(String statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	/**
     * Converte uma data do formato do banco de dados (yyyy-MM-dd) para o formato de tela (dd/MM/yyyy).
     * @param dataString Data no formato do banco de dados (yyyy-MM-dd)
     * @return Data convertida para o formato de tela (dd/MM/yyyy)
     */
	public String converteDataBancoTela(String dataString) {
		SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
		Date data = null;

		try {
			data = formatoOriginal.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");
		return formatoDesejado.format(data);
	}
	/**
     * Converte uma data do formato de tela (dd/MM/yyyy) para o formato do banco de dados (yyyy-MM-dd).
     * @param dataString Data no formato de tela (dd/MM/yyyy)
     * @return Data convertida para o formato do banco de dados (yyyy-MM-dd)
     */
	public String converteDataTelaBanco(String dataString) {
		SimpleDateFormat formatoOriginal = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		try {
			data = formatoOriginal.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat formatoDesejado = new SimpleDateFormat("yyyy-MM-dd");

		return formatoDesejado.format(data);
	}

	/**
     * Sobrescrita do método toString() para retornar o nome do usuário.
     * @return Nome do usuário
     */
	@Override
	public String toString() {
	    return getNomeUsuario();
	}
	
}


