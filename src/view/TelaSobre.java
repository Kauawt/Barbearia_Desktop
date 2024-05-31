package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class TelaSobre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobre frame = new TelaSobre();
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
	public TelaSobre() {
		setTitle("Sobre Nós");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 655, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSistema = new JLabel("Sistema de Gerenciamento de Barbearia");
		lblSistema.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblSistema.setBounds(90, 251, 477, 43);
		contentPane.add(lblSistema);
		
		JLabel lblNewLabel = new JLabel("Desenvolvido por InovaTech");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(205, 284, 262, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaSobre.class.getResource("/icones/inovaTechIcon.png")));
		lblLogo.setBounds(62, 0, 408, 323);
		contentPane.add(lblLogo);
		
		JLabel lbl_filipe = new JLabel("Filipe Batista");
		lbl_filipe.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lbl_filipe.setBounds(0, 343, 122, 20);
		contentPane.add(lbl_filipe);

		JLabel lbl_gabriel = new JLabel("Gabriel Marins");
		lbl_gabriel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lbl_gabriel.setBounds(0, 360, 122, 20);
		contentPane.add(lbl_gabriel);

		JLabel lbl_anthony = new JLabel("Anthony Oliveira");
		lbl_anthony.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lbl_anthony.setBounds(0, 329, 122, 20);
		contentPane.add(lbl_anthony);

		JLabel lbl_vinicius = new JLabel("Vinicius Rabaneda");
		lbl_vinicius.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lbl_vinicius.setBounds(0, 391, 122, 20);
		contentPane.add(lbl_vinicius);

		JLabel lbl_kaua = new JLabel("Kauã Watanabe");
		lbl_kaua.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lbl_kaua.setBounds(0, 374, 122, 20);
		contentPane.add(lbl_kaua);

		JPictureBox pictureBox_1 = new JPictureBox();
		pictureBox_1.setIcon(new ImageIcon(TelaSobre.class.getResource("/icones/fundo_cinza.png")));
		pictureBox_1.setBounds(0, 0, 639, 411);
		contentPane.add(pictureBox_1);
	}

}
