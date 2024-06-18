package dao;
/**
 * Classe utilizada para tratar exceções específicas relacionadas ao acesso a dados no sistema.
 * Fornece métodos para tratamento e registro de exceções em operações de acesso a dados.
 */
public class ExceptionDao extends Exception {
	/**
	 * Construtor que cria uma nova exceção com a mensagem especificada.
	 * 
	 * @param mensagem A mensagem de erro associada a esta exceção.
	 */
	public ExceptionDao(String mensagem) {
		super(mensagem);
	}

}
