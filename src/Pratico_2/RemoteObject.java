package Pratico_2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RemoteObject extends UnicastRemoteObject implements RemoteInterface {
    private List<Recurso> listaRecursos = new ArrayList<>();
    private Map<Integer, ClienteCallback> callbacks = new HashMap<>();

    public RemoteObject() throws RemoteException {
        // Construtor padrão
    }

    @Override
    public void inserirRecurso(Recurso recurso) throws RemoteException {
        listaRecursos.add(recurso);
    }

    @Override
    public List<Recurso> consultarRecursos(String descricao) throws RemoteException {
        return listaRecursos.stream()
                .filter(r -> r.getDescricao().contains(descricao))
                .collect(Collectors.toList());
    }

    @Override
    public String requisitarRecurso(int id, ClienteCallback callback) throws RemoteException {
        Recurso recurso = getRecursoById(id);
        if (recurso != null) {
            if (recurso.isDisponivel()) {
                recurso.setRequisitado(true);
                callbacks.put(id, callback);
                return "Recurso requisitado com sucesso";
            } else {
                return "Recurso reservado";
            }
        } else {
            return "Recurso não encontrado";
        }
    }
    public void devolverRecurso(int id, ClienteCallback callback) throws RemoteException {
        Recurso recurso = getRecursoById(id);
        if (recurso != null) {
            if (recurso.isRequisitado() && callbacks.get(id) == callback) {
                recurso.setDisponivel(true);
                recurso.setRequisitado(false);

                // Chamar o callback para informar que o recurso está disponível novamente
                callback = callbacks.get(id);
                if (callback != null) {
                    callback.notificarRecursoDisponivel();
                }
            } else {
                throw new RemoteException("Você não pode devolver este recurso. Ele não foi requisitado por você.");
            }
        } else {
            throw new RemoteException("Recurso não encontrado.");
        }
    }

    @Override
    public List<Recurso> listarRecursos() throws RemoteException {
        return listaRecursos.stream()
                .filter(Recurso::isDisponivel)
                .collect(Collectors.toList());
    }

    private Recurso getRecursoById(int id) {
        return listaRecursos.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
