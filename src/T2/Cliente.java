package T2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    static Scanner leitor = new Scanner(System.in);
    public static void main(String[] args) {
       try{
           Socket socket = new Socket("localhost" , 1024);
           DataInputStream dis = new DataInputStream(socket.getInputStream());
           DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


           System.out.println("===================== BEM  VINDO =============================");
           for (int i = 0; i < 3; i ++){
               System.out.print("Digite um numero "+(i+1)+":");
               int Numero = leitor.nextInt();
               dos.writeInt(Numero);

           }
           System.out.println("Aguarde por uma resposta...");

           int Maior_Numero = dis.readInt();
           System.out.println("O Maior numero dos que enviou e :"+Maior_Numero);

       } catch (UnknownHostException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
