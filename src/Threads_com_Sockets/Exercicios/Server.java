package Threads_com_Sockets.Exercicios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        int Numero = 0;
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Aguardando conexões do cliente...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Conexão estabelecida com: " + socket.getInetAddress());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());


                for (int i = 0; i < 3; i++) {
                    Numero = dis.readInt();
                    list.add(Numero);
                }
               // System.out.println(list);


                for (int k = 0; k < list.size(); k++) {
                    for (int j = 0+1; j < list.size()-1; j++) {
                    if (list.get(k) > list.get(j)){
                        list.set(j,list.get(k));

                    }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}