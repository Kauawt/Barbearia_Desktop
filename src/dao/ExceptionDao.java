package dao;

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
