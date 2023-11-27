package Exercicios_da_Ficha;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Connection extends Thread {
    private Socket socket;
    private int premio = 0;
    public Connection(Socket s) {
        this.socket = s;
    }

    public void run() {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            premio++;
            int Aposta = dis.readInt();
            int aleatorio = (int) (Math.random() * 100);
            if (Aposta == aleatorio) {

            dos.writeBoolean(true);
                System.out.println("ganhou");
            }


        } catch (Exception e) {
            e.getMessage();
        }
    }
}
