package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Servico;
import java.sql.*;

public class ServicoDao {

	public void cadastrarServico(Servico servico) throws ExceptionDao {
		String sql = "insert into tbServico(codServico,tipoServico,descricaoServico,precoServico,duracaoServico,statusServico) values (?,?,?,?,?,?)";
		Connection conexao = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conexao = ModuloConexao.conector(); // abre conexao
			pst = conexao.prepareStatement(sql); // passa o comando sql como argumento
			pst.setInt(1, servico.getCodServico()); // atribui o valor do model a linha SQL
			pst.setString(2, servico.getTipoServico());
			pst.setString(3, servico.getDescricaoServico());
			pst.setDouble(4, servico.getPrecoServico());
			pst.setDouble(5, servico.getDuracaoServico());
			pst.setString(6, servico.getStatusServico());
			pst.executeUpdate(); // atualiza o banco de dados
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao Cadastrar o Servico: " + e);
		}finally {
			try {
				if(pst != null) {pst.close();} // fecha conexão com banco
			}catch(SQLException e) {
				throw new ExceptionDao("Erro ao fechar o Statement" + e);
			}
			try {
				if(conexao != null) {conexao.close();}
			}catch(SQLException e) {
				 throw new ExceptionDao ("Erro ao fechar a conexão: "+ e);
			 }
				}
	}

}
