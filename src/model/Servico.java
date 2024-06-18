package model;

import javax.swing.JOptionPane;
import dao.ClienteDao;
import dao.ExceptionDao;
import dao.ServicoDao;
import dao.UsuarioDao;
/**
 * A classe Servico representa um serviço oferecido pela empresa,
 * com atributos como código, tipo, descrição, preço, duração e status.
 */
public class Servico {
    private int codServico;
    private String tipoServico;
    private String descricaoServico;
    private double precoServico;
    private double duracaoServico;
    private String statusServico;

    /**
     * Construtor completo para inicializar todos os atributos do serviço.
     * @param codServico Código identificador único do serviço
     * @param tipoServico Tipo ou categoria do serviço
     * @param descricaoServico Descrição detalhada do serviço
     * @param precoServico Preço do serviço
     * @param duracaoServico Duração estimada do serviço em horas
     * @param statusServico Status do serviço (ativo, inativo, etc.)
     */
	public Servico(int codServico, String tipoServico, String descricaoServico, double precoServico,double duracaoServico, String statusServico) {
			this.codServico = codServico;
			this.tipoServico = tipoServico;
			this.descricaoServico = descricaoServico;
			this.precoServico = precoServico;
			this.duracaoServico = duracaoServico;
			this.statusServico = statusServico;
		}
	
	/**
     * Construtor utilizado para criar um novo serviço sem especificar o código.
     * @param tipoServico Tipo ou categoria do serviço
     * @param descricaoServico Descrição detalhada do serviço
     * @param precoServico Preço do serviço
     * @param duracaoServico Duração estimada do serviço em horas
     * @param statusServico Status do serviço (ativo, inativo, etc.)
     */

	public Servico(String tipoServico, String descricaoServico, double precoServico, double duracaoServico,String statusServico) {
		this.tipoServico = tipoServico;
		this.descricaoServico = descricaoServico;
		this.precoServico = precoServico;
		this.duracaoServico = duracaoServico;
		this.statusServico = statusServico;
	}
	/**
     * Construtor utilizado para inicializar um serviço com código, tipo e preço do serviço.
     * @param codServico Código identificador único do serviço
     * @param tipoServico Tipo ou categoria do serviço
     * @param precoServico Preço do serviço
     */
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

	/**
     * Método utilizado para cadastrar um novo serviço no banco de dados.
     * @param servico Objeto Servico a ser cadastrado
     * @throws ExceptionDao se ocorrer algum erro durante o cadastro
     */
	public void cadastrarServico(Servico servico) throws ExceptionDao {
		new ServicoDao().cadastrarServico(servico);
	}
	/**
     * Método utilizado para alterar um serviço existente no banco de dados.
     * @param servico Objeto Servico com os novos dados a serem atualizados
     * @throws ExceptionDao se ocorrer algum erro durante a atualização
     */
	public void alterarServico(Servico servico) throws ExceptionDao {
		new ServicoDao().alterarServico(codServico, servico);
	}
	/**
     * Retorna uma representação em String do tipo de serviço.
     * @return tipoServico Tipo de serviço
     */
	@Override
	public String toString() {
		return getTipoServico();
	}
	
}
