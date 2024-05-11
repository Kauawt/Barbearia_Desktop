package controller;

import dao.ExceptionDao;
import model.Cliente;

public class ClienteController {
	
	public boolean validarCliente(int codCliente, String nomeCliente, String enderecoCliente, String telefoneCliente,
			String cpfCliente, String statusCliente) throws ExceptionDao {

		if (codCliente > 0 && (nomeCliente != null) && (nomeCliente.length() > 0) && (enderecoCliente != null)
				&& (enderecoCliente.length() > 0) && (telefoneCliente != null) && (telefoneCliente.length() > 0)
				&& (cpfCliente != null) && (cpfCliente.length() > 0) && (statusCliente != null)
				&& (statusCliente.length() > 0)) {
			Cliente cliente = new Cliente(codCliente, nomeCliente, enderecoCliente, telefoneCliente, cpfCliente,
					statusCliente);
			cliente.cadastrarCliente(cliente);
			return true;
		} else {
			return false;
		}
	}
		
	public boolean validarCpf(String cpfCliente) {
		for (int i = 0; i < cpfCliente.length(); i++) {
			if (!Character.isDigit(cpfCliente.charAt(i))) {
				if (!(i == 3 || i == 7 || i == 11)) {
					return false;
				}
			}
		}
		return true;
	}
		
	
	}
