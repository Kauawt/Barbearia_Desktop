package model;

import javax.swing.JOptionPane;

import dao.ClienteDao;
import dao.ExceptionDao;
import dao.ServicoDao;

public class Servico {
    private int codServico;
    private String tipoServico;
    private String descricaoServico;
    private double precoServico;
    private double duracaoServico;
    private String statusServico;

    
	public Servico(int codServico, String tipoServico, String descricaoServico, double precoServico,double duracaoServico, String statusServico) {
		if(codServico > 0 && tipoServico != null && descricaoServico != null && descricaoServico.length()>0 && precoServico > 0 && duracaoServico>0 && Validador.validadorStatus(statusServico)){
			this.codServico = codServico;
			this.tipoServico = tipoServico;
			this.descricaoServico = descricaoServico;
			this.precoServico = precoServico;
			this.duracaoServico = duracaoServico;
			this.statusServico = statusServico;
		}
		else if(codServico<0) {
			JOptionPane.showMessageDialog(null, "O código não pode ser negativo");
		}else if(precoServico<0) {
			JOptionPane.showMessageDialog(null, "O Preço do serviço não pode ser negativo");
		}else if(duracaoServico<0) {
			JOptionPane.showMessageDialog(null, "A duração do serviço não pode ser negativa");
		}else if(Validador.validadorCpf(statusServico) == false) {
			JOptionPane.showMessageDialog(null, "Durante a criação do Serviço, o status precisa ser ativo");
		}
	
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
}
