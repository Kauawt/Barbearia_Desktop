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
import javax.swing.SwingUtilities;

import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;
import controller.UsuarioController;
import dao.ExceptionDao;
import dao.ModuloConexao;
import dao.UsuarioDao;
import model.Usuario;
import model.Validador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Rectangle;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.LineBorder;
import view.JPictureBox.SizeMode;

public class TelaUsuario extends JInternalFrame {

	public Connection conexao = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	public JTextField txtNomeUsuario;
	private JTextField txtSalarioUsuario;
	public JTextField txtEmailUsuario;
	public JPasswordField txtSenhaUsuario;
	private JComboBox cbPerfilUsuario = new JComboBox();
	private JComboBox cbPerfilUsuarioPanel = new JComboBox();
	public JFormattedTextField ftxtCpfUsuario = new JFormattedTextField();
	public JFormattedTextField ftxtDataNascimentoUsuario = new JFormattedTextField();
	private JComboBox cbStatusUsuario = new JComboBox();
	private JComboBox cbStatusUsuarioPanel = new JComboBox();
	private UsuarioController usuarioController = new UsuarioController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuario frame = new TelaUsuario(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ExceptionDao
	 */
	public TelaUsuario(Usuario usuarioSelecionado) throws ExceptionDao {
		getContentPane().setBackground(new Color(232, 227, 225));

		setResizable(true);
		getContentPane().setSize(new Dimension(450, 480));
		getContentPane().setPreferredSize(new Dimension(450, 480));
		getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		getContentPane().addComponentListener(new ComponentAdapter() {
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
		panel_1.setBounds(0, 0, 640, 453);
		getContentPane().add(panel_1);
		panel_1.setLayout(new MigLayout("insets 0", "[200,grow][200,grow][200,grow]", "[grow,fill][grow,fill][grow,fill][grow,fill][grow,fill][grow,fill][grow,fill][grow,fill][grow,fill][grow,center][grow,center][grow][grow,fill]"));

		JButton btnConsultarUsuario = new JButton("Consultar");
		btnConsultarUsuario.setBounds(405, 368, 124, 41);
		getContentPane().add(btnConsultarUsuario);
		btnConsultarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaUsuario listarUsuarios = new TelaConsultaUsuario();
				JDesktopPane desktop = getDesktopPane();
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(desktop);
				if (frame instanceof TelaMenuPrincipal) {
					JInternalFrame[] frames = desktop.getAllFrames();
					for (JInternalFrame frame1 : frames) {
						frame1.dispose();
					}
				}
				desktop.add(listarUsuarios);
				listarUsuarios.setVisible(true);
			}
		});

		JLabel lblFormularioUsuario = new JLabel("Formulário Usuario");
		lblFormularioUsuario.setForeground(new Color(255, 255, 255));
		lblFormularioUsuario.setBounds(220, 57, 280, 32);
		getContentPane().add(lblFormularioUsuario);
		lblFormularioUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 22));

		JLabel lblNomeusuario = new JLabel("Nome");
		lblNomeusuario.setForeground(new Color(255, 255, 255));
		lblNomeusuario.setBounds(197, 108, 60, 21);
		getContentPane().add(lblNomeusuario);
		lblNomeusuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtNomeUsuario = new JTextField();
		if (usuarioSelecionado == null) {
			txtNomeUsuario = new PlaceholderTextField("NOME");
		}
		txtNomeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeUsuario.setForeground(new Color(128, 128, 128));
		txtNomeUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtNomeUsuario.setBounds(261, 110, 176, 20);
		getContentPane().add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);

		JLabel lblSalarioUsuario = new JLabel("Salario");
		lblSalarioUsuario.setForeground(new Color(255, 255, 255));
		lblSalarioUsuario.setBounds(185, 201, 68, 21);
		getContentPane().add(lblSalarioUsuario);
		lblSalarioUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtSalarioUsuario = new JTextField();
		txtSalarioUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		if (usuarioSelecionado == null) {
			txtSalarioUsuario = new PlaceholderTextField("SALÁRIO");
		}
		txtSalarioUsuario.setForeground(new Color(128, 128, 128));
		txtSalarioUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtSalarioUsuario.setBounds(261, 203, 176, 20);
		getContentPane().add(txtSalarioUsuario);
		txtSalarioUsuario.setColumns(10);

		JLabel lblDataNascimentoUsuario = new JLabel("Data Nascimento");
		lblDataNascimentoUsuario.setForeground(new Color(255, 255, 255));
		lblDataNascimentoUsuario.setBounds(121, 171, 136, 21);
		getContentPane().add(lblDataNascimentoUsuario);
		lblDataNascimentoUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblCpfUsuario = new JLabel("CPF");
		lblCpfUsuario.setForeground(new Color(255, 255, 255));
		lblCpfUsuario.setBounds(204, 139, 49, 21);
		getContentPane().add(lblCpfUsuario);
		lblCpfUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(202, 240, 49, 21);
		getContentPane().add(lblEmail);
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtEmailUsuario = new JTextField();
		txtEmailUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		if (usuarioSelecionado == null) {
			txtEmailUsuario = new PlaceholderTextField("E-MAIL");
		}
		txtEmailUsuario.setForeground(new Color(128, 128, 128));
		txtEmailUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtEmailUsuario.setColumns(10);
		txtEmailUsuario.setBounds(261, 242, 176, 20);
		getContentPane().add(txtEmailUsuario);

		JLabel lblSenhaUsuario = new JLabel("Senha");
		lblSenhaUsuario.setForeground(new Color(255, 255, 255));
		lblSenhaUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSenhaUsuario.setBounds(202, 272, 49, 21);
		getContentPane().add(lblSenhaUsuario);

		txtSenhaUsuario = new JPasswordField();
		txtSenhaUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenhaUsuario.setForeground(new Color(128, 128, 128));
		txtSenhaUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtSenhaUsuario.setColumns(10);
		txtSenhaUsuario.setBounds(261, 274, 176, 20);
		getContentPane().add(txtSenhaUsuario);

		JLabel lblPerfilUsuario = new JLabel("Perfil");
		lblPerfilUsuario.setForeground(new Color(255, 255, 255));
		lblPerfilUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPerfilUsuario.setBounds(202, 305, 49, 21);
		getContentPane().add(lblPerfilUsuario);

		cbPerfilUsuario.setForeground(new Color(128, 128, 128));
		cbPerfilUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cbPerfilUsuario.setBounds(261, 305, 176, 22);
		cbPerfilUsuario.setModel(new DefaultComboBoxModel(new String[] { "Administrador", "Funcionário" }));
		getContentPane().add(cbPerfilUsuario);

		MaskFormatter cpfMask = null;
		MaskFormatter dataMask = null;

		try {
			cpfMask = new MaskFormatter("###.###.###-##");
			dataMask = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ftxtCpfUsuario = new JFormattedTextField(cpfMask);
		ftxtCpfUsuario.setForeground(new Color(128, 128, 128));
		ftxtCpfUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		ftxtCpfUsuario.setBounds(261, 141, 176, 20);
		getContentPane().add(ftxtCpfUsuario);

		ftxtDataNascimentoUsuario = new JFormattedTextField(dataMask);
		ftxtDataNascimentoUsuario.setForeground(new Color(128, 128, 128));
		ftxtDataNascimentoUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		ftxtDataNascimentoUsuario.setBounds(261, 172, 176, 20);
		getContentPane().add(ftxtDataNascimentoUsuario);

		JLabel lblStatusUsuario = new JLabel("Status");
		lblStatusUsuario.setForeground(new Color(255, 255, 255));
		lblStatusUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblStatusUsuario.setBounds(202, 334, 49, 21);
		getContentPane().add(lblStatusUsuario);

		cbStatusUsuario.setForeground(new Color(128, 128, 128));
		cbStatusUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		if (usuarioSelecionado == null) {
			cbStatusUsuario.setModel(new DefaultComboBoxModel(new String[] { "Ativo" }));
		} else {
			cbStatusUsuario.setModel(new DefaultComboBoxModel(new String[] { "Ativo", "Inativo" }));
		}

		cbStatusUsuario.setBounds(261, 335, 176, 22);
		getContentPane().add(cbStatusUsuario);

		JButton btnCadastrarUsuario = new JButton(usuarioSelecionado == null ? "Cadastrar" : "Alterar");
		btnCadastrarUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarUsuario.setBackground(new Color(240, 240, 240));
		btnCadastrarUsuario.setIcon(null);
		btnCadastrarUsuario.setPreferredSize(new Dimension(80, 80));
		btnCadastrarUsuario.setBounds(261, 367, 124, 41);
		getContentPane().add(btnCadastrarUsuario);

		btnCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean validaUsuario = usuarioController.validadorCamposTelaUsuario(txtNomeUsuario.getText(),
							ftxtCpfUsuario.getText(), ftxtDataNascimentoUsuario.getText(), txtSalarioUsuario.getText(),
							txtEmailUsuario.getText(), txtSenhaUsuario.getText());
					if (validaUsuario == true) {
						double salarioUsuario = Double.parseDouble(txtSalarioUsuario.getText());
						if (usuarioSelecionado == null) {
							usuarioController.cadastrarUsuario(txtNomeUsuario.getText(), ftxtCpfUsuario.getText(),
									ftxtDataNascimentoUsuario.getText(), salarioUsuario, txtEmailUsuario.getText(),
									txtSenhaUsuario.getText(), cbPerfilUsuario.getSelectedItem().toString(),
									cbStatusUsuario.getSelectedItem().toString());
						} else {
							usuarioController.alterarUsuario(usuarioSelecionado.getCodUsuario(),
									txtNomeUsuario.getText(), ftxtCpfUsuario.getText(),
									ftxtDataNascimentoUsuario.getText(), salarioUsuario, txtEmailUsuario.getText(),
									txtSenhaUsuario.getText(), cbPerfilUsuario.getSelectedItem().toString(),
									cbStatusUsuario.getSelectedItem().toString());

						}
					}
				} catch (ParseException | ExceptionDao e1) {
					JOptionPane.showMessageDialog(null, e1 + "Não foi possível converter os dados captados");
					e1.printStackTrace();
				}
			}
		});
		JButton btnDeletarUsuario = new JButton("Deletar");
		btnDeletarUsuario.setPreferredSize(new Dimension(80, 80));
		btnDeletarUsuario.setBackground(UIManager.getColor("Button.background"));
		btnDeletarUsuario.setBounds(148, 367, 104, 41);
		getContentPane().add(btnDeletarUsuario);

		if (usuarioSelecionado != null) {
			btnDeletarUsuario.setVisible(true);
			btnDeletarUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UsuarioDao usuario = new UsuarioDao();
					try {
						usuario.deletarUsuario(usuarioSelecionado.getCodUsuario());
					} catch (ExceptionDao e1) {
						e1.printStackTrace();
					}

				}
			});

		} else {
			btnDeletarUsuario.setVisible(false);
		}

		MaskFormatter cpfMaskPanel = null;
		MaskFormatter dataMaskPanel = null;

		try {
			cpfMaskPanel = new MaskFormatter("###.###.###-##");
			dataMaskPanel = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (usuarioSelecionado == null) {
			cbStatusUsuarioPanel.setModel(new DefaultComboBoxModel(new String[] { "Ativo" }));
		} else {
			cbStatusUsuarioPanel.setModel(new DefaultComboBoxModel(new String[] { "Ativo", "Inativo" }));
		}

		JLabel lblFormularioUsuarioPanel = new JLabel("Formulário Usuario");
		lblFormularioUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblFormularioUsuarioPanel.setBounds(220, 57, 280, 32);
		panel_1.add(lblFormularioUsuarioPanel, "cell 1 0,alignx center,aligny center");
		lblFormularioUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		

		JLabel lblNomeusuarioPanel = new JLabel("Nome");
		lblNomeusuarioPanel.setForeground(new Color(255, 255, 255));
		lblNomeusuarioPanel.setBounds(197, 108, 60, 21);
		panel_1.add(lblNomeusuarioPanel, "cell 0 2,alignx right,aligny center");
		lblNomeusuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JTextField txtNomeUsuarioPanel = new JTextField();
		txtNomeUsuarioPanel.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeUsuarioPanel.setForeground(new Color(128, 128, 128));
		txtNomeUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtNomeUsuarioPanel.setBounds(261, 110, 176, 20);
		panel_1.add(txtNomeUsuarioPanel, "cell 1 2,growx,aligny center");
		txtNomeUsuarioPanel.setColumns(10);
		
		if (usuarioSelecionado == null) {
			txtNomeUsuarioPanel = new PlaceholderTextField("NOME");
		}
		
		JLabel lblCpfUsuarioPanel = new JLabel("CPF");
		lblCpfUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblCpfUsuarioPanel.setBounds(204, 139, 49, 21);
		panel_1.add(lblCpfUsuarioPanel, "cell 0 3,alignx right,aligny center");
		lblCpfUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		JFormattedTextField ftxtCpfUsuarioPanel = new JFormattedTextField(cpfMaskPanel);
		ftxtCpfUsuarioPanel.setForeground(new Color(128, 128, 128));
		ftxtCpfUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		ftxtCpfUsuarioPanel.setBounds(261, 141, 176, 20);
		panel_1.add(ftxtCpfUsuarioPanel, "cell 1 3,growx");

		JLabel lblDataNascimentoUsuarioPanel = new JLabel("Data Nascimento");
		lblDataNascimentoUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblDataNascimentoUsuarioPanel.setBounds(121, 171, 136, 21);
		panel_1.add(lblDataNascimentoUsuarioPanel, "cell 0 4,alignx right");
		lblDataNascimentoUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));


		JFormattedTextField ftxtDataNascimentoUsuarioPanel = new JFormattedTextField(dataMaskPanel);
		ftxtDataNascimentoUsuarioPanel.setForeground(new Color(128, 128, 128));
		ftxtDataNascimentoUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		ftxtDataNascimentoUsuarioPanel.setBounds(261, 172, 176, 20);
		panel_1.add(ftxtDataNascimentoUsuarioPanel, "cell 1 4,growx");

		JLabel lblSalarioUsuarioPanel = new JLabel("Salario");
		lblSalarioUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblSalarioUsuarioPanel.setBounds(185, 201, 68, 21);
		panel_1.add(lblSalarioUsuarioPanel, "cell 0 5,alignx right");
		lblSalarioUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JTextField txtSalarioUsuarioPanel = new JTextField();
		txtSalarioUsuarioPanel.setHorizontalAlignment(SwingConstants.CENTER);
		txtSalarioUsuarioPanel.setForeground(new Color(128, 128, 128));
		txtSalarioUsuarioPanel.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtSalarioUsuarioPanel.setBounds(261, 203, 176, 20);
		panel_1.add(txtSalarioUsuarioPanel, "cell 1 5,growx");
		txtSalarioUsuarioPanel.setColumns(10);
		if (usuarioSelecionado == null) {
			txtSalarioUsuarioPanel = new PlaceholderTextField("SALÁRIO");
		}
		JLabel lblEmailPanel = new JLabel("Email");
		lblEmailPanel.setForeground(new Color(255, 255, 255));
		lblEmailPanel.setBounds(202, 240, 49, 21);
		panel_1.add(lblEmailPanel, "cell 0 6,alignx right");
		lblEmailPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JTextField txtEmailUsuarioPanel = new JTextField();
		txtEmailUsuarioPanel.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmailUsuarioPanel.setForeground(new Color(128, 128, 128));
		txtEmailUsuarioPanel.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtEmailUsuarioPanel.setColumns(10);
		txtEmailUsuarioPanel.setBounds(261, 242, 176, 20);
		panel_1.add(txtEmailUsuarioPanel, "cell 1 6,growx");

		if (usuarioSelecionado == null) {
			txtEmailUsuarioPanel = new PlaceholderTextField("E-MAIL");
		}

		JLabel lblSenhaUsuarioPanel = new JLabel("Senha");
		lblSenhaUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblSenhaUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSenhaUsuarioPanel.setBounds(202, 272, 49, 21);
		panel_1.add(lblSenhaUsuarioPanel, "cell 0 7,alignx right");

		JPasswordField txtSenhaUsuarioPanel = new JPasswordField();
		txtSenhaUsuarioPanel.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenhaUsuarioPanel.setForeground(new Color(128, 128, 128));
		txtSenhaUsuarioPanel.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtSenhaUsuarioPanel.setColumns(10);
		txtSenhaUsuarioPanel.setBounds(261, 274, 176, 20);
		panel_1.add(txtSenhaUsuarioPanel, "cell 1 7,growx");

		JLabel lblPerfilUsuarioPanel = new JLabel("Perfil");
		lblPerfilUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblPerfilUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPerfilUsuarioPanel.setBounds(202, 305, 49, 21);
		panel_1.add(lblPerfilUsuarioPanel, "cell 0 8,alignx right");

		cbPerfilUsuarioPanel.setForeground(new Color(128, 128, 128));
		cbPerfilUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cbPerfilUsuarioPanel.setBounds(261, 305, 176, 22);
		cbPerfilUsuarioPanel.setModel(new DefaultComboBoxModel(new String[] { "Administrador", "Funcionário" }));
		panel_1.add(cbPerfilUsuarioPanel, "cell 1 8,growx");

		JLabel lblStatusUsuarioPanel = new JLabel("Status");
		lblStatusUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblStatusUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblStatusUsuarioPanel.setBounds(202, 334, 49, 21);
		panel_1.add(lblStatusUsuarioPanel, "cell 0 9,alignx right");

		cbStatusUsuarioPanel.setForeground(new Color(128, 128, 128));
		cbStatusUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		if (usuarioSelecionado == null) {
			cbStatusUsuarioPanel.setModel(new DefaultComboBoxModel(new String[] { "Ativo" }));
		} else {
			cbStatusUsuarioPanel.setModel(new DefaultComboBoxModel(new String[] { "Ativo", "Inativo" }));
		}
		cbStatusUsuarioPanel.setBounds(261, 335, 176, 22);
		panel_1.add(cbStatusUsuarioPanel, "cell 1 9,growx");

		JButton btnDeletarUsuarioPanel = new JButton("Deletar");
		btnDeletarUsuarioPanel.setPreferredSize(new Dimension(100, 40));
		btnDeletarUsuarioPanel.setBackground(UIManager.getColor("Button.background"));
		btnDeletarUsuarioPanel.setBounds(148, 367, 104, 41);
		panel_1.add(btnDeletarUsuarioPanel, "cell 0 11,alignx right");

		JButton btnCadastrarUsuarioPanel = new JButton(usuarioSelecionado == null ? "Cadastrar" : "Alterar");
		btnCadastrarUsuarioPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarUsuarioPanel.setBackground(new Color(240, 240, 240));
		btnCadastrarUsuarioPanel.setIcon(null);
		btnCadastrarUsuarioPanel.setBounds(261, 367, 124, 41);
		btnCadastrarUsuarioPanel.setPreferredSize(new Dimension(100, 40));
		panel_1.add(btnCadastrarUsuarioPanel, "cell 1 11,alignx center");

		btnCadastrarUsuarioPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean validaUsuario = usuarioController.validadorCamposTelaUsuario(txtNomeUsuario.getText(),
							ftxtCpfUsuario.getText(), ftxtDataNascimentoUsuario.getText(), txtSalarioUsuario.getText(),
							txtEmailUsuario.getText(), txtSenhaUsuario.getText());
					if (validaUsuario == true) {
						double salarioUsuario = Double.parseDouble(txtSalarioUsuario.getText());
						if (usuarioSelecionado == null) {
							usuarioController.cadastrarUsuario(txtNomeUsuario.getText(), ftxtCpfUsuario.getText(),
									ftxtDataNascimentoUsuario.getText(), salarioUsuario, txtEmailUsuario.getText(),
									txtSenhaUsuario.getText(), cbPerfilUsuario.getSelectedItem().toString(),
									cbStatusUsuario.getSelectedItem().toString());
						} else {
							usuarioController.alterarUsuario(usuarioSelecionado.getCodUsuario(),
									txtNomeUsuario.getText(), ftxtCpfUsuario.getText(),
									ftxtDataNascimentoUsuario.getText(), salarioUsuario, txtEmailUsuario.getText(),
									txtSenhaUsuario.getText(), cbPerfilUsuario.getSelectedItem().toString(),
									cbStatusUsuario.getSelectedItem().toString());

						}
					}
				} catch (ParseException | ExceptionDao e1) {
					JOptionPane.showMessageDialog(null, e1 + "Não foi possível converter os dados captados");
					e1.printStackTrace();
				}
			}
		});
		JButton btnConsultarUsuarioPanel = new JButton("Consultar");
		btnConsultarUsuarioPanel.setBounds(405, 368, 124, 41);
		panel_1.add(btnConsultarUsuarioPanel, "cell 2 11");
		btnConsultarUsuarioPanel.setPreferredSize(new Dimension(100, 40));
		btnConsultarUsuarioPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaUsuario listarUsuarios = new TelaConsultaUsuario();
				JDesktopPane desktop = getDesktopPane();
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(desktop);
				if (frame instanceof TelaMenuPrincipal) {
					JInternalFrame[] frames = desktop.getAllFrames();
					for (JInternalFrame frame1 : frames) {
						frame1.dispose();
					}
				}
				desktop.add(listarUsuarios);
				listarUsuarios.setVisible(true);
			}
		});

		if (usuarioSelecionado != null) {
			btnDeletarUsuarioPanel.setVisible(true);
			btnDeletarUsuarioPanel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UsuarioDao usuario = new UsuarioDao();
					try {
						usuario.deletarUsuario(usuarioSelecionado.getCodUsuario());
					} catch (ExceptionDao e1) {
						e1.printStackTrace();
					}

				}
			});
		} else {
			btnDeletarUsuario.setVisible(false);
		}
		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setBounds(0, 0, 640, 453);
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("insets 0", "[grow,fill]", "[grow,fill]"));
		getContentPane().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension newSize = getSize();
				panel.setSize(newSize);
				panel.revalidate();
				panel.repaint();
			}
		});
		JPictureBox pictureBox_1 = new JPictureBox();
		pictureBox_1.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/wallpaper_telas_maior.png")));
		pictureBox_1.setBounds(0, 0, 640, 453);
		panel.add(pictureBox_1, "cell 0 0,grow");
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBorder(null);
		setPreferredSize(new Dimension(640, 480));
		setRootPaneCheckingEnabled(false);
		setMinimumSize(new Dimension(450, 480));
		setTitle("Usuario");
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBounds(100, 100, 640, 480);

		if (usuarioSelecionado != null) {
			preencherCampos(usuarioSelecionado);
		}
	}

	private void preencherCampos(Usuario usuarioSelecionado) throws ExceptionDao {
		txtNomeUsuario.setText(usuarioSelecionado.getNomeUsuario());
		ftxtCpfUsuario.setText(usuarioSelecionado.getCpfUsuario());
		ftxtDataNascimentoUsuario
				.setText(usuarioSelecionado.converteDataBancoTela(usuarioSelecionado.getDataNascimentoUsuario()));
		txtEmailUsuario.setText(usuarioSelecionado.getEmailUsuario());
		txtSalarioUsuario.setText(usuarioSelecionado.getSalarioUsuario() + "");
		Usuario usuarioSenha = UsuarioDao.consultarUsuarioByCPF(usuarioSelecionado.getCpfUsuario().toString());
		String senha = usuarioSenha.getSenhaUsuario();
		txtSenhaUsuario.setText(senha);
		cbPerfilUsuario.setSelectedItem(usuarioSelecionado.getPerfilUsuario());
		cbStatusUsuario.setSelectedItem(usuarioSelecionado.getStatusUsuario());

	}
}
