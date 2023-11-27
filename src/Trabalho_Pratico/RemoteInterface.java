package Trabalho_Pratico;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteInterface extends Remote {
    void inserirRecurso(EquipamentoApoio recurso) throws RemoteException;

    List<EquipamentoApoio> consultarRecursos(String descricao) throws RemoteException;

    String requisitarRecurso(int id, ClienteCallback callback) throws RemoteException;

    String devolverRecurso(int id ) throws RemoteException;

    List<EquipamentoApoio> listarRecursos() throws RemoteException;
}
