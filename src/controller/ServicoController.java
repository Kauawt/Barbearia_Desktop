package controller;

import java.text.ParseException;

import java.sql.SQLException;

import dao.ExceptionDao;
import dao.ServicoDao;
import model.Servico;
import model.Usuario;

public class ServicoController {
	/**
	 * Cadastra um novo serviço com as informações fornecidas.
	 * 
	 * @param tipoServico O tipo de serviço a ser cadastrado.
	 * @param descricaoServico A descrição do serviço.
	 * @param precoServico O preço do serviço.
	 * @param duracaoServico A duração do serviço.
	 * @param statusServico O status do serviço.
	 * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
	 */
	public void cadastrarServico(String tipoServico, String descricaoServico, double precoServico, double duracaoServico, String statusServico) throws ExceptionDao {
		Servico servico = new Servico(tipoServico, descricaoServico, precoServico, duracaoServico, statusServico);
		servico.cadastrarServico(servico);
	}
	
	/**
	 * Altera um serviço existente com as informações fornecidas.
	 * 
	 * @param codServico O código do serviço a ser alterado.
	 * @param tipoServico O novo tipo de serviço.
	 * @param descricaoServico A nova descrição do serviço.
	 * @param precoServico O novo preço do serviço.
	 * @param duracaoServico A nova duração do serviço.
	 * @param statusServico O novo status do serviço.
	 * @throws ParseException Se ocorrer um erro ao analisar as informações.
	 * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
	 */
	public void alterarServico(int codServico, String tipoServico, String descricaoServico, double precoServico, double duracaoServico, String statusServico) throws ParseException, ExceptionDao {
		Servico servico = new Servico(codServico, tipoServico, descricaoServico, precoServico, duracaoServico, statusServico);
		servico.alterarServico(servico);
	}
	
	/**
	 * Busca o código de um serviço pelo seu tipo.
	 * 
	 * @param tipoServico O tipo de serviço a ser buscado.
	 * @return O código do serviço encontrado.
	 * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
	 */
	public static int buscarCodigoServicoPorNome(String tipoServico) throws ExceptionDao {
		return ServicoDao.buscarCodigoServicoPorNome(tipoServico);
	}
}