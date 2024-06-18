package model;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;

import dao.ExceptionDao;
import dao.ModuloConexao;
import dao.UsuarioDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 * Classe que representa um gerente, que é um tipo especializado de usuário no sistema.
 * Herda todas as características básicas de um usuário e possui métodos específicos para gerenciar outros usuários.
 */
public class Gerente extends Usuario{
	public UsuarioDao usuarioDao = new UsuarioDao();
	/**
     * Construtor para criar um novo gerente com todas as informações.
     *
     * @param nomeUsuario Nome do gerente
     * @param cpfUsuario CPF do gerente
     * @param dataNascimentoUsuario Data de nascimento do gerente
     * @param salarioUsuario Salário do gerente
     * @param emailUsuario Email do gerente
     * @param senhaUsuario Senha do gerente
     * @param perfilUsuario Perfil do gerente
     * @param statusUsuario Status do gerente
     */
	public Gerente(String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario, double salarioUsuario,
			String emailUsuario, String senhaUsuario, String perfilUsuario, String statusUsuario) {
		super(nomeUsuario, cpfUsuario, dataNascimentoUsuario, salarioUsuario, emailUsuario, senhaUsuario, perfilUsuario, statusUsuario);
	}
	 /**
     * Construtor para criar um gerente com código identificador e todas as informações.
     *
     * @param codUsuario Código identificador único do gerente
     * @param nomeUsuario Nome do gerente
     * @param cpfUsuario CPF do gerente
     * @param dataNascimentoUsuario Data de nascimento do gerente
     * @param salarioUsuario Salário do gerente
     * @param emailUsuario Email do gerente
     * @param senhaUsuario Senha do gerente
     * @param perfilUsuario Perfil do gerente
     * @param statusUsuario Status do gerente
     */
	public Gerente(int codUsuario, String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario, double salarioUsuario,
			String emailUsuario, String senhaUsuario, String perfilUsuario, String statusUsuario) {
		super(codUsuario, nomeUsuario, cpfUsuario, dataNascimentoUsuario, salarioUsuario, emailUsuario, senhaUsuario, perfilUsuario, statusUsuario);
	}
	
	/**
     * Método para cadastrar um novo usuário no sistema.
     *
     * @param usuario Objeto do tipo Usuario a ser cadastrado
     * @throws ExceptionDao Exceção lançada em caso de erro no acesso aos dados
     */
	public void cadastrarUsuario(Usuario usuario) throws ExceptionDao {
		new UsuarioDao().cadastrarUsuario(usuario);
	}
	/**
     * Método para alterar informações de um usuário existente no sistema.
     *
     * @param usuario Objeto do tipo Usuario com as novas informações
     * @throws ExceptionDao Exceção lançada em caso de erro no acesso aos dados
     */
	public void alterarUsuario(Usuario usuario) throws ExceptionDao {
		usuarioDao.alterarUsuario(codUsuario, usuario);
	}
	/**
	 * Método estático para gerar e exibir um relatório utilizando JasperReports.
	 * O relatório é compilado a partir de um arquivo JRXML localizado no diretório específico do projeto.
	 * Utiliza a conexão estabelecida com o banco de dados através de ModuloConexao.
	 */
	public static void GeraRelatorio() {
		Connection conexao = ModuloConexao.conector();
		
		File file = new File("GeraRelatorio.java");
		String pathAbsoluto = file.getAbsolutePath();
		
		String pathAbsolutoParcial = pathAbsoluto.substring(0,pathAbsoluto.lastIndexOf('\\'))+"\\relatorios\\Coffee.jrxml";
		
		try {
			JasperReport jasperreport = JasperCompileManager.compileReport(pathAbsolutoParcial);
			
			JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport,new HashMap<>(), conexao);
			
			JasperViewer jasperviewer = new JasperViewer(jasperprint, false);
			
			jasperviewer.setVisible(true);
			
			ModuloConexao.fecharConexao();
			
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
