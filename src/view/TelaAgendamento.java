package view;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaAgendamento extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCodigoAgenda;
    private JTextField txtNomeCliente;
    private JTextField txtServico;
    private JTextField txtValor;
    private JTextField txtDataAgenda;
    private JTextField txtHoraAgenda;
    private JTextField txtObs;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaAgendamento frame = new TelaAgendamento();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaAgendamento() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 655, 450);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblAgenda = new JLabel("Agenda");
        lblAgenda.setForeground(new Color(255, 255, 255));
        lblAgenda.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
        lblAgenda.setBounds(273, 59, 116, 32);
        contentPane.add(lblAgenda);
        
        txtCodigoAgenda = new JTextField();
        txtCodigoAgenda.setText("ID");
        txtCodigoAgenda.setHorizontalAlignment(SwingConstants.CENTER);
        txtCodigoAgenda.setForeground(new Color(128, 128, 128));
        txtCodigoAgenda.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtCodigoAgenda.setColumns(10);
        txtCodigoAgenda.setBounds(74, 113, 134, 20);
        contentPane.add(txtCodigoAgenda);
        
        txtNomeCliente = new JTextField();
        txtNomeCliente.setText("Cliente");
        txtNomeCliente.setHorizontalAlignment(SwingConstants.CENTER);
        txtNomeCliente.setForeground(new Color(128, 128, 128));
        txtNomeCliente.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtNomeCliente.setColumns(10);
        txtNomeCliente.setBounds(74, 147, 134, 20);
        contentPane.add(txtNomeCliente);
        
        txtServico = new JTextField();
        txtServico.setText("Serviço");
        txtServico.setHorizontalAlignment(SwingConstants.CENTER);
        txtServico.setForeground(new Color(128, 128, 128));
        txtServico.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtServico.setColumns(10);
        txtServico.setBounds(74, 184, 134, 20);
        contentPane.add(txtServico);
        
        txtValor = new JTextField();
        txtValor.setText("Valor (R$)");
        txtValor.setHorizontalAlignment(SwingConstants.CENTER);
        txtValor.setForeground(new Color(128, 128, 128));
        txtValor.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtValor.setColumns(10);
        txtValor.setBounds(74, 215, 134, 20);
        contentPane.add(txtValor);
        
        txtDataAgenda = new JTextField();
        txtDataAgenda.setText("Data");
        txtDataAgenda.setHorizontalAlignment(SwingConstants.CENTER);
        txtDataAgenda.setForeground(new Color(128, 128, 128));
        txtDataAgenda.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtDataAgenda.setColumns(10);
        txtDataAgenda.setBounds(74, 246, 134, 20);
        contentPane.add(txtDataAgenda);
        
        txtHoraAgenda = new JTextField();
        txtHoraAgenda.setText("Hora");
        txtHoraAgenda.setHorizontalAlignment(SwingConstants.CENTER);
        txtHoraAgenda.setForeground(new Color(128, 128, 128));
        txtHoraAgenda.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtHoraAgenda.setColumns(10);
        txtHoraAgenda.setBounds(74, 280, 134, 20);
        contentPane.add(txtHoraAgenda);
        
        txtObs = new JTextField();
        txtObs.setToolTipText("");
        txtObs.setHorizontalAlignment(SwingConstants.LEFT);
        txtObs.setText("Observações:");
        txtObs.setForeground(new Color(128, 128, 128));
        txtObs.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtObs.setColumns(10);
        txtObs.setBounds(247, 113, 320, 189);
        contentPane.add(txtObs);
        
        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setPreferredSize(new Dimension(80, 80));
        btnAgendar.setBackground(UIManager.getColor("Button.background"));
        btnAgendar.setBounds(328, 319, 148, 27);
        contentPane.add(btnAgendar);
        
        JPictureBox pictureBox = new JPictureBox();
        pictureBox.setIcon(new ImageIcon(TelaAgendamento.class.getResource("/icones/wallpaper_telas_maior.png")));
        pictureBox.setBounds(0, -15, 649, 437);
        contentPane.add(pictureBox);
    }
}
