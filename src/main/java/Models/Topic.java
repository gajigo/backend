package Models;

import java.util.Locale;

// categoria da palestra, ex: Palestra de Tecnologia
public class Topic {
    private Long id;
    private String name;

    public Topic() {
    }

    public Topic(String name) {
        setName(name);
    }

    @Override
    public String toString() {
        return "Assunto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase(Locale.ROOT);
    }
}
