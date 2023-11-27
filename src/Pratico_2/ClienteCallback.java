package Pratico_2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClienteCallback extends Remote {
    void notificarRecursoDisponivel() throws RemoteException;
}
