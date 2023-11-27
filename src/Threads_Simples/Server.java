package Threads_Simples;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private Connection connection;
    private static int Contador;

    public Server() {
        try {
            serverSocket = new ServerSocket(1024);
            System.out.println("Servidor a espera de conexoes...");
            Contador = 0;

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Conexao estabelecida com sucesso!");
                Contador++;
                System.out.println("conexoes = " + Contador);
                connection = new Connection(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    public static void main(String[] args) {
        Server server = new Server();

    }
}
