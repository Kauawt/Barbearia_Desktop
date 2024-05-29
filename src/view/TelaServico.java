package view;

import java.awt.EventQueue;
import javax.swing.SwingConstants;

import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;
import controller.ServicoController;
import controller.UsuarioController;
import dao.ExceptionDao;
import dao.ModuloConexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class TelaServico extends JInternalFrame {

	public Connection conexao = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigoServico;
	private JTextField txtTipoServico;
	private JTextField txtDuracaoServico;
	private JTextField txtPrecoServico;
	private JTextField txtDescricaoServico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaServico frame = new TelaServico();
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
	public TelaServico() {
		getContentPane().setBackground(new Color(232, 227, 225));
		
		setResizable(true);
		getContentPane().setSize(new Dimension(450, 480));
		getContentPane().setPreferredSize(new Dimension(450, 480));
		getContentPane().setLayout(null);
		
		JLabel lblFormularioServico = new JLabel("Formulário Serviço");
		lblFormularioServico.setForeground(new Color(255, 255, 255));
		lblFormularioServico.setBounds(224, 77, 280, 32);
		getContentPane().add(lblFormularioServico);
		lblFormularioServico.setFont(new Font("Comic Sans MS", Font.BOLD, 22));

		JLabel lblCodigoServico = new JLabel("ID");
		lblCodigoServico.setForeground(new Color(255, 255, 255));
		lblCodigoServico.setBounds(133, 126, 112, 21);
		getContentPane().add(lblCodigoServico);
		lblCodigoServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtCodigoServico = new JTextField();
		txtCodigoServico.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigoServico.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtCodigoServico.setForeground(new Color(128, 128, 128));
		txtCodigoServico.setText("ID");
		txtCodigoServico.setBounds(249, 128, 176, 20);
		getContentPane().add(txtCodigoServico);
		txtCodigoServico.setColumns(10);

		JLabel lblNomeServico = new JLabel("Tipo");
		lblNomeServico.setForeground(new Color(255, 255, 255));
		lblNomeServico.setBounds(133, 163, 112, 21);
		getContentPane().add(lblNomeServico);
		lblNomeServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtTipoServico = new JTextField();
		txtTipoServico.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipoServico.setText("Tipo");
		txtTipoServico.setForeground(new Color(128, 128, 128));
		txtTipoServico.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtTipoServico.setBounds(249, 165, 176, 20);
		getContentPane().add(txtTipoServico);
		txtTipoServico.setColumns(10);

		JLabel lblDuracao = new JLabel("Duração");
		lblDuracao.setForeground(new Color(255, 255, 255));
		lblDuracao.setBounds(133, 227, 112, 21);
		getContentPane().add(lblDuracao);
		lblDuracao.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtDuracaoServico = new JTextField();
		txtDuracaoServico.setHorizontalAlignment(SwingConstants.CENTER);
		txtDuracaoServico.setText("Duração");
		txtDuracaoServico.setForeground(new Color(128, 128, 128));
		txtDuracaoServico.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtDuracaoServico.setBounds(249, 229, 176, 20);
		getContentPane().add(txtDuracaoServico);
		txtDuracaoServico.setColumns(10);

		JLabel lblPrecoServico = new JLabel("Preço");
		lblPrecoServico.setForeground(new Color(255, 255, 255));
		lblPrecoServico.setBounds(133, 259, 58, 21);
		getContentPane().add(lblPrecoServico);
		lblPrecoServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtPrecoServico = new JTextField();
		txtPrecoServico.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecoServico.setText("Preço");
		txtPrecoServico.setForeground(new Color(128, 128, 128));
		txtPrecoServico.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtPrecoServico.setBounds(249, 261, 176, 20);
		getContentPane().add(txtPrecoServico);
		txtPrecoServico.setColumns(10);

		JLabel lblDescricaoServico = new JLabel("Descrição");
		lblDescricaoServico.setForeground(new Color(255, 255, 255));
		lblDescricaoServico.setBounds(133, 195, 112, 21);
		getContentPane().add(lblDescricaoServico);
		lblDescricaoServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtDescricaoServico = new JTextField();
		txtDescricaoServico.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescricaoServico.setText("Descrição");
		txtDescricaoServico.setForeground(new Color(128, 128, 128));
		txtDescricaoServico.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtDescricaoServico.setBounds(249, 197, 176, 20);
		getContentPane().add(txtDescricaoServico);
		txtDescricaoServico.setColumns(10);

		JLabel lblStatusServico = new JLabel("Status");
		lblStatusServico.setForeground(new Color(255, 255, 255));
		lblStatusServico.setBounds(133, 291, 49, 21);
		getContentPane().add(lblStatusServico);
		lblStatusServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JButton btnCadastrarServico = new JButton("Adicionar");
		btnCadastrarServico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarServico.setBackground(new Color(240, 240, 240));
		btnCadastrarServico.setIcon(null);
		btnCadastrarServico.setPreferredSize(new Dimension(80, 80));
		btnCadastrarServico.setBounds(84, 342, 104, 40);
		getContentPane().add(btnCadastrarServico);

		JButton btnAlterarServico = new JButton("Alterar");
		btnAlterarServico.setIcon(null);
		btnAlterarServico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterarServico.setPreferredSize(new Dimension(80, 80));
		btnAlterarServico.setBounds(203, 342, 104, 40);
		getContentPane().add(btnAlterarServico);

		JButton btnConsultarServico = new JButton("Pesquisar");
		btnConsultarServico.setIcon(null);
		btnConsultarServico.setBounds(321, 342, 104, 40);
		getContentPane().add(btnConsultarServico);

		JButton btnDeletarServico = new JButton("Excluir");
		btnDeletarServico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletarServico.setIcon(null);
		btnDeletarServico.setBounds(444, 342, 104, 40);
		getContentPane().add(btnDeletarServico);

		final JComboBox cbStatusServico = new JComboBox();
		cbStatusServico.setForeground(new Color(128, 128, 128));
		cbStatusServico.setFont(new Font("Arial Black", Font.PLAIN, 11));
		cbStatusServico.setModel(new DefaultComboBoxModel(new String[] {"Ativo", "Inativo"}));
		cbStatusServico.setBounds(250, 292, 176, 22);
		getContentPane().add(cbStatusServico);

		JPictureBox pictureBox = new JPictureBox();
		pictureBox.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/wallpaper_telas.png")));
		pictureBox.setBounds(0, 0, 640, 453);
		getContentPane().add(pictureBox);
		
		JButton btnCadastrarServico1 = new JButton("");
		btnCadastrarServico1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarServico1.setBackground(new Color(240, 240, 240));
		btnCadastrarServico1.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/addicon.png")));
		btnCadastrarServico1.setPreferredSize(new Dimension(80, 80));
		btnCadastrarServico1.setBounds(34, 374, 117, 68);
		getContentPane().add(btnCadastrarServico1);
		
		JButton btnAlterarServico1 = new JButton("");
		btnAlterarServico1.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/editicon.png")));
		btnAlterarServico1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterarServico1.setPreferredSize(new Dimension(80, 80));
		btnAlterarServico1.setBounds(185, 374, 117, 68);
		getContentPane().add(btnAlterarServico1);
		
		JButton btnConsultarServico1 = new JButton("");
		btnConsultarServico1.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/findicon.png")));
		btnConsultarServico1.setBounds(336, 374, 117, 68);
		getContentPane().add(btnConsultarServico1);
		
		JButton btnDeletarServico1 = new JButton("");
		btnDeletarServico1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletarServico1.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/deleteicon.png")));
		btnDeletarServico1.setBounds(487, 374, 117, 68);
		getContentPane().add(btnDeletarServico1);
		
		final JComboBox cbStatusServico1 = new JComboBox();
		cbStatusServico1.setModel(new DefaultComboBoxModel(new String[] {"Ativo", "Inativo"}));
		cbStatusServico1.setBounds(452, 235, 176, 22);
		getContentPane().add(cbStatusServico1);
		btnConsultarServico1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
			}
		});
		btnCadastrarServico1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int codServico = Integer.parseInt(txtCodigoServico.getText());
				double precoServico = Double.parseDouble(txtPrecoServico.getText());
				double duracaoServico =Double.parseDouble(txtDuracaoServico.getText());
				ServicoController servicoController = new ServicoController();
				try {
					servicoController.cadastrarServico(codServico,txtTipoServico.getText(),txtDescricaoServico.getText(),precoServico,duracaoServico,cbStatusServico1.getSelectedItem().toString());
				} catch (ExceptionDao e1) {
					e1.printStackTrace();
				}
			}
		});
		setIconifiable(true);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBorder(null);
		setPreferredSize(new Dimension(640, 480));
		setMaximizable(true);
		setClosable(true);
		setRootPaneCheckingEnabled(false);
		setMinimumSize(new Dimension(450, 480));
		setTitle("Usuario");
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBounds(100, 100, 640, 480);

	}
}
