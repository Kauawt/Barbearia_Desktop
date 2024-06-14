package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

import controller.AgendamentoController;
import controller.ClienteController;
import controller.ServicoController;
import controller.UsuarioController;
import controller.helper.AgendaHelper;
import controller.helper.ClienteHelper;
import model.Cliente;
import model.ModeloTabelaAgendamento;
import model.Servico;
import model.Usuario;
import net.miginfocom.swing.MigLayout;
import dao.AgendaDao;
import dao.ClienteDao;
import dao.ExceptionDao;
import dao.UsuarioDao;
import model.Agendamento;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ItemEvent;

public class TelaAgendamentoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextField txtNomeCliente = new JTextField();
	private JTextField txtCpfCliente;
	private JFormattedTextField txtValor;
	private JFormattedTextField txtDataAgenda;
	// private PlaceholderTextField txtObs;
	JTextField txtObs = new JTextField();
	private JComboBox<Object> jboxServico;
	private JComboBox<Usuario> jboxBarbeiro;
	private AgendamentoController controller;
	private JComboBox<LocalTime> jboxHora;
	// private JTextField txtHoraAgenda;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAgendamentoPanel frame = new TelaAgendamentoPanel(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAgendamentoPanel(Agendamento agendamentoSelecionado) throws ExceptionDao, SQLException {

		setBackground(new Color(232, 227, 225));

		setSize(new Dimension(640, 480));
		setPreferredSize(new Dimension(640, 480));
		setLayout(null);

		JPanel panel_1 = new JPanel();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension newSize = getSize();
				panel_1.setSize(newSize);
				panel_1.revalidate();
				panel_1.repaint();
			}
		});
		panel_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel_1.setOpaque(false);
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_1.setBounds(0, 0, 640, 480);
		add(panel_1);
		panel_1.setLayout(new MigLayout("insets 0", "[100,grow][::80,grow][:80:200,grow][100px:100px,grow,fill][120,grow]", "[grow,fill][grow 50,fill][grow 50,fill][grow 50][grow,fill]"));

		MaskFormatter dataMask = null;
		// MaskFormatter horaMask = null;
		MaskFormatter precoMask = null;
		controller = new AgendamentoController(this);

		try {
			// horaMask = new MaskFormatter("##:##");
			dataMask = new MaskFormatter("##/##/####");
			precoMask = new MaskFormatter("##.##");
		} catch (ParseException e) {
		}
		if (agendamentoSelecionado == null) {
			txtNomeCliente = new PlaceholderTextField("Nome");
		}

		JLabel lblAgenda = new JLabel("Agenda");
		lblAgenda.setForeground(new Color(255, 255, 255));
		lblAgenda.setBounds(220, 57, 280, 32);
		panel_1.add(lblAgenda, "cell 1 1 3 1,alignx center,aligny center");
		lblAgenda.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

		JLabel lblNomeusuarioPanel = new JLabel("Nome");
		lblNomeusuarioPanel.setForeground(new Color(255, 255, 255));
		lblNomeusuarioPanel.setBounds(197, 108, 60, 21);
		panel_1.add(lblNomeusuarioPanel, "flowy,cell 1 2,alignx right,aligny center, gapbottom 15");
		lblNomeusuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblCpfUsuarioPanel = new JLabel("CPF");
		lblCpfUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblCpfUsuarioPanel.setBounds(204, 139, 49, 21);
		panel_1.add(lblCpfUsuarioPanel, "cell 1 2,alignx right,aligny center, gapbottom 15");
		lblCpfUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblDataNascimentoUsuarioPanel = new JLabel("Data Nascimento");
		lblDataNascimentoUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblDataNascimentoUsuarioPanel.setBounds(121, 171, 136, 21);
		panel_1.add(lblDataNascimentoUsuarioPanel, "cell 1 2,alignx right, gapbottom 15");
		lblDataNascimentoUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblSalarioUsuarioPanel = new JLabel("Salario");
		lblSalarioUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblSalarioUsuarioPanel.setBounds(185, 201, 68, 21);
		panel_1.add(lblSalarioUsuarioPanel, "cell 1 2,alignx right, gapbottom 15");
		lblSalarioUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblEmailPanel = new JLabel("Email");
		lblEmailPanel.setForeground(new Color(255, 255, 255));
		lblEmailPanel.setBounds(202, 240, 49, 21);
		panel_1.add(lblEmailPanel, "cell 1 2,alignx right, gapbottom 15");
		lblEmailPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblSenhaUsuarioPanel = new JLabel("Senha");
		lblSenhaUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblSenhaUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSenhaUsuarioPanel.setBounds(202, 272, 49, 21);
		panel_1.add(lblSenhaUsuarioPanel, "cell 1 2,alignx right, gapbottom 15");

		JLabel lblSenhaUsuarioPanel2 = new JLabel("Senha");
		lblSenhaUsuarioPanel2.setForeground(new Color(255, 255, 255));
		lblSenhaUsuarioPanel2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSenhaUsuarioPanel2.setBounds(202, 272, 49, 21);
		panel_1.add(lblSenhaUsuarioPanel2, "cell 1 2,alignx right, gapbottom 15");

		jboxBarbeiro = new JComboBox<>();

		jboxBarbeiro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarHorariosDisponiveis();
			}
		});

		jboxBarbeiro.setToolTipText("");
		jboxBarbeiro.setName("");

		jboxBarbeiro.setForeground(new Color(128, 128, 128));
		jboxBarbeiro.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		jboxBarbeiro.setBounds(261, 305, 176, 22);
		jboxBarbeiro.setEditable(true);
		jboxBarbeiro.setModel(new DefaultComboBoxModel(new String[] { "João Paulo", "Fabricio Vieira", "Matheus Inacio",
				"Anthony Oliveira", "Sergio Ramos" }));
		panel_1.add(jboxBarbeiro, "flowy,cell 2 2,growx,gapbottom 10");

		txtCpfCliente = new JTextField("CPF");
		txtCpfCliente.setForeground(new Color(128, 128, 128));
		txtCpfCliente.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtCpfCliente.setBounds(261, 141, 176, 20);
		panel_1.add(txtCpfCliente, "cell 2 2,growx,gapbottom 10");

		txtNomeCliente.setBorder(null);
		txtNomeCliente.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtNomeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeCliente.setForeground(new Color(128, 128, 128));
		txtNomeCliente.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtNomeCliente.setBounds(261, 110, 176, 20);
		panel_1.add(txtNomeCliente, "cell 2 2,growx,gapbottom 10");
		txtNomeCliente.setColumns(10);

		jboxServico = new JComboBox<Object>();
		jboxServico.setEditable(true);
		jboxServico.setToolTipText("");
		jboxServico.setForeground(new Color(128, 128, 128));
		jboxServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		jboxServico.setBounds(261, 305, 176, 22);
		panel_1.add(jboxServico, "cell 2 2,growx,gapbottom 10");

		txtValor = new JFormattedTextField(precoMask);
		txtValor.setForeground(new Color(128, 128, 128));
		txtValor.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtValor.setBounds(261, 172, 176, 20);
		panel_1.add(txtValor, "cell 2 2,growx,gapbottom 10");

		txtDataAgenda = new JFormattedTextField(dataMask);
		txtDataAgenda.setText("Data");
		txtDataAgenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarHorariosDisponiveis();
			}
		});
		txtDataAgenda.setForeground(new Color(128, 128, 128));
		txtDataAgenda.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtDataAgenda.setBounds(261, 172, 176, 20);
		panel_1.add(txtDataAgenda, "cell 2 2,growx,gapbottom 10");

		txtObs = new PlaceholderTextField("Observações:");
		txtObs.setForeground(new Color(128, 128, 128));
		txtObs.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtObs.setBounds(261, 172, 176, 20);
		panel_1.add(txtObs, "cell 3 2,gapbottom 10,grow");

		/*
		 * txtHoraAgenda = new JFormattedTextField(horaMask);
		 * txtHoraAgenda.setText("Hora");
		 * txtHoraAgenda.setHorizontalAlignment(SwingConstants.CENTER);
		 * txtHoraAgenda.setForeground(new Color(128, 128, 128));
		 * txtHoraAgenda.setFont(new Font("Arial Black", Font.PLAIN, 12));
		 * txtHoraAgenda.setText("Hora"); txtHoraAgenda.setBounds(401, 69, 134, 20);
		 * contentPane.add(txtHoraAgenda); txtHoraAgenda.setColumns(10);
		 */

		jboxHora = new JComboBox();
		jboxHora.setForeground(new Color(128, 128, 128));
		jboxHora.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		jboxHora.setBounds(261, 305, 176, 22);
		jboxHora.setEditable(true);
		panel_1.add(jboxHora, "cell 2 2,growx,gapbottom 10");

		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servico servico = (Servico) jboxServico.getSelectedItem();
				String cpfCliente = txtCpfCliente.getText();
				String nomeUsuario = (String) jboxBarbeiro.getSelectedItem();
				String precoServico = txtValor.getText();

				try {
					controller.cadastrarAgendamento(servico, cpfCliente, nomeUsuario, txtDataAgenda.getText(),
							jboxHora.getToolTipText(), precoServico);

				} catch (ParseException | ExceptionDao | SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao agendar: " + ex.getMessage());
				}
			}
		});

		btnAgendar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnAgendar.setPreferredSize(new Dimension(100, 40));
		btnAgendar.setBackground(new Color(240, 240, 240));
		btnAgendar.setBounds(148, 367, 104, 41);
		panel_1.add(btnAgendar, "flowx,cell 2 3,growx");

		JButton btnReagendar = new JButton("Reagendar");
		btnReagendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servico servico = (Servico) jboxServico.getSelectedItem();
				String cpfCliente = txtCpfCliente.getText();
				String nomeUsuario = (String) jboxBarbeiro.getSelectedItem();
				String precoServico = txtValor.getText();
				try {
					controller.atualizarAgendamento(servico, cpfCliente, nomeUsuario, txtDataAgenda.getText(),
							jboxHora.getToolTipText(), precoServico);
				} catch (ParseException | ExceptionDao | SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao atualizar o agendamento: " + ex.getMessage());
				}
			}
		});

		btnReagendar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnReagendar.setBackground(new Color(240, 240, 240));
		btnReagendar.setBounds(261, 367, 104, 41);
		btnReagendar.setPreferredSize(new Dimension(100, 40));
		panel_1.add(btnReagendar, "cell 2 3,growx");

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnConsultar.setPreferredSize(new Dimension(100, 40));
		btnConsultar.setBackground(new Color(240, 240, 240));
		btnConsultar.setBounds(148, 367, 104, 41);
		panel_1.add(btnConsultar, "cell 2 3,growx");

		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// AgendaHelper agendaHelper = new AgendaHelper(TelaAgendamento.this);
				// agendaHelper.limparTelaAgendamento();
				String cpf = txtCpfCliente.getText();
				try {
					Cliente cliente = ClienteDao.consultarClientePorCPF(cpf);
					if (cliente != null) {
						// preencherCampos(cliente);
					} else {
						JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
					}
				} catch (ExceptionDao ex) {
					ex.printStackTrace();
				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpfCliente = txtCpfCliente.getText();
				String dataAgendamento = txtDataAgenda.getText();
				int codCliente = -1;
				try {
					codCliente = ClienteController.buscarCodigoClientePorCPF(cpfCliente);
				} catch (ExceptionDao e1) {
					e1.printStackTrace();
				}

				if (codCliente != -1) {
					// Chama o método do controlador para cancelar o agendamento
					try {
						controller.cancelarAgendamento(codCliente, dataAgendamento);
					} catch (ExceptionDao e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
				}
			}

		});

		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnCancelar.setBackground(new Color(240, 240, 240));
		btnCancelar.setIcon(null);
		btnCancelar.setBounds(261, 367, 124, 41);
		btnCancelar.setPreferredSize(new Dimension(100, 40));
		panel_1.add(btnCancelar, "cell 2 3,growx");

		if (agendamentoSelecionado != null) {
			preencherInfos(agendamentoSelecionado);
		}
		iniciar();

		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setBounds(0, 0, 640, 480);
		add(panel);
		panel.setLayout(new MigLayout("insets 0", "[grow,fill]", "[grow,fill]"));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension newSize = getSize();
				panel.setSize(newSize);
				panel.revalidate();
				panel.repaint();
			}
		});

		JPictureBox pictureBox_1 = new JPictureBox();
		pictureBox_1.setIcon(new ImageIcon(TelaUsuarioPanel.class.getResource("/icones/cadastroWallpaper.png")));
		pictureBox_1.setBounds(0, 0, 640, 453);
		panel.add(pictureBox_1, "cell 0 0,grow");

		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBorder(null);
		setPreferredSize(new Dimension(640, 480));
		setMinimumSize(new Dimension(450, 480));
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBounds(100, 100, 640, 480);

	}

	private void preencherCampos(Cliente clienteSelecionado) throws ExceptionDao {
		txtNomeCliente.setText(clienteSelecionado.getNomeCliente());
		txtCpfCliente.setText(clienteSelecionado.getCpfCliente());
	}

	private void preencherInfos(Agendamento agendamentoSelecionado) throws SQLException {
		txtCpfCliente.setText(agendamentoSelecionado.getCliente().getCpfCliente());
		txtNomeCliente.setText(agendamentoSelecionado.getCliente().getNomeCliente());
		jboxServico.setSelectedItem(agendamentoSelecionado.getServico());
		txtValor.setText(String.valueOf(agendamentoSelecionado.getPrecoServico()));
		String dataAgenda = agendamentoSelecionado.getDataAtendimento();
		txtDataAgenda.setText(dataAgenda);
		// String horaAgenda = agendamentoSelecionado.getHoraAtendimento();
		jboxHora.setSelectedItem(agendamentoSelecionado.getHoraAtendimento());
		int codUsuario = agendamentoSelecionado.getCodUsuario();
		Usuario barbeiro = null;
		barbeiro = UsuarioDao.buscarUsuarioPorId(codUsuario);
		if (barbeiro != null) {
			jboxBarbeiro.setSelectedItem(barbeiro.getNomeUsuario());
		} else {
		}
	}

	private void atualizarHorariosDisponiveis() {
		String nomeUsuarioSelecionado = (String) jboxBarbeiro.getSelectedItem();
		String dataAgendamento = txtDataAgenda.getText();

		if (dataAgendamento == null || dataAgendamento.isEmpty()) {
			System.out.println("Data de agendamento não fornecida.");
			return;
		}
		if (nomeUsuarioSelecionado != null && !nomeUsuarioSelecionado.isEmpty() && dataAgendamento != null
				&& !dataAgendamento.isEmpty()) {
			int codUsuario = -1;

			try {
				codUsuario = UsuarioDao.buscarCodigoUsuarioPorNome(nomeUsuarioSelecionado);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (codUsuario != -1) {
				ArrayList<LocalTime> horariosDisponiveis = controller.obterHorariosDisponiveis(codUsuario,
						dataAgendamento);

				// Atualizar a JComboBox com os horários disponíveis
				jboxHora.removeAllItems();
				for (LocalTime horario : horariosDisponiveis) {
					jboxHora.addItem(horario);
				}
			} else {
				System.out.println("Código do usuário não encontrado.");
			}
		} else {
			System.out.println("Nome do usuário ou data de agendamento não fornecidos.");
		}
	}

	private void iniciar() throws ExceptionDao, SQLException {
		getJboxBarbeiro().setSelectedItem("");
		getJboxServico().setSelectedItem(null);
		controller = new AgendamentoController(this);
		controller.atualizaServico();
		controller.atualizaValor();
		atualizarHorariosDisponiveis();
	}

	public JComboBox<Object> getJboxServico() {
		return jboxServico;
	}

	public JComboBox<Usuario> getJboxBarbeiro() {
		return jboxBarbeiro;
	}

	public void setJboxBarbeiro(JComboBox<Usuario> jboxBarbeiro) {
		this.jboxBarbeiro = jboxBarbeiro;
	}

	public void setJboxServico(JComboBox<Object> jboxServico) {
		this.jboxServico = jboxServico;
	}

	public JFormattedTextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(JFormattedTextField txtValor) {
		this.txtValor = txtValor;
	}

	public JTextField getTxtCpfCliente() {
		return txtCpfCliente;
	}

	public void setTxtNomeCliente(PlaceholderTextField txtNomeCliente) {
		this.txtNomeCliente = txtNomeCliente;
	}

	public void setTxtCpfCliente(JTextField txtCpfCliente) {
		this.txtCpfCliente = txtCpfCliente;
	}

	/*
	 * public JTextField getTxtHoraAgenda() { return txtHoraAgenda; }
	 * 
	 * public void setTxtHoraAgenda(JTextField txtHoraAgenda) { this.txtHoraAgenda =
	 * txtHoraAgenda; }
	 */

	public JComboBox<LocalTime> getJboxHora() {
		return jboxHora;
	}

	public void setJboxHora(JComboBox<LocalTime> jboxHora) {
		this.jboxHora = jboxHora;
	}

	public JFormattedTextField getTxtDataAgenda() {
		return txtDataAgenda;
	}

	public void setTxtDataAgenda(JFormattedTextField txtDataAgenda) {
		this.txtDataAgenda = txtDataAgenda;
	}

	public JTextComponent getTxtNomeCliente() {
		return txtNomeCliente;
	}
}
