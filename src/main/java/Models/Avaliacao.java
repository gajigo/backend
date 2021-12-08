package Models;


import java.io.Serializable;

public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private float nota;
    private Lecture lectureAssociada;
    private User avaliador;
    private boolean ativo;

    public Avaliacao(float nota, Lecture lectureAssociada, User avaliador) {
        this.nota = nota;
        this.lectureAssociada = lectureAssociada;
        this.avaliador = avaliador;
        this.ativo = true;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "nota=" + nota +
                ", palestraAssociada=" + lectureAssociada +
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

    public Lecture getPalestraAssociada() {
        return lectureAssociada;
    }

    public void setPalestraAssociada(Lecture lectureAssociada) {
        this.lectureAssociada = lectureAssociada;
    }

    public User getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(User avaliador) {
        this.avaliador = avaliador;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
