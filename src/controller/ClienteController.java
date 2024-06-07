package controller;

import java.text.ParseException;

import javax.swing.JOptionPane;

import controller.helper.ClienteHelper;
import view.TelaCliente;
import model.Cliente;
import model.Usuario;
import dao.ClienteDao;
import dao.ExceptionDao;
import model.Cliente;

public class ClienteController {
	private final TelaCliente telaCliente;
	private ClienteHelper clienteHelper;
	private static ClienteDao clienteDao;

	public ClienteController(TelaCliente telaCliente) {
		this.telaCliente = telaCliente;
		this.clienteHelper = new ClienteHelper(telaCliente);
	}

	/*
	 * Utiliza o Helper para construir um objeto a partir das informações dos campos
	 * da TelaCliente, instância esse objeto em um variavel(cliente), posteriormente
	 * passa este objeto como parâmetro para o método cadastrarCliente da ClasseDao
	 * (Responsável por puxar dados do BD)
	 */
	public void cadastrarCliente() {
		Cliente cliente = clienteHelper.validadorCamposTelaCliente();
		try {
			cliente.cadastrarCliente(cliente);
			clienteHelper.limparTelaCliente();
		} catch (ExceptionDao e) {
			e.printStackTrace();
		}
	}

	/*
	 * Utiliza o Helper para construir um objeto a partir das informações dos campos
	 * da TelaCliente, instância esse objeto em um variavel(cliente), posteriormente
	 * passa este objeto como parâmetro para o método alterarCliente da ClasseDao
	 * (Responsável por alterar dados do BD)
	 */
	public void alterarCliente() {
		Cliente cliente = clienteHelper.validadorCamposTelaCliente(); 
		try {
			cliente.alterarCliente(cliente);
			clienteHelper.limparTelaCliente();
		} catch (ExceptionDao e) {
			e.printStackTrace();
		}
	}
	public static int buscarCodigoClientePorCPF(String cpfCliente) throws ExceptionDao {
	    Cliente cliente = clienteDao.consultarClientePorCPF(cpfCliente);
	    if (cliente != null) {
	        return cliente.getCodCliente();
	    } else {
	        return -1;
	    }
	}
	
}

