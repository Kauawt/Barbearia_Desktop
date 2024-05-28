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
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;
import controller.helper.ClienteHelper;
import dao.ClienteDao;
import dao.ExceptionDao;
import dao.UsuarioDao;
import model.Cliente;
import model.ModeloTabelaCliente;
import model.ModeloTabelaUsuario;
import javax.swing.JScrollPane;

public class TelaCliente extends JInternalFrame {

	private Connection conexao = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	private static final long serialVersionUID = 1L;
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
	private JFormattedTextField ftxtTelefoneCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente(null);
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
	public TelaCliente(Cliente clienteSelecionado) throws ExceptionDao {

		ClienteController clienteController = new ClienteController(this);
		ClienteHelper clienteHelper = new ClienteHelper(this);
		getContentPane().setBackground(new Color(232, 227, 225));

		setResizable(true);
		getContentPane().setSize(new Dimension(450, 480));
		getContentPane().setPreferredSize(new Dimension(450, 480));
		getContentPane().setLayout(null);

		JLabel lblFormularioCliente = new JLabel("Formulário Cliente");
		lblFormularioCliente.setBounds(208, 11, 224, 32);
		getContentPane().add(lblFormularioCliente);
		lblFormularioCliente.setFont(new Font("Arial Black", Font.PLAIN, 22));

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(181, 119, 112, 21);
		getContentPane().add(lblNome);
		lblNome.setFont(new Font("Arial Black", Font.PLAIN, 14));

		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setBounds(181, 233, 112, 21);
		getContentPane().add(lblEndereco);
		lblEndereco.setFont(new Font("Arial Black", Font.PLAIN, 14));

		txtEnderecoCliente = new JTextField();
		txtEnderecoCliente.setBounds(297, 235, 176, 20);
		getContentPane().add(txtEnderecoCliente);
		txtEnderecoCliente.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(181, 176, 112, 21);
		getContentPane().add(lblTelefone);
		lblTelefone.setFont(new Font("Arial Black", Font.PLAIN, 14));

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(181, 290, 112, 21);
		getContentPane().add(lblCpf);
		lblCpf.setFont(new Font("Arial Black", Font.PLAIN, 14));

		JLabel lblStatusCliente = new JLabel("Status");
		lblStatusCliente.setBounds(181, 347, 49, 21);
		getContentPane().add(lblStatusCliente);
		lblStatusCliente.setFont(new Font("Arial Black", Font.PLAIN, 14));

		cbStatusCliente = new JComboBox<String>();
		cbStatusCliente.setModel(new DefaultComboBoxModel<String>(new String[] { "Ativo", "Inativo" }));
		cbStatusCliente.setBounds(297, 349, 176, 20);
		getContentPane().add(cbStatusCliente);

		txtNomeCliente = new JTextField();
		txtNomeCliente.setBounds(297, 121, 173, 20);
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

		ftxtTelefoneCliente = new JFormattedTextField(telefoneMask);
		ftxtTelefoneCliente.setBounds(297, 178, 173, 20);
		getContentPane().add(ftxtTelefoneCliente);

		ftxtCpfCliente = new JFormattedTextField(cpfMask);
		ftxtCpfCliente.setBounds(297, 292, 176, 20);
		getContentPane().add(ftxtCpfCliente);

		JButton btnConsultarCliente = new JButton("");
		btnConsultarCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/findicon.png")));
		btnConsultarCliente.setBounds(513, 47, 117, 68);
		getContentPane().add(btnConsultarCliente);
		btnConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaCliente listarClientes = new TelaConsultaCliente();
				JDesktopPane desktop = getDesktopPane();
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(desktop);
				if (frame instanceof TelaMenuPrincipal) {
					JInternalFrame[] frames = desktop.getAllFrames();
					for (JInternalFrame frame1 : frames) {
						frame1.dispose();
					}
				}
				desktop.add(listarClientes);
				listarClientes.setVisible(true);
			}
		});
		if (clienteSelecionado != null) {
			JButton btnDeletarCliente = new JButton("");
			btnDeletarCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ClienteDao cliente = new ClienteDao();
					try {
						cliente.deletarCliente(clienteSelecionado.getCodCliente());
					} catch (ExceptionDao e1) {
						e1.printStackTrace();
					}
				}
			});
			btnDeletarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnDeletarCliente.setBackground(UIManager.getColor("Button.background"));
			btnDeletarCliente.setPreferredSize(new Dimension(80, 80));
			btnDeletarCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/deleteicon.png")));
			btnDeletarCliente.setBounds(37, 374, 155, 68);
			getContentPane().add(btnDeletarCliente);
		}

		JButton btnCadastrarCliente = new JButton();
		btnCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarCliente.setBackground(new Color(240, 240, 240));
		if (clienteSelecionado == null) {
			btnCadastrarCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/addicon.png")));
		} else {
			btnCadastrarCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/editicon.png")));
		}
		btnCadastrarCliente.setPreferredSize(new Dimension(80, 80));
		btnCadastrarCliente.setBounds(513, 374, 117, 68);
		getContentPane().add(btnCadastrarCliente);
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clienteSelecionado == null) {
					clienteController.cadastrarCliente();
				} else {
					clienteController.alterarCliente();
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

		if (clienteSelecionado != null) {
			preencherCampos(clienteSelecionado);
		}
	}

	private void preencherCampos(Cliente clienteSelecionado) throws ExceptionDao {
		txtNomeCliente.setText(clienteSelecionado.getNomeCliente());
		txtEnderecoCliente.setText(clienteSelecionado.getEnderecoCliente());
		ftxtTelefoneCliente.setText(clienteSelecionado.getTelefoneCliente());
		ftxtCpfCliente.setText(clienteSelecionado.getCpfCliente());
		cbStatusCliente.setSelectedItem(clienteSelecionado.getStatusCliente());
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

	public JFormattedTextField getFtxtCpfCliente() {
		return ftxtCpfCliente;
	}

	public void setFtxtCpfCliente(JFormattedTextField ftxtCpfCliente) {
		this.ftxtCpfCliente = ftxtCpfCliente;
	}

	public JFormattedTextField getFtxtTelefoneCliente() {
		return ftxtTelefoneCliente;
	}

	public void setFtxtTelefoneCliente(JFormattedTextField ftxtTelefoneCliente) {
		this.ftxtTelefoneCliente = ftxtTelefoneCliente;
	}
}
