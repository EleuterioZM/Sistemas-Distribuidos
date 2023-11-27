package Threads_com_Sockets;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteOperacao2 {
    public static void main(String[] args) throws IOException {
        String hostName = "127.0.0.1";
        int portNumber = 12345;

        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                String[] valores = userInput.split(",");
                if (valores.length != 2) {
                    System.out.println("Formato incorreto. Digite dois valores separados por vírgula.");
                    continue;
                }

                out.println(userInput);
                String serverResponse = in.readLine();
                System.out.println("Resultado da soma: " + serverResponse);

                System.out.print("Deseja fazer outra operação? (s/n): ");
                String choice = stdIn.readLine();
                if (!choice.equalsIgnoreCase("s")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro no cliente: " + e.getMessage());
        }
    }
}
