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
import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.UIManager;
/**
 * Painel de interface para gerenciamento de serviços.
 */
public class TelaServicoPanel extends JPanel {

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
					TelaServicoPanel frame = new TelaServicoPanel(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ExceptionDao
	 */
	public TelaServicoPanel(Servico servicoSelecionado) throws ExceptionDao {

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
		panel_1.setLayout(new MigLayout("insets 0", "[200,grow][::300,grow][180,grow]",
				"[grow,fill][grow,fill][::250,grow,fill][grow][grow,fill]"));

		JLabel lblFormularioServico = new JLabel("Formulário Serviço");
		lblFormularioServico.setForeground(new Color(255, 255, 255));
		lblFormularioServico.setBounds(220, 57, 280, 32);
		panel_1.add(lblFormularioServico, "cell 1 1,alignx center,aligny center");
		lblFormularioServico.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

		JLabel lblNomeServico = new JLabel("Tipo");
		lblNomeServico.setForeground(new Color(255, 255, 255));
		lblNomeServico.setBounds(197, 108, 60, 21);
		panel_1.add(lblNomeServico, "flowy,cell 0 2,alignx right,aligny center,gapbottom 10");
		lblNomeServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblDuracao = new JLabel("Duração(Min)");
		lblDuracao.setForeground(new Color(255, 255, 255));
		lblDuracao.setBounds(197, 108, 60, 21);
		panel_1.add(lblDuracao, "flowy,cell 0 2,alignx right,aligny center,gapbottom 10");
		lblDuracao.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblPrecoServico = new JLabel("Preço(R$)");
		lblPrecoServico.setForeground(new Color(255, 255, 255));
		lblPrecoServico.setBounds(197, 108, 60, 21);
		panel_1.add(lblPrecoServico, "flowy,cell 0 2,alignx right,aligny center,gapbottom 10");
		lblPrecoServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblDescricaoServico = new JLabel("Descrição");
		lblDescricaoServico.setForeground(new Color(255, 255, 255));
		lblDescricaoServico.setBounds(197, 108, 60, 21);
		panel_1.add(lblDescricaoServico, "flowy,cell 0 2,alignx right,aligny center,gapbottom 10");
		lblDescricaoServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		JLabel lblStatusServico = new JLabel("Status");
		lblStatusServico.setForeground(new Color(255, 255, 255));
		lblStatusServico.setBounds(197, 108, 60, 21);
		panel_1.add(lblStatusServico, "flowy,cell 0 2,alignx right,aligny center,gapbottom 10");
		lblStatusServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		txtTipoServico = new JTextField();
		txtTipoServico.setBorder(null);
		txtTipoServico.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtTipoServico.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipoServico.setForeground(new Color(128, 128, 128));
		txtTipoServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtTipoServico.setBounds(261, 110, 176, 20);
		panel_1.add(txtTipoServico, "gap bottom 10, flowy,cell 1 2,growx");
		txtTipoServico.setColumns(10);

		txtDuracaoServico = new JTextField();
		txtDuracaoServico.setMaximumSize(new Dimension(60, 2147483647));
		txtDuracaoServico.setBorder(null);
		txtDuracaoServico.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtDuracaoServico.setHorizontalAlignment(SwingConstants.CENTER);
		txtDuracaoServico.setForeground(new Color(128, 128, 128));
		txtDuracaoServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtDuracaoServico.setBounds(261, 110, 176, 20);
		panel_1.add(txtDuracaoServico, "flowy,cell 1 2,alignx left,gapbottom 10");
		txtDuracaoServico.setColumns(10);

		txtPrecoServico = new JTextField();
		txtPrecoServico.setMaximumSize(new Dimension(60, 2147483647));
		txtPrecoServico.setBorder(null);
		txtPrecoServico.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtPrecoServico.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecoServico.setForeground(new Color(128, 128, 128));
		txtPrecoServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtPrecoServico.setBounds(261, 110, 176, 20);
		panel_1.add(txtPrecoServico, "flowy,cell 1 2,alignx left,gapbottom 10");
		txtPrecoServico.setColumns(10);

		txtDescricaoServico = new JTextField();
		txtDescricaoServico.setBorder(null);
		txtDescricaoServico.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtDescricaoServico.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescricaoServico.setForeground(new Color(128, 128, 128));
		txtDescricaoServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtDescricaoServico.setBounds(261, 110, 176, 20);
		panel_1.add(txtDescricaoServico, "gap bottom 10, flowy,cell 1 2,growx");
		txtDescricaoServico.setColumns(10);

		cbStatusServico = new JComboBox();
		cbStatusServico.setModel(new DefaultComboBoxModel(new String[] { "Ativo", "Inativo" }));
		cbStatusServico.setForeground(new Color(128, 128, 128));
		cbStatusServico.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cbStatusServico.setBounds(261, 305, 176, 22);
		panel_1.add(cbStatusServico, "gap bottom 10, cell 1 2,growx");

		JButton btnDeletarServico = new JButton("Deletar");
		btnDeletarServico.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnDeletarServico.setPreferredSize(new Dimension(100, 40));
		btnDeletarServico.setBackground(new Color(240, 240, 240));
		btnDeletarServico.setBounds(148, 367, 104, 41);
		panel_1.add(btnDeletarServico, "cell 1 3,growx");
		if (servicoSelecionado != null) {
			btnDeletarServico.setVisible(true);
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

		} else {
			btnDeletarServico.setVisible(false);
		}

		JButton btnCadastrarServico = new JButton(servicoSelecionado == null ? "Cadastrar" : "Alterar");
		btnCadastrarServico.setBounds(405, 368, 124, 41);
		panel_1.add(btnCadastrarServico, "cell 1 3,alignx right");
		btnCadastrarServico.setPreferredSize(new Dimension(100, 40));
		btnCadastrarServico.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnCadastrarServico.setBackground(new Color(240, 240, 240));
		btnCadastrarServico.setIcon(null);
		btnCadastrarServico.setVisible(true);
		btnCadastrarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (servicoSelecionado == null) {
					try {
						servicoController.cadastrarServico(txtTipoServico.getText(), txtDescricaoServico.getText(),
								Double.parseDouble(txtPrecoServico.getText()),
								Double.parseDouble(txtDuracaoServico.getText()),
								cbStatusServico.getSelectedItem().toString());
					} catch (NumberFormatException | ExceptionDao e1) {
						JOptionPane.showMessageDialog(null, e1 + "Não foi possível converter os dados captados");
						e1.printStackTrace();
					}
				} else {
					try {
						servicoController.alterarServico(servicoSelecionado.getCodServico(), txtTipoServico.getText(),
								txtDescricaoServico.getText(), Double.parseDouble(txtPrecoServico.getText()),
								Double.parseDouble(txtDuracaoServico.getText()),
								cbStatusServico.getSelectedItem().toString());
					} catch (NumberFormatException | ParseException | ExceptionDao e1) {
						JOptionPane.showMessageDialog(null, e1 + "Não foi possível converter os dados captados");
						e1.printStackTrace();
					}
				}
			}
		});

		JButton btnConsultarServico = new JButton("Consultar");
		btnConsultarServico.setBounds(405, 368, 124, 41);
		panel_1.add(btnConsultarServico, "cell 1 3,alignx right");
		btnConsultarServico.setPreferredSize(new Dimension(100, 40));
		btnConsultarServico.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnConsultarServico.setBackground(new Color(240, 240, 240));
		btnConsultarServico.setIcon(null);
		btnConsultarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMenuPrincipal mainFrame = (TelaMenuPrincipal) SwingUtilities
						.getWindowAncestor(TelaServicoPanel.this);
				JPanel desktop = mainFrame.getDesktop();
				desktop.removeAll();
				TelaConsultaServico consulta = new TelaConsultaServico();
				consulta.setVisible(true);
				desktop.add(consulta);
				desktop.revalidate();
				desktop.repaint();
			}
		});

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

		if (servicoSelecionado != null) {
			preencherCampos(servicoSelecionado);
		}
	}
	/**
     * Preenche os campos da interface com as informações do serviço selecionado.
     * @param servicoSelecionado O serviço cujas informações serão exibidas nos campos da interface.
     * @throws ExceptionDao Se ocorrer um erro ao acessar os dados do serviço no banco de dados.
     */
	private void preencherCampos(Servico servicoSelecionado) throws ExceptionDao {
		txtTipoServico.setText(servicoSelecionado.getTipoServico());
		txtDescricaoServico.setText(servicoSelecionado.getDescricaoServico());
		txtPrecoServico.setText(servicoSelecionado.getPrecoServico() + "");
		txtDuracaoServico.setText(servicoSelecionado.getDuracaoServico() + "");
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
