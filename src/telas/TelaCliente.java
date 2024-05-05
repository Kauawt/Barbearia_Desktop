package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexao.ModuloConexao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaCliente extends JFrame {

	public Connection conexao = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigoCliente;
	private JTextField txtNomeCliente;
	private JTextField txtEnderecoCliente;
	private JTextField txtTelefoneCliente;
	private JTextField txtCpfCliente;
	private JTextField txtStatusCliente;

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TelaCliente() {
		// modulo conexao
		conexao = ModuloConexao.conector(); // modulo de conexao
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 439);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(400, 400));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JLabel lblFormularioCliente = new JLabel("Formulário Cliente");
		lblFormularioCliente.setFont(new Font("Arial Black", Font.PLAIN, 22));

		JLabel lblCodigoCliente = new JLabel("ID");
		lblCodigoCliente.setFont(new Font("Arial Black", Font.PLAIN, 14));

		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial Black", Font.PLAIN, 14));

		txtNomeCliente = new JTextField();
		txtNomeCliente.setColumns(10);

		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setFont(new Font("Arial Black", Font.PLAIN, 14));

		txtEnderecoCliente = new JTextField();
		txtEnderecoCliente.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial Black", Font.PLAIN, 14));

		txtTelefoneCliente = new JTextField();
		txtTelefoneCliente.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Arial Black", Font.PLAIN, 14));

		txtCpfCliente = new JTextField();
		txtCpfCliente.setColumns(10);

		JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//chamando o metodo adicionar
			adicionarCliente();
			}
		});

		JButton btnAlterarCliente = new JButton("Alterar Cliente");
		btnAlterarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnVoltarMenu = new JButton("Voltar");

		JButton btnConsultarCliente = new JButton("Consultar Cliente");
		btnConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// chamando o metodo consultar
				consultarCliente();
			}
		});

		JLabel lblStatusCliente = new JLabel("Status");
		lblStatusCliente.setFont(new Font("Arial Black", Font.PLAIN, 14));

		txtStatusCliente = new JTextField();
		txtStatusCliente.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCadastrarCliente, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAlterarCliente, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnConsultarCliente, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnVoltarMenu, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(151))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(156)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCodigoCliente, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCodigoCliente))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNomeCliente))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblEndereco, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtEnderecoCliente))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtTelefoneCliente))
								.addComponent(lblFormularioCliente, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStatusCliente))
									.addGap(4)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtStatusCliente)
										.addComponent(txtCpfCliente, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFormularioCliente, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCodigoCliente, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(txtCodigoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(txtNomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEndereco, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(txtEnderecoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(txtTelefoneCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(txtCpfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStatusCliente)
						.addComponent(txtStatusCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrarCliente)
						.addComponent(btnAlterarCliente)
						.addComponent(btnConsultarCliente)
						.addComponent(btnVoltarMenu))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
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
				txtStatusCliente.setText(rs.getString(6));// status cliente

			} else {
				JOptionPane.showMessageDialog(null, "Usuário não Cadastrado");
				txtNomeCliente.setText(null);
				txtEnderecoCliente.setText(null);
				txtTelefoneCliente.setText(null);
				txtCpfCliente.setText(null);
				txtStatusCliente.setText(null);
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
			pst.setString(6, txtStatusCliente.getText());
			
			//atualiza a tabela cliente com os dados do formulário
			pst.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
