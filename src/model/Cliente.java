package model;

import javax.swing.JOptionPane;

import dao.ClienteDao;
import dao.ExceptionDao;
import dao.UsuarioDao;
import model.Validador;
public class Cliente {
	private int codCliente;
	private String nomeCliente;
	private String enderecoCliente;
	private String telefoneCliente;
	private String cpfCliente;
	private String statusCliente;
	
	public Cliente(int codCliente, String nomeCliente, String enderecoCliente, String telefoneCliente, String cpfCliente,String statusCliente) {
		this.codCliente = codCliente;
		this.nomeCliente = nomeCliente;
		this.enderecoCliente = enderecoCliente;
		this.telefoneCliente = telefoneCliente;
		this.cpfCliente = cpfCliente;
		this.statusCliente = statusCliente;
	}

	public Cliente(String nomeCliente, String enderecoCliente, String telefoneCliente, String cpfCliente,String statusCliente) {
		this.nomeCliente = nomeCliente;
		this.enderecoCliente = enderecoCliente;
		this.telefoneCliente = telefoneCliente;
		this.cpfCliente = cpfCliente;
		this.statusCliente = statusCliente;
	}
	
	public Cliente(int codCliente, String nomeCliente, String cpfCliente) {
	    this.codCliente = codCliente;
	    this.nomeCliente = nomeCliente;
	    this.cpfCliente = cpfCliente;
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
	
	public void alterarCliente(Cliente cliente) throws ExceptionDao {
		new ClienteDao().alterarCliente(cpfCliente, cliente);
	}
	
}
