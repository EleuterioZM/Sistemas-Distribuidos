package Threads_com_Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteHandler implements Runnable {
    private static int totalConexoes = 0;
    private Socket clienteSocket;

    public ClienteHandler(Socket socket) {
        this.clienteSocket = socket;
        totalConexoes++;
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()))
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] valores = inputLine.split(",");
                if (valores.length != 2) {
                    out.println("Formato incorreto. Envie dois valores separados por vírgula.");
                    continue;
                }

                try {
                    double num1 = Double.parseDouble(valores[0]);
                    double num2 = Double.parseDouble(valores[1]);
                    double resultado = num1 + num2;
                    out.println(Double.toString(resultado));
                } catch (NumberFormatException e) {
                    out.println("Valores inválidos. Certifique-se de enviar números.");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro no manipulador do cliente: " + e.getMessage());
        } finally {
            try {
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getTotalConexoes() {
        return totalConexoes;
    }
}
