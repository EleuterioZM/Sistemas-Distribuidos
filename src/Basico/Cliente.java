package Basico;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Conecta ao servidor na porta 12345

            // Aqui você pode enviar e receber dados com o servidor
            // Exemplo: DataInputStream dis = new DataInputStream(socket.getInputStream());
            // Exemplo: DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Feche a conexão quando terminar
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
