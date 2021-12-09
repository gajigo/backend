package Models;

// categoria da palestra, ex: Palestra de Tecnologia
public class Topic {
    private Long id;
    private String name;

    public Topic() {
    }

    public Topic(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Assunto{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
