package Models;


import java.io.Serializable;

public class DuvidaPalestra implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String duvida;
    private User user;

    public DuvidaPalestra() {
    }

    public DuvidaPalestra(long id, String duvida, User user) {
        this.id = id;
        this.duvida = duvida;
        this.user = user;
    }

    @Override
    public String toString() {
        return "DuvidaPalestra{" +
                "id=" + id +
                ", duvida='" + duvida + '\'' +
                ", usuario=" + user +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDuvida() {
        return duvida;
    }

    public void setDuvida(String duvida) {
        this.duvida = duvida;
    }

    public User getUsuario() {
        return user;
    }

    public void setUsuario(User user) {
        this.user = user;
    }
}
