package controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import dao.ExceptionDao;
import model.Agendamento;
import model.Cliente;
import model.Servico;
import model.Usuario;

public class AgendamentoController {

	public void cadastrarAgendamento(int codAtendimento, Servico servico, Cliente cliente,
			Usuario usuario, double valorTotal, LocalDate dataAtendimento, LocalTime horaAtendimento)
			throws ParseException, ExceptionDao {
		
		Agendamento agendamento = new Agendamento(codAtendimento, servico, cliente, usuario, valorTotal, dataAtendimento, horaAtendimento);
		agendamento.cadastrarAgendamento(agendamento);
		System.out.println(agendamento);
	}	
}
