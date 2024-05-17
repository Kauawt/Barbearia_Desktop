package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ModuloConexao;

import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Font;
import controller.LoginController;
public class TelaLogin extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
		LoginController loginController = new LoginController(this); // passando a propria view como parâ admetro
		setTitle("Login");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 176);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		txtUserUsuario = new JTextField();
		txtUserUsuario.setBounds(93, 8, 117, 20);
		txtUserUsuario.setColumns(10);

		JLabel lblUseUsuario = new JLabel("User");
		lblUseUsuario.setBounds(25, 5, 50, 22);
		lblUseUsuario.setFont(new Font("Arial Black", Font.PLAIN, 15));

		JLabel lblSenhaUsuario = new JLabel("Senha");
		lblSenhaUsuario.setBounds(25, 48, 59, 22);
		lblSenhaUsuario.setFont(new Font("Arial Black", Font.PLAIN, 15));

		JButton btnLoginUsuario = new JButton("Login");
		btnLoginUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnLoginUsuario.setBounds(97, 77, 96, 23);
		btnLoginUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginController.fazLogin();
			}
		});
		
		txtSenhaUsuario = new JPasswordField();
		txtSenhaUsuario.setBounds(94, 48, 117, 20);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 5, 0, 0);

		lblStatus = new JLabel("");
		lblStatus.setBounds(24, 84, 39, 42);
		lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/icones/notconnectedicon1.png")));

		lblConexao = new JLabel("Desconectado");
		lblConexao.setBounds(5, 123, 81, 18);
		lblConexao.setFont(new Font("Arial Black", Font.PLAIN, 10));
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(lblUseUsuario);
		contentPane.add(txtUserUsuario);
		contentPane.add(lblSenhaUsuario);
		contentPane.add(txtSenhaUsuario);
		contentPane.add(lblConexao);
		contentPane.add(lblStatus);
		contentPane.add(btnLoginUsuario);
		
		JLabel lblUserIcon = new JLabel("");
		lblUserIcon.setIcon(new ImageIcon(TelaLogin.class.getResource("/icones/usericone.png")));
		lblUserIcon.setBounds(220, 6, 59, 64);
		contentPane.add(lblUserIcon);
		
		loginController.verificaConexão();

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
	
