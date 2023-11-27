package Pratico_2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteInterface extends Remote {
    void inserirRecurso(Recurso recurso) throws RemoteException;

    List<Recurso> consultarRecursos(String descricao) throws RemoteException;

    String requisitarRecurso(int id, ClienteCallback callback) throws RemoteException;

    void devolverRecurso(int id, ClienteCallback callback) throws RemoteException;


    List<Recurso> listarRecursos() throws RemoteException;
}
