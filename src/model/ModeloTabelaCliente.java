package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabelaCliente extends AbstractTableModel {
	
	private static final String[] colunas = {"Código", "Nome", "Endereço", "Telefone", "CPF",
			"Status"};
	
	private ArrayList<Cliente> clientes;
	
	public ModeloTabelaCliente(ArrayList<Cliente> clientes) {
		super();
		this.clientes = clientes;
	}

	@Override
	public int getRowCount() {
		return clientes.size();
	}
	
	@Override
	public int getColumnCount() {

		return colunas.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Cliente cliente = clientes.get(rowIndex);
		if(columnIndex == 0 ) {
			return cliente.getCodCliente();
		}else if(columnIndex == 1 ) {
			return cliente.getNomeCliente();
		}else if(columnIndex == 2 ) {
			return cliente.getEnderecoCliente();
		}else if(columnIndex == 3 ) {
			return cliente.getTelefoneCliente();
		}else if(columnIndex == 4) {
			return cliente.getCpfCliente();
		}else if(columnIndex == 5 ) {
			return cliente.getStatusCliente();
		}else {
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
}
