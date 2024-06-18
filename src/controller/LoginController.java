package controller;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import dao.ModuloConexao;
import view.TelaLogin;
import view.TelaMenuPrincipal;
/**
 * Controlador responsável pela lógica de login no sistema.
 * Gerencia a interação entre a tela de login (TelaLogin) e a tela do menu principal (TelaMenuPrincipal).
 */
public class LoginController {
	
	private final TelaLogin telaLogin;
	private final TelaMenuPrincipal principal;
	private Connection conexao = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	 /**
     * Construtor da classe LoginController.
     * @param telaLogin A tela de login associada a este controlador.
     */
	public LoginController(TelaLogin telaLogin) {
		this.telaLogin =telaLogin;
		this.principal = new TelaMenuPrincipal(); // Instância  um objeto direto

	}
	
	/**
	 * Verifica a conexão com o banco e devolve uma imagem de sucesso e falha
	 */
	public void verificaConexão() {
		conexao = ModuloConexao.conector(); // é static precisa ter uma variavel para receber valor. Caso ocorra algum re
		
		if (conexao != null) {
		telaLogin.getLblStatus().setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/connectedicon1.png")));
		telaLogin.getLblConexao().setText("Conectado");

		} else {
		telaLogin.getLblStatus().setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/notconnectedicon1.png")));
		telaLogin.getLblConexao().setText("Desconectado");
		}
		
	}
	
	/**
	 * Faz a validação do usuário com base no banco de dados
	 */
	
	public void fazLogin() { 
		try {
			// preparar banco para consulta em função do que foi digitado nas caixas de
			// texto.
			// O ? é substituido pelo conteeúdo das variáveis 
			
			Criptografia criptografia = new Criptografia(telaLogin.getTxtSenhaUsuario().getText(), Criptografia.MD5);
			
			String nome = telaLogin.getTxtUserUsuario().getText();
			//String senha = telaLogin.getTxtSenhaUsuario().getText();
			String senha = criptografia.criptografar();;
			String sql = "select * from tbUsuario where emailUsuario=? and senhaUsuario=? and statusUsuario = 'Ativo'";
			System.out.println(senha);
			pst = conexao.prepareStatement(sql);
			pst.setString(1, nome); //o numero é 1 do setString e referente a primeira posição do?
			pst.setString(2, senha); //o numero é 2 do setString e referente a segunda posição do?
			rs = pst.executeQuery();
			if (rs.next()) {
				// verifica o perfil do usuario
				String perfil = rs.getString(8);
				if (perfil.equals("Administrador")) { // uso do equal porque é String
					principal.setLocationRelativeTo(principal);
					principal.setVisible(true);
					principal.getMntmCadastrarUsuario().setEnabled(true);
					principal.getMntmRelatorioAgendamento().setEnabled(true); // libera a opção de gerar relatorio
					principal.getLblUser().setText(rs.getString(8));
					principal.getLblUser().setForeground(Color.red);
					//formatar a data do sistema de dia e hora para apenas dia
					Date data = new Date();
					DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
					principal.getLblData().setText(formatador.format(data).toString());
					telaLogin.dispose();
					
				}
				else {
					TelaMenuPrincipal principal = new TelaMenuPrincipal();
					principal.setVisible(true);
					principal.getMntmCadastrarUsuario().setEnabled(false);
					principal.getMntmConsultaUsuario().setEnabled(false);
					principal.setLocationRelativeTo(principal);
					principal.getLblUser().setText(rs.getString(6));
					//formatar a data do sistema de dia e hora para apenas dia
					Date data = new Date();
					DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
					principal.getLblData().setText(formatador.format(data).toString());
					telaLogin.dispose();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
			}
		}catch(	Exception e){
		JOptionPane.showMessageDialog(null, e);
	}
		
		
	}
}
