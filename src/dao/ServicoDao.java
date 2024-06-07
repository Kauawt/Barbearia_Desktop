package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Servico;
import java.sql.*;

public class ServicoDao {

	private static final String CONSULTAR_SERVICO_POR_NOME = "select codServico from tbServico where tipoServico = ?";
	
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

	 public ArrayList<Servico> selectAll() throws ExceptionDao {
	        String sql = "SELECT codServico, tipoServico, precoServico FROM tbServico";
	        ArrayList<Servico> servicos = new ArrayList<>();

	        try (Connection conexao = ModuloConexao.conector();
	             PreparedStatement pst = conexao.prepareStatement(sql);
	             ResultSet rs = pst.executeQuery()) {

	            while (rs.next()) {
	                Servico servico = new Servico(
	                    rs.getInt("codServico"),
	                    rs.getString("tipoServico"),
	                    rs.getDouble("precoServico")
	                );
	                servicos.add(servico);
	            }
	        } catch (SQLException e) {
	            throw new ExceptionDao("Erro ao buscar todos os serviços: " + e);
	        }
	        return servicos;
	    }
	 public static int buscarCodigoServicoPorNome(String nomeServico) throws ExceptionDao {
	        int codServico = -1; // Valor padrão para indicar que não foi encontrado
	        String query = CONSULTAR_SERVICO_POR_NOME;

	        try (Connection conexao = ModuloConexao.conector();
	             PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
	            preparedStatement.setString(1, nomeServico);
	            ResultSet rs = preparedStatement.executeQuery();
	            
	            if (rs.next()) {
	                codServico = rs.getInt("codServico");
	            }
	        } catch (SQLException e) {
	            throw new ExceptionDao("Erro ao buscar código do serviço por nome: " + e.getMessage());
	        }
	        
	        return codServico;
	    }
}
