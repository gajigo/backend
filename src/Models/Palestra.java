package Models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Palestra {
    private String nome;
    private String descricao;
    private long id;
    private Instant dataInicio;
    private Instant duracao;
    private Idioma idioma;
    private List<Assunto> assuntos = new ArrayList<>();
    private List<Avaliacao> avaliacoes = new ArrayList<>();
    private List<Usuario> palestrantes = new ArrayList<>();
    private List<Usuario> participantes = new ArrayList<>();
    private List<DuvidaPalestra> duvidas = new ArrayList<>();

    public Palestra() {
    }

    public Palestra(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Palestra{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", id=" + id +
                ", dataInicio=" + dataInicio +
                ", duracao=" + duracao +
                ", idioma=" + idioma +
                ", assuntos=" + assuntos +
                ", avaliacoes=" + avaliacoes +
                ", palestrantes=" + palestrantes +
                ", participantes=" + participantes +
                ", duvidas=" + duvidas +
                '}';
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

    public List<Assunto> getAssuntos() {
        return assuntos;
    }

    public void setAssunto(List<Assunto> assuntos) {
        this.assuntos = assuntos;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Usuario> getPalestrantes() {
        return palestrantes;
    }

    public void setPalestrantes(List<Usuario> palestrantes) {
        this.palestrantes = palestrantes;
    }

    public List<DuvidaPalestra> getDuvidas() {
        return duvidas;
    }

    public void setDuvidas(List<DuvidaPalestra> duvidas) {
        this.duvidas = duvidas;
    }

    public void addAssunto(Assunto assunto) {
        this.assuntos.add(assunto);
    }

    public void removeAssunto(Assunto assunto) {
        this.assuntos.remove(assunto);
    }

    public void addAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public void removeAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.remove(avaliacao);
    }

    public void addPalestrante(Usuario palestrante) {
        this.palestrantes.add(palestrante);
    }

    public void removePalestrante(Usuario palestrante) {
        this.palestrantes.remove(palestrante);
    }

    public void addDuvida(DuvidaPalestra duvida) {
        this.duvidas.add(duvida);
    }

    public void removeDuvida(DuvidaPalestra duvida) {
        this.duvidas.remove(duvida);
    }
}
