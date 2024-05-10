package controller;

import dao.ExceptionDao;
import model.Servico;

public class ServicoController {
	public boolean cadastrarServico(int codServico,String tipoServico,String descricaoServico,double precoServico,double duracaoServico,String statusServico) throws ExceptionDao {
		if(codServico > 0 && tipoServico != null && tipoServico.length()>0 && descricaoServico != null && descricaoServico.length()>0 && precoServico > 0 && duracaoServico>0 && statusServico != null && statusServico.length()>0) {
			Servico servico = new Servico(codServico,tipoServico,descricaoServico,precoServico,duracaoServico,statusServico);
			servico.cadastrarServico(servico);
			return true;
		}
		else {
			return false;
		}
			
		}			
	}
