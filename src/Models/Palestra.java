package Models;

public class Palestra {
    private String descricao;
    private float avaliacao;
    private int quantidadeAvaliadores;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void addAvaliacao(float novaAvaliacao) {
        float avaliacaoTotal = this.avaliacao * this.quantidadeAvaliadores + novaAvaliacao;
        quantidadeAvaliadores++;

        this.avaliacao = avaliacaoTotal / this.quantidadeAvaliadores;
    }

    public int getQuantidadeAvaliadores() {
        return quantidadeAvaliadores;
    }

    public void setQuantidadeAvaliadores(int quantidadeAvaliadores) {
        this.quantidadeAvaliadores = quantidadeAvaliadores;
    }
}
