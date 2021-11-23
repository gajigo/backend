package Models;

import DAO.DAOUser;

import java.util.ArrayList;
import java.util.List;

public class Evento implements DAOUser {
    private static final long serialVersionUID = 1L;
    private String nomeEvento;
    private String descricao;
    private Modalidade modalidade;
    private long id;
    private String dataEvento;
    private List<User> organizadores = new ArrayList<>();
    private List<Lecture> lectures = new ArrayList<>();

    public Evento() {
    }

    public Evento(String nomeEvento, String descricao, Modalidade modalidade, long id, String dataEvento, List<User> organizadores, List<Lecture> lectures) {
        this.nomeEvento = nomeEvento;
        this.descricao = descricao;
        this.modalidade = modalidade;
        this.id = id;
        this.dataEvento = dataEvento;
        this.organizadores = organizadores;
        this.lectures = lectures;
    }

    public Evento(String nomeEvento, String descricao, String dataEvento) {
        this.nomeEvento = nomeEvento;
        this.descricao = descricao;
        this.dataEvento = dataEvento;
    }

    @Override
    public String toString() {
        return "Evento{" +
                " id=" + id +
                ", nomeEvento='" + nomeEvento + '\'' +
                ", descricao='" + descricao + '\'' +
                ", modalidade=" + modalidade +
                ", dataEvento=" + dataEvento +
                ", organizadores=" + organizadores +
                ", palestras=" + lectures +
                '}';
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public List<User> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(List<User> organizadores) {
        this.organizadores = organizadores;
    }

    public List<Lecture> getPalestras() {
        return lectures;
    }

    public void setPalestras(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public void addPalestra(Lecture lecture) {
        this.lectures.add(lecture);
    }

    public void removePalestra(Lecture lecture) {
        this.lectures.remove(lecture);
    }

    public void addOrganizador(User organizador){
        this.organizadores.add(organizador);
    }

    public void removeOrganizador(User organizador){
        this.organizadores.remove(organizador);
    }
}
