package controller.helper;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import model.Servico;
import view.TelaAgendamento;

public class AgendaHelper {
	private final TelaAgendamento view;

	public AgendaHelper(TelaAgendamento view) {
		this.view = view;
	}

	public void preencherServicos(ArrayList<Servico> servicos) {
        JComboBox<Object> jboxServico = view.getJboxServico(); // Obtém a JComboBox da view
        DefaultComboBoxModel<Object> comboBoxModel = new DefaultComboBoxModel<>(); // Cria um novo modelo para a JComboBox

        for (Servico servico : servicos) {
            comboBoxModel.addElement(servico); // Adiciona cada serviço ao modelo da JComboBox
        }

        jboxServico.setModel(comboBoxModel); // Define o novo modelo na JComboBox
    }
}
