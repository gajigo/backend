package Models;

import java.io.Serializable;

public class Idioma implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String nome;

    public Idioma() {
    }

    public Idioma(String nome) {
        this.nome = nome;
    }

    public Idioma(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Idioma{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
