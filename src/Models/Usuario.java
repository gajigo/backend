package Models;

public class Usuario {
    private int userId;
    private String nome;
    private String senha;
    private String statusLogin;

    public Usuario() {
    }

    public Usuario(int userId, String nome, String senha, String statusLogin) {
        this.userId = userId;
        this.nome = nome;
        this.senha = senha;
        this.statusLogin = statusLogin;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "userId=" + userId +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", statusLogin='" + statusLogin + '\'' +
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

    public boolean verificaLogin(String senhaTeste) {
        return senhaTeste.equals(this.senha);
    }
}