package controller;

import javax.swing.JOptionPane;

import controller.helper.ClienteHelper;
import view.TelaCliente;
import model.Cliente;
import dao.ClienteDao;
import dao.ExceptionDao;
import model.Cliente;

public class ClienteController {
	private final TelaCliente telaCliente;
	private ClienteHelper clienteHelper;
	private ClienteDao clienteDao;
	
	public ClienteController(TelaCliente telaCliente){
		this.telaCliente = telaCliente;
		this.clienteHelper = new ClienteHelper(telaCliente);

	}
	
	/*
	 * Utiliza o Helper para construir um objeto a partir das informações dos campos da TelaCliente, instância esse objeto em um variavel(cliente), posteriormente
	 * passa este objeto como parâmetro para o método cadastrarCliente da ClasseDao (Responsável por puxar dados do BD)
	 */
	public void cadastrarCliente() {
			
			Cliente cliente = clienteHelper.obterModeloTelaCliente(); // pega o objeto criado a partir dos dados da telaCliente e instância em um novo objeto de mesmo tipo
		try {
			this.clienteDao = new ClienteDao();
			clienteDao.cadastrarCliente(cliente);
		} catch (ExceptionDao e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}


}
