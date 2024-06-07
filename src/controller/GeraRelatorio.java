package controller;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;

import dao.ModuloConexao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class GeraRelatorio {
	public GeraRelatorio() {
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
