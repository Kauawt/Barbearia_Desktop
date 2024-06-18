	package model;

import javax.swing.JOptionPane;

import dao.ClienteDao;
import dao.ExceptionDao;
import dao.UsuarioDao;
import model.Validador;
/**
 * Classe que representa um cliente no sistema.
 * Armazena informações como código identificador, nome, endereço, telefone, CPF e status do cliente.
 */
public class Cliente {
	private int codCliente;
	private String nomeCliente;
	private String enderecoCliente;
	private String telefoneCliente;
	private String cpfCliente;
	private String statusCliente;
	
	/**
     * Construtor utilizado para inicializar um cliente com todas as informações.
     * @param codCliente Código identificador único do cliente
     * @param nomeCliente Nome do cliente
     * @param enderecoCliente Endereço do cliente
     * @param telefoneCliente Número de telefone do cliente
     * @param cpfCliente CPF do cliente
     * @param statusCliente Status do cliente
     */
	public Cliente(int codCliente, String nomeCliente, String enderecoCliente, String telefoneCliente, String cpfCliente,String statusCliente) {
		this.codCliente = codCliente;
		this.nomeCliente = nomeCliente;
		this.enderecoCliente = enderecoCliente;
		this.telefoneCliente = telefoneCliente;
		this.cpfCliente = cpfCliente;
		this.statusCliente = statusCliente;
	}
	 /**
     * Construtor utilizado para inicializar um cliente sem o código identificador.
     * @param nomeCliente Nome do cliente
     * @param enderecoCliente Endereço do cliente
     * @param telefoneCliente Número de telefone do cliente
     * @param cpfCliente CPF do cliente
     * @param statusCliente Status do cliente
     */

	public Cliente(String nomeCliente, String enderecoCliente, String telefoneCliente, String cpfCliente,String statusCliente) {
		this.nomeCliente = nomeCliente;
		this.enderecoCliente = enderecoCliente;
		this.telefoneCliente = telefoneCliente;
		this.cpfCliente = cpfCliente;
		this.statusCliente = statusCliente;
	}
	/**
     * Construtor utilizado para inicializar um cliente com código identificador e nome.
     * @param codCliente Código identificador único do cliente
     * @param nomeCliente Nome do cliente
     * @param cpfCliente CPF do cliente
     */
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
	/**
	 * Método para cadastrar um novo cliente utilizando o ClienteDao.
	 * @param cliente Objeto do tipo Cliente contendo as informações do cliente a ser cadastrado
	 * @throws ExceptionDao Exceção lançada em caso de erro ao acessar ou manipular os dados no banco
	 */
	public void cadastrarCliente(Cliente cliente) throws ExceptionDao {
		
		new ClienteDao().cadastrarCliente(cliente);
	}
	/**
	 * Método para alterar as informações de um cliente existente utilizando o ClienteDao.
	 * @param cliente Objeto do tipo Cliente com as novas informações a serem atualizadas
	 * @throws ExceptionDao Exceção lançada em caso de erro ao acessar ou manipular os dados no banco
	 */
	public void alterarCliente(Cliente cliente) throws ExceptionDao {
		new ClienteDao().alterarCliente(cpfCliente, cliente);
	}
	
}
