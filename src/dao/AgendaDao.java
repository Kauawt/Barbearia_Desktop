package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Agendamento;

public class AgendaDao {

	public void cadastrarAgendamento(Agendamento agendamento) throws ExceptionDao {
		String sql = "insert into tbAgendamento(codAgendamento,codUsuario,codCliente,codServico,precoServico,dataAgendamento,horaAtendimento) values (?,?,?,?,?,?)";
		Connection conexao = null;
		PreparedStatement pst = null;
		ResultSet rs = null;	
		try {
			conexao = ModuloConexao.conector(); // abre conexao
			pst = conexao.prepareStatement(sql); // passa o comando sql como argumento
			pst.setInt(1, agendamento.getCodAtendimento());  // atribui o valor do model a linha SQL
            pst.setInt(2, agendamento.getUsuario().getCodUsuario()); // Suponho que getCodUsuario() retorne o código do usuário
            pst.setInt(3, agendamento.getCliente().getCodCliente());
            pst.setInt(4, agendamento.getServico().getCodServico());
            pst.setDouble(5, agendamento.getValorTotal());
            pst.setDate(6, java.sql.Date.valueOf(agendamento.getDataAtendimento())); // Não é necessário chamar toLocalDate()
            pst.setTime(7, java.sql.Time.valueOf(agendamento.getHoraAtendimento())); // Não é necessário chamar toLocalTime()
            pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Agendamento Realizado com Sucesso!!" );
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao Agendar: " + e);
		} finally {
			try {
				if (pst != null) {
					pst.close();
				} // fecha conexão com banco
			} catch (SQLException e) {
				throw new ExceptionDao("Erro ao fechar o Statement" + e);
			}
			try {
				if (conexao != null) {
					conexao.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDao("Erro ao fechar a conexão: " + e);
			}
		}
	}
}
