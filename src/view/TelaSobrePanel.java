package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
/**
 * Painel de interface para exibição de informações sobre o sistema.
 * Esta classe extende JPanel para criar um painel que apresenta detalhes sobre o sistema.
 */
public class TelaSobrePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobrePanel frame = new TelaSobrePanel();
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
	public TelaSobrePanel() {
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
		panel_1.setLayout(new MigLayout("insets 0", "[grow,fill][grow,fill][grow,fill]", "[grow,fill][::300,grow,fill][fill][grow][grow]"));
		;

		JLabel lblLogo = new JLabel("");
		lblLogo.setMaximumSize(new Dimension(500, 400));
		lblLogo.setIcon(new ImageIcon(TelaSobrePanel.class.getResource("/icones/inovaTechIcon.png")));
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setBounds(62, 0, 408, 323);
		panel_1.add(lblLogo, "cell 1 1");

		JLabel lblSistema = new JLabel("Sistema de Gerenciamento de Barbearia");
		lblSistema.setMaximumSize(new Dimension(400, 200));
		lblSistema.setBounds(90, 251, 200, 43);
		panel_1.add(lblSistema, "flowy,cell 1 3,alignx left");
		lblSistema.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

		JLabel lblNewLabel = new JLabel("Desenvolvido por InovaTech");
		lblNewLabel.setMaximumSize(new Dimension(400, 200));
		lblNewLabel.setBounds(205, 284, 262, 26);
		panel_1.add(lblNewLabel, "cell 1 3,alignx left");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

		JLabel lbl_filipe = new JLabel("Filipe Batista");
		panel_1.add(lbl_filipe, "flowy,cell 0 4,alignx center,aligny center");
		lbl_filipe.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lbl_filipe.setBounds(0, 343, 122, 20);

		JLabel lbl_anthony = new JLabel("Anthony Oliveira");
		panel_1.add(lbl_anthony, "cell 0 4,alignx center,aligny center");
		lbl_anthony.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lbl_anthony.setBounds(0, 329, 122, 20);

		JLabel lbl_gabriel = new JLabel("Gabriel Marins");
		panel_1.add(lbl_gabriel, "cell 0 4,alignx center,aligny center");
		lbl_gabriel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lbl_gabriel.setBounds(0, 360, 122, 20);

		JLabel lbl_kaua = new JLabel("Kauã Watanabe");
		panel_1.add(lbl_kaua, "cell 0 4,alignx center,aligny center");
		lbl_kaua.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lbl_kaua.setBounds(0, 374, 122, 20);

		JLabel lbl_vinicius = new JLabel("Vinicius Rabaneda");
		panel_1.add(lbl_vinicius, "cell 0 4,alignx center,aligny center");
		lbl_vinicius.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lbl_vinicius.setBounds(0, 391, 122, 20);

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
		pictureBox_1.setIcon(new ImageIcon(TelaUsuarioPanel.class.getResource("/icones/telasobre.jpg")));
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

}
