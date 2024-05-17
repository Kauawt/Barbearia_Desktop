package model;

import javax.swing.JOptionPane;

import dao.ClienteDao;
import dao.ExceptionDao;
import model.Validador;
public class Cliente {
	private int codCliente;
	private String nomeCliente;
	private String enderecoCliente;
	private String telefoneCliente;
	private String cpfCliente;
	private String statusCliente;
	
	public Cliente(int codCliente, String nomeCliente, String enderecoCliente, String telefoneCliente, String cpfCliente,String statusCliente) {
		if(codCliente >0 && enderecoCliente != null && Validador.validadorTelefone(telefoneCliente) && Validador.validadorCpf(cpfCliente)  && Validador.validadorStatus(statusCliente)) {
			this.codCliente = codCliente;
			this.nomeCliente = nomeCliente;
			this.enderecoCliente = enderecoCliente;
			this.telefoneCliente = telefoneCliente;
			this.cpfCliente = cpfCliente;
			this.statusCliente = statusCliente;
			JOptionPane.showMessageDialog(null, "Cliente Cadastrados com Sucesso");
		}else if(codCliente<0) {
			JOptionPane.showMessageDialog(null, "Não é permitido ter valores negativos");
		}else if(Validador.validadorCpf(cpfCliente) == false) {
			JOptionPane.showMessageDialog(null, "CPF Inválido");
		}else if(Validador.validadorStatus(statusCliente)==false) {
			JOptionPane.showMessageDialog(null, "O Status deve ser Ativo durante a criação de um novo Cadastro");
				}
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getStatusCliente() {
		return statusCliente;
	}

	public void setStatusCliente(String statusCliente) {
		this.statusCliente = statusCliente;
	}
	
	public void cadastrarCliente(Cliente cliente) throws ExceptionDao {
		
		new ClienteDao().cadastrarCliente(cliente);
	}
}
