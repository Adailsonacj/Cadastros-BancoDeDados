package BancoConexões;

import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Conection.Conexao;
import Views.viewUniversidades;

public class Servidor extends JFrame implements Runnable {
	// Atributos do servidor
	private static final long serialVersionUID = 1L;
	Connection conecta;
	PreparedStatement pst;
	ResultSet rs;
	ServerSocket servidor = null;
	ArrayList<ClienteThread> vetorClientes = new ArrayList<ClienteThread>();
	SocketCanais comunicacao = new SocketCanais();

	public void run() {
		while (true) {
			try {
				comunicacao.cliente = servidor.accept();
				ClienteThread clienteThread = new ClienteThread(comunicacao.cliente, this);
				vetorClientes.add(clienteThread);
			} catch (Exception e) {
			}
		}
	}

	// Construtor da classe
	public Servidor() {
		// Cria o objeto servidor com a porta
		// Valida os canais a partir do socket cliente
		System.out.println("Aguardando conexão!");
		try {
			servidor = new ServerSocket(3067);
			comunicacao.cliente = servidor.accept();
			System.out.println("Conectado!");
			new Thread(this).start();
		} catch (Exception e) {
		}

		try {
			conecta = Conexao.conexao();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Métodos de acesso ao banco de dados */

	// Metodo para cadastrar professor na tabela professores
	public void cadastrarProfesores(String nome, String sobrenome) throws SQLException {
		String sql = "INSERT INTO professores (nome, sobrenome) VALUES (?, ?);";
		try {
			pst = conecta.prepareStatement(sql);
			if (nome != "" || sobrenome != "") {
				pst.setString(1, nome);
				pst.setString(2, sobrenome);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Cadastrado",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// Metodo para excluir um professor pela id na tabela professores
	public void excluirProfessor(int idProfessor) {
		String sql = "DELETE FROM professores WHERE idProfessor = ?";

		try {
			pst = conecta.prepareStatement(sql);
			pst.setInt(1, idProfessor);
			pst.execute();
			JOptionPane.showMessageDialog(null, "Excluído com sucesso", "Excluído", JOptionPane.WARNING_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void populaJComboBox(JComboBox<String> cmbUniversidades) {
		String sql = "SELECT sigla FROM universidades";

		try {
			pst = conecta.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				cmbUniversidades.addItem(rs.getString("sigla"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet pesquisarNome(JTextField txtPesquisar) {
		String sql = "SELECT * FROM professores WHERE nome LIKE ?";

		try {
			pst = conecta.prepareStatement(sql);
			pst.setString(1, txtPesquisar.getText() + "%");
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

		// Lista todos os professores cadastrados
	public ResultSet listarTodos() {

		String sql = "SELECT * FROM view_PU ORDER BY idProfessor";
		try {
			pst = conecta.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

		// Metodo para atribuir um professor a uma universidade
	public void admitirProfessorUniversidade(int idProfessor, String sigla) {
		String sql = "INSERT INTO professoresUniversidades(idProfessor, sigla) VALUES (?, ?);";

		try {
			pst = conecta.prepareStatement(sql);
			pst.setInt(1, idProfessor);
			pst.setString(2, sigla);

			pst.execute();
			JOptionPane.showMessageDialog(null, "Admissão aceita", "Admissão", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Metodo para remover um professor de uma universidade
	public void demitirProfessorUniversidade(int idProfessor, String sigla) {
		String sql = "DELETE FROM professoresUniversidades WHERE idProfessor = ? AND sigla = ?";

		try {
			pst = conecta.prepareStatement(sql);
			pst.setInt(1, idProfessor);
			pst.setString(2, sigla);

			pst.execute();
			JOptionPane.showMessageDialog(null, "Demissão aceita", "Demissão", JOptionPane.WARNING_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Metodo para excluir um professor pela id na tabela
	// professoresUniversidades
	public void excluirProfessorUniversidade(int idProfessor) {
		String sql = "DELETE FROM professoresUniversidades WHERE idProfessor = ?";

		try {
			pst = conecta.prepareStatement(sql);
			pst.setInt(1, idProfessor);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Metodo para cadastrar universidades
	public void cadastrarUniversidades(String sigla, String nome, String estado) throws SQLException {
		String sql = "INSERT INTO universidades (sigla, nome, estado) VALUES (?, ?, ?);";
		try {
			pst = conecta.prepareStatement(sql);
			if (sigla != "" && nome != "" && estado != "") {
				pst.setString(1, sigla);
				pst.setString(2, nome);
				pst.setString(3, estado);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Cadastrado",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// Metodo para excluir universidade
	public void excluirUniversidade(String sigla) {
		String sql = "DELETE FROM universidades WHERE sigla = ?;";

		try {
			pst = conecta.prepareStatement(sql);
			pst.setString(1, sigla);

			pst.execute();
			JOptionPane.showMessageDialog(null, "Excluído com sucesso", "Excluído", JOptionPane.WARNING_MESSAGE);
		} catch (SQLException ex) {
			Logger.getLogger(viewUniversidades.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// Método usado para preencher tabela da tela universidades
	public ResultSet listarTodosUniversidades() {
		String sql = "SELECT * FROM universidades;";

		try {
			pst = conecta.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger(viewUniversidades.class.getName()).log(Level.SEVERE, null, ex);
		}
		return rs;
	}

	// Método usado na pesquisa na tela Universidades
	public ResultSet pesquisarUniversidades(JTextField txtPesquisar) {
		String sql = "SELECT * FROM universidades WHERE nome LIKE ?";

		try {
			pst = conecta.prepareStatement(sql);
			pst.setString(1, txtPesquisar.getText() + "%");

			rs = pst.executeQuery();

		} catch (SQLException ex) {
			Logger.getLogger(viewUniversidades.class.getName()).log(Level.SEVERE, null, ex);
		}
		return rs;
	}

	// Método que inicia o servidor
	public static void main(String... args) {
		new Servidor();
	}

}
