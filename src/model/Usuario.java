package model;

import java.util.Date;

import dao.ExceptionDao;
import dao.ServicoDao;
import dao.UsuarioDao;

public class Usuario {
    private int codUsuario;
    private String nomeUsuario;
    private String cpfUsuario;
    private Date dataNascimentoUsuario;
    private double salarioUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String perfilUsuario;
    
   public Usuario(int codUsuario, String nomeUsuario, String cpfUsuario, Date dataNascimentoUsuario,
			double salarioUsuario, String emailUsuario, String senhaUsuario, String perfilUsuario) {
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.cpfUsuario = cpfUsuario;
		this.dataNascimentoUsuario = dataNascimentoUsuario;
		this.salarioUsuario = salarioUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
		this.perfilUsuario = perfilUsuario;
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



	public Date getDataNascimentoUsuario() {
		return dataNascimentoUsuario;
	}



	public void setDataNascimentoUsuario(Date dataNascimentoUsuario) {
		this.dataNascimentoUsuario = dataNascimentoUsuario;
	}



	public double getSalarioUsuario() {
		return salarioUsuario;
	}



	public void setSalarioUsuario(double salarioUsuario) {
		this.salarioUsuario = salarioUsuario;
	}



	public String getUserUsuario() {
		return emailUsuario;
	}



	public void setUserUsuario(String userUsuario) {
		this.emailUsuario = userUsuario;
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



public void cadastrarUsuario(Usuario usuario) throws ExceptionDao {
	new UsuarioDao().cadastrarUsuario(usuario);
   }
}
