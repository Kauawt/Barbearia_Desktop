package controller;

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
		
		try {
			JasperReport jasperreport = JasperCompileManager.compileReport("C:\\Users\\antho\\OneDrive - Fatec Centro Paula Souza\\3º Semestre\\BarbeariaJavaDesktop\\Barbearia_Desktop\\Barbearia_Desktop\\relatorios\\Coffee.jrxml");
			
			JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport,new HashMap<>(), conexao);
			
			JasperViewer jasperviewer = new JasperViewer(jasperprint, false);
			
			jasperviewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
