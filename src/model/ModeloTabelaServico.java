package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * Modelo de tabela para exibição de serviços em uma interface gráfica.
 * Extende AbstractTableModel para fornecer os dados necessários à JTable.
 */
public class ModeloTabelaServico extends AbstractTableModel{

	private static final String[] colunas = {"Código", "Nome", "Descrição", "Preço", "Duração",
			"Status"};
	private ArrayList<Servico> servicos;
	
	/**
     * Construtor que inicializa o modelo com uma lista de serviços.
     * @param servicos Lista de serviços a serem exibidos na tabela
     */
	public ModeloTabelaServico(ArrayList<Servico> servicos) {
		super();
		this.servicos = servicos;
	}

	@Override
	public int getRowCount() {
		return servicos.size();
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
		
		Servico servico = servicos.get(rowIndex);
		if(columnIndex == 0 ) {
			return servico.getCodServico();
		}else if(columnIndex == 1 ) {
			return servico.getTipoServico();
		}else if(columnIndex == 2 ) {
			return servico.getDescricaoServico();
		}else if(columnIndex == 3 ) {
			return servico.getPrecoServico();
		}else if(columnIndex == 4) {
			return servico.getDuracaoServico();
		}else if(columnIndex == 5 ) {
			return servico.getStatusServico();
		}else {
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	
	
}
