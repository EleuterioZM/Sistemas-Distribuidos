package Exercicios_da_Ficha;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Apostas {
    static Scanner ler = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1024);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        try {

            System.out.println("============================BEM VINDO===============================");

            System.out.print("Faca uma aposta de 0-99 :");
            int Aposta = ler.nextInt();


        } catch (Exception e) {
            e.getMessage();
        }
    }
}
