package view;

import java.awt.EventQueue;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
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
import java.text.ParseException;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;
import controller.ServicoController;
import controller.UsuarioController;
import dao.ClienteDao;
import dao.ExceptionDao;
import dao.ModuloConexao;
import dao.ServicoDao;
import model.Cliente;
import model.Servico;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.UIManager;

public class TelaServico extends JInternalFrame {

	public Connection conexao = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private ServicoController servicoController = new ServicoController();
	private JTextField txtTipoServico;
	private JTextField txtDescricaoServico;
	private JTextField txtDuracaoServico;
	private JTextField txtPrecoServico;
	private ServicoController servicocontroller = new ServicoController();
	private JComboBox cbStatusServico;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaServico frame = new TelaServico(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ExceptionDao 
	 */
	public TelaServico(Servico servicoSelecionado) throws ExceptionDao {

		
		getContentPane().setBackground(new Color(232, 227, 225));
		
		setResizable(true);
		getContentPane().setSize(new Dimension(450, 480));
		getContentPane().setPreferredSize(new Dimension(450, 480));
		getContentPane().setLayout(null);
		
		JLabel lblMin = new JLabel("Min");
		lblMin.setForeground(Color.WHITE);
		lblMin.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblMin.setBounds(275, 254, 58, 21);
		getContentPane().add(lblMin);
		
		JLabel lblPreco = new JLabel("R$");
		lblPreco.setForeground(Color.WHITE);
		lblPreco.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPreco.setBounds(275, 224, 58, 21);
		getContentPane().add(lblPreco);
		
		cbStatusServico = new JComboBox();
		cbStatusServico.setModel(new DefaultComboBoxModel(new String[] {"Ativo", "Inativo"}));
		cbStatusServico.setBounds(224, 284, 95, 22);
		getContentPane().add(cbStatusServico);
		

		txtTipoServico = new JTextField();
		txtTipoServico.setColumns(10);
		txtTipoServico.setBounds(224, 157, 211, 20);
		getContentPane().add(txtTipoServico);
		
		txtPrecoServico = new JTextField();
		txtPrecoServico.setColumns(10);
		txtPrecoServico.setBounds(224, 221, 41, 20);
		getContentPane().add(txtPrecoServico);
		
		txtDuracaoServico = new JTextField();
		txtDuracaoServico.setColumns(10);
		txtDuracaoServico.setBounds(224, 254, 41, 20);
		getContentPane().add(txtDuracaoServico);
		
		txtDescricaoServico = new JTextField();
		txtDescricaoServico.setColumns(10);
		txtDescricaoServico.setBounds(224, 188, 211, 20);
		getContentPane().add(txtDescricaoServico);
		
		JLabel lblFormularioServico = new JLabel("Formulário Serviço");
		lblFormularioServico.setForeground(new Color(255, 255, 255));
		lblFormularioServico.setBounds(224, 77, 280, 32);
		getContentPane().add(lblFormularioServico);
		lblFormularioServico.setFont(new Font("Comic Sans MS", Font.BOLD, 22));

		JLabel lblNomeServico = new JLabel("Tipo");
		lblNomeServico.setForeground(new Color(255, 255, 255));
		lblNomeServico.setBounds(133, 155, 112, 21);
		getContentPane().add(lblNomeServico);
		lblNomeServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblDuracao = new JLabel("Duração");
		lblDuracao.setForeground(new Color(255, 255, 255));
		lblDuracao.setBounds(133, 252, 112, 21);
		getContentPane().add(lblDuracao);
		lblDuracao.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblPrecoServico = new JLabel("Preço");
		lblPrecoServico.setForeground(new Color(255, 255, 255));
		lblPrecoServico.setBounds(133, 219, 58, 21);
		getContentPane().add(lblPrecoServico);
		lblPrecoServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblDescricaoServico = new JLabel("Descrição");
		lblDescricaoServico.setForeground(new Color(255, 255, 255));
		lblDescricaoServico.setBounds(133, 187, 112, 21);
		getContentPane().add(lblDescricaoServico);
		lblDescricaoServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblStatusServico = new JLabel("Status");
		lblStatusServico.setForeground(new Color(255, 255, 255));
		lblStatusServico.setBounds(133, 283, 49, 21);
		getContentPane().add(lblStatusServico);
		lblStatusServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
	
		
		JButton btnCadastrarServico = new JButton("Cadastrar");
		btnCadastrarServico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarServico.setIcon(null);
		btnCadastrarServico.setBounds(287, 345, 104, 40);
		getContentPane().add(btnCadastrarServico);
		
		btnCadastrarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					servicoController.cadastrarServico(txtTipoServico.getText(), txtDescricaoServico.getText(),Double.parseDouble(txtPrecoServico.getText()), Double.parseDouble(txtDuracaoServico.getText()), cbStatusServico.getSelectedItem().toString());
				} catch (NumberFormatException | ExceptionDao e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		if (servicoSelecionado != null) {
			
			btnCadastrarServico.setVisible(false);
			
			JButton btnDeletarServico = new JButton("Deletar");
			btnDeletarServico.setBounds(107, 356, 117, 50);
			getContentPane().add(btnDeletarServico);
			btnDeletarServico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ServicoDao servico = new ServicoDao();
					try {
						servico.deletarServico(servicoSelecionado.getCodServico());
					} catch (ExceptionDao e1) {
						e1.printStackTrace();
					}		
					
				}
			});
			

			JButton btnConsultarServico = new JButton("Consultar");
			btnConsultarServico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnConsultarServico.setIcon(null);
			btnConsultarServico.setBounds(421, 356, 117, 50);
			getContentPane().add(btnConsultarServico);
			btnConsultarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaConsultaServico listarServico = new TelaConsultaServico();
					JDesktopPane desktop = getDesktopPane();
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(desktop);
					if (frame instanceof TelaMenuPrincipal) {
						JInternalFrame[] frames = desktop.getAllFrames();
						for (JInternalFrame frame1 : frames) {
							frame1.dispose();
						}
					}
					desktop.add(listarServico);
					listarServico.setVisible(true);
				}
		});
			
			JButton btnAlterarServico = new JButton("Alterar");
			btnAlterarServico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAlterarServico.setIcon(null);
			btnAlterarServico.setBounds(266, 356, 117, 50);
			getContentPane().add(btnAlterarServico);
			btnAlterarServico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						servicoController.alterarServico(servicoSelecionado.getCodServico(),txtTipoServico.getText(), txtDescricaoServico.getText(),
								Double.parseDouble(txtPrecoServico.getText()),Double.parseDouble(txtDuracaoServico.getText()),cbStatusServico.getSelectedItem().toString());
					} catch (NumberFormatException | ParseException | ExceptionDao e1) {

						e1.printStackTrace();
					}
				}
			});		
		}	
		
		
		setIconifiable(true);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBorder(null);
		setPreferredSize(new Dimension(640, 480));
		setMaximizable(true);
		setClosable(true);
		setRootPaneCheckingEnabled(false);
		setMinimumSize(new Dimension(450, 480));
		setTitle("Servico");
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBounds(100, 100, 640, 480);
		
		if (servicoSelecionado != null) {
			preencherCampos(servicoSelecionado);
		}
		

		JPictureBox pictureBox = new JPictureBox();
		pictureBox.setIcon(new ImageIcon(TelaServico.class.getResource("/icones/wallpaper_telas.png")));
		pictureBox.setBounds(0, 0, 640, 453);
		getContentPane().add(pictureBox);

	}
	
	
	
	private void preencherCampos(Servico servicoSelecionado) throws ExceptionDao {
		txtTipoServico.setText(servicoSelecionado.getTipoServico());
		txtDescricaoServico.setText(servicoSelecionado.getDescricaoServico());
		txtPrecoServico.setText(servicoSelecionado.getPrecoServico()+"");
		txtDuracaoServico.setText(servicoSelecionado.getDuracaoServico()+"");
		cbStatusServico.setSelectedItem(servicoSelecionado.getStatusServico().toString());
	}

	public JTextField getTxtTipoServico() {
		return txtTipoServico;
	}

	public void setTxtTipoServico(JTextField txtTipoServico) {
		this.txtTipoServico = txtTipoServico;
	}

	public JTextField getTxtDescricaoServico() {
		return txtDescricaoServico;
	}

	public void setTxtDescricaoServico(JTextField txtDescricaoServico) {
		this.txtDescricaoServico = txtDescricaoServico;
	}

	public JTextField getTxtDuracaoServico() {
		return txtDuracaoServico;
	}

	public void setTxtDuracaoServico(JTextField txtDuracaoServico) {
		this.txtDuracaoServico = txtDuracaoServico;
	}

	public JTextField getTxtPrecoServico() {
		return txtPrecoServico;
	}

	public void setTxtPrecoServico(JTextField txtPrecoServico) {
		this.txtPrecoServico = txtPrecoServico;
	}

	public JComboBox getCbStatusServico() {
		return cbStatusServico;
	}

	public void setCbStatusServico(JComboBox cbStatusServico) {
		this.cbStatusServico = cbStatusServico;
	}
	
	
}
