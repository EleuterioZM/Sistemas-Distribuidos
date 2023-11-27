package Trabalho_Pratico;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            RemoteObject remoteObject = new RemoteObject();
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("Servidor pronto, esperando por uma conexao...");
            registry.rebind("RemoteObject", (Remote) remoteObject);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

