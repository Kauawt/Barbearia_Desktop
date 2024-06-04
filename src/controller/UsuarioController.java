package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.ExceptionDao;
import model.Gerente;
import model.Usuario;
import model.Validador;

public class UsuarioController {

	public void cadastrarUsuario(String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario,
			double salarioUsuario, String emailUsuario, String senhaUsuario, String perfilUsuario, String statusUsuario)
			throws ParseException, ExceptionDao {
		
		Gerente usuario = new Gerente(nomeUsuario, cpfUsuario, dataNascimentoUsuario, salarioUsuario, emailUsuario,
				senhaUsuario, perfilUsuario, statusUsuario);
		usuario.cadastrarUsuario(usuario);
		System.out.println(usuario);
	}

	public void alterarUsuario(int codUsuario, String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario,
			double salarioUsuario, String emailUsuario, String senhaUsuario, String perfilUsuario, String statusUsuario)
			throws ParseException, ExceptionDao {
		
		Gerente usuario = new Gerente(codUsuario, nomeUsuario, cpfUsuario, dataNascimentoUsuario, salarioUsuario, emailUsuario,
				senhaUsuario, perfilUsuario, statusUsuario);
		usuario.alterarUsuario(usuario);
		System.out.println(usuario);

	}
	

}
