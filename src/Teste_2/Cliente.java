package Teste_2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner teclado = new Scanner(System.in);
        Socket s = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        int op;
        int idade = 0;
        boolean sucesso = false, sair = false;

        try {
            s = new Socket("localhost", 1234);
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());
            while (!sair) {
                System.out.println("1-Inserir Idade \n" + "2.Relátorios\n" + "3.Nr Cliente \n" + "4.Reset \n" + "0. sair");
                op = teclado.nextInt();
                dos.writeInt(op);
                dos.flush();
                switch (op) {
                    case 0:
                        sair = true;
                        System.exit(0);
                        break;
                    case 1:
                        System.out.println("Idade: ");
                        idade = teclado.nextInt();
                        dos.writeInt(idade);
                        dos.flush();
                        sucesso = dis.readBoolean();
                        System.out.println(dis.readUTF()+"  "+ sucesso);
                        break;
                    case 2:
                        int Media = dis.readInt();
                        int menor = dis.readInt();
                        int maior = dis.readInt();

                        System.out.println("Maior: " + maior);
                        System.out.println("Menor: " + menor);
                        System.out.println("Média: " + Media);
                        break;


                    case 3:
                       int ncliente = dis.readInt();
                        System.out.println("Cliente nr: " + ncliente);
                        break;
                    case 4:
                        System.out.println("Reset: " + dis.readBoolean());
                        break;
                    default:
                        System.out.println("Opcao invalida");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
