package Models;


import DAO.DAOUser;

import java.util.ArrayList;

public class User implements DAOUser {
    private static final long serialVersionUID = 2L;
    private long userId;
    private String name;
    private String password;
    private String statusLogin;
    private String email;
    private String phone;
    private CartaoVisita businessCard;
    private ArrayList<Roles> roles = new ArrayList<>();

    public User() {
        statusLogin = "logado";
    }

    public User(String name, String password) {
        this();
        this.name = name;
        this.password = password;
    }

    public User(int userId, String name, String password, String statusLogin, ArrayList<Roles> roles) {
        this(name, password);
        this.userId = userId;
        this.statusLogin = statusLogin;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "userId=" + userId +
                ", nome='" + name + '\'' +
                ", senha='" + password + '\'' +
                ", statusLogin='" + statusLogin + '\'' +
                ", roles=" + roles +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return userId;
    }

    public void setId(long id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(String statusLogin) {
        this.statusLogin = statusLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CartaoVisita getBusinessCard() {
        return businessCard;
    }

    public void setBusinessCard(CartaoVisita businessCard) {
        this.businessCard = businessCard;
    }

    public ArrayList<Roles> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Roles> roles) {
        this.roles = roles;
    }

    public void addRole(Roles newRole) {
        if (this.roles.contains(newRole)) {
            return;
        }
        this.roles.add(newRole);
    }

    public void removeRole(Roles role) {
        this.roles.remove(role);
    }

    public boolean checkLogin(String password) {
        return password.equals(this.password);
    }
}