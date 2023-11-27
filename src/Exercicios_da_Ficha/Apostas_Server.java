package Exercicios_da_Ficha;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Apostas_Server {

    public static void main(String[] args) throws IOException {
        try {

            ServerSocket serverSocket =  new ServerSocket(1024);
            System.out.println("Servidor aguardando conexões...");

            while (true){
                Socket socket = serverSocket.accept();
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                System.out.println("Conectado");


                Connection con = new Connection(socket);
                con.start();
                System.out.println("Conexao tratada e enviada");
            }
        }catch (Exception e){
            System.out.println("problemas na conexao"+e);
        }
    }
}
