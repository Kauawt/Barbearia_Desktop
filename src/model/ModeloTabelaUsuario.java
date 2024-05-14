package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabelaUsuario extends AbstractTableModel{

	private static final String[] colunas = {"codUsuario", "nomeUsuario", "cpfUsuario", "dataNascimento", "salarioUsuario",
			"emailUsuario", "PerfilUsuario", "statusUsuario"};
	private ArrayList<Usuario> usuarios;
	
	public ModeloTabelaUsuario(ArrayList<Usuario> usuarios) {
		super();
		this.usuarios = usuarios;
	}

	@Override
	public int getRowCount() {
		return usuarios.size();
	}
	
	@Override
	public int getColumnCount() {

		return colunas.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Usuario usuario = usuarios.get(rowIndex);
		if(columnIndex == 0 ) {
			return usuario.getCodUsuario();
		}else if(columnIndex == 1 ) {
			return usuario.getNomeUsuario();
		}else if(columnIndex == 0 ) {
			return usuario.getCpfUsuario();
		}else if(columnIndex == 0 ) {
			return usuario.getDataNascimentoUsuario();
		}else if(columnIndex == 0) {
			return usuario.getSalarioUsuario();
		}else if(columnIndex == 0 ) {
			return usuario.getEmailUsuario();
		}else if(columnIndex == 0 ) {
			return usuario.getPerfilUsuario();
		}else if(columnIndex == 0 ) {
			return usuario.getStatusUsuario();
		}else {
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	
	
}
