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
	 * Capta dos dados da tela do cliente e valida os campos. Caso esteja tudo correto ele cria um objeto do tipo cliente e devolve ao Cliente Controller. Caso
	 * não esteja ele simplesmente retorna null e apresenta uma mensagem atrelado ao campo inválido
	 */
	
	public Cliente validadorCamposTelaCliente() {
		
		int codCliente = Integer.parseInt(telaCliente.getTxtCodigoCliente().getText());
		String nomeCliente = telaCliente.getTxtNomeCliente().getText();
		String enderecoCliente = telaCliente.getTxtEnderecoCliente().getText();
		String telefoneCliente = telaCliente.getFtxtCpfCliente().getText();
		String cpfCliente = telaCliente.getFtxtCpfCliente().getText();
		String statusCliente = telaCliente.getCbStatusCliente().getSelectedItem().toString();
		
		if (codCliente > 0 && nomeCliente != null && enderecoCliente != null 
				&& ValidadorHelper.validadorCpf(cpfCliente)
				&& ValidadorHelper.validadorStatus(statusCliente)) {
			Cliente cliente = new Cliente(codCliente,nomeCliente,enderecoCliente,telefoneCliente,cpfCliente,statusCliente);
			return cliente;
		}else {
			if (codCliente < 0) {
				JOptionPane.showMessageDialog(null, "Não é permitido ter valores negativos");}
			else if (ValidadorHelper.validadorCpf(cpfCliente) == false) {
				JOptionPane.showMessageDialog(null, "CPF Inválido");}
			else if (ValidadorHelper.validadorStatus(statusCliente) == false) {
				JOptionPane.showMessageDialog(null, "O Status deve ser Ativo durante a criação de um novo Cadastro");}
		return null;}
	}
	
	

	public void setarModeloTelaCliente(Cliente cliente) {
		int codCliente = cliente.getCodCliente();
		String nomeCliente = cliente.getNomeCliente();
		String enderecoCliente = cliente.getEnderecoCliente();
		String telefoneCliente = cliente.getTelefoneCliente();
		String cpfCliente = cliente.getCpfCliente();
		String statusCliente = cliente.getStatusCliente();

		telaCliente.getTxtCodigoCliente().setText(String.valueOf(codCliente));
		telaCliente.getTxtNomeCliente().setText(nomeCliente.toString());
		telaCliente.getTxtEnderecoCliente().setText(enderecoCliente.toString());
		telaCliente.getFtxtCpfCliente().setText(telefoneCliente.toString());
		telaCliente.getFtxtCpfCliente().setText(cpfCliente.toString());
		telaCliente.getCbStatusCliente().setSelectedItem(statusCliente.toString());

	}

	public void limparTelaCliente() {
		telaCliente.getTxtCodigoCliente().setText("");
		telaCliente.getTxtNomeCliente().setText("");
		telaCliente.getTxtEnderecoCliente().setText("");
		telaCliente.getTxtTelefoneCliente().setText("");
		telaCliente.getFtxtCpfCliente().setText("");
		telaCliente.getCbStatusCliente().setSelectedItem("");
	}
}
