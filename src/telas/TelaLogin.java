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

public class TelaLogin extends JFrame {
	
	public Connection conexao = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserUsuario;
	private JPasswordField txtSenhaUsuario;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		txtUserUsuario = new JTextField();
		txtUserUsuario.setColumns(10);
		
		JLabel lblUseUsuario = new JLabel("User");
		
		JLabel lblSenhaUsuario = new JLabel("Senha");
		
		JButton btnLoginUsuario = new JButton("Login");
		btnLoginUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		
		txtSenhaUsuario = new JPasswordField();
		
		JLabel lblNewLabel = new JLabel("");
		
		JLabel lblStatus = new JLabel("---");
		lblStatus.setIcon(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSenhaUsuario, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUseUsuario, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStatus))
							.addGap(49)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnLoginUsuario)
								.addComponent(txtUserUsuario)
								.addComponent(txtSenhaUsuario)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel)))
					.addContainerGap(203, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(62)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblUseUsuario)
							.addGap(26)
							.addComponent(lblSenhaUsuario))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtUserUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtSenhaUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnLoginUsuario)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel))
						.addComponent(lblStatus))
					.addGap(23))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		// conecta com banco
		
		conexao = ModuloConexao.conector();
		if(conexao != null) {
			lblStatus.setText("Conectou");
		}else {
			lblStatus.setText("Não Conectou");
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
				TelaPrincipal principal = new TelaPrincipal();
				principal.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
