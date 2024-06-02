package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.AgendamentoController;
import controller.helper.AgendaHelper;

public class TelaAgendamento extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private PlaceholderTextField txtCodigoAgenda;
    private PlaceholderTextField txtNomeCliente;
    private PlaceholderTextField txtValor;
    private PlaceholderTextField txtDataAgenda;
    private PlaceholderTextField txtHoraAgenda;
    private JTextField txtObs;
    private JButton btnReagendar;
    private JButton btnCancelar;
    private JComboBox<Object> jboxServico;

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
        AgendamentoController agendamentoController = new AgendamentoController(this);
        AgendaHelper agendaHelper = new AgendaHelper(this);
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

        txtCodigoAgenda = new PlaceholderTextField("ID");
        txtCodigoAgenda.setEditable(false);
        txtCodigoAgenda.setHorizontalAlignment(SwingConstants.CENTER);
        txtCodigoAgenda.setForeground(new Color(128, 128, 128));
        txtCodigoAgenda.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtCodigoAgenda.setBounds(74, 113, 134, 20);
        contentPane.add(txtCodigoAgenda);

        txtNomeCliente = new PlaceholderTextField("Cliente");
        txtNomeCliente.setHorizontalAlignment(SwingConstants.CENTER);
        txtNomeCliente.setForeground(new Color(128, 128, 128));
        txtNomeCliente.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtNomeCliente.setBounds(74, 147, 134, 20);
        contentPane.add(txtNomeCliente);

        txtValor = new PlaceholderTextField("Valor (R$)");
        txtValor.setHorizontalAlignment(SwingConstants.CENTER);
        txtValor.setForeground(new Color(128, 128, 128));
        txtValor.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtValor.setBounds(74, 215, 134, 20);
        contentPane.add(txtValor);

        txtDataAgenda = new PlaceholderTextField("Data");
        txtDataAgenda.setHorizontalAlignment(SwingConstants.CENTER);
        txtDataAgenda.setForeground(new Color(128, 128, 128));
        txtDataAgenda.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtDataAgenda.setBounds(74, 246, 134, 20);
        contentPane.add(txtDataAgenda);

        txtHoraAgenda = new PlaceholderTextField("Hora");
        txtHoraAgenda.setHorizontalAlignment(SwingConstants.CENTER);
        txtHoraAgenda.setForeground(new Color(128, 128, 128));
        txtHoraAgenda.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtHoraAgenda.setBounds(74, 280, 134, 20);
        contentPane.add(txtHoraAgenda);

        txtObs = new PlaceholderTextField("Observações:");
        txtObs.setHorizontalAlignment(SwingConstants.LEFT);
        txtObs.setForeground(new Color(128, 128, 128));
        txtObs.setFont(new Font("Arial Black", Font.PLAIN, 12));
        txtObs.setBounds(247, 113, 320, 189);
        contentPane.add(txtObs);

        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setPreferredSize(new Dimension(80, 80));
        btnAgendar.setBackground(UIManager.getColor("Button.background"));
        btnAgendar.setBounds(74, 323, 104, 27);
        contentPane.add(btnAgendar);

        btnReagendar = new JButton("Reagendar");
        btnReagendar.setPreferredSize(new Dimension(80, 80));
        btnReagendar.setBackground(UIManager.getColor("Button.background"));
        btnReagendar.setBounds(203, 323, 104, 27);
        contentPane.add(btnReagendar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(80, 80));
        btnCancelar.setBackground(UIManager.getColor("Button.background"));
        btnCancelar.setBounds(463, 323, 104, 27);
        contentPane.add(btnCancelar);

        JLabel lbl_teste = new JLabel("Serviço");
        lbl_teste.setForeground(new Color(128, 128, 128));
        lbl_teste.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        lbl_teste.setBounds(117, 184, 67, 14);
        contentPane.add(lbl_teste);

        jboxServico = new JComboBox<>();
        jboxServico.setToolTipText("");
        jboxServico.setBounds(74, 182, 134, 22);
        setIconifiable(true);
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setBorder(null);
        setMaximizable(true);
        setClosable(true);
        setRootPaneCheckingEnabled(false);
        contentPane.add(jboxServico);

        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setPreferredSize(new Dimension(80, 80));
        btnPesquisar.setBackground(UIManager.getColor("Button.background"));
        btnPesquisar.setBounds(330, 325, 104, 27);
        contentPane.add(btnPesquisar);

        JPictureBox pictureBox = new JPictureBox();
        pictureBox.setIcon(new ImageIcon(TelaAgendamento.class.getResource("/icones/wallpaper_telas_maior.png")));
        pictureBox.setBounds(0, -15, 655, 438);
        contentPane.add(pictureBox);
    }

    public JComboBox<Object> getJboxServico() {
        return jboxServico;
    }

    public void setJboxServico(JComboBox<Object> jboxServico) {
        this.jboxServico = jboxServico;
    }
}
