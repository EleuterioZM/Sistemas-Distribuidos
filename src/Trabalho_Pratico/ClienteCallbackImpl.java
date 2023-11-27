package Trabalho_Pratico;

import java.rmi.RemoteException;
import java.io.Serializable;

public abstract class ClienteCallbackImpl implements ClienteCallback, Serializable {
    @Override
    public void notificarRecursoDisponivel() throws RemoteException {
        System.out.println("Recurso disponível novamente. Faça algo!");
    }
}
