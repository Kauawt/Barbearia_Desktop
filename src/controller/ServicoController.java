package controller;

import dao.ExceptionDao;
import model.Servico;

public class ServicoController {
	public void cadastrarServico(int codServico,String tipoServico,String descricaoServico,double precoServico,double duracaoServico,String statusServico) throws ExceptionDao {
			Servico servico = new Servico(codServico,tipoServico,descricaoServico,precoServico,duracaoServico,statusServico);
			servico.cadastrarServico(servico);
		}
}