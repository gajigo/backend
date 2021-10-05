package Models;

public class DuvidaPalestra {
    private long id;
    private String duvida;
    private Usuario usuario;

    public DuvidaPalestra() {
    }

    public DuvidaPalestra(long id, String duvida, Usuario usuario) {
        this.id = id;
        this.duvida = duvida;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
