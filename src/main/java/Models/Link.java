package Models;


public class Link {
    private Long id;
    private String icon;
    private String identifier;

    public Link() {
    }

    public Link(String icon, String identifier) {
        this.icon = icon;
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
