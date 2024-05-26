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

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 655, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		txtUserUsuario = new JTextField();
		txtUserUsuario.setText("admin@gmail.com");
		txtUserUsuario.setBounds(315, 165, 117, 20);
		txtUserUsuario.setColumns(10);

		JLabel lblUseUsuario = new JLabel("User");
		lblUseUsuario.setForeground(new Color(255, 255, 255));
		lblUseUsuario.setBounds(247, 162, 50, 22);
		lblUseUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblSenhaUsuario = new JLabel("Senha");
		lblSenhaUsuario.setForeground(new Color(255, 255, 255));
		lblSenhaUsuario.setBounds(247, 205, 59, 22);
		lblSenhaUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JButton btnLoginUsuario = new JButton("Login");
		btnLoginUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnLoginUsuario.setBounds(315, 236, 117, 25);
		btnLoginUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginController.fazLogin();
				
			}
		});
		
		txtSenhaUsuario = new JPasswordField();
		txtSenhaUsuario.setText("admin");
		txtSenhaUsuario.setBounds(316, 205, 117, 20);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 5, 0, 0);

		lblStatus = new JLabel("");
		lblStatus.setBounds(223, 238, 39, 42);
		lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/icones/notconnectedicon1.png")));
		
		lblConexao = new JLabel("Desconectado");
		lblConexao.setForeground(new Color(255, 255, 255));
		lblConexao.setBounds(207, 277, 81, 18);
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
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		lblLogin.setBounds(307, 124, 111, 27);
		contentPane.add(lblLogin);
		
		JPictureBox pictureBox = new JPictureBox();
		pictureBox.setIcon(new ImageIcon(TelaLogin.class.getResource("/icones/wallpaper_login.png")));
		pictureBox.setBounds(5, 5, 634, 406);
		contentPane.add(pictureBox);
		
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
	
