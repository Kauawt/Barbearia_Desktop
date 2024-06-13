package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import model.ModeloTabelaUsuario;
public class TelaConsultaAgendamento extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private TableRowSorter<ModeloTabelaAgendamento> rowSorter;
	private JPanel contentPane;
	private JTable table;


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
		setTitle("Consulta Agendamentos");
		setBounds(100, 100, 650, 455);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 35, 602, 363);
		getContentPane().add(scrollPane);
		
		ModeloTabelaAgendamento modeloTabela = new ModeloTabelaAgendamento(AgendaDao.listarAgendamentos());
		
		table = new JTable();
		table.setModel(modeloTabela);
		scrollPane.setViewportView(table);
		JPictureBox pictureBox = new JPictureBox();
		pictureBox.setBounds(0, 0, 634, 425);
		getContentPane().add(pictureBox);
		pictureBox.setIcon(new ImageIcon(TelaConsultaAgendamento.class.getResource("/icones/fundo_barberia.jpeg")));
		
		setIconifiable(true);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBorder(null);
		setMaximizable(true);
		setClosable(true);
		setRootPaneCheckingEnabled(false);
		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if (e.getButton() == MouseEvent.BUTTON1) { 
		            try {
		                int selectedRow = table.getSelectedRow(); // Obtém a linha selecionada na tabela
		                String codAgendamentoStr = modeloTabela.getValueAt(selectedRow, 0).toString();
		                String nomeUsuario = modeloTabela.getValueAt(selectedRow, 7).toString();
		                String dataAtendimentoStr = modeloTabela.getValueAt(selectedRow, 5).toString();
		                int codAgendamento = Integer.parseInt(codAgendamentoStr);
		                String dataAtendimentoFormatada = DataUtil.formatarData(dataAtendimentoStr); // Formatar a data
		                int codUsuario = UsuarioDao.buscarCodigoUsuarioPorNome(nomeUsuario);
		                Agendamento agendamentoSelecionado = AgendaDao.consultarAgendamentoPorId(codAgendamento);
		                if (agendamentoSelecionado != null) {
		                    agendamentoSelecionado.setCodUsuario(codUsuario); // Atualiza o agendamento com os valores obtidos da tabela (caso necessário)
		                    agendamentoSelecionado.setDataAtendimento(dataAtendimentoFormatada);
		                    TelaAgendamento cadastrarAgendamento = new TelaAgendamento(agendamentoSelecionado);
		                    JDesktopPane desktop = getDesktopPane();
		                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(desktop);
		                    if (frame instanceof TelaMenuPrincipal) {
		                        JInternalFrame[] frames = desktop.getAllFrames();
		                        for (JInternalFrame frame1 : frames) {
		                            frame1.dispose();
		                        }
		                    }
		                    desktop.add(cadastrarAgendamento);
		                    cadastrarAgendamento.setVisible(true);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Agendamento não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
		                }
		            } catch (NumberFormatException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Erro ao converter ID do agendamento: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		            } catch (ExceptionDao | SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Erro ao buscar agendamento: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		            } catch (ParseException e1) {
		                e1.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Erro ao formatar data: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});



	}
	
	public class DataUtil {
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
}
