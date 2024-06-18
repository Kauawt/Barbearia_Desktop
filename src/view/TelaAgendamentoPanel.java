package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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

/**
 * Painel de interface para gerenciamento de agendamentos.
 * Este painel permite preencher informações de agendamento, atualizar horários disponíveis,
 * inicializar a interface e formatar dados para criação de um novo agendamento.
 */
public class TelaAgendamentoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextField txtNomeCliente = new JTextField();
	private JTextField txtCpfCliente;
	private JFormattedTextField txtValor;
	private JFormattedTextField txtDataAgenda;
	JTextField txtObs = new JTextField();
	private JComboBox<Object> jboxServico;
	private JComboBox<Usuario> jboxBarbeiro;
	private AgendamentoController controller;
	private JComboBox<LocalTime> jboxHora;
	private UsuarioDao UsuarioDao;

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
		AgendaHelper agendaHelper = new AgendaHelper(TelaAgendamentoPanel.this);
		UsuarioController usuarioController = new UsuarioController();
		usuarioController.inicializarUsuarioDao();
		this.UsuarioDao = new UsuarioDao();
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
		MaskFormatter precoMask = null;
		MaskFormatter cpfMask = null;
		controller = new AgendamentoController(this);

		try {
			dataMask = new MaskFormatter("##/##/####");
			precoMask = new MaskFormatter("##.##");
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
		}
		if (agendamentoSelecionado == null) {
			txtNomeCliente = new PlaceholderTextField("Nome");
		}
		if (agendamentoSelecionado == null) {
			txtCpfCliente = new PlaceholderTextField("CPF");
		}
		if (agendamentoSelecionado == null) {
			txtValor = new JFormattedTextField("Valor R$");
		}
		JLabel lblAgenda = new JLabel("Agenda");
		lblAgenda.setForeground(new Color(255, 255, 255));
		lblAgenda.setBounds(220, 57, 280, 32);
		panel_1.add(lblAgenda, "cell 1 1 3 1,alignx center,aligny center");
		lblAgenda.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

		JLabel lblBarbeiro = new JLabel("Barbeiro");
		lblBarbeiro.setForeground(new Color(255, 255, 255));
		lblBarbeiro.setBounds(197, 108, 60, 21);
		panel_1.add(lblBarbeiro, "flowy,cell 1 2,alignx right,aligny center, gapbottom 15");
		lblBarbeiro.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblCpfUsuarioPanel = new JLabel("CPF");
		lblCpfUsuarioPanel.setForeground(new Color(255, 255, 255));
		lblCpfUsuarioPanel.setBounds(204, 139, 49, 21);
		panel_1.add(lblCpfUsuarioPanel, "cell 1 2,alignx right,aligny center, gapbottom 15");
		lblCpfUsuarioPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblNomeCliente = new JLabel("Nome");
		lblNomeCliente.setForeground(new Color(255, 255, 255));
		lblNomeCliente.setBounds(121, 171, 136, 21);
		panel_1.add(lblNomeCliente, "cell 1 2,alignx right, gapbottom 15");
		lblNomeCliente.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblServico = new JLabel("Serviço");
		lblServico.setForeground(new Color(255, 255, 255));
		lblServico.setBounds(185, 201, 68, 21);
		panel_1.add(lblServico, "cell 1 2,alignx right, gapbottom 15");
		lblServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblValor = new JLabel("Valor R$");
		lblValor.setForeground(new Color(255, 255, 255));
		lblValor.setBounds(202, 240, 49, 21);
		panel_1.add(lblValor, "cell 1 2,alignx right, gapbottom 15");
		lblValor.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblData = new JLabel("Data");
		lblData.setForeground(new Color(255, 255, 255));
		lblData.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblData.setBounds(202, 272, 49, 21);
		panel_1.add(lblData, "cell 1 2,alignx right, gapbottom 15");

		JLabel lblHora = new JLabel("Hora");
		lblHora.setForeground(new Color(255, 255, 255));
		lblHora.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblHora.setBounds(202, 272, 49, 21);
		panel_1.add(lblHora, "cell 1 2,alignx right, gapbottom 15");

		jboxBarbeiro = new JComboBox<>();

		jboxBarbeiro.setToolTipText("");
		jboxBarbeiro.setName("");

		jboxBarbeiro.setForeground(new Color(128, 128, 128));
		jboxBarbeiro.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		jboxBarbeiro.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        try {
					carregarBarbeiros();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}}
		});
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		jboxBarbeiro.setRenderer(renderer);
		jboxBarbeiro.setBounds(261, 305, 176, 22);
		jboxBarbeiro.setEditable(true);
		panel_1.add(jboxBarbeiro, "flowy,cell 2 2,growx,gapbottom 10");

		txtCpfCliente = new JFormattedTextField(cpfMask);
		txtCpfCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtCpfCliente.setForeground(new Color(128, 128, 128));
		txtCpfCliente.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtCpfCliente.setBounds(261, 141, 176, 20);
		panel_1.add(txtCpfCliente, "cell 2 2,growx,gapbottom 10");
		
		txtNomeCliente.addFocusListener(new FocusAdapter() {
		    private boolean avisoExibido = false; 
		    @Override
		    public void focusGained(FocusEvent e) {
		        String cpf = txtCpfCliente.getText().trim(); 
		        if (cpf.isEmpty()) {
		            if (!avisoExibido) {
		                JOptionPane.showMessageDialog(null, "Por favor, insira um CPF antes de buscar o nome do Cliente!");
		                avisoExibido = true; }
		            return;}

		        try {
		            String nomeCliente = ClienteController.consultarNomeCliente(cpf);
		            if (nomeCliente != null) {
		                txtNomeCliente.setText(nomeCliente);
		            } else {
		                txtNomeCliente.setText("");
		                if (!avisoExibido) {
		                    JOptionPane.showMessageDialog(null, "Por favor, insira um Cliente cadastrado!");
		                    avisoExibido = true; }}
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Por favor, insira um CPF correto! " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        }}
		});

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
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		jboxServico.setRenderer(renderer);
		jboxServico.setBounds(261, 305, 176, 22);
		panel_1.add(jboxServico, "cell 2 2,growx,gapbottom 10");
		jboxServico.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	controller.atualizaValor();
		    }
		});

		txtValor = new JFormattedTextField(precoMask);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(new Color(128, 128, 128));
		txtValor.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtValor.setBounds(261, 172, 176, 20);
		panel_1.add(txtValor, "cell 2 2,growx,gapbottom 10");

		txtDataAgenda = new JFormattedTextField(dataMask);
		txtDataAgenda.setHorizontalAlignment(SwingConstants.CENTER);
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
		txtDataAgenda.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        String dataTexto = txtDataAgenda.getText();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        try {
		            LocalDate dataAgendamento = LocalDate.parse(dataTexto, formatter);
		            LocalDate hoje = LocalDate.now();
		            LocalDate dataMaxima = hoje.plusDays(45);
		            if (dataAgendamento.isAfter(hoje.minusDays(1)) && dataAgendamento.isBefore(dataMaxima.plusDays(1))) {
		                atualizarHorariosDisponiveis();
		            } else {
		                JOptionPane.showMessageDialog(null, "Data de agendamento não permitida! (Permitido: 45 dias a partir de hoje)");
		            }
		        } catch (DateTimeParseException ex) {
		            JOptionPane.showMessageDialog(null, "Por favor preencha uma data, use o formato dd/MM/yyyy.");
		        }}
		});
		txtObs = new PlaceholderTextField("Observações:");
		txtObs.setForeground(new Color(128, 128, 128));
		txtObs.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtObs.setBounds(261, 172, 176, 20);
		panel_1.add(txtObs, "cell 3 2,gapbottom 10,grow");
		
		jboxHora = new JComboBox<LocalTime>();
		jboxHora.setForeground(new Color(128, 128, 128));
		jboxHora.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		jboxServico.setRenderer(renderer);
		jboxHora.setBounds(261, 305, 176, 22);
		jboxHora.setEditable(true);
		panel_1.add(jboxHora, "cell 2 2,growx,gapbottom 10");

		JButton btnCadastrarAgendaPanel = new JButton(agendamentoSelecionado == null ? "Agendar" : "Reagendar");
		btnCadastrarAgendaPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnCadastrarAgendaPanel.setBackground(new Color(240, 240, 240));
		btnCadastrarAgendaPanel.setBounds(261, 367, 104, 41);
		btnCadastrarAgendaPanel.setPreferredSize(new Dimension(100, 40));
		panel_1.add(btnCadastrarAgendaPanel, "cell 2 3,growx");
		btnCadastrarAgendaPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            Object[] dadosFormatados = formatarDados();
		            if (dadosFormatados == null || dadosFormatados.length != 6) {
		                JOptionPane.showMessageDialog(null, "Por favor, preencha os dados corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		            int codUsuario = (int) dadosFormatados[0];
		            int codCliente = (int) dadosFormatados[1];
		            int codServico = (int) dadosFormatados[2];
		            Double precoServico = (Double) dadosFormatados[3];
		            String dataAtendimento = (String) dadosFormatados[4];
		            LocalTime horaAtendimento = (LocalTime) dadosFormatados[5];

		            AgendaHelper agendaHelper = new AgendaHelper(TelaAgendamentoPanel.this);
		            boolean validaAgendamento = agendaHelper.validadorCamposTelaAgendamento(codUsuario, codCliente, codServico, precoServico, dataAtendimento, horaAtendimento);

		            if (validaAgendamento) {
		                if (agendamentoSelecionado == null) {
		                    controller.cadastrarAgendamento(codUsuario, codCliente, codServico, precoServico, dataAtendimento, horaAtendimento.toString());
		                    agendaHelper.limparAgendamentoConcluido();
		                } else {
		                    int codAgendamento = agendamentoSelecionado.getCodAgendamento();
		                    controller.atualizarAgendamento(codUsuario, codCliente, codServico, precoServico, dataAtendimento, horaAtendimento.toString(), codAgendamento);
		                    agendaHelper.limparAgendamentoConcluido();
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Validação dos dados falhou.", "Erro", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (NumberFormatException | ExceptionDao | SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Erro ao reagendar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        }}
		});       
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnConsultar.setPreferredSize(new Dimension(100, 40));
		btnConsultar.setBackground(new Color(240, 240, 240));
		btnConsultar.setBounds(148, 367, 104, 41);
		panel_1.add(btnConsultar, "cell 2 3,growx");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMenuPrincipal mainFrame = (TelaMenuPrincipal) SwingUtilities
						.getWindowAncestor(TelaAgendamentoPanel.this);
				JPanel desktop = mainFrame.getDesktop();
				desktop.removeAll();
				TelaConsultaAgendamento consulta = new TelaConsultaAgendamento();
				consulta.setVisible(true);
				desktop.add(consulta);
				desktop.revalidate();
				desktop.repaint();
			}
		});
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
		            int codAgendamento = agendamentoSelecionado.getCodAgendamento();
		            controller.cancelarAgendamento(codAgendamento);
		            AgendaHelper agendaHelper = new AgendaHelper(TelaAgendamentoPanel.this);
		            agendaHelper.limparAgendamentoConcluido();
		        } catch (NumberFormatException | ExceptionDao ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Erro ao cancelar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        }}
		});
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnCancelar.setBackground(new Color(240, 240, 240));
		btnCancelar.setIcon(null);
		btnCancelar.setBounds(261, 367, 124, 41);
		btnCancelar.setPreferredSize(new Dimension(100, 40));
		panel_1.add(btnCancelar, "cell 2 3,growx");

		if (agendamentoSelecionado != null) {
			preencherInfos(agendamentoSelecionado);
			controller.atualizaServico();
			jboxServico.setSelectedItem(agendamentoSelecionado.getServico());
		}
		if (agendamentoSelecionado == null) {
			iniciar();
		}
		
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
		setBounds(100, 100, 640, 480);}
		
		/**
	     * Preenche as informações de um agendamento selecionado nos campos correspondentes.
	     * @param agendamentoSelecionado O agendamento a ser exibido nos campos.
	     * @throws SQLException Se houver um problema ao acessar dados do banco.
	     */
	private void preencherInfos(Agendamento agendamentoSelecionado) throws SQLException {
		txtCpfCliente.setText(agendamentoSelecionado.getCliente().getCpfCliente());
		txtNomeCliente.setText(agendamentoSelecionado.getCliente().getNomeCliente());
		jboxServico.setSelectedItem(agendamentoSelecionado.getServico());
		txtValor.setText(String.valueOf(agendamentoSelecionado.getPrecoServico()));
		String dataAgenda = agendamentoSelecionado.getDataAtendimento();
		txtDataAgenda.setText(dataAgenda);
		jboxHora.setSelectedItem(agendamentoSelecionado.getHoraAtendimento());
		int codUsuario = agendamentoSelecionado.getCodUsuario();
		Usuario barbeiro = null;
		barbeiro = UsuarioDao.buscarUsuarioPorId(codUsuario);
		if (barbeiro != null) {
			jboxBarbeiro.setSelectedItem(barbeiro.getNomeUsuario());
		} else {
		}}
	
		/**
	     * Atualiza os horários disponíveis baseados no barbeiro selecionado e na data do agendamento.
	     * Exibe uma mensagem de erro se a data do agendamento não estiver preenchida.
	     */
	private void atualizarHorariosDisponiveis() {
	    Object selectedObject = jboxBarbeiro.getSelectedItem();
	    if (selectedObject == null) {
	        return;
	    }
	    String nomeUsuarioSelecionado;
	    if (selectedObject instanceof Usuario) {
	        nomeUsuarioSelecionado = ((Usuario) selectedObject).getNomeUsuario();
	    } else if (selectedObject instanceof String) {
	        nomeUsuarioSelecionado = (String) selectedObject;
	    } else {
	        nomeUsuarioSelecionado = selectedObject.toString(); 
	    }
	    if (nomeUsuarioSelecionado.isEmpty()) {
	        return;
	    }
	    int codUsuario = -1;
	    try {
	        codUsuario = UsuarioDao.buscarCodigoUsuarioPorNome(nomeUsuarioSelecionado);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return;
	    }
	    if (codUsuario != -1) {
	        String dataAgendamento = txtDataAgenda.getText();

	        if (dataAgendamento.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Por favor, forneça a data de agendamento (formato: dd/MM/yyyy)");
	            return;
	        }
	        ArrayList<LocalTime> horariosDisponiveis = controller.obterHorariosDisponiveis(codUsuario, dataAgendamento);
	        jboxHora.removeAllItems();
	        for (LocalTime horario : horariosDisponiveis) {
	            jboxHora.addItem(horario);
	        }
	    } else {
	    }
	}
	/**
     * Inicializa a interface de agendamento, atualizando serviços, valores e carregando a lista de barbeiros.
     * @throws ExceptionDao Se houver um problema ao acessar dados do banco.
     * @throws SQLException Se houver um problema ao acessar dados do banco.
     */
	private void iniciar() throws ExceptionDao, SQLException {
		controller = new AgendamentoController(this);
		controller.atualizaServico();
		controller.atualizaValor();
		carregarBarbeiros();
	}
	/**
     * Formata os dados inseridos na interface para criar um novo agendamento.
     * Exibe mensagens de erro se os dados estiverem incompletos ou inválidos.
     * @return Um array de objetos contendo os dados formatados para o agendamento.
     * Retorna null se os dados não puderem ser formatados corretamente.
     * @throws ParseException Se houver um erro ao analisar os dados inseridos.
     * @throws ExceptionDao Se houver um problema ao acessar dados do banco.
     * @throws SQLException Se houver um problema ao acessar dados do banco.
     */
	private Object[] formatarDados() throws ParseException, ExceptionDao, SQLException {
	    Object selectedObject = jboxBarbeiro.getSelectedItem();

	    if (selectedObject == null) {
	        JOptionPane.showMessageDialog(null, "Selecione um barbeiro válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
	        return null;
	    }

	    int codUsuario = -1;
	    if (selectedObject instanceof Usuario) {
	        codUsuario = ((Usuario) selectedObject).getCodUsuario();
	    } else if (selectedObject instanceof String) {
	        String nomeUsuario = (String) selectedObject;
	        codUsuario = UsuarioController.buscarCodigoUsuarioPorNome(nomeUsuario);
	    } else {
	        JOptionPane.showMessageDialog(null, "Item selecionado inválido.", "Aviso", JOptionPane.WARNING_MESSAGE);
	        return null;}
	    String cpfCliente = txtCpfCliente.getText();
	    Servico servico = (Servico) jboxServico.getSelectedItem();
	    double precoServico = Double.parseDouble(txtValor.getText());
	    String dataAtendimento = txtDataAgenda.getText();
	    LocalTime horaAtendimento = (LocalTime) jboxHora.getSelectedItem();
	    
	    if (horaAtendimento == null) {
	        return null;
	    }

	    int codServico = ServicoController.buscarCodigoServicoPorNome(servico.getTipoServico());
	    if (codServico == -1) {
	        return null;
	    }
	    int codCliente = ClienteController.buscarCodigoClientePorCPF(cpfCliente);
	    if (codCliente == -1) {
	        return null;
	    }
	    return new Object[]{codUsuario, codCliente, codServico, precoServico, dataAtendimento, horaAtendimento};
	}
	/**
     * Carrega a lista de barbeiros disponíveis no sistema e os adiciona ao seletor de barbeiros (jboxBarbeiro).
     * Exibe uma mensagem de erro se houver problema ao acessar dados do banco.
     * @throws SQLException Se houver um problema ao acessar dados do banco.
     */
	private void carregarBarbeiros() throws SQLException {
	    try {
	        UsuarioController usuarioController = new UsuarioController();
	        ArrayList<Usuario> barbeiros = usuarioController.obterUsuarios();

	        jboxBarbeiro.removeAllItems();
	        for (Usuario barbeiro : barbeiros) {
	            if (barbeiro != null) {
	                jboxBarbeiro.addItem(barbeiro);
	            }
	        }
	    } catch (ExceptionDao ex) {
	        ex.printStackTrace();
	    }
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

	public void setTxtNomeCliente(JTextField txtNomeCliente) {
		this.txtNomeCliente = txtNomeCliente;
	}

	public void setTxtCpfCliente(JTextField txtCpfCliente) {
		this.txtCpfCliente = txtCpfCliente;
	}
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
