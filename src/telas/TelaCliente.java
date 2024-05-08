package telas;

import java.awt.EventQueue;

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

import conexao.ModuloConexao;

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

public class TelaCliente extends JInternalFrame {
	
	public Connection conexao = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigoCliente;
	private JTextField txtNomeCliente;
	private JTextField txtEnderecoCliente;
	private JTextField txtTelefoneCliente;
	private JTextField txtCpfCliente;
	private JComboBox<String> cbStatusCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
		getContentPane().setBackground(new Color(232, 227, 225));
		conexao = ModuloConexao.conector(); // modulo de conexao
		
		setResizable(true);
		getContentPane().setSize(new Dimension(450, 480));
		getContentPane().setPreferredSize(new Dimension(450, 480));
		getContentPane().setLayout(null);
		
		JLabel lblFormularioCliente = new JLabel("Formulário Cliente");
		lblFormularioCliente.setBounds(211, 11, 224, 32);
		getContentPane().add(lblFormularioCliente);
		lblFormularioCliente.setFont(new Font("Arial Black", Font.PLAIN, 22));
		
		JLabel lblCodigoCliente = new JLabel("ID");
		lblCodigoCliente.setBounds(10, 126, 112, 21);
		getContentPane().add(lblCodigoCliente);
		lblCodigoCliente.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setBounds(126, 128, 176, 20);
		getContentPane().add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(336, 126, 112, 21);
		getContentPane().add(lblNome);
		lblNome.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtNomeCliente = new JTextField();
		txtNomeCliente.setBounds(452, 128, 176, 20);
		getContentPane().add(txtNomeCliente);
		txtNomeCliente.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setBounds(10, 185, 112, 21);
		getContentPane().add(lblEndereco);
		lblEndereco.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtEnderecoCliente = new JTextField();
		txtEnderecoCliente.setBounds(126, 187, 176, 20);
		getContentPane().add(txtEnderecoCliente);
		txtEnderecoCliente.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(336, 184, 112, 21);
		getContentPane().add(lblTelefone);
		lblTelefone.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtTelefoneCliente = new JTextField();
		txtTelefoneCliente.setBounds(452, 186, 176, 20);
		getContentPane().add(txtTelefoneCliente);
		txtTelefoneCliente.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 249, 112, 21);
		getContentPane().add(lblCpf);
		lblCpf.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		txtCpfCliente = new JTextField();
		txtCpfCliente.setBounds(126, 251, 176, 20);
		getContentPane().add(txtCpfCliente);
		txtCpfCliente.setColumns(10);
		
		JLabel lblStatusCliente = new JLabel("Status");
		lblStatusCliente.setBounds(336, 249, 49, 21);
		getContentPane().add(lblStatusCliente);
		lblStatusCliente.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		cbStatusCliente = new JComboBox<String>();
		cbStatusCliente.setModel(new DefaultComboBoxModel(new String[] {"Ativo", "Inativo"}));
		cbStatusCliente.setBounds(452, 251, 176, 20);
		getContentPane().add(cbStatusCliente);
		
		JButton btnCadastrarCliente = new JButton("");
		btnCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrarCliente.setBackground(new Color(240, 240, 240));
		btnCadastrarCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/addicon.png")));
		btnCadastrarCliente.setPreferredSize(new Dimension(80, 80));
		btnCadastrarCliente.setBounds(34, 374, 117, 68);
		getContentPane().add(btnCadastrarCliente);
		
		JButton btnAlterarCliente = new JButton("");
		btnAlterarCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/editicon.png")));
		btnAlterarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterarCliente.setPreferredSize(new Dimension(80, 80));
		btnAlterarCliente.setBounds(185, 374, 117, 68);
		getContentPane().add(btnAlterarCliente);
		
		JButton btnConsultarCliente = new JButton("");
		btnConsultarCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/findicon.png")));
		btnConsultarCliente.setBounds(336, 374, 117, 68);
		getContentPane().add(btnConsultarCliente);
		
		JButton btnVoltarMenu = new JButton("");
		btnVoltarMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVoltarMenu.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/deleteicon.png")));
		btnVoltarMenu.setBounds(487, 374, 117, 68);
		getContentPane().add(btnVoltarMenu);
		btnConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// chamando o metodo consultar
				consultarCliente();
			}
		});
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chamando o metodo adicionar
				adicionarCliente();
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
		setTitle("Cliente");
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBounds(100, 100, 640, 480);

	}
	/**
	 * Realiza consulta a partir do banco de Dados cliente.
	 */
	private void consultarCliente() {
		String sql = "select * from tbCliente where codCliente=?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtCodigoCliente.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtNomeCliente.setText(rs.getString(2)); // pega a 2 coluna da tabela que equivale ao nome do cliente
				txtEnderecoCliente.setText(rs.getString(3)); // pega a 3 coluna da tabela que equivale ao Endereco
				txtTelefoneCliente.setText(rs.getString(4));// pega a 4 coluna da tabela que equivale ao Telefone
				txtCpfCliente.setText(rs.getString(5)); // pega a 5 coluna da tabela que equivale ao cpf
				cbStatusCliente.setSelectedItem(rs.getString(6));// status cliente

			} else {
				JOptionPane.showMessageDialog(null, "Usuário não Cadastrado");
				txtNomeCliente.setText(null);
				txtEnderecoCliente.setText(null);
				txtTelefoneCliente.setText(null);
				txtCpfCliente.setText(null);
				cbStatusCliente.setSelectedItem(null);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Adiciona novos clientes ao Banco de Dados
	 */
	private void adicionarCliente() {
		String sql = "insert into tbCliente(codCliente,nomeCliente,EnderecoCliente,telefoneCliente,cpfCliente,statusCliente)values(?,?,?,?,?,?)";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtCodigoCliente.getText()); // campo 1 d tela equivale a codigo cliente
			pst.setString(2, txtNomeCliente.getText());
			pst.setString(3, txtEnderecoCliente.getText());
			pst.setString(4, txtTelefoneCliente.getText());
			pst.setString(5, txtCpfCliente.getText());
			//pst.setString(6, txtStatusCliente.getText());
			pst.setString(6, cbStatusCliente.getSelectedItem().toString());
			
			//atualiza a tabela cliente com os dados do formulário
			pst.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
}
