package Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Evento {
    private String nomeEvento;
    private Date dataEvento;
    private ArrayList<Usuario> organizadores;
    private String descricao;
    private ArrayList<Palestra> palestrasAssociadas;
    private String modalidade;

    public Evento() {
        this.palestrasAssociadas = new ArrayList<>();
        this.organizadores = new ArrayList<>();
    }

    public Evento(String nomeEvento, Date dataEvento, String descricao, String modalidade) {
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.modalidade = modalidade;
        this.palestrasAssociadas = new ArrayList<>();
        this.organizadores = new ArrayList<>();
    }

    public Evento(String nomeEvento, Date dataEvento, ArrayList<Usuario> organizadores, String descricao, ArrayList<Palestra> palestrasAssociadas, String modalidade) {
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.organizadores = organizadores;
        this.descricao = descricao;
        this.palestrasAssociadas = palestrasAssociadas;
        this.modalidade = modalidade;
    }

    @Override
    public String toString() {
        return "Eventos{" +
                "nomeEvento='" + nomeEvento + '\'' +
                ", dataEvento=" + dataEvento +
                ", organizadores=" + organizadores +
                ", descricao='" + descricao + '\'' +
                ", palestrasAssociadas=" + palestrasAssociadas +
                ", modalidade='" + modalidade + '\'' +
                '}';
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public ArrayList<Usuario> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(ArrayList<Usuario> organizadores) {
        this.organizadores = organizadores;
    }

    public void addOrganizador(Usuario novoOrganizador) {
        this.organizadores.add(novoOrganizador);
    }

    public void removeOrganizador(Usuario organizador) {
        this.organizadores.remove(organizador);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Palestra> getPalestrasAssociadas() {
        return palestrasAssociadas;
    }

    public void setPalestrasAssociadas(ArrayList<Palestra> palestrasAssociadas) {
        this.palestrasAssociadas = palestrasAssociadas;
    }

    public void addPalestraAssociada(Palestra novaPalestra) {
        this.palestrasAssociadas.add(novaPalestra);
    }

    public void removePalestraAssociada(Palestra palestra) {
        this.palestrasAssociadas.remove(palestra);
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
}
