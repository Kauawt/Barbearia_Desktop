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
import javax.swing.SwingConstants;

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
		getContentPane().setSize(new Dimension(655, 450));
		getContentPane().setPreferredSize(new Dimension(655, 450));
		getContentPane().setLayout(null);

		JLabel lblFormularioCliente = new JLabel("Cadastro de Clientes");
		lblFormularioCliente.setForeground(new Color(255, 255, 255));
		lblFormularioCliente.setBounds(213, 64, 270, 32);
		getContentPane().add(lblFormularioCliente);
		lblFormularioCliente.setFont(new Font("Comic Sans MS", Font.BOLD, 22));

		JLabel lblCodigoCliente = new JLabel("ID");
		lblCodigoCliente.setForeground(new Color(255, 255, 255));
		lblCodigoCliente.setBounds(134, 115, 41, 21);
		getContentPane().add(lblCodigoCliente);
		lblCodigoCliente.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtCodigoCliente.setForeground(new Color(192, 192, 192));
		txtCodigoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigoCliente.setText("ID");
		txtCodigoCliente.setBounds(258, 117, 134, 20);
		getContentPane().add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBounds(134, 147, 63, 21);
		getContentPane().add(lblNome);
		lblNome.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setForeground(new Color(255, 255, 255));
		lblEndereco.setBounds(134, 243, 77, 21);
		getContentPane().add(lblEndereco);
		lblEndereco.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtEnderecoCliente = new JTextField();
		txtEnderecoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnderecoCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtEnderecoCliente.setForeground(new Color(192, 192, 192));
		txtEnderecoCliente.setText("Endereço");
		txtEnderecoCliente.setBounds(258, 245, 134, 20);
		getContentPane().add(txtEnderecoCliente);
		txtEnderecoCliente.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(new Color(255, 255, 255));
		lblTelefone.setBounds(134, 211, 112, 21);
		getContentPane().add(lblTelefone);
		lblTelefone.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(new Color(255, 255, 255));
		lblCpf.setBounds(134, 179, 49, 21);
		getContentPane().add(lblCpf);
		lblCpf.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblStatusCliente = new JLabel("Status");
		lblStatusCliente.setForeground(new Color(255, 255, 255));
		lblStatusCliente.setBounds(134, 275, 49, 21);
		getContentPane().add(lblStatusCliente);
		lblStatusCliente.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		cbStatusCliente = new JComboBox<String>();
		cbStatusCliente.setToolTipText("");
		cbStatusCliente.setBackground(new Color(255, 255, 255));
		cbStatusCliente.setModel(new DefaultComboBoxModel<String>(new String[] { "Ativo", "Inativo" }));
		cbStatusCliente.setBounds(258, 277, 134, 20);
		getContentPane().add(cbStatusCliente);

		JButton btnCadastrarCliente = new JButton("Adicionar");
		btnCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarCliente.setBackground(new Color(240, 240, 240));
		btnCadastrarCliente.setIcon(null);
		btnCadastrarCliente.setPreferredSize(new Dimension(80, 80));
		btnCadastrarCliente.setBounds(97, 320, 104, 42);
		getContentPane().add(btnCadastrarCliente);

		JButton btnAlterarCliente = new JButton("Alterar");
		btnAlterarCliente.setIcon(null);
		btnAlterarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterarCliente.setPreferredSize(new Dimension(80, 80));
		btnAlterarCliente.setBounds(226, 320, 104, 42);
		getContentPane().add(btnAlterarCliente);

		JButton btnConsultarCliente = new JButton("Pesquisar");
		btnConsultarCliente.setIcon(null);
		btnConsultarCliente.setBounds(344, 320, 104, 42);
		getContentPane().add(btnConsultarCliente);

		JButton btnDeletarCliente = new JButton("Excluir");
		btnDeletarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletarCliente.setIcon(null);
		btnDeletarCliente.setBounds(470, 320, 104, 42);
		getContentPane().add(btnDeletarCliente);
		
		txtCpfCliente = new JTextField();
		txtCpfCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtCpfCliente.setForeground(new Color(192, 192, 192));
		txtCpfCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtCpfCliente.setText("CPF");
		txtCpfCliente.setBounds(258, 181, 134, 20);
		getContentPane().add(txtCpfCliente);
		txtCpfCliente.setColumns(10);
		
		txtTelefoneCliente = new JTextField();
		txtTelefoneCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtTelefoneCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtTelefoneCliente.setForeground(new Color(192, 192, 192));
		txtTelefoneCliente.setText("Telefone");
		txtTelefoneCliente.setBounds(258, 214, 134, 20);
		getContentPane().add(txtTelefoneCliente);
		txtTelefoneCliente.setColumns(10);
		
		txtNomeCliente = new JTextField();
		txtNomeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNomeCliente.setForeground(new Color(192, 192, 192));
		txtNomeCliente.setText("Nome");
		txtNomeCliente.setBounds(258, 149, 134, 20);
		getContentPane().add(txtNomeCliente);
		txtNomeCliente.setColumns(10);
		
		JPictureBox pictureBox = new JPictureBox();
		pictureBox.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/wallpaper_telas.png")));
		pictureBox.setBounds(0, 0, 655, 423);
		getContentPane().add(pictureBox);
		
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
		setBounds(100, 100, 655, 450);

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
