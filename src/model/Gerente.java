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

public class Gerente extends Usuario{
	
	public Gerente(String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario, double salarioUsuario,
			String emailUsuario, String senhaUsuario, String perfilUsuario, String statusUsuario) {
		super(nomeUsuario, cpfUsuario, dataNascimentoUsuario, salarioUsuario, statusUsuario, statusUsuario, statusUsuario, statusUsuario);
	}
	
	public void cadastrarUsuario(Usuario usuario) throws ExceptionDao {
		new UsuarioDao().cadastrarUsuario(usuario);
	}

	public void alterarUsuario(Usuario usuario) throws ExceptionDao {
		new UsuarioDao().alterarUsuario(cpfUsuario, usuario);
	}
	
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
