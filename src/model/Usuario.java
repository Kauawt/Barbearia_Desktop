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

public class Usuario {
	private int codUsuario;
	private String nomeUsuario;
	private String cpfUsuario;
	private String dataNascimentoUsuario;
	private double salarioUsuario;
	private String emailUsuario;
	private String senhaUsuario;
	private String perfilUsuario;
	private String statusUsuario;

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
	
	public Usuario(int codUsuario, String nomeUsuario) {
	    this.codUsuario = codUsuario;
	    this.nomeUsuario = nomeUsuario;
	}

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

	public void cadastrarUsuario(Usuario usuario) throws ExceptionDao {
		new UsuarioDao().cadastrarUsuario(usuario);
	}

	public void alterarUsuario(Usuario usuario) throws ExceptionDao {
		new UsuarioDao().alterarUsuario(cpfUsuario, usuario);
	}

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

	
	@Override
	public String toString() {
	    return getNomeUsuario();
	}
	
}


