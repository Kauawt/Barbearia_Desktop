package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;
import controller.helper.ClienteHelper;

public class TelaCliente extends JInternalFrame {

	private Connection conexao = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigoCliente;
	private JTextField txtEnderecoCliente;
	private JComboBox<String> cbStatusCliente;
	private JFormattedTextField ftxtCpfCliente;
	private JButton btnCadastrarCliente;
	private JButton btnAlterarCliente;
	private JButton btnConsultarCliente;
	private JButton btnDeletarCliente;
	private JFormattedTextField formattedTextField;
	private JTextField txtCpfCliente;
	private JTextField txtTelefoneCliente;
	private JTextField txtNomeCliente;

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
		ClienteController clienteController = new ClienteController(this);
		ClienteHelper clienteHelper = new ClienteHelper(this);
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

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 249, 112, 21);
		getContentPane().add(lblCpf);
		lblCpf.setFont(new Font("Arial Black", Font.PLAIN, 14));

		JLabel lblStatusCliente = new JLabel("Status");
		lblStatusCliente.setBounds(336, 249, 49, 21);
		getContentPane().add(lblStatusCliente);
		lblStatusCliente.setFont(new Font("Arial Black", Font.PLAIN, 14));

		cbStatusCliente = new JComboBox<String>();
		cbStatusCliente.setModel(new DefaultComboBoxModel<String>(new String[] { "Ativo", "Inativo" }));
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
		
		txtCpfCliente = new JTextField();
		txtCpfCliente.setBounds(126, 251, 176, 20);
		getContentPane().add(txtCpfCliente);
		txtCpfCliente.setColumns(10);
		
		txtTelefoneCliente = new JTextField();
		txtTelefoneCliente.setBounds(452, 187, 176, 20);
		getContentPane().add(txtTelefoneCliente);
		txtTelefoneCliente.setColumns(10);
		
		txtNomeCliente = new JTextField();
		txtNomeCliente.setBounds(455, 128, 173, 20);
		getContentPane().add(txtNomeCliente);
		txtNomeCliente.setColumns(10);
		
		MaskFormatter cpfMask = null;
		MaskFormatter dataMask = null;
		MaskFormatter telefoneMask = null;

		try {
			cpfMask = new MaskFormatter("###.###.###-##");
			dataMask = new MaskFormatter("##/##/####");
			telefoneMask = new MaskFormatter("(##)#####-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		btnConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCadastrarCliente.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clienteController.cadastrarCliente();
			
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

	public JTextField getTxtCodigoCliente() {
		return txtCodigoCliente;
	}

	public void setTxtCodigoCliente(JTextField txtCodigoCliente) {
		this.txtCodigoCliente = txtCodigoCliente;
	}

	public JTextField getTxtNomeCliente() {
		return txtNomeCliente;
	}

	public void setTxtNomeCliente(JTextField txtNomeCliente) {
		this.txtNomeCliente = txtNomeCliente;
	}

	public JTextField getTxtEnderecoCliente() {
		return txtEnderecoCliente;
	}

	public void setTxtEnderecoCliente(JTextField txtEnderecoCliente) {
		this.txtEnderecoCliente = txtEnderecoCliente;
	}

	public JComboBox<String> getCbStatusCliente() {
		return cbStatusCliente;
	}

	public void setCbStatusCliente(JComboBox<String> cbStatusCliente) {
		this.cbStatusCliente = cbStatusCliente;
	}

	public JFormattedTextField getFtxtCpfCliente() {
		return ftxtCpfCliente;
	}

	public void setFtxtCpfCliente(JFormattedTextField ftxtCpfCliente) {
		this.ftxtCpfCliente = ftxtCpfCliente;
	}


	public JButton getBtnCadastrarCliente() {
		return btnCadastrarCliente;
	}

	public void setBtnCadastrarCliente(JButton btnCadastrarCliente) {
		this.btnCadastrarCliente = btnCadastrarCliente;
	}

	public JButton getBtnAlterarCliente() {
		return btnAlterarCliente;
	}

	public void setBtnAlterarCliente(JButton btnAlterarCliente) {
		this.btnAlterarCliente = btnAlterarCliente;
	}

	public JButton getBtnConsultarCliente() {
		return btnConsultarCliente;
	}

	public void setBtnConsultarCliente(JButton btnConsultarCliente) {
		this.btnConsultarCliente = btnConsultarCliente;
	}

	public JButton getBtnDeletarCliente() {
		return btnDeletarCliente;
	}

	public void setBtnDeletarCliente(JButton btnDeletarCliente) {
		this.btnDeletarCliente = btnDeletarCliente;
	}

	public JTextField getTxtCpfCliente() {
		return txtCpfCliente;
	}

	public void setTxtCpfCliente(JTextField txtCpfCliente) {
		this.txtCpfCliente = txtCpfCliente;
	}

	public JTextField getTxtTelefoneCliente() {
		return txtTelefoneCliente;
	}

	public void setTxtTelefoneCliente(JTextField txtTelefoneCliente) {
		this.txtTelefoneCliente = txtTelefoneCliente;
	}

	
	
}
