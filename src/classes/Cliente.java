package classes;

public class Cliente {
	private int codCliente;
	private String nomeCliente;
	private String endereco;
	private String telefone;
	private String cpf;
	
	public Cliente(int codCliente, String nomeCliente, String endereco, String telefone, String cpf) {
		this.codCliente = codCliente;
		this.nomeCliente = nomeCliente;
		this.endereco = endereco;
		this.telefone = telefone;
		this.cpf = cpf;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
	

}
