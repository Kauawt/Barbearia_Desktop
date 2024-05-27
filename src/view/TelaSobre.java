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
		setBounds(100, 100, 537, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSistema = new JLabel("Sistema de Gerenciamento de Barbearia");
		lblSistema.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblSistema.setBounds(34, 285, 477, 43);
		contentPane.add(lblSistema);
		
		JLabel lblNewLabel = new JLabel("Desenvolvido por InovaTech");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(141, 326, 262, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaSobre.class.getResource("/icones/inovaTechIcon.png")));
		lblLogo.setBounds(22, 11, 418, 352);
		contentPane.add(lblLogo);
	}

}
