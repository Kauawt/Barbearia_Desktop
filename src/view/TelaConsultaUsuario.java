package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import net.miginfocom.swing.MigLayout;

import java.util.Date;

import javax.swing.JScrollPane;
import dao.UsuarioDao;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
/**
 * Painel de interface para consulta de usuários.
 */
public class TelaConsultaUsuario extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

		setBackground(new Color(232, 227, 225));

		setSize(new Dimension(640, 480));
		setPreferredSize(new Dimension(640, 480));
		setLayout(null);

		JPanel panel_1 = new JPanel();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension newSize = getSize();
				panel_1.setSize(newSize);
				panel_1.revalidate();
				panel_1.repaint();
			}
		});
		panel_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel_1.setOpaque(false);
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_1.setBounds(0, 0, 640, 480);
		add(panel_1);
		panel_1.setLayout(new MigLayout("insets 0", "[100,grow][::600,grow][100,grow]",
				"[grow,fill][grow 50,fill][][::300,grow,fill][grow][grow,fill]"));

		JLabel lblConsultarUsuario = new JLabel("Consultar Usuário");
		lblConsultarUsuario.setForeground(new Color(255, 255, 255));
		lblConsultarUsuario.setBounds(220, 57, 280, 32);
		panel_1.add(lblConsultarUsuario, "cell 1 1,alignx center");
		lblConsultarUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

		ModeloTabelaUsuario modeloTabela = new ModeloTabelaUsuario(UsuarioDao.listarUsuarios());

		rowSorter = new TableRowSorter<>(modeloTabela);

		txtFiltrar = new JTextField();
		txtFiltrar.setMaximumSize(new Dimension(180, 2147483647));
		txtFiltrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrar();
			}
		});

		JLabel lblFiltrar = new JLabel("Filtrar");
		lblFiltrar.setForeground(new Color(255, 255, 255));
		lblFiltrar.setBounds(197, 108, 60, 21);
		panel_1.add(lblFiltrar, "flowx,cell 1 2,alignx left,aligny center");
		lblFiltrar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtFiltrar.setBorder(null);
		txtFiltrar.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		txtFiltrar.setForeground(new Color(128, 128, 128));
		txtFiltrar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtFiltrar.setBounds(261, 110, 176, 20);
		panel_1.add(txtFiltrar, "cell 1 2,alignx left");
		txtFiltrar.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(25, 99, 591, 301);
		panel_1.add(scrollPane, "cell 1 3,growx");
		table = new JTable();
		table.setModel(modeloTabela);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					try {
						int selectedRow = table.getSelectedRow();
						int modelRow = table.convertRowIndexToModel(selectedRow);

						Usuario usuarioSelecionado = UsuarioDao
								.consultarUsuarioByCPF(modeloTabela.getValueAt(modelRow, 2).toString());
						TelaMenuPrincipal mainFrame = (TelaMenuPrincipal) SwingUtilities
								.getWindowAncestor(TelaConsultaUsuario.this);
						JPanel desktop = mainFrame.getDesktop();

						desktop.removeAll();
						TelaUsuarioPanel cadastraUsuario = new TelaUsuarioPanel(usuarioSelecionado);
						cadastraUsuario.setVisible(true);
						desktop.add(cadastraUsuario);
						desktop.revalidate();
						desktop.repaint();

					} catch (ExceptionDao e1) {
						e1.printStackTrace();
					}
				}
			}

		});
		table.setRowSorter(rowSorter);

		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setBounds(0, 0, 640, 480);
		add(panel);
		panel.setLayout(new MigLayout("insets 0", "[grow,fill]", "[grow,fill]"));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension newSize = getSize();
				panel.setSize(newSize);
				panel.revalidate();
				panel.repaint();
			}
		});

		JPictureBox pictureBox_1 = new JPictureBox();
		pictureBox_1.setIcon(new ImageIcon(TelaUsuarioPanel.class.getResource("/icones/cadastroWallpaper.png")));
		pictureBox_1.setBounds(0, 0, 640, 453);
		panel.add(pictureBox_1, "cell 0 0,grow");

		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBorder(null);
		setPreferredSize(new Dimension(640, 480));
		setMinimumSize(new Dimension(450, 480));
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBounds(100, 100, 640, 480);
	}
	/**
     * Filtra os resultados da consulta de usuários com base no texto inserido no campo de filtragem.
     * O filtro é aplicado de forma case insensitive.
     */
	private void filtrar() {
		String filtrar = txtFiltrar.getText().trim();
		if (filtrar.length() == 0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + filtrar));
		}
	}
}
