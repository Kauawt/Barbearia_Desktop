package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
/**
 * Modelo de tabela para exibição de usuários em uma interface gráfica.
 * Extende AbstractTableModel para fornecer os dados necessários à JTable.
 */
public class ModeloTabelaUsuario extends AbstractTableModel{

	private static final String[] colunas = {"Código", "Nome", "CPF", "data de Nascimento", "salario",
			"email", "Perfil", "status"};
	private ArrayList<Usuario> usuarios;
	/**
     * Construtor que inicializa o modelo com uma lista de usuários.
     * @param usuarios Lista de usuários a serem exibidos na tabela
     */
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
	/**
     * Obtém o valor a ser exibido em uma célula da tabela.
     * @param rowIndex Indice da linha
     * @param columnIndex Indice da coluna
     * @return Objeto a ser exibido na célula especificada
     */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Usuario usuario = usuarios.get(rowIndex);
		if(columnIndex == 0 ) {
			return usuario.getCodUsuario();
		}else if(columnIndex == 1 ) {
			return usuario.getNomeUsuario();
		}else if(columnIndex == 2 ) {
			return usuario.getCpfUsuario();
		}else if(columnIndex == 3 ) {
			return usuario.converteDataBancoTela(usuario.getDataNascimentoUsuario());
		}else if(columnIndex == 4) {
			return usuario.getSalarioUsuario();
		}else if(columnIndex == 5 ) {
			return usuario.getEmailUsuario();
		}else if(columnIndex == 6 ) {
			return usuario.getPerfilUsuario();
		}else if(columnIndex == 7 ) {
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
