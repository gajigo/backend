package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Eventos {
    private String nomeEvento;
    private Date dataEvento;
    private ArrayList<Organizador> organizadores;
    private String descricao;
    private ArrayList<Palestra> palestrasAssociadas;
    private String modalidade;

    public static void main(String[] args){
        Eventos atual = new Eventos();

    }

    public Eventos() {
    }

    public Eventos(String nomeEvento, Date dataEvento, ArrayList<Organizador> organizadores, String descricao, ArrayList<Palestra> palestrasAssociadas, String modalidade) {
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

    public ArrayList<Organizador> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(ArrayList<Organizador> organizadores) {
        this.organizadores = organizadores;
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

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
}
