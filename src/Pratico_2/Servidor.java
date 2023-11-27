package Pratico_2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            RemoteObject remoteObject = new RemoteObject();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("RemoteObject", remoteObject);

            System.out.println("Servidor pronto...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
