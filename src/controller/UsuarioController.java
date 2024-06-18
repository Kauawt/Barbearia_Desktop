package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

import controller.helper.ValidadorHelper;

import dao.ExceptionDao;
import model.Cliente;
import model.Gerente;
import dao.UsuarioDao;
import model.Usuario;
import model.Validador;
/**
 * Controlador responsável pela lógica de gestão de usuários.
 * Gerencia a interação entre a interface de usuário para usuários (TelaUsuarioPanel),
 * os dados do sistema (UsuarioDao) e a lógica de negócios relacionada aos usuários.
 */
public class UsuarioController {
	
	private static UsuarioDao usuarioDao;
	
	/**
	 * Valida os campos fornecidos na tela de cadastro de usuários.
	 * 
	 * @param nomeUsuario O nome do usuário.
	 * @param cpfUsuario O CPF do usuário.
	 * @param salarioUsuario O salário do usuário.
	 * @param dataNascimentoUsuario A data de nascimento do usuário.
	 * @param emailUsuario O email do usuário.
	 * @param senhaUsuario A senha do usuário.
	 * @return true se todos os campos forem válidos, false caso contrário.
	 */
	public boolean validadorCamposTelaUsuario(String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario, String salarioUsuario,
			String emailUsuario, String senhaUsuario) {
		if (nomeUsuario.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo nome precisa estar preenchido");
			return false;
		}
		if (Validador.validadorCpf(cpfUsuario) == false || cpfUsuario == null) {
			JOptionPane.showMessageDialog(null, "CPF Inválido, por favor, insira um cpf válido");
			return false;
		}
		if (Validador.isNumero(salarioUsuario) == false || salarioUsuario.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira um valor de salário válido");
			return false;
		}
		if (Validador.validadorDataNascimento(dataNascimentoUsuario) == false || dataNascimentoUsuario == null) {
			JOptionPane.showMessageDialog(null, "Insira uma data válida");
			return false;
		}
		if (Validador.validadorEmail(emailUsuario) == false || emailUsuario == null) {
			JOptionPane.showMessageDialog(null, "Insira um email válido");
			return false;
		}
		if (senhaUsuario.isEmpty() || senhaUsuario.length() < 5) {
			JOptionPane.showMessageDialog(null, "A senha precisa ser preenchida e ter mais de 5 caracteres");
			return false;
		}else {
			return true;
		}
	}

	/**
	 * Cadastra um novo usuário com as informações fornecidas.
	 * 
	 * @param nomeUsuario O nome do usuário.
	 * @param cpfUsuario O CPF do usuário.
	 * @param dataNascimentoUsuario A data de nascimento do usuário.
	 * @param salarioUsuario O salário do usuário.
	 * @param emailUsuario O email do usuário.
	 * @param senhaUsuario A senha do usuário.
	 * @param perfilUsuario O perfil do usuário.
	 * @param statusUsuario O status do usuário.
	 * @throws ParseException Se ocorrer um erro ao analisar as informações.
	 * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
	 */
	public void cadastrarUsuario(String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario,
			double salarioUsuario, String emailUsuario, String senhaUsuario, String perfilUsuario, String statusUsuario)
			throws ParseException, ExceptionDao {

		Gerente usuario = new Gerente(nomeUsuario, cpfUsuario, dataNascimentoUsuario, salarioUsuario, emailUsuario,
				senhaUsuario, perfilUsuario, statusUsuario);
		usuario.cadastrarUsuario(usuario);

	}
	/**
	 * Altera um usuário existente com as informações fornecidas.
	 * 
	 * @param codUsuario O código do usuário a ser alterado.
	 * @param nomeUsuario O novo nome do usuário.
	 * @param cpfUsuario O novo CPF do usuário.
	 * @param dataNascimentoUsuario A nova data de nascimento do usuário.
	 * @param salarioUsuario O novo salário do usuário.
	 * @param emailUsuario O novo email do usuário.
	 * @param senhaUsuario A nova senha do usuário.
	 * @param perfilUsuario O novo perfil do usuário.
	 * @param statusUsuario O novo status do usuário.
	 * @throws ParseException Se ocorrer um erro ao analisar as informações.
	 * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
	 */
	public void alterarUsuario(int codUsuario, String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario,
			double salarioUsuario, String emailUsuario, String senhaUsuario, String perfilUsuario, String statusUsuario)
			throws ParseException, ExceptionDao {

		Gerente usuario = new Gerente(codUsuario, nomeUsuario, cpfUsuario, dataNascimentoUsuario, salarioUsuario,
				emailUsuario, senhaUsuario, perfilUsuario, statusUsuario);
		usuario.alterarUsuario(usuario);

	}
	public UsuarioController() {
        this.usuarioDao = new UsuarioDao();
    }
	public void inicializarUsuarioDao() {
        this.usuarioDao = new UsuarioDao();
    }
	/**
	 * Busca um usuário pelo seu código.
	 * 
	 * @param codUsuario O código do usuário a ser buscado.
	 * @return O usuário encontrado.
	 * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
	 * @throws SQLException Se ocorrer um erro de SQL.
	 */
	public Usuario buscarUsuarioPorId(int codUsuario) throws ExceptionDao, SQLException {
	        return usuarioDao.buscarUsuarioPorId(codUsuario);
	    }
	
	/**
     * Busca o código de um usuário pelo seu nome.
     * 
     * @param nomeUsuario O nome do usuário a ser buscado.
     * @return O código do usuário, ou -1 se não for encontrado.
     * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    public static int buscarCodigoUsuarioPorNome(String nomeUsuario) throws ExceptionDao, SQLException {
        return usuarioDao.buscarCodigoUsuarioPorNome(nomeUsuario);
    }
    /**
     * Obtém todos os usuários cadastrados no sistema.
     *
     * @return ArrayList contendo todos os usuários cadastrados.
     * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
     * Pode ser lançado se o UsuarioDao não estiver inicializado corretamente.
     */
    public ArrayList<Usuario> obterUsuarios() throws ExceptionDao {
        if (usuarioDao == null) {
            throw new IllegalStateException("UsuarioDao não foi inicializado corretamente.");
        }

        try {
            return usuarioDao.selectAllUsuarios();
        } catch (Exception e) {
            throw new ExceptionDao("Erro ao buscar todos os usuários: " + e.getMessage());
        }
    }
}

	


