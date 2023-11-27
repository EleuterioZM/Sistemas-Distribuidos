package Pratico_2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SegundoClienteCallbackImpl extends UnicastRemoteObject implements ClienteCallback {
    public SegundoClienteCallbackImpl() throws RemoteException {
        // Construtor padrão
    }

    @Override
    public void notificarRecursoDisponivel() throws RemoteException {
        System.out.println("Recurso disponível. Segundo usuário foi notificado!");
    }
}
