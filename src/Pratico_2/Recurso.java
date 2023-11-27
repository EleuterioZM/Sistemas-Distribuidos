package Pratico_2;

import java.io.Serializable;
import java.util.Collection;

public class Recurso implements Serializable {
    private int id;
    private String descricao;
    private boolean disponivel;
    private boolean requisitado;
    // Outros atributos específicos do recurso

    public Recurso(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.disponivel = true;
        this.requisitado = false;
    }

    // Métodos getters e setters aqui

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public boolean isRequisitado() {
        return requisitado;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setRequisitado(boolean requisitado) {
        this.requisitado = requisitado;
    }


    @Override
    public String toString() {
        return "Recurso [id=" + id + ", descricao=" + descricao + ", disponivel=" + disponivel + ", requisitado=" + requisitado + "]";
    }

    public String getDescricao() {
        return descricao;
    }

}
