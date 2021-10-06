package Models;
// categoria da palestra, ex: Palestra de Tecnologia
public class Assunto {
    private long id;
    private String nome;

    public Assunto() {
    }

    public Assunto(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Assunto{" +
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
