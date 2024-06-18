package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.ComponentOrientation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.text.MaskFormatter;

import controller.UsuarioController;
import dao.ExceptionDao;
import dao.UsuarioDao;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TelaUsuarioPanel extends JPanel {

	public Connection conexao = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private JComboBox cbPerfilUsuarioPanel = new JComboBox();
	public JFormattedTextField ftxtCpfUsuarioPanel = new JFormattedTextField();
	public JFormattedTextField ftxtDataNascimentoUsuarioPanel = new JFormattedTextField();
	private JComboBox cbStatusUsuarioPanel = new JComboBox();
	private UsuarioController usuarioController = new UsuarioController();
	JTextField txtNomeUsuarioPanel = new JTextField();
	JTextField txtSalarioUsuarioPanel = new JTextField();
	JTextField txtEmailUsuarioPanel = new JTextField();
	JPasswordField txtSenhaUsuarioPanel = new JPasswordField();
	JPictureBox pictureBox_1 = new JPictureBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuarioPanel frame = new TelaUsuarioPanel(null);
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
	public TelaUsuarioPanel(Usuario usuarioSelecionado) throws ExceptionDao {
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
		panel_1.setLayout(new MigLayout("insets 0", "[200,grow][::300,grow][200,grow]", "[grow,fill][grow,fill][grow,fill][grow][grow,fill]"));
	
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

		if (usuarioSelecionado == null) {
			txtNomeUsuarioPanel = new PlaceholderTextField("NOME");
		}
		if (usuarioSelecionado == null) {
			txtSalarioUsuarioPanel = new PlaceholderTextField("SALARIO");
		}

		if (usuarioSelecionado == null) {
			txtEmailUsuarioPanel = new PlaceholderTextField("E-MAIL");
		}

		JLabel lblFormularioUsuarioPanel = new JLabel("Formulário Usuario");
		lblFormularioUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblFormularioUsuarioPanel.setBounds(220, 57, 280, 32);
		panel_1.add(lblFormularioUsuarioPanel, "cell 1 1,alignx center,aligny center");
		lblFormularioUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

		JLabel lblNomeusuarioPanel = new JLabel("Nome");
		lblNomeusuarioPanel.setForeground(new Color(255, 255, 255));
		lblNomeusuarioPanel.setBounds(197, 108, 60, 21);
		panel_1.add(lblNomeusuarioPanel, "flowy,cell 0 2,alignx right,aligny center");
		lblNomeusuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblCpfUsuarioPanel = new JLabel("CPF");
		lblCpfUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblCpfUsuarioPanel.setBounds(204, 139, 49, 21);
		panel_1.add(lblCpfUsuarioPanel, "cell 0 2,alignx right,aligny center");
		lblCpfUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblDataNascimentoUsuarioPanel = new JLabel("Data Nascimento");
		lblDataNascimentoUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblDataNascimentoUsuarioPanel.setBounds(121, 171, 136, 21);
		panel_1.add(lblDataNascimentoUsuarioPanel, "cell 0 2,alignx right");
		lblDataNascimentoUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblSalarioUsuarioPanel = new JLabel("Salario");
		lblSalarioUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblSalarioUsuarioPanel.setBounds(185, 201, 68, 21);
		panel_1.add(lblSalarioUsuarioPanel, "cell 0 2,alignx right");
		lblSalarioUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		
		JLabel lblEmailPanel = new JLabel("Email");
		lblEmailPanel.setForeground(new Color(255, 255, 255));
		lblEmailPanel.setBounds(202, 240, 49, 21);
		panel_1.add(lblEmailPanel, "cell 0 2,alignx right");
		lblEmailPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblSenhaUsuarioPanel = new JLabel("Senha");
		lblSenhaUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblSenhaUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSenhaUsuarioPanel.setBounds(202, 272, 49, 21);
		panel_1.add(lblSenhaUsuarioPanel, "cell 0 2,alignx right");
		
		txtNomeUsuarioPanel.setBorder(null);
		txtNomeUsuarioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtNomeUsuarioPanel.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeUsuarioPanel.setForeground(new Color(128, 128, 128));
		txtNomeUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtNomeUsuarioPanel.setBounds(261, 110, 176, 20);
		panel_1.add(txtNomeUsuarioPanel, "gap bottom 10, flowy,cell 1 2,growx");
		txtNomeUsuarioPanel.setColumns(10);
		
		ftxtCpfUsuarioPanel = new JFormattedTextField(cpfMaskPanel);
		ftxtCpfUsuarioPanel.setForeground(new Color(128, 128, 128));
		ftxtCpfUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		ftxtCpfUsuarioPanel.setBounds(261, 141, 176, 20);
		panel_1.add(ftxtCpfUsuarioPanel, "gap bottom 10, cell 1 2,growx");

		ftxtDataNascimentoUsuarioPanel = new JFormattedTextField(dataMaskPanel);
		ftxtDataNascimentoUsuarioPanel.setForeground(new Color(128, 128, 128));
		ftxtDataNascimentoUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		ftxtDataNascimentoUsuarioPanel.setBounds(261, 172, 176, 20);
		panel_1.add(ftxtDataNascimentoUsuarioPanel, "gap bottom 10, cell 1 2,growx");

		txtSalarioUsuarioPanel.setHorizontalAlignment(SwingConstants.CENTER);
		txtSalarioUsuarioPanel.setForeground(new Color(128, 128, 128));
		txtSalarioUsuarioPanel.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtSalarioUsuarioPanel.setBounds(261, 203, 176, 20);
		panel_1.add(txtSalarioUsuarioPanel, "gap bottom 10, cell 1 2,growx");
		txtSalarioUsuarioPanel.setColumns(10);

		txtEmailUsuarioPanel.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmailUsuarioPanel.setForeground(new Color(128, 128, 128));
		txtEmailUsuarioPanel.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtEmailUsuarioPanel.setColumns(10);
		txtEmailUsuarioPanel.setBounds(261, 242, 176, 20);
		panel_1.add(txtEmailUsuarioPanel, "gap bottom 10, cell 1 2,growx");

		txtSenhaUsuarioPanel.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenhaUsuarioPanel.setForeground(new Color(128, 128, 128));
		txtSenhaUsuarioPanel.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtSenhaUsuarioPanel.setColumns(10);
		txtSenhaUsuarioPanel.setBounds(261, 274, 176, 20);
		panel_1.add(txtSenhaUsuarioPanel, "gap bottom 10, cell 1 2,growx");


		JButton btnDeletarUsuarioPanel = new JButton("Deletar");
		btnDeletarUsuarioPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnDeletarUsuarioPanel.setPreferredSize(new Dimension(100, 40));
		btnDeletarUsuarioPanel.setBackground(UIManager.getColor("Button.background"));
		btnDeletarUsuarioPanel.setBounds(148, 367, 104, 41);
		panel_1.add(btnDeletarUsuarioPanel, "flowx,cell 1 3,alignx right");
		
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
			btnDeletarUsuarioPanel.setVisible(false);
		}
		JButton btnCadastrarUsuarioPanel = new JButton(usuarioSelecionado == null ? "Cadastrar" : "Alterar");
		btnCadastrarUsuarioPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnCadastrarUsuarioPanel.setBackground(new Color(240, 240, 240));
		btnCadastrarUsuarioPanel.setIcon(null);
		btnCadastrarUsuarioPanel.setBounds(261, 367, 124, 41);
		btnCadastrarUsuarioPanel.setPreferredSize(new Dimension(100, 40));
		panel_1.add(btnCadastrarUsuarioPanel, "cell 1 3,alignx center");

		btnCadastrarUsuarioPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean validaUsuario = usuarioController.validadorCamposTelaUsuario(txtNomeUsuarioPanel.getText(),
							ftxtCpfUsuarioPanel.getText(), ftxtDataNascimentoUsuarioPanel.getText(),
							txtSalarioUsuarioPanel.getText(), txtEmailUsuarioPanel.getText(),
							txtSenhaUsuarioPanel.getText());
					if (validaUsuario == true) {
						double salarioUsuario = Double.parseDouble(txtSalarioUsuarioPanel.getText());
						if (usuarioSelecionado == null) {
							usuarioController.cadastrarUsuario(txtNomeUsuarioPanel.getText(),
									ftxtCpfUsuarioPanel.getText(), ftxtDataNascimentoUsuarioPanel.getText(),
									salarioUsuario, txtEmailUsuarioPanel.getText(), txtSenhaUsuarioPanel.getText(),
									cbPerfilUsuarioPanel.getSelectedItem().toString(),
									cbStatusUsuarioPanel.getSelectedItem().toString());
						} else {
							usuarioController.alterarUsuario(usuarioSelecionado.getCodUsuario(),
									txtNomeUsuarioPanel.getText(), ftxtCpfUsuarioPanel.getText(),
									ftxtDataNascimentoUsuarioPanel.getText(), salarioUsuario,
									txtEmailUsuarioPanel.getText(), txtSenhaUsuarioPanel.getText(),
									cbPerfilUsuarioPanel.getSelectedItem().toString(),
									cbStatusUsuarioPanel.getSelectedItem().toString());

						}
					}
				} catch (ParseException | ExceptionDao e1) {
					JOptionPane.showMessageDialog(null, e1 + "Não foi possível converter os dados captados");
					e1.printStackTrace();
				}
			}
		});

		cbPerfilUsuarioPanel.setForeground(new Color(128, 128, 128));
		cbPerfilUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cbPerfilUsuarioPanel.setBounds(261, 305, 176, 22);
		cbPerfilUsuarioPanel.setModel(new DefaultComboBoxModel(new String[] { "Administrador", "Funcionário" }));
		panel_1.add(cbPerfilUsuarioPanel, "gap bottom 10, cell 1 2,growx");

		JLabel lblPerfilUsuarioPanel = new JLabel("Perfil");
		lblPerfilUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblPerfilUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPerfilUsuarioPanel.setBounds(202, 305, 49, 21);
		panel_1.add(lblPerfilUsuarioPanel, "gap bottom 10, cell 0 2,alignx right");

		cbStatusUsuarioPanel.setForeground(new Color(128, 128, 128));
		cbStatusUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cbStatusUsuarioPanel.setBounds(261, 335, 176, 22);
		panel_1.add(cbStatusUsuarioPanel, "cell 1 2,growx");

		JLabel lblStatusUsuarioPanel = new JLabel("Status");
		lblStatusUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblStatusUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblStatusUsuarioPanel.setBounds(202, 334, 49, 21);
		panel_1.add(lblStatusUsuarioPanel, "cell 0 2,alignx right");
		
		JButton btnConsultarUsuarioPanel = new JButton("Consultar");
		btnConsultarUsuarioPanel.setBounds(405, 368, 124, 41);
		panel_1.add(btnConsultarUsuarioPanel, "cell 1 3");
		btnConsultarUsuarioPanel.setPreferredSize(new Dimension(100, 40));
		btnConsultarUsuarioPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnConsultarUsuarioPanel.setBackground(new Color(240, 240, 240));
		btnConsultarUsuarioPanel.setIcon(null);
		btnConsultarUsuarioPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMenuPrincipal mainFrame = (TelaMenuPrincipal) SwingUtilities
						.getWindowAncestor(TelaUsuarioPanel.this);
				JPanel desktop = mainFrame.getDesktop();
				desktop.removeAll();
				TelaConsultaUsuario consulta = new TelaConsultaUsuario();
				consulta.setVisible(true);
				desktop.add(consulta);
				desktop.revalidate();
				desktop.repaint();
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
			btnDeletarUsuarioPanel.setVisible(false);
		}
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

		if (usuarioSelecionado != null) {
			preencherCampos(usuarioSelecionado);
		}
	}

	private void preencherCampos(Usuario usuarioSelecionado) throws ExceptionDao {
		txtNomeUsuarioPanel.setText(usuarioSelecionado.getNomeUsuario());
		ftxtCpfUsuarioPanel.setText(usuarioSelecionado.getCpfUsuario());
		ftxtDataNascimentoUsuarioPanel
				.setText(usuarioSelecionado.converteDataBancoTela(usuarioSelecionado.getDataNascimentoUsuario()));
		txtEmailUsuarioPanel.setText(usuarioSelecionado.getEmailUsuario());
		txtSalarioUsuarioPanel.setText(usuarioSelecionado.getSalarioUsuario() + "");
		Usuario usuarioSenha = UsuarioDao.consultarUsuarioByCPF(usuarioSelecionado.getCpfUsuario().toString());
		String senha = usuarioSenha.getSenhaUsuario();
		txtSenhaUsuarioPanel.setText(senha);
		cbPerfilUsuarioPanel.setSelectedItem(usuarioSelecionado.getPerfilUsuario());
		cbStatusUsuarioPanel.setSelectedItem(usuarioSelecionado.getStatusUsuario());

	}
}
