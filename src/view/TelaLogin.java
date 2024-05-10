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

public class TelaLogin extends JFrame {

	public Connection conexao = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserUsuario;
	private JPasswordField txtSenhaUsuario;
	private JLabel lblConexao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
				logar();
			}
		});
		
		txtSenhaUsuario = new JPasswordField();
		txtSenhaUsuario.setBounds(94, 48, 117, 20);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 5, 0, 0);

		JLabel lblStatus = new JLabel("");
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

		// conecta com banco

		conexao = ModuloConexao.conector();
		if (conexao != null) {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/connectedicon1.png")));
			lblConexao.setText("Conectado");

		} else {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/notconnectedicon1.png")));
			lblConexao.setText("Desconectado");
		}

	}
	public void logar() {
		String sql = "select * from tbUsuario where userUsuario=? and senhaUsuario=?";
		try {
			// preparar banco para consulta em função do que foi digitado nas caixas de
			// texto.
			// O ? é substituido pelo conteeúdo das variáveis 

			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtUserUsuario.getText()); //o numero é 1 do setString e referente a primeira posição do?
			pst.setString(2, txtSenhaUsuario.getText()); //o numero é 2 do setString e referente a segunda posição do?

			// executa a Query
			rs = pst.executeQuery();
			if (rs.next()) {
				// verifica o perfil do usuario
				String perfil = rs.getString(8);
				if (perfil.equals("Admin")) { // uso do equal porque é String
					TelaMenuPrincipal principal = new TelaMenuPrincipal();
					principal.setVisible(true);
					principal.mntmCadastrarUsuario.setEnabled(true);
					principal.mntmRelatorioAgendamento.setEnabled(true); // libera a opção de gerar relatorio
					principal.lblUser.setText(rs.getString(6));
					principal.lblUser.setForeground(Color.red);
					//formatar a data do sistema de dia e hora para apenas dia
					Date data = new Date();
					DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
					principal.lblData.setText(formatador.format(data).toString());
					this.dispose();
				}
				else {
					TelaMenuPrincipal principal = new TelaMenuPrincipal();
					principal.setVisible(true);
					principal.lblUser.setText(rs.getString(6));
					//formatar a data do sistema de dia e hora para apenas dia
					Date data = new Date();
					DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
					principal.lblData.setText(formatador.format(data).toString());
					this.dispose();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
			}
		}catch(

	Exception e)
	{
		JOptionPane.showMessageDialog(null, e);
	}
}	
}
