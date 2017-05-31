package BancoConexões;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteThread extends Thread {
	// Atributos da classe
	Socket cliente = null;
	DataInputStream entrada = null;
	DataOutputStream saida = null;
	Servidor copiaServidor = null;

	String nome, sobrenomeProfessor, mensagem, sigla, estado;
	int idProfessor;

	// Construtor da classe
	ClienteThread(Socket cliente, Servidor copia) {
		copiaServidor = copia;
		// Valida a ref para o socket
		// Cria os canais de entrada e saida
		this.cliente = cliente;
		try {
			this.entrada = new DataInputStream(cliente.getInputStream());
			this.saida = new DataOutputStream(cliente.getOutputStream());
			this.start();
		} catch (Exception e) {
		}
	}

	// Define o comportamento da Thread
	public void run() {
		while (true) {
				try {
					mensagem = entrada.readUTF();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					if (mensagem.equals("cadastrarProfessor")) {
						nome = entrada.readUTF();
						sobrenomeProfessor = entrada.readUTF();
						copiaServidor.cadastrarProfesores(nome, sobrenomeProfessor);
					} else if (mensagem.equals("excluirProfessor")) {
						idProfessor = entrada.readInt();
						copiaServidor.excluirProfessorUniversidade(idProfessor);
						copiaServidor.excluirProfessor(idProfessor);
					} else if (mensagem.equals("admitir")) {
						idProfessor = entrada.readInt();
						sigla = entrada.readUTF();
						copiaServidor.admitirProfessorUniversidade(idProfessor, sigla);
					} else if (mensagem.equals("demitir")) {
						idProfessor = entrada.readInt();
						sigla = entrada.readUTF();
						copiaServidor.demitirProfessorUniversidade(idProfessor, sigla);
					} else if (mensagem.equals("cadastrarUniversidades")) {
						sigla = entrada.readUTF();
						nome = entrada.readUTF();
						estado = entrada.readUTF();
						copiaServidor.cadastrarUniversidades(sigla, nome, estado);
					} else if (mensagem.equals("excluirUniversidade")) {
						sigla = entrada.readUTF();
						copiaServidor.excluirUniversidade(sigla);
					}

				} catch (Exception e) {
				}
		}
	}
}
