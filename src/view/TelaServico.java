package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;
import controller.ServicoController;
import controller.UsuarioController;
import dao.ExceptionDao;
import dao.ModuloConexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class TelaServico extends JInternalFrame {

	public Connection conexao = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigoServico;
	private JTextField txtTipoServico;
	private JTextField txtDuracaoServico;
	private JTextField txtPrecoServico;
	private JTextField txtDescricaoServico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaServico frame = new TelaServico();
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
	public TelaServico() {
		getContentPane().setBackground(new Color(232, 227, 225));
		
		setResizable(true);
		getContentPane().setSize(new Dimension(450, 480));
		getContentPane().setPreferredSize(new Dimension(450, 480));
		getContentPane().setLayout(null);
		
		JLabel lblFormularioServico = new JLabel("Formulário Serviço");
		lblFormularioServico.setBounds(211, 11, 280, 32);
		getContentPane().add(lblFormularioServico);
		lblFormularioServico.setFont(new Font("Arial Black", Font.PLAIN, 22));
		
		JLabel lblCodigoServico = new JLabel("ID");
		lblCodigoServico.setBounds(10, 126, 112, 21);
		getContentPane().add(lblCodigoServico);
		lblCodigoServico.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtCodigoServico = new JTextField();
		txtCodigoServico.setBounds(126, 128, 176, 20);
		getContentPane().add(txtCodigoServico);
		txtCodigoServico.setColumns(10);
		
		JLabel lblNomeServico = new JLabel("Tipo");
		lblNomeServico.setBounds(336, 126, 112, 21);
		getContentPane().add(lblNomeServico);
		lblNomeServico.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtTipoServico = new JTextField();
		txtTipoServico.setBounds(452, 128, 176, 20);
		getContentPane().add(txtTipoServico);
		txtTipoServico.setColumns(10);
		
		JLabel lblDuracao = new JLabel("Duração");
		lblDuracao.setBounds(10, 234, 112, 21);
		getContentPane().add(lblDuracao);
		lblDuracao.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtDuracaoServico = new JTextField();
		txtDuracaoServico.setBounds(126, 236, 176, 20);
		getContentPane().add(txtDuracaoServico);
		txtDuracaoServico.setColumns(10);
		
		JLabel lblPrecoServico = new JLabel("Preço");
		lblPrecoServico.setBounds(336, 184, 58, 21);
		getContentPane().add(lblPrecoServico);
		lblPrecoServico.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtPrecoServico = new JTextField();
		txtPrecoServico.setBounds(452, 186, 176, 20);
		getContentPane().add(txtPrecoServico);
		txtPrecoServico.setColumns(10);
		
		JLabel lblDescricaoServico = new JLabel("Descrição");
		lblDescricaoServico.setBounds(10, 184, 112, 21);
		getContentPane().add(lblDescricaoServico);
		lblDescricaoServico.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtDescricaoServico = new JTextField();
		txtDescricaoServico.setBounds(126, 186, 176, 20);
		getContentPane().add(txtDescricaoServico);
		txtDescricaoServico.setColumns(10);
		
		JLabel lblStatusServico = new JLabel("Status");
		lblStatusServico.setBounds(335, 234, 49, 21);
		getContentPane().add(lblStatusServico);
		lblStatusServico.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		JButton btnCadastrarServico = new JButton("");
		btnCadastrarServico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarServico.setBackground(new Color(240, 240, 240));
		btnCadastrarServico.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/addicon.png")));
		btnCadastrarServico.setPreferredSize(new Dimension(80, 80));
		btnCadastrarServico.setBounds(34, 374, 117, 68);
		getContentPane().add(btnCadastrarServico);
		
		JButton btnAlterarServico = new JButton("");
		btnAlterarServico.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/editicon.png")));
		btnAlterarServico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterarServico.setPreferredSize(new Dimension(80, 80));
		btnAlterarServico.setBounds(185, 374, 117, 68);
		getContentPane().add(btnAlterarServico);
		
		JButton btnConsultarServico = new JButton("");
		btnConsultarServico.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/findicon.png")));
		btnConsultarServico.setBounds(336, 374, 117, 68);
		getContentPane().add(btnConsultarServico);
		
		JButton btnDeletarServico = new JButton("");
		btnDeletarServico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletarServico.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/deleteicon.png")));
		btnDeletarServico.setBounds(487, 374, 117, 68);
		getContentPane().add(btnDeletarServico);
		
		final JComboBox cbStatusServico = new JComboBox();
		cbStatusServico.setModel(new DefaultComboBoxModel(new String[] {"Ativo", "Inativo"}));
		cbStatusServico.setBounds(452, 235, 176, 22);
		getContentPane().add(cbStatusServico);
		btnConsultarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
			}
		});
		btnCadastrarServico.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int codServico = Integer.parseInt(txtCodigoServico.getText());
				double precoServico = Double.parseDouble(txtPrecoServico.getText());
				double duracaoServico =Double.parseDouble(txtDuracaoServico.getText());
				ServicoController servicoController = new ServicoController();
				try {
					servicoController.cadastrarServico(codServico,txtTipoServico.getText(),txtDescricaoServico.getText(),precoServico,duracaoServico,cbStatusServico.getSelectedItem().toString());
				} catch (ExceptionDao e1) {
					e1.printStackTrace();
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
		setTitle("Usuario");
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBounds(100, 100, 640, 480);

	}
}
