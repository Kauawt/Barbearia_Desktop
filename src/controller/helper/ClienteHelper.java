package controller.helper;

import model.Cliente;
import view.TelaClientePanel;

import javax.swing.JOptionPane;

import dao.ExceptionDao;

public class ClienteHelper {
	private TelaClientePanel telaClientePanel;

	public ClienteHelper(TelaClientePanel telaClientePanel) {
		this.telaClientePanel = telaClientePanel;
	}

	/*
	 * Capta dos dados da tela do cliente e valida os campos. Caso esteja tudo
	 * correto ele cria um objeto do tipo cliente e devolve ao Cliente Controller.
	 * Caso não esteja ele simplesmente retorna null e apresenta uma mensagem
	 * atrelado ao campo inválido
	 */

	public Cliente validadorCamposTelaCliente(){

		String nomeCliente = telaClientePanel.getTxtNomeCliente().getText();
		String enderecoCliente = telaClientePanel.getTxtEnderecoCliente().getText();
		String telefoneCliente = telaClientePanel.getFtxtTelefoneCliente().getText();
		String cpfCliente = telaClientePanel.getFtxtCpfCliente().getText();
		String statusCliente = telaClientePanel.getCbStatusCliente().getSelectedItem().toString();

		if (nomeCliente != null && enderecoCliente != null && ValidadorHelper.validadorCpf(cpfCliente))  {
			Cliente cliente = new Cliente(nomeCliente, enderecoCliente, telefoneCliente, cpfCliente, statusCliente);
			return cliente;
		} else {
			JOptionPane.showMessageDialog( null, "Todos os campos precisam ser preenchidos");
			if (ValidadorHelper.validadorCpf(cpfCliente) == false) {
				JOptionPane.showMessageDialog(null, "CPF Inválido");
			}
			return null;
		}
	}


	public void limparTelaCliente() {
	
		telaClientePanel.getTxtNomeCliente().setText("");
		telaClientePanel.getTxtEnderecoCliente().setText("");
		telaClientePanel.getFtxtTelefoneCliente().setText("");
		telaClientePanel.getFtxtCpfCliente().setText("");
		telaClientePanel.getCbStatusCliente().setSelectedItem("");
	}
}
