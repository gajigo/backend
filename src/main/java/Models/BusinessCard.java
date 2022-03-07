package Models;


import java.util.List;

public class BusinessCard {
    private Long id;
    private User user;
    private List<Link> links;

    public BusinessCard() {
    }

    public BusinessCard(User user, List<Link> links) {
        setUser(user);
        setLinks(links);
    }

    @Override
    public String toString() {
        return "BusinessCard{" +
                "id=" + id +
                ", user=" + user +
                ", links=" + links +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
