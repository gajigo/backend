package Models;


import java.io.Serializable;
import java.util.List;

public class CartaoVisita implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private User user;
    private List<Link> links;

    public CartaoVisita() {
    }

    public CartaoVisita(User user, List<Link> links) {
        this.user = user;
        this.links = links;
    }

    @Override
    public String toString() {
        return "CartaoVisita{" +
                "usuario=" + user +
                ", links=" + links +
                '}';
    }

    public User getUsuario() {
        return user;
    }

    public void setUsuario(User user) {
        this.user = user;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
