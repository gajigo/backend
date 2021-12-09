package Models;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    private Long userId;
    private String name;
    private String password;
    private String statusLogin;
    private String email;
    private String phone;
    private BusinessCard businessCard;
    private ArrayList<Roles> roles = new ArrayList<>();

    public User() {
        statusLogin = "logado";
    }

    public User(String name, String password) {
        this();
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "userId=" + userId +
                ", nome='" + name +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
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

    public void setNewPassword(String password) {
        this.setPassword(this.generateHashPassword(password));
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

    public BusinessCard getBusinessCard() {
        return businessCard;
    }

    public void setBusinessCard(BusinessCard businessCard) {
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

    protected String generateHashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            byte[] messageDigest = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            BigInteger no = new BigInteger(1, messageDigest);

            return no.toString(16);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public boolean checkLogin(String password) {
        String hashPassword = this.generateHashPassword(password);
        return this.password.equals(hashPassword);
    }
}