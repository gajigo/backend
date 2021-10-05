package Models;

import java.time.Instant;
import java.util.List;

public class Palestra {
    private String nome;
    private String descricao;
    private long id;
    private Instant dataInicio;
    private Instant duracao;
    private Idioma idioma;
    private List<Assunto> assunto;
    private List<Avaliacao> avaliacao;
    private List<Usuario> palestrante;
    private List<DuvidaDaPalestra> perguntas;

    public Palestra() {
    }

    public Palestra(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Instant dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Instant getDuracao() {
        return duracao;
    }

    public void setDuracao(Instant duracao) {
        this.duracao = duracao;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public List<Assunto> getAssunto() {
        return assunto;
    }

    public void setAssunto(List<Assunto> assunto) {
        this.assunto = assunto;
    }

    public List<Avaliacao> getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(List<Avaliacao> avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Usuario> getPalestrante() {
        return palestrante;
    }

    public void setPalestrante(List<Usuario> palestrante) {
        this.palestrante = palestrante;
    }

    public List<DuvidaDaPalestra> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<DuvidaDaPalestra> perguntas) {
        this.perguntas = perguntas;
    }
}
