package Charles;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Server extends java.rmi.server.UnicastRemoteObject implements Server2{

    private static Client2 client;
    private static ArrayList<Livros> list =  new ArrayList<>(); //Array List with all board games

    public Server()  throws java.rmi.RemoteException {
        super();
    }

    //writes on server
    public void printOnServer(String s, Client2 c) throws java.rmi.RemoteException {
        System.out.println("Servidor : " + s);
        this.client = c;
    }

    //sends menu to client
    public void subscribe(String name, Client2 c) throws java.rmi.RemoteException {
        System.out.println("Novo Cliente chegou");
        this.client = c;
        client.menu();
    }

    //add Board games inserted by clients into the array list
    public void add(Client2 c, String nome, String autor, String editora, int data_de_publicacao) throws java.rmi.RemoteException {
        this.client = c;
        Livros b = new Livros(nome, autor, editora, data_de_publicacao, editora, client, client);
        list.add(b);
        client.printOnClient("Livro inserido com Sucesso!");
    }

    //list all board games to client
    public void list(Client2 c) throws RemoteException {
        int i;
        this.client = c;
        String quote = "";

        for (i = 0; i < list.size(); i++) {
            quote +=  "\n Nome do Livro: " + list.get(i).getNome()
                    + "\n Disponiblidade: " + list.get(i).getDisponibilidade()+ "\n";
        }
        if (quote.equals("")) {
            client.printOnClient("Nao tem livro na Lista");
        } else {
            client.printOnClient(quote);
        }
    }

    //request & reserve board games
    public synchronized void request(Client2 c, int id) throws RemoteException {

        this.client = c;
        if (list.get(id).getDisponibilidade().equals("Reservado")) {

            client.printOnClient("o Livro ja foi requesitado & reservado.");
        }

        if (list.get(id).getDisponibilidade().equals("Requesitado")) {
            if (list.get(id).getReserva()== null) {
                list.get(id).setDisponibilidade("Reservado");
                list.get(id).setReserva(client);
                client.printOnClient("Reservaste o livro, desde que  requesitaste");
            } else {
                client.printOnClient("O Livro ja foi requesitado / reservado");
            }
        } else {
            list.get(id).setDisponibilidade("Requesitado");
            list.get(id).setClient(client);
            client.printOnClient("Livro requesitado com sucesso.");
        }
    }

    public void deliver(Client2 c, int id) throws RemoteException {
        this.client = c;

        if (list.get(id).getCliente().equals(c)) {
            if (list.get(id).getReserva() == null) {
                list.get(id).setDisponibilidade("Disponivel");
                c.printOnClient("Livro entregado com sucesso.");
            } else {
                list.get(id).setDisponibilidade("Requesitado");
                list.get(id).setClient(list.get(id).getReserva());
                list.get(id).setReserva(null);
                c.printOnClient("Livro entregado com sucesso.");
                list.get(id).setDisponibilidade("Requesitado");
                list.get(id).getCliente().printOnClient("O Livro que reservaste ja esta disponivel " + "e foi requisitado por voce!");
            }
        } else {
            client.printOnClient("O Livro nao foi requesitado por voce.");
        }
    }

    public void consult(Client2 c, String Board) throws RemoteException {
        int i;
        this.client = c;
        String quote = "";
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getNome().equals(Board)) {
                quote += "\nID: " +  i
                        + "\nNome do Livro: " + list.get(i).getNome()
                        + "\nAutor: " + list.get(i).getAutor()
                        + "\nEditora: " + list.get(i).getEditora()
                        + "\nData_de_publicacao: " + list.get(i).getdata_de_publicacao()
                        + "\nDisponiblidade: " + list.get(i).getDisponibilidade();
            }
        }
        if(quote.equals("")) {
            client.printOnClient("O livro que queres consultar nao existe.");
        } else {
            client.printOnClient(quote);
        }
    }

    public static String lerString() {
        String s = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in), 1);
            s  = in.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public static int lerInt() {
        while(true){
            try{
                return Integer.valueOf(lerString().trim()).intValue();
            }
            catch(Exception e){
                System.out.println("Nao e um int valido.");
            }
        }
    }

    public static void main(String[] args) throws RemoteException {
        String s;
        System.setProperty("java.rmi.server.hostname", "localhost");
        /*System.setSecurityManager(new SecurityManager()); */
        java.rmi.registry.LocateRegistry.createRegistry(1099);

        try {
            Server h = new Server() {


            };
            Naming.rebind("Livros", (Remote)h);
            System.out.println("Servidor esta activo.");

        } catch (RemoteException r) {
            System.out.println("Exception in server" + r.getMessage());

        } catch (java.net.MalformedURLException u) {
            System.out.println("Exception in server -URL");
        }

    }
}


