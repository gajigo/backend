package Models;

import DAO.DAOUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Palestra implements DAOUser {

    private static final long serialVersionUID = 1L;
    private String nome;
    private String descricao;
    private long id;
    private String dataInicio;//E do tipo Instant mas esta String por enquanto para facilitar
    private String duracao;//E do tipo Instant mas esta String por enquanto para facilitar
    private List<Idioma> idioma = new ArrayList<>();
    private List<Assunto> assuntos = new ArrayList<>();
    private List<Avaliacao> avaliacoes = new ArrayList<>();
    private List<Usuario> palestrantes = new ArrayList<>();
    private List<Usuario> participantes = new ArrayList<>();
    private List<DuvidaPalestra> duvidas = new ArrayList<>();
    private boolean status;

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

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public List<Idioma> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<Idioma> idioma) {
        this.idioma = idioma;
    }

    public List<Assunto> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List<Assunto> assuntos) {
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

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

    public void addDuvida(DuvidaPalestra duvida) {
        this.duvidas.add(duvida);
    }

    public void removeDuvida(DuvidaPalestra duvida) {
        this.duvidas.remove(duvida);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
