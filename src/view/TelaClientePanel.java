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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;
import dao.ClienteDao;
import dao.ExceptionDao;
import model.Cliente;
import net.miginfocom.swing.MigLayout;
/**
 * Painel de interface para gerenciamento de clientes.
 */
public class TelaClientePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtEnderecoCliente;
	private JComboBox<String> cbStatusCliente;
	private JFormattedTextField ftxtCpfCliente;
	private JButton btnCadastrarCliente;
	private JButton btnAlterarCliente;
	private JButton btnConsultarCliente;
	private JTextField txtNomeCliente;
	private JFormattedTextField ftxtTelefoneCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaClientePanel frame = new TelaClientePanel(null);
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
	public TelaClientePanel(Cliente clienteSelecionado) throws ExceptionDao {

		ClienteController clienteController = new ClienteController(this);
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
		panel_1.setLayout(new MigLayout("insets 0", "[200,grow][::300,grow][180,grow]",
				"[grow,fill][grow,fill][::250,grow,fill][grow][grow,fill]"));

		JLabel lblFormularioCliente = new JLabel("Formulário Cliente");
		lblFormularioCliente.setForeground(new Color(255, 255, 255));
		lblFormularioCliente.setBounds(220, 57, 280, 32);
		panel_1.add(lblFormularioCliente, "cell 1 1,alignx center,aligny center");
		lblFormularioCliente.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBounds(197, 108, 60, 21);
		panel_1.add(lblNome, "flowy,cell 0 2,alignx right,aligny center");
		lblNome.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setForeground(new Color(255, 255, 255));
		lblEndereco.setBounds(204, 139, 49, 21);
		panel_1.add(lblEndereco, "cell 0 2,alignx right,aligny center");
		lblEndereco.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(new Color(255, 255, 255));
		lblTelefone.setBounds(204, 139, 49, 21);
		panel_1.add(lblTelefone, "cell 0 2,alignx right,aligny center");
		lblTelefone.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(new Color(255, 255, 255));
		lblCpf.setBounds(121, 171, 136, 21);
		panel_1.add(lblCpf, "cell 0 2,alignx right");
		lblCpf.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblStatusCliente = new JLabel("Status");
		lblStatusCliente.setForeground(new Color(255, 255, 255));
		lblStatusCliente.setBounds(185, 201, 68, 21);
		panel_1.add(lblStatusCliente, "cell 0 2,alignx right");
		lblStatusCliente.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtNomeCliente = new JTextField();
		txtNomeCliente.setBorder(null);
		txtNomeCliente.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtNomeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeCliente.setForeground(new Color(128, 128, 128));
		txtNomeCliente.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtNomeCliente.setBounds(261, 110, 176, 20);
		panel_1.add(txtNomeCliente, "gap bottom 10, flowy,cell 1 2,growx");
		txtNomeCliente.setColumns(10);

		txtEnderecoCliente = new JTextField();
		txtEnderecoCliente.setBorder(null);
		txtEnderecoCliente.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtEnderecoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnderecoCliente.setForeground(new Color(128, 128, 128));
		txtEnderecoCliente.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtEnderecoCliente.setBounds(261, 110, 176, 20);
		panel_1.add(txtEnderecoCliente, "gap bottom 10, flowy,cell 1 2,growx");
		txtEnderecoCliente.setColumns(10);

		MaskFormatter cpfMask = null;
		MaskFormatter telefoneMask = null;

		try {
			cpfMask = new MaskFormatter("###.###.###-##");
			telefoneMask = new MaskFormatter("(##)#####-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ftxtTelefoneCliente = new JFormattedTextField(telefoneMask);
		ftxtTelefoneCliente.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtTelefoneCliente.setForeground(new Color(128, 128, 128));
		ftxtTelefoneCliente.setFont(new Font("Arial Black", Font.PLAIN, 11));
		ftxtTelefoneCliente.setColumns(10);
		ftxtTelefoneCliente.setBounds(261, 242, 176, 20);
		panel_1.add(ftxtTelefoneCliente, "gap bottom 10, cell 1 2,growx");

		ftxtCpfCliente = new JFormattedTextField(cpfMask);
		ftxtCpfCliente.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtCpfCliente.setForeground(new Color(128, 128, 128));
		ftxtCpfCliente.setFont(new Font("Arial Black", Font.PLAIN, 11));
		ftxtCpfCliente.setColumns(10);
		ftxtCpfCliente.setBounds(261, 242, 176, 20);
		panel_1.add(ftxtCpfCliente, "gap bottom 10, cell 1 2,growx");

		cbStatusCliente = new JComboBox<String>();
		cbStatusCliente.setModel(new DefaultComboBoxModel<String>(new String[] { "Ativo", "Inativo" }));
		cbStatusCliente.setForeground(new Color(128, 128, 128));
		cbStatusCliente.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cbStatusCliente.setBounds(261, 305, 176, 22);
		panel_1.add(cbStatusCliente, "gap bottom 10, cell 1 2,growx");

		JButton btnDeletarCliente = new JButton("Deletar");
		btnDeletarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnDeletarCliente.setPreferredSize(new Dimension(100, 40));
		btnDeletarCliente.setBackground(new Color(240, 240, 240));
		btnDeletarCliente.setBounds(148, 367, 104, 41);
		panel_1.add(btnDeletarCliente, "cell 1 3,growx");
		if (clienteSelecionado != null) {
			btnDeletarCliente.setVisible(true);
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

		} else {
			btnDeletarCliente.setVisible(false);
		}

		JButton btnCadastrarCliente = new JButton(clienteSelecionado == null ? "Cadastrar" : "Alterar");
		btnCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnCadastrarCliente.setBackground(new Color(240, 240, 240));
		btnCadastrarCliente.setIcon(null);
		btnCadastrarCliente.setBounds(261, 367, 124, 41);
		btnCadastrarCliente.setPreferredSize(new Dimension(100, 40));
		btnCadastrarCliente.setVisible(true);
		panel_1.add(btnCadastrarCliente, "cell 1 3");
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clienteSelecionado == null) {
						try {
							clienteController.cadastrarCliente();
						} catch (ExceptionDao e1) {
							JOptionPane.showMessageDialog(null, e1 + "Não foi possível converter os dados captados");
							e1.printStackTrace();
						}
				} else {
					try {
						clienteController.alterarCliente();
					} catch (ExceptionDao e1) {
						JOptionPane.showMessageDialog(null, e1 + "Não foi possível converter os dados captados");
						e1.printStackTrace();
					}
				}
			}
		});
		JButton btnConsultarCliente = new JButton("Consultar");
		btnConsultarCliente.setBounds(405, 368, 124, 41);
		panel_1.add(btnConsultarCliente, "cell 1 3,alignx right");
		btnConsultarCliente.setPreferredSize(new Dimension(100, 40));
		btnConsultarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnConsultarCliente.setBackground(new Color(240, 240, 240));
		btnConsultarCliente.setIcon(null);
		btnConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMenuPrincipal mainFrame = (TelaMenuPrincipal) SwingUtilities
						.getWindowAncestor(TelaClientePanel.this);
				JPanel desktop = mainFrame.getDesktop();
				desktop.removeAll();
				TelaConsultaCliente consulta = new TelaConsultaCliente();
				consulta.setVisible(true);
				desktop.add(consulta);
				desktop.revalidate();
				desktop.repaint();
			}
		});

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

		if (clienteSelecionado != null) {
			preencherCampos(clienteSelecionado);
		}
	}
	 /**
     * Preenche os campos da interface com as informações do cliente selecionado.
     * @param clienteSelecionado O cliente cujas informações serão exibidas nos campos da interface.
     * @throws ExceptionDao Se ocorrer um erro ao acessar os dados do cliente no banco de dados.
     */
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
