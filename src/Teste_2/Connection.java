package Teste_2;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Connection extends Thread {
    private Socket socket;
    private ArrayList<Integer> list; // Declare a lista aqui
    private int clientes = 0;

    public Connection(Socket s) throws IOException {
        this.socket = s;
        this.list = new ArrayList<>(); // Inicialize a lista
    }

    @Override
    public void run() {
        super.run();
        clientes++;
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            boolean sucesso = false;
            int Soma = 0; // Inicialize Soma com zero
            int maior = Integer.MIN_VALUE; // Inicialize com o menor valor possível
            int menor = Integer.MAX_VALUE; // Inicialize com o maior valor possível
            while (true) {
                int op = dis.readInt();
                switch (op) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        int Idade = dis.readInt();
                        if (Idade < 0 || Idade > 120) {
                            sucesso = false;
                            dos.writeBoolean(sucesso);
                            dos.writeUTF("Idade está fora dos parâmetros exigidos, não foi adicionada à lista");
                        } else {
                            sucesso = true;
                            list.add(Idade);
                            dos.writeBoolean(sucesso);
                            dos.writeUTF("Idade foi inserida com sucesso!");
                        }
                        break;
                    case 2:
                        for (int i = 0; i < list.size(); i++) {
                            int numeroAtual = list.get(i);
                            Soma += numeroAtual;
                            if (numeroAtual > maior) {
                                maior = numeroAtual;
                            }
                            if (numeroAtual < menor) {
                                menor = numeroAtual;
                            }
                        }
                        int Media = Soma / list.size();
                        dos.writeInt(Media);
                        dos.writeInt(menor);
                        dos.writeInt(maior);
                        dos.writeBoolean(sucesso);
                        break;
                    case 3:
                        dos.writeInt(clientes);
                        break;
                    case 4:

                        list.clear();
                        dos.writeBoolean(sucesso);
                        break;
                    // Adicione outros casos conforme necessário
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
