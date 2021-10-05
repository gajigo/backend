package Models;

public class Link {
    private long id;
    private String icone;
    private String identificado;

    public Link() {
    }

    public Link(long id, String icone, String identificado) {
        this.id = id;
        this.icone = icone;
        this.identificado = identificado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getIdentificado() {
        return identificado;
    }

    public void setIdentificado(String identificado) {
        this.identificado = identificado;
    }
}
