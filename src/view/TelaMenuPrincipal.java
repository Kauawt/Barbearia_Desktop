
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

public class TelaMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane desktop;
	private JMenuItem mntmNewSair;
	private JMenuItem mntmRelatorioAgendamento;
	private JMenuItem mntmCadastrarUsuario;
	private JMenu mnRelatorio;
	private JMenu mnMenuCadastro;
	private JLabel lblData;
	private JLabel lblUser;

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

		setTitle("Menu");
		addWindowListener(new WindowAdapter() {
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 879, 538);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnMenuCadastro = new JMenu("Cadastro");
		menuBar.add(mnMenuCadastro);

		JMenuItem mntmCadastrarCliente = new JMenuItem("Cliente");
		mntmCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame[] frames = desktop.getAllFrames();
				for (JInternalFrame frame1 : frames) {
					frame1.dispose();
				}
				try {
					TelaCliente telaCliente = new TelaCliente(null);
					telaCliente.setVisible(true);
					desktop.add(telaCliente);
				} catch (ExceptionDao e1) {
					e1.printStackTrace();
				}
			}
		});
		mnMenuCadastro.add(mntmCadastrarCliente);

		mntmCadastrarUsuario = new JMenuItem("Usuario");
		mntmCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame[] frames = desktop.getAllFrames();
				for (JInternalFrame frame1 : frames) {
					frame1.dispose();
				}
				TelaUsuario telaUsuario;
				try {
					telaUsuario = new TelaUsuario(null);
					telaUsuario.setVisible(true);
					desktop.add(telaUsuario);
				} catch (ExceptionDao e1) {
					e1.printStackTrace();
				}

			}
		});
		mnMenuCadastro.add(mntmCadastrarUsuario);

		JMenuItem mntmCadastrarServico = new JMenuItem("Servico");
		mntmCadastrarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame[] frames = desktop.getAllFrames();
				for (JInternalFrame frame1 : frames) {
					frame1.dispose();
				}
				TelaServico telaServico;
				try {
					telaServico = new TelaServico(null);
					telaServico.setVisible(true);
					desktop.add(telaServico);
				} catch (ExceptionDao e1) {
					e1.printStackTrace();
				}
			}
		});
		mnMenuCadastro.add(mntmCadastrarServico);

		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);

		JMenuItem mntmConsultaUsuario = new JMenuItem("Usuario");
		mntmConsultaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame[] frames = desktop.getAllFrames();
				for (JInternalFrame frame1 : frames) {
					frame1.dispose();
				}
				TelaConsultaUsuario consulta = new TelaConsultaUsuario();
				consulta.setVisible(true);
				desktop.add(consulta);
			}
		});
		mnConsulta.add(mntmConsultaUsuario);

		JMenuItem mntmConsultaCliente = new JMenuItem("Cliente");
		mntmConsultaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame[] frames = desktop.getAllFrames();
				for (JInternalFrame frame1 : frames) {
					frame1.dispose();
				}
				TelaConsultaCliente consulta = new TelaConsultaCliente();
				consulta.setVisible(true);
				desktop.add(consulta);
			}
		});
		mnConsulta.add(mntmConsultaCliente);
		
		JMenuItem mntmServico = new JMenuItem("Servico");
		mntmServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JInternalFrame[] frames = desktop.getAllFrames();
				for (JInternalFrame frame1 : frames) {
					frame1.dispose();
				}
				TelaConsultaServico consulta = new TelaConsultaServico();
				consulta.setVisible(true);
				desktop.add(consulta);

			}
		});
		mnConsulta.add(mntmServico);
		
		JMenuItem mntmConsultaAgendamentos = new JMenuItem("Agendamentos");
		mntmConsultaAgendamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame[] frames = desktop.getAllFrames();
				for (JInternalFrame frame1 : frames) {
					frame1.dispose();
				}
				TelaConsultaAgendamento consulta = new TelaConsultaAgendamento();
				consulta.setVisible(true);
				desktop.add(consulta);
			}
		});
		mnConsulta.add(mntmConsultaAgendamentos);

		JMenu mnAgendamento = new JMenu("Agendamento");
		menuBar.add(mnAgendamento);
		
		JMenuItem mntmAgenda = new JMenuItem("Agenda");
		mntmAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame[] frames = desktop.getAllFrames();
				for (JInternalFrame frame1 : frames) {
					frame1.dispose();
				}
				TelaAgendamento telaagendamento = new TelaAgendamento();	
				telaagendamento.setVisible(true);
				desktop.add(telaagendamento);
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
				JInternalFrame[] frames = desktop.getAllFrames();
				for (JInternalFrame frame1 : frames) {
					frame1.dispose();
				}
				TelaSobre telaSobre = new TelaSobre();
				telaSobre.setVisible(true);
				desktop.add(telaSobre);
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
		JPanel contentPane = new JPanel();
		contentPane.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setPreferredSize(new Dimension(640, 480));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);

		desktop = new JDesktopPane();
		desktop.setBounds(0, 0, 640, 481);
		desktop.setPreferredSize(new Dimension(640, 480));

		JLabel lblNomeUser = new JLabel("USER:");
		lblNomeUser.setBounds(650, 89, 68, 29);
		lblNomeUser.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

		JLabel lblNomeData = new JLabel("DATA:");
		lblNomeData.setBounds(650, 159, 69, 29);
		lblNomeData.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		contentPane.setLayout(null);
		desktop.setLayout(new CardLayout(0, 0));
		contentPane.add(desktop);
		contentPane.add(lblNomeUser);
		contentPane.add(lblNomeData);

		lblData = new JLabel("----");
		lblData.setBounds(729, 162, 159, 22);
		lblData.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		contentPane.add(lblData);

		lblUser = new JLabel("----");
		lblUser.setBounds(728, 92, 160, 22);
		lblUser.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		contentPane.add(lblUser);
		
		JPictureBox pbox_iconeJP = new JPictureBox();
		pbox_iconeJP.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/icones/logo3.png")));
		pbox_iconeJP.setBounds(650, 199, 203, 227);
		contentPane.add(pbox_iconeJP);

	}

	public JDesktopPane getDesktop() {
		return desktop;
	}

	public void setDesktop(JDesktopPane desktop) {
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