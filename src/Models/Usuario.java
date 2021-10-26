package Models;


import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 2L;
    private int userId;
    private String nome;
    private String senha;
    private String statusLogin;
    private String email;
    private String telefone;
    private CartaoVisita cartao;
    private ArrayList<Roles> roles = new ArrayList<>();

    public Usuario() {
        statusLogin = "logado";
    }

    public Usuario(String nome, String senha) {
        this();
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario(int userId, String nome, String senha, String statusLogin, ArrayList<Roles> roles) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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