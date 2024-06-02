package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.AgendaDao;
import dao.UsuarioDao;
import model.ModeloTabelaAgendamento;
import model.ModeloTabelaUsuario;
public class TelaConsultaAgendamento extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private TableRowSorter<ModeloTabelaAgendamento> rowSorter;
	private JPanel contentPane;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaAgendamento frame = new TelaConsultaAgendamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaConsultaAgendamento() {
		setBounds(100, 100, 650, 455);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 35, 602, 363);
		getContentPane().add(scrollPane);
		
		ModeloTabelaAgendamento modeloTabela = new ModeloTabelaAgendamento(AgendaDao.listarAgendamentos());
		
		table = new JTable();
		table.setModel(modeloTabela);
		scrollPane.setViewportView(table);
		JPictureBox pictureBox = new JPictureBox();
		pictureBox.setBounds(0, 0, 634, 425);
		getContentPane().add(pictureBox);
		pictureBox.setIcon(new ImageIcon(TelaConsultaAgendamento.class.getResource("/icones/fundo_barberia.jpeg")));
		
		setIconifiable(true);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBorder(null);
		setMaximizable(true);
		setClosable(true);
		setRootPaneCheckingEnabled(false);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}
