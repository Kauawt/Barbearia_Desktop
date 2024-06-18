package controller.helper;

import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import model.Servico;
import model.Validador;
import view.TelaAgendamentoPanel;

/**
 * Classe helper responsável por auxiliar na manipulação de dados e interação da interface
 * relacionados ao agendamento de serviços.
 * Recebe e manipula a interface de agendamento (TelaAgendamentoPanel).
 */
public class AgendaHelper {
	private final TelaAgendamentoPanel view;
	 /**
     * Construtor da classe AgendaHelper.
     * @param telaAgendamentoPanel A interface de agendamento associada a este helper.
     */
	public AgendaHelper(TelaAgendamentoPanel telaAgendamentoPanel) {
		this.view = telaAgendamentoPanel;
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
		Object selectedItem = view.getJboxServico().getSelectedItem();
	    if (selectedItem instanceof Servico) {
	        return (Servico) selectedItem;
	    } else {
	        return null;
	    }}
	
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
	 * Valida os campos da tela de agendamento.
	 * @param codUsuario - O código do usuário (barbeiro).
	 * @param codCliente - O código do cliente.
	 * @param codServico - O código do serviço.
	 * @param precoServico - O preço do serviço.
	 * @param dataAtendimento - A data do agendamento.
	 * @param horaAtendimento - A hora do agendamento.
	 * @return true se os campos estiverem preenchidos corretamente, false caso contrário.
	 */
	public boolean validadorCamposTelaAgendamento(int codUsuario, int codCliente, int codServico, double precoServico, String dataAtendimento, LocalTime horaAtendimento) {
		String codUsuarioStr = String.valueOf(codUsuario);
		String codClienteStr = String.valueOf(codCliente);
		String codServicoStr = String.valueOf(codServico);
		String precoServicoStr = String.valueOf(precoServico);
		String horaAtendimentoStr = String.valueOf(horaAtendimento);
		if (codUsuarioStr.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo Barbeiro precisa estar preenchido");
			return false;
		}
		if (codClienteStr.isEmpty() || codClienteStr == null) {
			JOptionPane.showMessageDialog(null, "O campo CPF precisa estar preenchido");
			return false;
		}
		if (codServicoStr.isEmpty()){
			JOptionPane.showMessageDialog(null, "O campo Serviço precisa estar preenchido");
			return false;
		}
		if (precoServicoStr.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo Preço precisa estar preenchido");
			return false;
		}
		if (dataAtendimento.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo Data precisa estar preenchido");
			return false;
		}
		if (horaAtendimentoStr.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo Hora precisa estar preenchido");
			return false;
		}
		else {
			return true;
		}}
	
	/**
     * Limpa os campos após um agendamento ser concluído.
     */
	public void limparAgendamentoConcluido() {
		
		view.getJboxBarbeiro().setSelectedItem("");
		view.getTxtCpfCliente().setText("");
		view.getTxtNomeCliente().setText("");
		view.getJboxServico().setSelectedItem("");
		view.getTxtValor().setText("");
		view.getJboxHora().setSelectedItem("");
		view.getTxtDataAgenda().setText("");
	}}
