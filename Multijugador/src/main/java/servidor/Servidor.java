package servidor;

import java.net.ServerSocket;

public class Servidor {
	// private ArrayList<ClientModel> clientes;

	public Servidor(int puerto) {
		/// clientes = new ArrayList<ClientModel>();
		ServerSocket server = null;
		try {
			server = new ServerSocket(puerto);
			System.out.println("Servidor en linea en puerto: " + puerto);
			while (true) {
//				ClientModel cliente = new ClientModel(server.accept());
//				clientes.add(cliente);
//				new HiloCliente(cliente, clientes, chat).start();
			}
		} catch (Exception e) {
			System.out.println("Ocurrio un problema con el server: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
//			new Servidor(Constantes.puerto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
