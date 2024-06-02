package controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import controller.helper.AgendaHelper;
import dao.AgendaDao;
import dao.ExceptionDao;
import dao.ServicoDao;
import model.Agendamento;
import model.Cliente;
import model.Servico;
import model.Usuario;
import view.TelaAgendamento;

public class AgendamentoController {
	private final AgendaHelper helper;
	private TelaAgendamento view;
	private AgendaDao agendaDao;
	
    public void cadastrarAgendamento(int codAtendimento, Servico servico, Cliente cliente,
			Usuario usuario, LocalDate dataAtendimento, LocalTime horaAtendimento)
			throws ParseException, ExceptionDao {
		
		Agendamento agendamento = new Agendamento(codAtendimento, servico, cliente, usuario, dataAtendimento, horaAtendimento);
		agendamento.cadastrarAgendamento(agendamento);
		System.out.println(agendamento);
	}	
    public AgendamentoController(TelaAgendamento view) {
        this.view = view;
        this.helper = new AgendaHelper(view);
        this.agendaDao = new AgendaDao(); // Inicializa a instância de AgendaDao
    }

	 public void atualizaServico() {
		ArrayList<Servico> servicos = AgendaDao.listarServicos();
		helper.preencherServicos(servicos);
	    }
}
