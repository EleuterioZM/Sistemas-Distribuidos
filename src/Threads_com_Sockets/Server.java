
package Threads_com_Sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int portNumber = 12345;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Aguardando conexões do cliente...");

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Conexão estabelecida com: " + clienteSocket.getInetAddress());

                ClienteHandler clienteHandler = new ClienteHandler(clienteSocket);
                Thread clienteThread = new Thread(clienteHandler);
                clienteThread.start();
            }
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        } finally {
            System.out.println("Número total de conexões: " + ClienteHandler.getTotalConexoes());

        }
    }
}
