package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ModuloConexao;
import model.ModeloTabelaUsuario;
import model.Usuario;

import java.util.Date;

import javax.swing.JScrollPane;
import dao.UsuarioDao;

public class TelaConsultaUsuario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Usuario> usuarios;

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
		
		/*
		 * UsuarioDao usuarioDao = new UsuarioDao(); try { usuarios =
		 * UsuarioDao.listarUsuarios(); } catch(Exception e) { e.printStackTrace(); }
		 */
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 125, 591, 301);
		contentPane.add(scrollPane);
		
		
		
		ModeloTabelaUsuario modeloTabela = new ModeloTabelaUsuario(UsuarioDao.listarUsuarios());
		
		table = new JTable();
		table.setModel(modeloTabela);
		scrollPane.setViewportView(table);
		
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
	}
}
