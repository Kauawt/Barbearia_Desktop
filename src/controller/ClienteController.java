package controller;

import javax.swing.JOptionPane;

import dao.ExceptionDao;
import model.Cliente;

public class ClienteController {

	public void cadastarCliente(int codCliente, String nomeCliente, String enderecoCliente, String telefoneCliente, String cpfCliente, String statusCliente) throws ExceptionDao {
		try {
			Cliente cliente = new Cliente(codCliente, nomeCliente, enderecoCliente, telefoneCliente, cpfCliente,
					statusCliente);
			cliente.cadastrarCliente(cliente);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e + "Não foi possível Instânciar o Cliente:/");
		}
	}
}
