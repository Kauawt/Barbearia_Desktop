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
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;
import controller.UsuarioController;
import dao.ExceptionDao;
import dao.ModuloConexao;
import dao.UsuarioDao;
import model.Usuario;

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
import javax.swing.SwingConstants;
import javax.swing.UIManager;


public class TelaUsuario extends JInternalFrame {

	public Connection conexao = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private JTextField txtNomeUsuario;
	private JTextField txtSalarioUsuario;
	private JTextField txtEmailUsuario;
	private JTextField txtSenhaUsuario;
	private JComboBox cbPerfilUsuario = new JComboBox();
	private JFormattedTextField ftxtCpfUsuario = new JFormattedTextField();
	private JFormattedTextField ftxtDataNascimentoUsuario = new JFormattedTextField();
	private JComboBox cbStatusUsuario = new JComboBox();
	private UsuarioController usuarioController = new UsuarioController();
	private JButton btnDeletarUsuario;
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
		txtNomeUsuario.setText("Nome");
		txtNomeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeUsuario.setForeground(new Color(128, 128, 128));
		txtNomeUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
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
		txtSalarioUsuario.setText("Salário");
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
		txtEmailUsuario.setText("E-mail");
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

		txtSenhaUsuario = new JTextField();
		txtSenhaUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenhaUsuario.setText("Senha");
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
		cbPerfilUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
		
		cbPerfilUsuario.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Funcionário"}));

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
		ftxtCpfUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
		ftxtCpfUsuario.setText("CPF");
		ftxtCpfUsuario.setBounds(261, 141, 176, 20);
		getContentPane().add(ftxtCpfUsuario);

		ftxtDataNascimentoUsuario = new JFormattedTextField(dataMask);
		ftxtDataNascimentoUsuario.setForeground(new Color(128, 128, 128));
		ftxtDataNascimentoUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
		ftxtDataNascimentoUsuario.setText("");
		ftxtDataNascimentoUsuario.setBounds(261, 172, 176, 20);
		getContentPane().add(ftxtDataNascimentoUsuario);

		JLabel lblStatusUsuario = new JLabel("Status");
		lblStatusUsuario.setForeground(new Color(255, 255, 255));
		lblStatusUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblStatusUsuario.setBounds(202, 334, 49, 21);
		getContentPane().add(lblStatusUsuario);

		cbStatusUsuario.setForeground(new Color(128, 128, 128));
		cbStatusUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
		
		cbStatusUsuario.setModel(new DefaultComboBoxModel(new String[] {"Ativo", "Inativo"}));
		cbStatusUsuario.setBounds(261, 335, 176, 22);

		getContentPane().add(cbStatusUsuario);

		JButton btnCadastrarUsuario = new JButton(usuarioSelecionado == null ? "Cadastrar" : "Alterar");
		btnCadastrarUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarUsuario.setBackground(new Color(240, 240, 240));
		btnCadastrarUsuario.setIcon(null);
		btnCadastrarUsuario.setPreferredSize(new Dimension(80, 80));
		btnCadastrarUsuario.setBounds(286, 367, 124, 41);
		getContentPane().add(btnCadastrarUsuario);
		btnCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double salarioUsuario = Double.parseDouble(txtSalarioUsuario.getText());
				try {
					if (usuarioSelecionado == null) {
						usuarioController.cadastrarUsuario(txtNomeUsuario.getText(), ftxtCpfUsuario.getText(),
								ftxtDataNascimentoUsuario.getText(), salarioUsuario, txtEmailUsuario.getText(),
								txtSenhaUsuario.getText(), cbPerfilUsuario.getSelectedItem().toString(),
								cbStatusUsuario.getSelectedItem().toString());
					} else {
						usuarioController.alterarUsuario(txtNomeUsuario.getText(), ftxtCpfUsuario.getText(),
								ftxtDataNascimentoUsuario.getText(), salarioUsuario, txtEmailUsuario.getText(),
								txtSenhaUsuario.getText(), cbPerfilUsuario.getSelectedItem().toString(),
								cbStatusUsuario.getSelectedItem().toString());

					}
				} catch (ParseException | ExceptionDao e1) {
					JOptionPane.showMessageDialog(null, e1 + "Não foi possível converter os dados captados");
					e1.printStackTrace();
				}
			}
		});

		JButton btnConsultarUsuario = new JButton("Consultar");
		btnConsultarUsuario.setIcon(null);
		btnConsultarUsuario.setBounds(481, 104, 104, 32);
		getContentPane().add(btnConsultarUsuario);
		
		JPictureBox pictureBox = new JPictureBox();
		pictureBox.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/wallpaper_telas_maior.png")));
		pictureBox.setBounds(0, 0, 640, 453);
		getContentPane().add(pictureBox);
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
		JButton btnDeletarUsuario = new JButton("Deletar");
		btnDeletarUsuario.setPreferredSize(new Dimension(80, 80));
		btnDeletarUsuario.setBackground(UIManager.getColor("Button.background"));
		btnDeletarUsuario.setBounds(172, 366, 104, 41);
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
