package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.ExceptionDao;
import model.Usuario;

public class UsuarioController {
	
	public boolean cadastrarUsuario(int codUsuario, String nomeUsuario, String cpfUsuario, String dataNascimentoUsuario, double salarioUsuario,String emailUsuario,String senhaUsuario, String perfilUsuario ) throws ParseException, ExceptionDao{
		if(codUsuario > 0 && (nomeUsuario != null) && (nomeUsuario.length()>0) && (cpfUsuario != null) && (cpfUsuario.length()>0) && (dataNascimentoUsuario != null) && (salarioUsuario > 0 ) && (emailUsuario != null) && (emailUsuario.length()>0) && (senhaUsuario != null) && (senhaUsuario.length()>0)&& (perfilUsuario != null) && (perfilUsuario.length()>0)){
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date data = formato.parse(dataNascimentoUsuario);
			Usuario usuario = new Usuario(codUsuario,nomeUsuario,cpfUsuario,data,salarioUsuario,emailUsuario,senhaUsuario,perfilUsuario);
			usuario.cadastrarUsuario(usuario);
			return true;
			}
		else {
				return false;
			}
					
		}
		
}
