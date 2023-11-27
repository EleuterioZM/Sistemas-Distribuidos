package Teste_2;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private Socket socket;


    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println(" Estabelecendo conexao ");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("conectado..");

                Connection connection = new Connection(socket);
                connection.start();
                System.out.println("Thread inicializada");
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
