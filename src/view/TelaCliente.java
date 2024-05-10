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

public class TelaCliente extends JInternalFrame {
	
	public Connection conexao = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigoCliente;
	private JTextField txtNomeCliente;
	private JTextField txtEnderecoCliente;
	private JTextField txtTelefoneCliente;
	private JTextField txtCpfCliente;
	private JComboBox cbStatusCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
		getContentPane().setBackground(new Color(232, 227, 225));
		
		setResizable(true);
		getContentPane().setSize(new Dimension(450, 480));
		getContentPane().setPreferredSize(new Dimension(450, 480));
		getContentPane().setLayout(null);
		
		JLabel lblFormularioCliente = new JLabel("Formulário Cliente");
		lblFormularioCliente.setBounds(211, 11, 224, 32);
		getContentPane().add(lblFormularioCliente);
		lblFormularioCliente.setFont(new Font("Arial Black", Font.PLAIN, 22));
		
		JLabel lblCodigoCliente = new JLabel("ID");
		lblCodigoCliente.setBounds(10, 126, 112, 21);
		getContentPane().add(lblCodigoCliente);
		lblCodigoCliente.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setBounds(126, 128, 176, 20);
		getContentPane().add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(336, 126, 112, 21);
		getContentPane().add(lblNome);
		lblNome.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtNomeCliente = new JTextField();
		txtNomeCliente.setBounds(452, 128, 176, 20);
		getContentPane().add(txtNomeCliente);
		txtNomeCliente.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setBounds(10, 185, 112, 21);
		getContentPane().add(lblEndereco);
		lblEndereco.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtEnderecoCliente = new JTextField();
		txtEnderecoCliente.setBounds(126, 187, 176, 20);
		getContentPane().add(txtEnderecoCliente);
		txtEnderecoCliente.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(336, 184, 112, 21);
		getContentPane().add(lblTelefone);
		lblTelefone.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtTelefoneCliente = new JTextField();
		txtTelefoneCliente.setBounds(452, 186, 176, 20);
		getContentPane().add(txtTelefoneCliente);
		txtTelefoneCliente.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 249, 112, 21);
		getContentPane().add(lblCpf);
		lblCpf.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtCpfCliente = new JTextField();
		txtCpfCliente.setBounds(126, 251, 176, 20);
		getContentPane().add(txtCpfCliente);
		txtCpfCliente.setColumns(10);
		
		JLabel lblStatusCliente = new JLabel("Status");
		lblStatusCliente.setBounds(336, 249, 49, 21);
		getContentPane().add(lblStatusCliente);
		lblStatusCliente.setFont(new Font("Arial Black", Font.PLAIN, 14));
	
		cbStatusCliente = new JComboBox();
		cbStatusCliente.setModel(new DefaultComboBoxModel(new String[] {"Ativo", "Inativo"}));
		cbStatusCliente.setBounds(452, 251, 176, 20);
		getContentPane().add(cbStatusCliente);
		
		JButton btnCadastrarCliente = new JButton("");
		btnCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarCliente.setBackground(new Color(240, 240, 240));
		btnCadastrarCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/addicon.png")));
		btnCadastrarCliente.setPreferredSize(new Dimension(80, 80));
		btnCadastrarCliente.setBounds(34, 374, 117, 68);
		getContentPane().add(btnCadastrarCliente);
		
		JButton btnAlterarCliente = new JButton("");
		btnAlterarCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/editicon.png")));
		btnAlterarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterarCliente.setPreferredSize(new Dimension(80, 80));
		btnAlterarCliente.setBounds(185, 374, 117, 68);
		getContentPane().add(btnAlterarCliente);
		
		JButton btnConsultarCliente = new JButton("");
		btnConsultarCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/findicon.png")));
		btnConsultarCliente.setBounds(336, 374, 117, 68);
		getContentPane().add(btnConsultarCliente);
		
		JButton btnDeletarCliente = new JButton("");
		btnDeletarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletarCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/deleteicon.png")));
		btnDeletarCliente.setBounds(487, 374, 117, 68);
		getContentPane().add(btnDeletarCliente);
		btnConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCadastrarCliente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int codCliente = Integer.parseInt(txtCodigoCliente.getText());
				boolean sucesso;
				try {
					ClienteController clienteController = new ClienteController();
					sucesso = clienteController.validarCliente(codCliente,txtNomeCliente.getText(),txtEnderecoCliente.getText(),txtTelefoneCliente.getText(),txtCpfCliente.getText(),cbStatusCliente.getSelectedItem().toString());
					if(sucesso == true) {
						JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso");
					}
				}catch(Exception el) {
					JOptionPane.showMessageDialog(null, "Erro: " + el);
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
		setTitle("Cliente");
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBounds(100, 100, 640, 480);

	}
	
}
