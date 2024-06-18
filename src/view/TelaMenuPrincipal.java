
package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;
import controller.LoginController;
import dao.ExceptionDao;
import model.Gerente;

import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import view.JPictureBox.SizeMode;
import java.awt.ComponentOrientation;
/**
 * Classe responsável pela interface do menu principal do sistema.
 * Esta classe estende JFrame para criar uma janela que exibe opções principais de navegação.
 */
public class TelaMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JPanel desktop;
	private JMenuItem mntmNewSair;
	private JMenuItem mntmRelatorioAgendamento;
	private JMenuItem mntmCadastrarUsuario;
	private JMenu mnRelatorio;
	private JMenu mnMenuCadastro;
	private JLabel lblData;
	private JLabel lblUser;
	JMenuItem mntmConsultaUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuPrincipal frame = new TelaMenuPrincipal();
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
	public TelaMenuPrincipal() {
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		contentPane.setPreferredSize(new Dimension(640, 480));

		desktop = new JPanel();
		desktop.setBorder(null);
		contentPane.setLayout(
				new MigLayout("insets 0", "[100px:n,grow,fill][200]", "[100px][100px][250][200][grow,fill]"));
		contentPane.add(desktop, "cell 0 0 1 5,grow");
		desktop.setLayout(new MigLayout("insets 0", "[grow,fill]", "[grow,fill]"));

		JPictureBox pictureBox_1 = new JPictureBox();
		pictureBox_1
				.setIcon(new ImageIcon(TelaUsuarioPanel.class.getResource("/icones/vintage-chairs-barbershop.png")));
		pictureBox_1.setBounds(0, 0, 640, 453);
		desktop.add(pictureBox_1, "cell 0 0,grow");

		setMinimumSize(new Dimension(840, 600));
		setTitle("Menu");
		addWindowListener(new WindowAdapter() {
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 879, 538);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnMenuCadastro = new JMenu("Cadastro");
		menuBar.add(mnMenuCadastro);

		JMenuItem mntmCadastrarCliente = new JMenuItem("Cliente");
		mntmCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desktop.removeAll();
					TelaClientePanel telaCliente = new TelaClientePanel(null);
					telaCliente.setVisible(true);
					desktop.add(telaCliente);
					desktop.revalidate();
					desktop.repaint();
				} catch (ExceptionDao e1) {
					e1.printStackTrace();
				}
			}
		});
		mnMenuCadastro.add(mntmCadastrarCliente);

		mntmCadastrarUsuario = new JMenuItem("Usuario");
		mntmCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desktop.removeAll();
					TelaUsuarioPanel telaUsuario = new TelaUsuarioPanel(null);
					telaUsuario.setVisible(true);
					desktop.add(telaUsuario);
					desktop.revalidate();
					desktop.repaint();
				} catch (ExceptionDao e1) {
					e1.printStackTrace();
				}

			}
		});
		mnMenuCadastro.add(mntmCadastrarUsuario);

		JMenuItem mntmCadastrarServico = new JMenuItem("Servico");
		mntmCadastrarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desktop.removeAll();
					TelaServicoPanel telaServico = new TelaServicoPanel(null);
					telaServico.setVisible(true);
					desktop.add(telaServico);
					desktop.revalidate();
					desktop.repaint();
				} catch (ExceptionDao e1) {
					e1.printStackTrace();
				}
			}
		});
		mnMenuCadastro.add(mntmCadastrarServico);

		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);

		mntmConsultaUsuario = new JMenuItem("Usuario");
		mntmConsultaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.removeAll();
				TelaConsultaUsuario consulta = new TelaConsultaUsuario();
				consulta.setVisible(true);
				desktop.add(consulta);
				desktop.revalidate();
				desktop.repaint();
			}
		});

		mnConsulta.add(mntmConsultaUsuario);

		JMenuItem mntmConsultaCliente = new JMenuItem("Cliente");
		mntmConsultaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.removeAll();
				TelaConsultaCliente consulta = new TelaConsultaCliente();
				consulta.setVisible(true);
				desktop.add(consulta);
				desktop.revalidate();
				desktop.repaint();
			}
		});
		mnConsulta.add(mntmConsultaCliente);

		JMenuItem mntmServico = new JMenuItem("Servico");
		mntmServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.removeAll();
				TelaConsultaServico consulta = new TelaConsultaServico();
				consulta.setVisible(true);
				desktop.add(consulta);
				desktop.revalidate();
				desktop.repaint();

			}
		});
		mnConsulta.add(mntmServico);

		JMenuItem mntmConsultaAgendamentos = new JMenuItem("Agendamentos");
		mntmConsultaAgendamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.removeAll();
				TelaConsultaAgendamento consulta = new TelaConsultaAgendamento();
				consulta.setVisible(true);
				desktop.add(consulta);
				desktop.revalidate();
				desktop.repaint();
			}
		});
		mnConsulta.add(mntmConsultaAgendamentos);

		JMenu mnAgendamento = new JMenu("Agendamento");
		menuBar.add(mnAgendamento);

		JMenuItem mntmAgenda = new JMenuItem("Agenda");
		mntmAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desktop.removeAll();
					TelaAgendamentoPanel telaAgendamento = new TelaAgendamentoPanel(null);
					telaAgendamento.setVisible(true);
					desktop.add(telaAgendamento);
					desktop.revalidate();
					desktop.repaint();
				} catch (ExceptionDao e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmAgenda.setHorizontalAlignment(SwingConstants.LEFT);
		mnAgendamento.add(mntmAgenda);

		mnRelatorio = new JMenu("Relatorio");
		menuBar.add(mnRelatorio);

		mntmRelatorioAgendamento = new JMenuItem("Agendamento");
		mntmRelatorioAgendamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gerente.GeraRelatorio();
			}
		});
		mntmRelatorioAgendamento.setEnabled(false);
		mnRelatorio.add(mntmRelatorioAgendamento);

		JMenu mnNewMenu = new JMenu("Ajuda");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewSobre = new JMenuItem("Sobre");
		mntmNewSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.removeAll();
				TelaSobrePanel telaSobre = new TelaSobrePanel();
				telaSobre.setVisible(true);
				desktop.add(telaSobre);
				desktop.revalidate();
				desktop.repaint();
			}
		});
		mnNewMenu.add(mntmNewSobre);

		JMenu mnOpcoes = new JMenu("Opções");
		menuBar.add(mnOpcoes);

		mntmNewSair = new JMenuItem("Sair");
		mntmNewSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Caixa de Diálogo (Sim ou não)
				int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção",
						JOptionPane.YES_NO_OPTION);
				if (sair == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		mnOpcoes.add(mntmNewSair);

		setContentPane(contentPane);

		JLabel lblNomeData = new JLabel("DATA:");
		lblNomeData.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		contentPane.add(lblNomeData, "flowx,cell 1 1,growx,aligny center");

		JLabel lblNomeUser = new JLabel("USER:");
		lblNomeUser.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		contentPane.add(lblNomeUser, "flowx,cell 1 0,growx,aligny center");

		lblUser = new JLabel("----");
		lblUser.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		contentPane.add(lblUser, "cell 1 0,growx,aligny center");

		lblData = new JLabel("----");
		lblData.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		contentPane.add(lblData, "cell 1 1,growx,aligny center");

		JPictureBox pbox_iconeJP = new JPictureBox();
		pbox_iconeJP.setSizeMode(SizeMode.CENTER);
		pbox_iconeJP.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/icones/logo3.png")));
		contentPane.add(pbox_iconeJP, "cell 1 2,grow");

	}

	public JPanel getDesktop() {
		return desktop;
	}

	public void setDesktop(JPanel desktop) {
		this.desktop = desktop;
	}

	public JMenuItem getMntmNewSair() {
		return mntmNewSair;
	}

	public void setMntmNewSair(JMenuItem mntmNewSair) {
		this.mntmNewSair = mntmNewSair;
	}

	public JMenuItem getMntmRelatorioAgendamento() {
		return mntmRelatorioAgendamento;
	}

	public void setMntmRelatorioAgendamento(JMenuItem mntmRelatorioAgendamento) {
		this.mntmRelatorioAgendamento = mntmRelatorioAgendamento;
	}
	
	public JMenuItem getMntmConsultaUsuario() {
		return mntmConsultaUsuario;
	}

	public JMenuItem getMntmCadastrarUsuario() {
		return mntmCadastrarUsuario;
	}

	public void setMntmCadastrarUsuario(JMenuItem mntmCadastrarUsuario) {
		this.mntmCadastrarUsuario = mntmCadastrarUsuario;
	}

	public JMenu getMnRelatorio() {
		return mnRelatorio;
	}

	public void setMnRelatorio(JMenu mnRelatorio) {
		this.mnRelatorio = mnRelatorio;
	}

	public JMenu getMnMenuCadastro() {
		return mnMenuCadastro;
	}

	public void setMnMenuCadastro(JMenu mnMenuCadastro) {
		this.mnMenuCadastro = mnMenuCadastro;
	}

	public JLabel getLblData() {
		return lblData;
	}

	public void setLblData(JLabel lblData) {
		this.lblData = lblData;
	}

	public JLabel getLblUser() {
		return lblUser;
	}

	public void setLblUser(JLabel lblUser) {
		this.lblUser = lblUser;
	}
}