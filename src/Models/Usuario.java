package Models;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Scanner;

public class Usuario {
    private int userId;
    private String nome;
    private String senha;
    private String statusLogin;
    private ArrayList<Roles> roles;

    public static void main(String[] args) {
    }

    public Usuario() {
        this.roles = new ArrayList<>();
    }

    public Usuario(int userId, String nome, String senha, String statusLogin, ArrayList<Roles> roles) {
        this.userId = userId;
        this.nome = nome;
        this.senha = senha;
        this.statusLogin = statusLogin;
        this.roles = roles;
    }

    public Usuario(String nome, String senha) {
        this.userId = 0;
        this.nome = nome;
        this.senha = senha;
        this.statusLogin = "logado";
        this.roles = new ArrayList<>();
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