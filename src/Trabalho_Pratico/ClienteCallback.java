package Trabalho_Pratico;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface ClienteCallback extends Remote, Serializable {
    void notificarRecursoDisponivel() throws RemoteException;
}
