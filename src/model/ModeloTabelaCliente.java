package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
/**
 * Modelo de tabela para exibição de clientes em uma interface gráfica.
 * Extende AbstractTableModel para fornecer os dados necessários à JTable.
 */
public class ModeloTabelaCliente extends AbstractTableModel {
	
	private static final String[] colunas = {"Código", "Nome", "Endereço", "Telefone", "CPF",
			"Status"};
	
	private ArrayList<Cliente> clientes;
	/**
     * Construtor que inicializa o modelo com uma lista de clientes.
     * @param clientes Lista de clientes a serem exibidos na tabela
     */
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
	 /**
     * Obtém o valor a ser exibido em uma célula da tabela.
     * @param rowIndex Indice da linha
     * @param columnIndex Indice da coluna
     * @return Objeto a ser exibido na célula especificada
     */
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
