package model;

import javax.swing.JOptionPane;
import dao.ClienteDao;
import dao.ExceptionDao;
import dao.ServicoDao;
import dao.UsuarioDao;

public class Servico {
    private int codServico;
    private String tipoServico;
    private String descricaoServico;
    private double precoServico;
    private double duracaoServico;
    private String statusServico;

    
	public Servico(int codServico, String tipoServico, String descricaoServico, double precoServico,double duracaoServico, String statusServico) {
			this.codServico = codServico;
			this.tipoServico = tipoServico;
			this.descricaoServico = descricaoServico;
			this.precoServico = precoServico;
			this.duracaoServico = duracaoServico;
			this.statusServico = statusServico;
		}
	
	

	public Servico(String tipoServico, String descricaoServico, double precoServico, double duracaoServico,String statusServico) {
		this.tipoServico = tipoServico;
		this.descricaoServico = descricaoServico;
		this.precoServico = precoServico;
		this.duracaoServico = duracaoServico;
		this.statusServico = statusServico;
	}
	public Servico(int codServico, String tipoServico, double precoServico) {
	    this.codServico = codServico;
	    this.tipoServico = tipoServico;
	    this.precoServico = precoServico;
	}


	public Servico() {

	}



	public int getCodServico() {
		return codServico;
	}


	public void setCodServico(int codServico) {
		this.codServico = codServico;
	}


	public String getTipoServico() {
		return tipoServico;
	}


	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}


	public String getDescricaoServico() {
		return descricaoServico;
	}


	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}


	public double getPrecoServico() {
		return precoServico;
	}


	public void setPrecoServico(double precoServico) {
		this.precoServico = precoServico;
	}


	public double getDuracaoServico() {
		return duracaoServico;
	}


	public void setDuracaoServico(double duracaoServico) {
		this.duracaoServico = duracaoServico;
	}


	public String getStatusServico() {
		return statusServico;
	}


	public void setStatusServico(String statusServico) {
		this.statusServico = statusServico;
	}


	public void cadastrarServico(Servico servico) throws ExceptionDao {
		new ServicoDao().cadastrarServico(servico);
	}
	
	public void alterarServico(Servico servico) throws ExceptionDao {
		new ServicoDao().alterarServico(codServico, servico);
	}
	
	@Override
	public String toString() {
		return getTipoServico();
	}
	
}
