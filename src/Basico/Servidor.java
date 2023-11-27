package Basico;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Abre uma porta na porta 12345
            System.out.println("Servidor aguardando conexões...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Aceita a conexão de um cliente
                System.out.println("Conexão estabelecida com sucesso!");

                // Crie uma nova classe de conexão para lidar com o cliente
                ConnectionHandler connection = new ConnectionHandler(clientSocket);
                connection.start(); // Inicie a thread de conexão
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
