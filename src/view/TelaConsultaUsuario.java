package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.ExceptionDao;
import dao.ModuloConexao;
import model.ModeloTabelaUsuario;
import model.Usuario;

import java.util.Date;

import javax.swing.JScrollPane;
import dao.UsuarioDao;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaConsultaUsuario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtFiltrar;
	private TableRowSorter<ModeloTabelaUsuario> rowSorter;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaUsuario frame = new TelaConsultaUsuario();
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
	public TelaConsultaUsuario() {
			
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(232, 227, 225));
		
		setResizable(true);
		getContentPane().setSize(new Dimension(450, 480));
		getContentPane().setPreferredSize(new Dimension(450, 480));
		getContentPane().setLayout(null);
		 
		setBounds(100, 100, 708, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFiltrar = new JLabel("Filtrar");
		lblFiltrar.setBounds(32, 34, 46, 14);
		contentPane.add(lblFiltrar);
		
		txtFiltrar = new JTextField();
		txtFiltrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrar();
			}

		});
		txtFiltrar.setBounds(77, 31, 539, 20);
		contentPane.add(txtFiltrar);
		txtFiltrar.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 99, 591, 301);
		contentPane.add(scrollPane);
		
		
		
		ModeloTabelaUsuario modeloTabela = new ModeloTabelaUsuario(UsuarioDao.listarUsuarios());
		
		table = new JTable();
		table.setModel(modeloTabela);
		scrollPane.setViewportView(table);
		
		JPictureBox pictureBox = new JPictureBox();
		pictureBox.setIcon(new ImageIcon(TelaConsultaUsuario.class.getResource("/icones/fundo_barberia.jpeg")));
		pictureBox.setBounds(0, 0, 640, 453);
		contentPane.add(pictureBox);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					try {
						Usuario usuarioSelecionado = UsuarioDao.consultarUsuarioByCPF(modeloTabela.getValueAt(table.getSelectedRow(), 2).toString());
						
						TelaUsuario cadastraUsuario = new TelaUsuario(usuarioSelecionado);
						JDesktopPane desktop = getDesktopPane();
						JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(desktop);
						
						if (frame instanceof TelaMenuPrincipal) {
							JInternalFrame[] frames = desktop.getAllFrames();
			                for (JInternalFrame frame1 : frames) {
			                    frame1.dispose();
			                }
						}
						desktop.add(cadastraUsuario);
						cadastraUsuario.setVisible(true);
					} catch (ExceptionDao e1) {
						e1.printStackTrace();
					}
				}
			}
			
		});
		
		setIconifiable(true);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBorder(null);
		setPreferredSize(new Dimension(640, 480));
		setMaximizable(true);
		setClosable(true);
		setRootPaneCheckingEnabled(false);
		setMinimumSize(new Dimension(450, 480));
		setTitle("Consulta Usuario");
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBounds(100, 100, 640, 480);
		
		rowSorter = new TableRowSorter<>(modeloTabela);
		table.setRowSorter(rowSorter);
		
	}
	private void filtrar() {
		String filtrar = txtFiltrar.getText().trim();
		if(filtrar.length()==0) {
			rowSorter.setRowFilter(null);
		}else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+filtrar));
		}
	}
}
