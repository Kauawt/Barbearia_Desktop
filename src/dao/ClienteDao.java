package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Cliente;
import model.Usuario;
/**
 * Classe responsável por realizar operações de acesso a dados relacionadas a clientes no banco de dados.
 * Implementa métodos para inserção, consulta, atualização e exclusão de dados de clientes.
 */
public class ClienteDao {

	private final String CADASTRAR_CLIENTE = "insert into tbCliente(nomeCliente,enderecoCliente,telefoneCliente,cpfCliente,statusCliente) values (?,?,?,?,?)";
	private static final String LISTAR_CLIENTES = "select * from tbCliente where statusCliente = 'Ativo'";
	private static final String CONSULTAR_CLIENTE_POR_CPF = "select * from tbCliente where cpfCliente = ?";
	private static final String ALTERAR_CLIENTE = "UPDATE tbCliente set nomeCliente = ?, enderecoCliente = ?, telefoneCliente = ?, cpfCliente = ?, statusCliente = ? where cpfCliente = ?";
	private static final String DELETAR_CLIENTE = "UPDATE tbCliente set statusCliente = 'Inativo' where codCliente = ?";

	private Connection conexao = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	/**
	 * Método destinado a realizar o cadastro de um cliente. Recebe um objeto, capta seus
	 * campos e atribui ao banco através da variável query. Após isso, finaliza a conexão
	 * com o banco a fim de liberar a porta para novas consultas.
	 * @param cliente
	 * @throws ExceptionDao
	 */
	
	public void cadastrarCliente(Cliente cliente) throws ExceptionDao {
		try {
			String query = CADASTRAR_CLIENTE;
			conexao = ModuloConexao.conector(); // abre conexao
			pst = conexao.prepareStatement(query); // passa o comando sql como argumento
			pst.setString(1, cliente.getNomeCliente());
			pst.setString(2, cliente.getEnderecoCliente());
			pst.setString(3, cliente.getTelefoneCliente());
			pst.setString(4, cliente.getCpfCliente());
			pst.setString(5, cliente.getStatusCliente());
			pst.executeUpdate(); // atualiza o banco de dados
			JOptionPane.showMessageDialog( null, "Cliente cadastrado com sucesso!");
		} catch (SQLException e) {
			throw new ExceptionDao("Erro ao Cadastrar o Cliente: " + e);
		} finally {
				ModuloConexao.fecharConexao();
		}
	}
	
	/**
	 * Cria um array do tipo Cliente, na qual retorna todos os objetos do banco.
	 * Diferente de uma consulta, aqui não é passado um parâmetro.
	 * @return
	 */
	public static ArrayList<Cliente> listarClientes() {
		
		String query = LISTAR_CLIENTES;
		Connection conexao = ModuloConexao.conector();
		ArrayList<Cliente> clientes = new ArrayList<>();

		try {
			Statement pst = conexao.createStatement();
			conexao.prepareStatement(query);
			rs = pst.executeQuery(query);
			
			while(rs.next()) {
				clientes.add(new Cliente(rs.getInt("codCliente"), 
						rs.getString("nomeCliente"),
						rs.getString("enderecoCliente"), 
						rs.getString("telefoneCliente"), 
						rs.getString("cpfCliente"), 
						rs.getString("statusCliente")));
			};
			
		} catch (SQLException e) {
			e.printStackTrace();
			clientes = null;
		}
		return clientes;
	}
	
	/**
	 * Realiza uma consulta no banco a partir do CPF e retorna todos os campos.
	 * @param cpfCliente
	 * @return
	 * @throws ExceptionDao
	 */
	public static Cliente consultarClientePorCPF(String cpfCliente) throws ExceptionDao {
		String query = CONSULTAR_CLIENTE_POR_CPF;
		Connection conexao = ModuloConexao.conector();
		Cliente cliente = null;

		try {
			pst = conexao.prepareStatement(query);
			
			int i = 1;
			pst.setString(i++, cpfCliente);
			rs = pst.executeQuery();
			while(rs.next()) {
				cliente = new Cliente(rs.getInt("codCliente"), 
							rs.getString("nomeCliente"), 
							rs.getString("enderecoCliente"),
							rs.getString("telefoneCliente"), 
							rs.getString("cpfCliente"), 
							rs.getString("statusCliente"));
			};		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ModuloConexao.fecharConexao();
		}
		return cliente;
	}
	/**
	 * Consulta o nome do cliente no banco de dados a partir do CPF fornecido.
	 * @param cpfCliente CPF do cliente a ser consultado.
	 * @return O nome do cliente correspondente ao CPF, ou null se o cliente não for encontrado.
	 * @throws ExceptionDao Se ocorrer algum erro durante a consulta ao banco de dados.
	 */
	public static String consultarNomeClientePorCPF(String cpfCliente) throws ExceptionDao {
	    String query = CONSULTAR_CLIENTE_POR_CPF;
	    Connection conexao = ModuloConexao.conector();
	    String nomeCliente = null;

	    try {
	        pst = conexao.prepareStatement(query);
	        pst.setString(1, cpfCliente);
	        rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            nomeCliente = rs.getString("nomeCliente");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ModuloConexao.fecharConexao();
	    }

	    return nomeCliente;
	}
	/**
	 * Altera as informações de um cliente no banco de dados.
	 * 
	 * @param cpfCliente O CPF do cliente a ser alterado.
	 * @param cliente O objeto cliente contendo as novas informações.
	 * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
	 */
	public void alterarCliente(String cpfCliente, Cliente cliente) throws ExceptionDao {
		try {
			String query = ALTERAR_CLIENTE;
			conexao = ModuloConexao.conector(); // abre conexao
			pst = conexao.prepareStatement(query); // passa o comando sql como argumento
			pst.setString(1, cliente.getNomeCliente());
			pst.setString(2, cliente.getEnderecoCliente());
			pst.setString(3, cliente.getTelefoneCliente());
			pst.setString(4, cliente.getCpfCliente());
			pst.setString(5, cliente.getStatusCliente());
			pst.setString(6, cpfCliente);
			pst.executeUpdate(); // atualiza o banco de dados		
			JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
		} catch (SQLException e) {
			
			throw new ExceptionDao("Erro ao alterar o Cliente: " + e);
		} finally {
			ModuloConexao.fecharConexao();
		}
	}
	/**
	 * Exclui um cliente do banco de dados.
	 * 
	 * @param codCliente O código do cliente a ser excluído.
	 * @throws ExceptionDao Se ocorrer um erro ao acessar o banco de dados.
	 */
	
	public void deletarCliente(int codCliente) throws ExceptionDao {
		try {
			String query = DELETAR_CLIENTE;
			conexao = ModuloConexao.conector(); // abre conexao
			pst = conexao.prepareStatement(query); // passa o comando sql como argumento
			pst.setInt(1, codCliente);
			pst.executeUpdate(); // atualiza o banco de dados
			
			JOptionPane.showMessageDialog(null, "Cliente Inativo!");
		} catch (SQLException e) {
			throw new ExceptionDao("Erro deixar Cliente Inativo: " + e);
		} finally {
			ModuloConexao.fecharConexao();
		}
	}

}
