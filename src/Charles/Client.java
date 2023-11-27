package Charles;


import java.rmi.Naming;
import java.rmi.RemoteException;

public class Client extends java.rmi.server.UnicastRemoteObject implements Client2 {

    private static Server2 h;
    private static Client c;
    public Client() throws RemoteException {
        super();
    }

    public void menu() throws java.rmi.RemoteException {

        int option = 10, id;
        String nome, autor, editora,livro;
        int data_de_publicacao;

        do {
            System.out.println("1 => Inserir o Livro.");
            System.out.println("2 => Consultar Livro.");
            System.out.println("3 => Requesitar/Reservar Livro.");
            System.out.println("4 => Retornar o Livro.");
            System.out.println("5 => Listar todos os Livros.");
            System.out.println("0 => Quit.");

            option = Ler.umInt();

            switch (option) {
                case 1:
                    System.out.println("Insere o nome do Livro: ");
                    nome = Server.lerString();
                    System.out.println("insere o nome Do autor: ");
                    autor = Server.lerString();
                    System.out.println("Insere a Editora: ");
                    editora = Server.lerString();
                    System.out.println("Insere o Ano De Publicacao: ");
                    data_de_publicacao = Server.lerInt();

                    h.add((Client2) c, nome, autor, editora, data_de_publicacao);

                    break;

                case 2:
                    System.out.println("Insere o nome do Livro: ");
                    livro = Server.lerString();
                    h.consult((Client2) c, livro);

                    break;

                case 3:
                    System.out.println("Inserir o ID do Livro que queres Reservar/Requesitar: ");
                    id = Server.lerInt();
                    h.request((Client2) c, id);

                    break;

                case 4:
                    System.out.println("Insere o ID do Livro que queres retornar: ");
                    id = Server.lerInt();
                    h.deliver((Client2) c, id);

                    break;

                case 5:
                    h.list((Client2) c);
                    break;

                case 0:
                    System.exit(0);


                default:
                    System.out.println("Opcao Invalida: " + option + ".");
            }
        } while (option != 0);
    }

    public void printOnClient(String s) throws java.rmi.RemoteException{
        System.out.println("Messagem do Servidor: " + s);
    }

    public static void main(String[] args) {
     /*   System.setProperty("", "");
        System.setSecurityManager(new SecurityManager());
        String s, nome;
        int op = 10, num;*/
        try {
            h = (Server2) Naming.lookup("Livros");
            Client.c = new Client();
            h.subscribe("Nome dos clientes na maquina: ", (Client2) c);

        } catch (Exception r) {
            System.out.println("Expcepcao no cliente" + r.getMessage());
        }
    }
}


