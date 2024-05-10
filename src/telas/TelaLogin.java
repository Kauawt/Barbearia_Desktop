package telas;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexao.ModuloConexao;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
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
	 * Create the frame.
	 */
	public TelaLogin() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		txtUserUsuario = new JTextField();
		txtUserUsuario.setBounds(106, 25, 117, 20);
		txtUserUsuario.setColumns(10);
		
		JLabel lblUseUsuario = new JLabel("User");
		lblUseUsuario.setBounds(38, 22, 50, 22);
		lblUseUsuario.setFont(new Font("Arial Black", Font.PLAIN, 15));
		
		JLabel lblSenhaUsuario = new JLabel("Senha");
		lblSenhaUsuario.setBounds(38, 65, 59, 22);
		lblSenhaUsuario.setFont(new Font("Arial Black", Font.PLAIN, 15));
		
		JButton btnLoginUsuario = new JButton("Login");
		btnLoginUsuario.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnLoginUsuario.setBounds(71, 98, 96, 23);
		btnLoginUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		
		txtSenhaUsuario = new JPasswordField();
		txtSenhaUsuario.setBounds(107, 65, 117, 20);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 5, 0, 0);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setBounds(291, 11, 48, 48);
		lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/icones/notconnectedicon.png")));
		
		lblConexao = new JLabel("Banco Desconectado");
		lblConexao.setBounds(249, 65, 138, 18);
		lblConexao.setFont(new Font("Arial Black", Font.PLAIN, 12));
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(lblUseUsuario);
		contentPane.add(txtUserUsuario);
		contentPane.add(lblSenhaUsuario);
		contentPane.add(txtSenhaUsuario);
		contentPane.add(lblConexao);
		contentPane.add(lblStatus);
		contentPane.add(btnLoginUsuario);
		
		
		// conecta com banco
		
		conexao = ModuloConexao.conector();
		if(conexao != null) {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/connectedicon.png")));
			lblConexao.setText("Banco Conectado");
			
		}else {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/notconnectedicon.png")));
			lblConexao.setText("Banco Desconectado");
		}
		
	}
	public void logar(){
		String sql = "select * from tbBarbeiro where userUsuario=? and senhaUsuario=?";
		try {
			//preparar banco para consulta em função do que foi digitado nas caixas de texto.
			//O ? é substituido pelo conteeúdo das variáveis
			
			pst = conexao.prepareStatement(sql);
			pst.setString(1,txtUserUsuario.getText());
			pst.setString(2,txtSenhaUsuario.getText());
			
			//executa a Query
		    rs = pst.executeQuery();
		    
			//se existir usuário e senha correspondente
			if(rs.next()){
				TelaMenuPrincipal principal = new TelaMenuPrincipal();
				principal.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
