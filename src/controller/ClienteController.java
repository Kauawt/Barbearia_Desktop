package controller;

import java.text.ParseException;

import javax.swing.JOptionPane;

import controller.helper.ClienteHelper;
import view.TelaClientePanel;
import model.Cliente;
import model.Usuario;
import dao.ClienteDao;
import dao.ExceptionDao;
import model.Cliente;

/**
 * Controlador responsável por gerenciar a lógica relacionada aos clientes.
 * Coordena a interação entre a interface de cliente (TelaClientePanel) e os dados do sistema.
 */
public class ClienteController {
	private final TelaClientePanel telaClientePanel;
	private ClienteHelper clienteHelper;
	private static ClienteDao clienteDao;

	 /**
     * Construtor da classe ClienteController.
     * @param telaClientePanel O painel de tela associado a este controlador.
     */
	public ClienteController(TelaClientePanel telaClientePanel) {
		this.telaClientePanel = telaClientePanel;
		this.clienteHelper = new ClienteHelper(telaClientePanel);
	}

	/**
	 * Utiliza o Helper para construir um objeto a partir das informações dos campos
	 * da TelaCliente, instância esse objeto em um variavel(cliente), posteriormente
	 * passa este objeto como parâmetro para o método cadastrarCliente da ClasseDao
	 * (Responsável por puxar dados do BD)
	 * @throws ExceptionDao 
	 */
	public void cadastrarCliente() throws ExceptionDao {
		Cliente cliente = clienteHelper.validadorCamposTelaCliente();
		try {
			cliente.cadastrarCliente(cliente);
			clienteHelper.limparTelaCliente();
		} catch (ExceptionDao e) {
			JOptionPane.showMessageDialog(null, e + "Não foi possível converter os dados captados");
			e.printStackTrace();
		}
	}

	/**
	 * Utiliza o Helper para construir um objeto a partir das informações dos campos
	 * da TelaCliente, instância esse objeto em um variavel(cliente), posteriormente
	 * passa este objeto como parâmetro para o método alterarCliente da ClasseDao
	 * (Responsável por alterar dados do BD)
	 * @throws ExceptionDao 
	 */
	public void alterarCliente() throws ExceptionDao {
		Cliente cliente = clienteHelper.validadorCamposTelaCliente(); 
		try {
			cliente.alterarCliente(cliente);
			clienteHelper.limparTelaCliente();
		} catch (ExceptionDao e) {
			JOptionPane.showMessageDialog(null, e + "Não foi possível converter os dados captados");
			e.printStackTrace();
		}
	}
	/**
	 * Busca o código do cliente no banco de dados a partir do CPF fornecido.
	 * @param cpfCliente CPF do cliente a ser consultado.
	 * @return O código do cliente correspondente ao CPF, ou -1 se o cliente não for encontrado.
	 * @throws ExceptionDao Se ocorrer algum erro durante a busca no banco de dados.
	 */
	public static int buscarCodigoClientePorCPF(String cpfCliente) throws ExceptionDao {
	    Cliente cliente = clienteDao.consultarClientePorCPF(cpfCliente);
	    if (cliente != null) {
	        return cliente.getCodCliente();
	    } else {
	        return -1;
	    }
	}
	/**
	 * Consulta o nome do cliente no banco de dados a partir do CPF fornecido.
	 * @param cpfCliente CPF do cliente a ser consultado.
	 * @return O nome do cliente correspondente ao CPF, ou null se o cliente não for encontrado.
	 * @throws ExceptionDao Se ocorrer algum erro durante a consulta ao banco de dados.
	 */
	public static String consultarNomeCliente(String cpfCliente) throws ExceptionDao {
	    try {
	        return clienteDao.consultarNomeClientePorCPF(cpfCliente);
	    } catch (ExceptionDao ex) {
	        throw new ExceptionDao("Erro ao consultar nome do cliente por CPF: " + ex.getMessage());
	    }
	}

	
}

