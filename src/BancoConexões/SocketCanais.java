package BancoConexões;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class SocketCanais {

	Socket cliente = null;
	DataInputStream canalEntrada = null;
	DataOutputStream canalSaida = null;

	public void socketCanais() {
		try {
			cliente = new Socket("localhost", 3067);
			canalEntrada = new DataInputStream(cliente.getInputStream());// ??
			canalSaida = new DataOutputStream(cliente.getOutputStream());
			new Thread().start();
		} catch (Exception e) {
		}
	}
	public DataOutputStream canalSaida(){
		return canalSaida;
	}
	public DataInputStream canalEntrada(){
		return canalEntrada;
	}
}
