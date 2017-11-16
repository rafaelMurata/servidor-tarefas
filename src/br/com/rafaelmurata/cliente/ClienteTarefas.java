
package br.com.rafaelmurata.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTarefas {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		final Socket socket = new Socket("localhost", 12345);
		System.out.println("conexao estabelecida");
		
		Thread threadEnviaComando = new Thread(new Runnable() {
			
			@Override
			public void run() {
				PrintStream saida;
				try {
					saida = new PrintStream(socket.getOutputStream());
					saida.print("c1");
					
					Scanner teclado =new Scanner(System.in);
					while(teclado.hasNext()){
						String linha =teclado.nextLine();
						if(linha.trim().equals("")){
							break;
						}
						saida.println(linha);
					}
					teclado.close();
					saida.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Scanner respostaServidor = new Scanner( socket.getInputStream());
		while (respostaServidor.hasNext()) {
			String linha =respostaServidor.nextLine();
			System.out.println(linha);
		}
		respostaServidor.close();
		
		
		socket.close();
	}
	

}
