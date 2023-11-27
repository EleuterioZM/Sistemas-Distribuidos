package T2.Ex2;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Servidor {

    public static void main(String[] args) throws IOException {
        Vector<String> listaDeNomes = new Vector<>();
        ServerSocket serverSocket = new ServerSocket(1234);

        System.out.println("Servidor aguardando conexões...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Conexão estabelecida com cliente " + socket.getRemoteSocketAddress());

            ClienteHandler handler = new ClienteHandler(socket, listaDeNomes);
            handler.start();
        }
    }
}

class    ClienteHandler extends Thread {
    private Socket socket;
    private Vector<String> listaDeNomes;

    public ClienteHandler(Socket socket, Vector<String> listaDeNomes) {
        this.socket = socket;
        this.listaDeNomes = listaDeNomes;
    }

    @Override
    public void run() {
        try {  
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

            while (true) {
                int op = is.readInt();
                switch (op) {
                    case 1: // Inserir nome na lista
                        String nome = (String) is.readObject();
                        if (!listaDeNomes.contains(nome)) {
                            listaDeNomes.add(nome);
                            os.writeBoolean(true); // Sucesso
                        } else {
                            os.writeBoolean(false); // Nome já existe
                        }
                        os.flush();
                        break;
                    case 2: // Número de clientes que se conectaram
                        int numeroDeClientes = listaDeNomes.size();
                        os.writeInt(numeroDeClientes);
                        os.flush();
                        break;
                    case 3:
                        os.writeObject(listaDeNomes);
                        os.flush();
                        break;

                    case 0: // Sair
                        socket.close();
                        return;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
