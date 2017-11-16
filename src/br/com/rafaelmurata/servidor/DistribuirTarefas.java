package br.com.rafaelmurata.servidor;

import java.net.Socket;
import java.util.Scanner;

public class DistribuirTarefas implements Runnable {

	private Socket socket;

	public DistribuirTarefas(Socket socket) {
		this.socket = socket;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			System.out.println("distribuindo tarefas para "+ socket);
			
			Scanner entradaCliente = new Scanner(socket.getInputStream());
			
			while (entradaCliente.hasNextLine()) {
					String comando = entradaCliente.nextLine();
					System.out.println(comando);
			}
			entradaCliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
