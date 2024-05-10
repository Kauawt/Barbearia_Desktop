package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

public class TelaMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane desktop;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 541);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenuCadastro = new JMenu("Cadastro");
		menuBar.add(mnMenuCadastro);
		
		JMenuItem mntmCadastrarCliente = new JMenuItem("Cliente");
		mntmCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente c2 = new TelaCliente();
				c2.setVisible(true);
				desktop.add(c2);
			}
		});
		mnMenuCadastro.add(mntmCadastrarCliente);
		
		JMenuItem mntmCadastrarFuncionario = new JMenuItem("Funcionario");
		mnMenuCadastro.add(mntmCadastrarFuncionario);
		
		JMenuItem mntmCadastrarServico = new JMenuItem("Servico");
		mnMenuCadastro.add(mntmCadastrarServico);
		JPanel contentPane = new JPanel();
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setPreferredSize(new Dimension(640, 480));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		
		desktop = new JDesktopPane();
		desktop.setPreferredSize(new Dimension(640, 480));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(244, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		desktop.setLayout(new CardLayout(0, 0));
		contentPane.setLayout(gl_contentPane);
	}

}
