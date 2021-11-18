package Models;


import DAO.DAOUser;

import java.util.ArrayList;

public class User implements DAOUser {
    private static final long serialVersionUID = 2L;
    private long userId;
    private String nome;
    private String senha;
    private String statusLogin;
    private String email;
    private String telefone;
    private CartaoVisita cartao;
    private ArrayList<Roles> roles = new ArrayList<>();

    public User() {
        statusLogin = "logado";
    }

    public User(String nome, String senha) {
        this();
        this.nome = nome;
        this.senha = senha;
    }

    public User(int userId, String nome, String senha, String statusLogin, ArrayList<Roles> roles) {
        this(nome, senha);
        this.userId = userId;
        this.statusLogin = statusLogin;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "userId=" + userId +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public CartaoVisita getCartao() {
        return cartao;
    }

    public void setCartao(CartaoVisita cartao) {
        this.cartao = cartao;
    }

    public ArrayList<Roles> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Roles> roles) {
        this.roles = roles;
    }

    public void addRole(Roles novoRole) {
        if (this.roles.contains(novoRole)) {
            return;
        }
        this.roles.add(novoRole);
    }

    public void removeRole(Roles role) {
        this.roles.remove(role);
    }

    public boolean verificaLogin(String senhaTeste) {
        return senhaTeste.equals(this.senha);
    }
}