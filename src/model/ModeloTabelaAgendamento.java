package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.sql.Time;
import java.util.Date;
import java.util.List;
/**
 * Modelo de tabela para exibição de agendamentos em uma interface gráfica.
 * Extende AbstractTableModel para fornecer os dados necessários à JTable.
 */
public class ModeloTabelaAgendamento extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String[] colunas = {"ID", "Nome", "CPF", "Servico", "Valor","Data","Horario","Barbeiro"};
	
	private ArrayList<Agendamento> agendamentos;
	private List<Time> horariosDisponiveis;
	/**
     * Construtor que inicializa o modelo com uma lista de agendamentos.
     *
     * @param agendamentos Lista de agendamentos a serem exibidos na tabela
     */
	public ModeloTabelaAgendamento(ArrayList<Agendamento> agendamentos) {
		super();
		this.agendamentos = agendamentos;
	}
	@Override
	public int getRowCount() {
		return agendamentos.size();
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
		
		Agendamento agendamento = agendamentos.get(rowIndex);
		if(columnIndex == 0 ) {
			return agendamento.getCodAgendamento();
		}else if(columnIndex == 1 ) {
			return agendamento.getCliente().getNomeCliente();
		}else if(columnIndex == 2 ) {
			return agendamento.getCliente().getCpfCliente();
		}else if(columnIndex == 3 ) {
			return agendamento.getServico().getTipoServico();
		}else if(columnIndex == 4) {
			return agendamento.getServico().getPrecoServico();
		}else if(columnIndex == 5) {
			return agendamento.getDataAtendimento();
		}else if(columnIndex == 6) {
			return agendamento.getHoraAtendimento();
		}else if(columnIndex == 7) {
			return agendamento.getUsuario().getNomeUsuario();
		}
		else {
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
}
