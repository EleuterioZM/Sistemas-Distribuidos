package T2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    static Scanner leitor = new Scanner(System.in);
    static ArrayList<Integer> list = new ArrayList<>();


    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(1024)) {
            System.out.println("Esperando por uma conexao..");
            Socket socket = serverSocket.accept();
            System.out.println("Conexao estabelecida com sucesso!");


            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


            for (int i = 0; i < 3; i++) {

                int Numero = dis.readInt();
                list.add(Numero);
            }
            System.out.println("Números recebidos :"+list);

            int Maior_numero = list.get(0);
            for (int j = 0; j < list.size(); j++) {
                for (int numero : list) {
                    if (numero > Maior_numero) {
                        Maior_numero = numero;
                    }
                }
            }
            System.out.println(Maior_numero);
            dos.writeInt(Maior_numero);
        }
    }
}
