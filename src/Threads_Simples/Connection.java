package Threads_Simples;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
    private Socket socket;

    public Connection(Socket s) {
        super();
        socket = s;
        start();

    }

    public void run() {

        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                System.out.println("Mensagem :" + dis.readUTF());



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
