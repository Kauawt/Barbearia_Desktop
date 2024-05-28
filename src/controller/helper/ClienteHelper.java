package controller.helper;

import model.Cliente;
import view.TelaCliente;

import javax.swing.JOptionPane;

public class ClienteHelper {
	private TelaCliente telaCliente;

	public ClienteHelper(TelaCliente telaCliente) {
		this.telaCliente = telaCliente;
	}

	/*
	 * Capta dos dados da tela do cliente e valida os campos. Caso esteja tudo
	 * correto ele cria um objeto do tipo cliente e devolve ao Cliente Controller.
	 * Caso não esteja ele simplesmente retorna null e apresenta uma mensagem
	 * atrelado ao campo inválido
	 */

	public Cliente validadorCamposTelaCliente() {

		String nomeCliente = telaCliente.getTxtNomeCliente().getText();
		String enderecoCliente = telaCliente.getTxtEnderecoCliente().getText();
		String telefoneCliente = telaCliente.getFtxtCpfCliente().getText();
		String cpfCliente = telaCliente.getFtxtCpfCliente().getText();
		String statusCliente = telaCliente.getCbStatusCliente().getSelectedItem().toString();

		if (nomeCliente != null && enderecoCliente != null && ValidadorHelper.validadorCpf(cpfCliente)) {
			Cliente cliente = new Cliente(nomeCliente, enderecoCliente, telefoneCliente, cpfCliente, statusCliente);
			return cliente;
		} else {

			if (ValidadorHelper.validadorCpf(cpfCliente) == false) {
				JOptionPane.showMessageDialog(null, "CPF Inválido");
			}
			return null;
		}
	}


	public void limparTelaCliente() {
	
		telaCliente.getTxtNomeCliente().setText("");
		telaCliente.getTxtEnderecoCliente().setText("");
		telaCliente.getFtxtTelefoneCliente().setText("");
		telaCliente.getFtxtCpfCliente().setText("");
		telaCliente.getCbStatusCliente().setSelectedItem("");
	}
}
