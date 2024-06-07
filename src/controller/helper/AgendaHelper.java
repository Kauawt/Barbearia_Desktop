package controller.helper;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import model.Servico;
import view.TelaAgendamento;

public class AgendaHelper {
	private final TelaAgendamento view;

	public AgendaHelper(TelaAgendamento view) {
		this.view = view;
	}
	
	/**
     * Preenche o ComboBox de serviços na view com os serviços fornecidos.
     * @param servicos A lista de serviços a serem exibidos no ComboBox.
     */
	public void preencherServicos(ArrayList<Servico> servicos) {

	    ComboBoxModel<Object> comboBoxModel = new DefaultComboBoxModel<>();
	    
	    for (Servico servico : servicos) {
	        ((DefaultComboBoxModel<Object>) comboBoxModel).addElement(servico);
	    }

	    view.getJboxServico().setModel(comboBoxModel);
	}
	 /**
     * Obtém o serviço selecionado no ComboBox.
     * @return O serviço selecionado.
     */
	public Servico obterServico() {
		return (Servico) view.getJboxServico().getSelectedItem();
	}
	
	/**
     * Define o valor do serviço na view.
     * @param precoServico O preço do serviço a ser exibido.
     */
	public void setarValor(double precoServico) {
		view.getTxtValor().setText(precoServico+"");
	}
	/**
     * Limpa os campos da tela de agendamento.
     */
	public void limparTelaAgendamento() {
		
		view.getTxtNomeCliente().setText("");
		view.getTxtValor().setText("");
		view.getTxtDataAgenda().setText("");
	}
	/**
     * Limpa os campos após um agendamento ser concluído.
     */
	public void limparAgendamentoConcluido() {
		view.getJboxBarbeiro().setToolTipText("");
		view.getTxtCpfCliente().setText("");
		view.getTxtNomeCliente().setText("");
		view.getJboxServico().setToolTipText("");
		view.getTxtValor().setText("");
		view.getTxtDataAgenda().setText("");
		view.getTxtHoraAgenda().setToolTipText("");
	}
	
}
