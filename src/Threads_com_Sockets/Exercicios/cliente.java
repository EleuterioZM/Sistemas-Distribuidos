package Threads_com_Sockets.Exercicios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class cliente {
    static Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) {
        int numero= 0;
        try (Socket socket = new Socket("localhost", 12345)) {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());



            for (int i = 0; i < 3; i++) {
                System.out.print("Digite um numero  "+(i + 1)+":"  );
                 numero = leitor.nextInt();
                dos.writeInt(numero);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
