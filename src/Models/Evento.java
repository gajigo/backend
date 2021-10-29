package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Evento implements Serializable, DAOUser {
    private static final long serialVersionUID = 1L;
    private String nomeEvento;
    private String descricao;
    private Modalidade modalidade;
    private long id;
    private String dataEvento;
    private List<Usuario> organizadores = new ArrayList<>();
    private List<Palestra> palestras = new ArrayList<>();

    public Evento() {
    }

    public Evento(String nomeEvento, String descricao, Modalidade modalidade, long id, String dataEvento, List<Usuario> organizadores, List<Palestra> palestras) {
        this.nomeEvento = nomeEvento;
        this.descricao = descricao;
        this.modalidade = modalidade;
        this.id = id;
        this.dataEvento = dataEvento;
        this.organizadores = organizadores;
        this.palestras = palestras;
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
                ", palestras=" + palestras +
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

    public long getId() {
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

    public List<Usuario> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(List<Usuario> organizadores) {
        this.organizadores = organizadores;
    }

    public List<Palestra> getPalestras() {
        return palestras;
    }

    public void setPalestras(List<Palestra> palestras) {
        this.palestras = palestras;
    }

    public void addPalestra(Palestra palestra) {
        this.palestras.add(palestra);
    }

    public void removePalestra(Palestra palestra) {
        this.palestras.remove(palestra);
    }

    public void addOrganizador(Usuario organizador){
        this.organizadores.add(organizador);
    }

    public void removeOrganizador(Usuario organizador){
        this.organizadores.remove(organizador);
    }
}
