package Threads_Simples;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente_2 {
    private Socket socket;

    public Cliente_2() throws IOException {
        socket = new Socket("localhost", 1024);
        System.out.println("Cliente 2 Tentando se conectar!");
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF("ola, aqui e o cliente 2");
    }

    public static void main(String[] args) throws IOException {
        Cliente_2  cliente_2 = new Cliente_2();
    }
}
