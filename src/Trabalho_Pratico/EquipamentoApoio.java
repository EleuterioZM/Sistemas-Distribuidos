package Trabalho_Pratico;

import java.io.Serializable;

public class EquipamentoApoio implements Serializable {
    private int id;
    private String descricao;
    private boolean disponivel;
    private boolean requisitado;
    private String tipoEquipamento;
    private String detalhesEquipamento;

    public EquipamentoApoio() {
    }

    public EquipamentoApoio(int id, String descricao, String tipoEquipamento, String detalhesEquipamento) {
        this.id = id;
        this.descricao = descricao;
        this.disponivel = true;
        this.requisitado = false;
        this.tipoEquipamento = tipoEquipamento;
        this.detalhesEquipamento = detalhesEquipamento;
    }

    public EquipamentoApoio(int id, String descricao, boolean disponivel, boolean requisitado, String tipoEquipamento, String detalhesEquipamento) {
    }

    // Métodos getters e setters aqui...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean isRequisitado() {
        return requisitado;
    }

    public void setRequisitado(boolean requisitado) {
        this.requisitado = requisitado;
    }

    public String getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(String tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public String getDetalhesEquipamento() {
        return detalhesEquipamento;
    }

    public void setDetalhesEquipamento(String detalhesEquipamento) {
        this.detalhesEquipamento = detalhesEquipamento;
    }

    @Override
    public String toString() {
        return "Recurso [id=" + id + ", descricao=" + descricao + ", disponivel=" + disponivel +
                ", requisitado=" + requisitado + ", tipoEquipamento=" + tipoEquipamento +
                ", detalhesEquipamento=" + detalhesEquipamento + "]";
    }
}

