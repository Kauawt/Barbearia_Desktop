package controller;

import java.text.ParseException;

import java.sql.SQLException;

import dao.ExceptionDao;
import dao.ServicoDao;
import model.Servico;
import model.Usuario;

public class ServicoController {
	public void cadastrarServico(String tipoServico,String descricaoServico,double precoServico,double duracaoServico,String statusServico) throws ExceptionDao {
			Servico servico = new Servico(tipoServico,descricaoServico,precoServico,duracaoServico,statusServico);
			servico.cadastrarServico(servico);
		}
	
	
	
	public void alterarServico(int codServico,String tipoServico, String descricaoServico, double precoServico,double duracaoServico, String statusServico) throws ParseException, ExceptionDao {
		Servico servico = new Servico(codServico,tipoServico, descricaoServico, precoServico, duracaoServico, statusServico);
		servico.alterarServico(servico);
	}
	
	public int buscarCodigoServicoPorNome(String tipoServico) throws ExceptionDao {
        return ServicoDao.buscarCodigoServicoPorNome(tipoServico);
    }

}