package T2.Ex2;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Cliente {
    public static void main(String[] args) {
        Socket s = null;
        ObjectOutputStream os = null;
        ObjectInputStream is = null;
        int op;
        String nome = null;
        boolean sucesso = false;
        int nCliente = 0;
        boolean sair = false;

        try {
            s = new Socket("127.0.0.1", 1234);
            os = new ObjectOutputStream(s.getOutputStream());
            is = new ObjectInputStream(s.getInputStream());

            while (!sair) {
                System.out.println("1 - Inserir nome\n2 - N° de Cliente\n0 - Sair");
                op = umInt(); // Ler um int do teclado
                os.writeInt(op);
                os.flush();

                switch (op) {
                    case 1:
                        System.out.println("Nome: ");
                        nome = umaString(); // Ler uma string do teclado
                        os.writeObject(nome);
                        os.flush();
                        sucesso = is.readBoolean();
                        System.out.println("Inserção: " + sucesso);
                        break;
                    case 2:
                        nCliente = is.readInt();
                        System.out.println("Cliente n°: " + nCliente);
                        break;
                    case 3:
                        List<String> lista = (List<String>) is.readObject();
                        System.out.println("Lista de Nomes:");

                            System.out.println(lista);

                        break;
                    case 0:
                        sair = true;
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int umInt() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            return Integer.parseInt(input);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0; // Valor padrão em caso de erro
        }
    }

    private static String umaString() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Valor padrão em caso de erro
        }
    }
}
