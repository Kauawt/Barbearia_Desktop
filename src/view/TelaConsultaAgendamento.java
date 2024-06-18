package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.AgendaDao;
import dao.ClienteDao;
import dao.ExceptionDao;
import dao.UsuarioDao;
import model.Agendamento;
import model.Cliente;
import model.ModeloTabelaAgendamento;
import model.ModeloTabelaCliente;
import model.ModeloTabelaUsuario;
import net.miginfocom.swing.MigLayout;
/**
 * Painel de interface para consulta de agendamentos.
 */
public class TelaConsultaAgendamento extends JPanel {

	private static final long serialVersionUID = 1L;
	private TableRowSorter<ModeloTabelaAgendamento> rowSorter;
	private JTable table;
	private JTextField txtFiltrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaAgendamento frame = new TelaConsultaAgendamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaConsultaAgendamento() {
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
		panel_1.setLayout(new MigLayout("insets 0", "[100,grow][::600,grow][100,grow]",
				"[grow,fill][grow 50,fill][][::300,grow,fill][grow][grow,fill]"));

		JLabel lblConsultarAgendamento = new JLabel("Consultar Agendamento");
		lblConsultarAgendamento.setForeground(new Color(255, 255, 255));
		lblConsultarAgendamento.setBounds(220, 57, 280, 32);
		panel_1.add(lblConsultarAgendamento, "cell 1 1,alignx center");
		lblConsultarAgendamento.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

		ModeloTabelaAgendamento modeloTabela = new ModeloTabelaAgendamento(AgendaDao.listarAgendamentos());

		rowSorter = new TableRowSorter<>(modeloTabela);

		txtFiltrar = new JTextField();
		txtFiltrar.setMaximumSize(new Dimension(180, 2147483647));
		txtFiltrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrar();
			}
		});

		JLabel lblFiltrar = new JLabel("Filtrar");
		lblFiltrar.setForeground(new Color(255, 255, 255));
		lblFiltrar.setBounds(197, 108, 60, 21);
		panel_1.add(lblFiltrar, "flowx,cell 1 2,alignx left,aligny center");
		lblFiltrar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtFiltrar.setBorder(null);
		txtFiltrar.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		txtFiltrar.setForeground(new Color(128, 128, 128));
		txtFiltrar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtFiltrar.setBounds(261, 110, 176, 20);
		panel_1.add(txtFiltrar, "cell 1 2,alignx left");
		txtFiltrar.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(25, 99, 591, 301);
		panel_1.add(scrollPane, "cell 1 3,growx");
		table = new JTable();
		table.setModel(modeloTabela);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					try {
						int selectedRow = table.getSelectedRow(); // Obtém a linha selecionada na tabela
						int modelRow = table.convertRowIndexToModel(selectedRow);
						String codAgendamentoStr = modeloTabela.getValueAt(modelRow, 0).toString();
						String nomeUsuario = modeloTabela.getValueAt(modelRow, 7).toString();
						String dataAtendimentoStr = modeloTabela.getValueAt(modelRow, 5).toString();
						int codAgendamento = Integer.parseInt(codAgendamentoStr);
						String dataAtendimentoFormatada = DataUtil.formatarData(dataAtendimentoStr); // Formatar a data
						int codUsuario = UsuarioDao.buscarCodigoUsuarioPorNome(nomeUsuario);
						Agendamento agendamentoSelecionado = AgendaDao.consultarAgendamentoPorId(codAgendamento);
						if (agendamentoSelecionado != null) {
							agendamentoSelecionado.setCodUsuario(codUsuario); // Atualiza o agendamento com os valores
																				// obtidos da tabela (caso necessário)
							agendamentoSelecionado.setDataAtendimento(dataAtendimentoFormatada);
							TelaMenuPrincipal mainFrame = (TelaMenuPrincipal) SwingUtilities
									.getWindowAncestor(TelaConsultaAgendamento.this);
							JPanel desktop = mainFrame.getDesktop();
							desktop.removeAll();
							TelaAgendamentoPanel cadastraAgendamento = new TelaAgendamentoPanel(agendamentoSelecionado);
							cadastraAgendamento.setVisible(true);
							desktop.add(cadastraAgendamento);
							desktop.revalidate();
							desktop.repaint();
						} else {
							JOptionPane.showMessageDialog(null, "Agendamento não encontrado.", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Erro ao converter ID do agendamento: " + ex.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					} catch (ExceptionDao | SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Erro ao buscar agendamento: " + ex.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					} catch (ParseException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Erro ao formatar data: " + e1.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		table.setRowSorter(rowSorter);

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
	/**
     * Classe utilitária para formatação de datas.
     * Converte uma data do formato "yyyy-MM-dd" para "dd/MM/yyyy".
     */
	public class DataUtil {
		/**
         * Formata uma data do formato "yyyy-MM-dd" para "dd/MM/yyyy".
         * @param dataOriginal A data no formato "yyyy-MM-dd".
         * @return A data formatada no formato "dd/MM/yyyy".
         * @throws ParseException Se houver um erro ao analisar a data original.
         */
		public static String formatarData(String dataOriginal) throws ParseException {
			SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");
			Date data = formatoBanco.parse(dataOriginal);
			return formatoDesejado.format(data);
		}
	}

	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	/**
     * Filtra os resultados da consulta com base no texto inserido no campo de filtragem.
     */
	private void filtrar() {
		String filtrar = txtFiltrar.getText().trim();
		if (filtrar.length() == 0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + filtrar));
		}
	}
}
