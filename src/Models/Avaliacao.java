package Models;

import java.io.Serial;
import java.io.Serializable;

public class Avaliacao implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private long id;
    private float nota;
    private Palestra palestraAssociada;
    private Usuario avaliador;
    private boolean ativo;

    public Avaliacao(float nota, Palestra palestraAssociada, Usuario avaliador) {
        this.nota = nota;
        this.palestraAssociada = palestraAssociada;
        this.avaliador = avaliador;
        this.ativo = true;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "nota=" + nota +
                ", palestraAssociada=" + palestraAssociada +
                ", avaliador=" + avaliador +
                ", ativo=" + ativo +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Palestra getPalestraAssociada() {
        return palestraAssociada;
    }

    public void setPalestraAssociada(Palestra palestraAssociada) {
        this.palestraAssociada = palestraAssociada;
    }

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
