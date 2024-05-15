package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import dao.ExceptionDao;
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
	private String statusUsuario;

	public Usuario(int codUsuario, String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario,
			double salarioUsuario, String emailUsuario, String senhaUsuario, String perfilUsuario,
			String statusUsuario) {
		if (codUsuario > 0 && nomeUsuario != null
				&& (Validador.validadorCpf(cpfUsuario) && Validador.validadorDataNascimento(dataNascimentoUsuario)
						&& salarioUsuario > 0 && Validador.validadorEmail(emailUsuario) && senhaUsuario != null
						&& senhaUsuario.length() > 0 && perfilUsuario != null
						&& (Validador.validadorStatus(statusUsuario)))) {
			this.codUsuario = codUsuario;
			this.nomeUsuario = nomeUsuario;
			this.cpfUsuario = cpfUsuario;
			try {
				this.dataNascimentoUsuario = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimentoUsuario); // converte String em Date
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.salarioUsuario = salarioUsuario;
			this.emailUsuario = emailUsuario;
			this.senhaUsuario = senhaUsuario;
			this.perfilUsuario = perfilUsuario;
			this.statusUsuario = statusUsuario;
			JOptionPane.showMessageDialog(null, "Usuário Cadastrado com sucesso!");
		} else if (codUsuario < 0) {
			JOptionPane.showMessageDialog(null, "Código do Usuário Inválido!");
		} else if (Validador.validadorCpf(cpfUsuario) == false) {
			JOptionPane.showMessageDialog(null, "CPF do Usuário Inválido");
		} else if (Validador.validadorDataNascimento(dataNascimentoUsuario) == false) {
			JOptionPane.showMessageDialog(null, "O Usuário deve Possuir Mais de 18 Anos e Menos de 100 Anos");
		} else if (Validador.validadorEmail(emailUsuario) == false) {
			JOptionPane.showMessageDialog(null,
					"O email do Usuário deve possuir um dos seguintes domínios: @gmail.com // @hotmail.com // outlook.com // yahoo.com // @terra.com // @icloud.com ");
		}
		else if(salarioUsuario<0) {
			JOptionPane.showMessageDialog(null, "O Salário do Usuário não pode ser negativo");
		}
		else if(Validador.validadorStatus(statusUsuario)==false) {
			JOptionPane.showMessageDialog(null, "O usuário deve ser cadastrado como ativo");
		}
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
}
