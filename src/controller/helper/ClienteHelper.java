package controller.helper;

import model.Cliente;
import view.TelaCliente;

public class ClienteHelper {
	
	private TelaCliente telaCliente;
	
	
	public ClienteHelper(TelaCliente telaCliente) {
		this.telaCliente = telaCliente;
	}
	
	public Cliente obterModeloTelaCliente() {
		int codCliente = Integer.parseInt(telaCliente.getTxtCodigoCliente().getText());
		String nomeCliente = telaCliente.getTxtNomeCliente().getText();
		String enderecoCliente = telaCliente.getTxtEnderecoCliente().getText();
		String telefoneCliente = telaCliente.getTxtTelefoneCliente().getText();
		String cpfCliente = telaCliente.getTxtCpfCliente().getText();
		String statusCliente = telaCliente.getCbStatusCliente().getSelectedItem().toString();
		Cliente cliente = new Cliente(codCliente,nomeCliente,enderecoCliente,telefoneCliente,cpfCliente,statusCliente);
		return cliente;
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
		telaCliente.getTxtTelefoneCliente().setText(telefoneCliente.toString());
		telaCliente.getTxtCpfCliente().setText(cpfCliente.toString());
		telaCliente.getCbStatusCliente().setSelectedItem(statusCliente.toString());
		
	}
	
	public void limparTelaCliente() {
		telaCliente.getTxtCodigoCliente().setText("");
		telaCliente.getTxtNomeCliente().setText("");
		telaCliente.getTxtEnderecoCliente().setText("");
		telaCliente.getTxtTelefoneCliente().setText("");
		telaCliente.getTxtCpfCliente().setText("");
		telaCliente.getCbStatusCliente().setSelectedItem("");
	}
}
