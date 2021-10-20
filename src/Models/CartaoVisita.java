package Models;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class CartaoVisita implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    private List<Link> links;

    public CartaoVisita() {
    }

    public CartaoVisita(Usuario usuario, List<Link> links) {
        this.usuario = usuario;
        this.links = links;
    }

    @Override
    public String toString() {
        return "CartaoVisita{" +
                "usuario=" + usuario +
                ", links=" + links +
                '}';
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
