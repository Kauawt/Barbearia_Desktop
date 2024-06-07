package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ExceptionDao;
import dao.UsuarioDao;
import model.Usuario;
import model.Validador;

public class UsuarioController {
	
	private static UsuarioDao usuarioDao;
	
	public void cadastrarUsuario(String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario,
			double salarioUsuario, String emailUsuario, String senhaUsuario, String perfilUsuario, String statusUsuario)
			throws ParseException, ExceptionDao {
		
		Usuario usuario = new Usuario(nomeUsuario, cpfUsuario, dataNascimentoUsuario, salarioUsuario, emailUsuario,
				senhaUsuario, perfilUsuario, statusUsuario);
		usuario.cadastrarUsuario(usuario);
		System.out.println(usuario);
		// SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		// Date data = formato.parse(dataNascimentoUsuario);
	}

	public void alterarUsuario(String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario,
			double salarioUsuario, String emailUsuario, String senhaUsuario, String perfilUsuario, String statusUsuario)
			throws ParseException, ExceptionDao {
		
		Usuario usuario = new Usuario(nomeUsuario, cpfUsuario, dataNascimentoUsuario, salarioUsuario, emailUsuario,
				senhaUsuario, perfilUsuario, statusUsuario);
		usuario.alterarUsuario(usuario);

	}
	
	public UsuarioController() {
        this.usuarioDao = new UsuarioDao();
    }
	
	public Usuario buscarUsuarioPorId(int codUsuario) throws ExceptionDao, SQLException {
	        return usuarioDao.buscarUsuarioPorId(codUsuario);
	    }
	

}
