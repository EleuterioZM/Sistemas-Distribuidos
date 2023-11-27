package Threads_Simples;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class Cliente {
    private Socket socket;

    public Cliente() throws IOException, InterruptedException {
        socket = new Socket("localhost", 1024);
        System.out.println("Cliente 1 Tentando se conectar!");
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        while (true) {
            dos.writeUTF("Mensagem do Cliente 1");
            Thread.sleep(1000); // Adicionando um pequeno atraso entre as mensagens
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Cliente cliente = new Cliente();
    }
}
