package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ModuloConexao;
import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Font;
import controller.LoginController;
import javax.swing.SwingConstants;
import view.JPictureBox.SizeMode;
import java.awt.Frame;
import java.awt.Rectangle;
/**
 * Classe responsável pela interface de login do sistema.
 * Esta classe estende JFrame para criar uma janela de login.
 */
public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtUserUsuario;
	private JPasswordField txtSenhaUsuario;
	private JLabel lblConexao;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setLocationRelativeTo(frame);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * Create the frame.
	 */
	public TelaLogin() {
		getContentPane().setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		getContentPane().setPreferredSize(new Dimension(640, 480));
		setResizable(true);
		setMinimumSize(new Dimension(840, 600));

		setTitle("Login");
		addWindowListener(new WindowAdapter() {
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 879, 538);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		JPanel panel_1 = new JPanel();
		getContentPane().setLayout(null);
		getContentPane().add(panel_1);
		LoginController loginController = new LoginController(this); // passando a propria view como parametro

		setBackground(new Color(232, 227, 225));

		getContentPane().setSize(new Dimension(640, 480));

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
		panel_1.setLayout(new MigLayout("insets 0", "[200,grow][300][200,grow]",
				"[60,grow,fill][70,fill][70,fill][30,grow,top][grow,fill]"));

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setBounds(220, 57, 280, 32);
		panel_1.add(lblLogin, "cell 1 1,alignx center,aligny center");
		lblLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

		JLabel lblUseUsuario = new JLabel("User");
		lblUseUsuario.setForeground(new Color(255, 255, 255));
		lblUseUsuario.setBounds(197, 108, 60, 21);
		panel_1.add(lblUseUsuario, "flowy,cell 0 2,alignx right,aligny center,gapbottom 10");
		lblUseUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblSenhaUsuario = new JLabel("Senha");
		lblSenhaUsuario.setForeground(new Color(255, 255, 255));
		lblSenhaUsuario.setBounds(197, 108, 60, 21);
		panel_1.add(lblSenhaUsuario, "flowy,cell 0 2,alignx right,aligny center,gapbottom 10");
		lblSenhaUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtUserUsuario = new JTextField();
		txtUserUsuario.setBorder(null);
		txtUserUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtUserUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUserUsuario.setForeground(new Color(128, 128, 128));
		txtUserUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtUserUsuario.setText("");
		txtUserUsuario.setBounds(261, 110, 200, 20);
		txtUserUsuario.setColumns(10);
		panel_1.add(txtUserUsuario, "flowy,cell 1 2,gapbottom 10,grow");

		txtSenhaUsuario = new JPasswordField();
		txtSenhaUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenhaUsuario.setForeground(new Color(128, 128, 128));
		txtSenhaUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtSenhaUsuario.setColumns(10);
		txtSenhaUsuario.setBounds(261, 274, 176, 20);
		panel_1.add(txtSenhaUsuario, "cell 1 2,gapbottom 10,grow");
		txtSenhaUsuario.setText("");

		lblStatus = new JLabel("");
		lblStatus.setBounds(223, 238, 39, 42);
		lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/icones/notconnectedicon1.png")));
		panel_1.add(lblStatus, "flowy,cell 2 2");

		lblConexao = new JLabel("Desconectado");
		lblConexao.setForeground(new Color(255, 255, 255));
		lblConexao.setBounds(207, 277, 81, 18);
		lblConexao.setFont(new Font("Arial Black", Font.PLAIN, 10));
		panel_1.add(lblConexao, "cell 2 2");

		JButton btnLoginUsuario = new JButton("Login");
		btnLoginUsuario.setBounds(405, 368, 124, 41);
		panel_1.add(btnLoginUsuario, "cell 1 3");
		btnLoginUsuario.setPreferredSize(new Dimension(100, 40));
		btnLoginUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnLoginUsuario.setBackground(new Color(240, 240, 240));
		btnLoginUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnLoginUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginController.fazLogin();

			}
		});
		panel_1.add(btnLoginUsuario, "cell 1 3");

		loginController.verificaConexão();

		JPictureBox pictureBox = new JPictureBox();

		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setBounds(0, 0, 640, 480);
		getContentPane().add(panel);
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

		pictureBox.setIcon(new ImageIcon(TelaUsuarioPanel.class.getResource("/icones/loginWallpaper.png")));
		pictureBox.setBounds(0, 0, 640, 453);
		panel.add(pictureBox, "cell 0 0,grow");
	}

	public JTextField getTxtUserUsuario() {
		return txtUserUsuario;
	}

	public void setTxtUserUsuario(JTextField txtUserUsuario) {
		this.txtUserUsuario = txtUserUsuario;
	}

	public JPasswordField getTxtSenhaUsuario() {
		return txtSenhaUsuario;
	}

	public void setTxtSenhaUsuario(JPasswordField txtSenhaUsuario) {
		this.txtSenhaUsuario = txtSenhaUsuario;
	}

	public JLabel getLblConexao() {
		return lblConexao;
	}

	public void setLblConexao(JLabel lblConexao) {
		this.lblConexao = lblConexao;
	}

	public JLabel getLblStatus() {
		return lblStatus;
	}

	public void setLblStatus(JLabel lblStatus) {
		this.lblStatus = lblStatus;
	}
}
