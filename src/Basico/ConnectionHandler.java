package Basico;

import java.io.*;
import java.net.*;

public class ConnectionHandler extends Thread {
    private Socket socket;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Lógica de comunicação com o cliente aqui
            // Exemplo: DataInputStream dis = new DataInputStream(socket.getInputStream());
            // Exemplo: DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Feche a conexão quando terminar
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

