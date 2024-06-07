package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTabelaAgendamento extends AbstractTableModel {
	
	private static final String[] colunas = {"ID", "Nome", "CPF", "Servico", "Valor","Data","Horario","Barbeiro"};
	
	private ArrayList<Agendamento> agendamentos;
	
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
