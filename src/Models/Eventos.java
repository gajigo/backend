package Models;

import java.util.Arrays;
import java.util.Date;

public class Eventos {
    private String nomeEvento;
    private Date dataEvento;
    private Organizador[] organizadores;
    private String descricao;
    private Palestra[] palestrasAssociadas;
    private String modalidade;

    public static void main(String[] args){
        Eventos atual = new Eventos();

    }

    public Eventos() {
    }

    public Eventos(String nomeEvento, Date dataEvento, Organizador[] organizadores, String descricao, Palestra[] palestrasAssociadas, String modalidade) {
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
                ", organizadores=" + Arrays.toString(organizadores) +
                ", descricao='" + descricao + '\'' +
                ", palestrasAssociadas=" + Arrays.toString(palestrasAssociadas) +
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

    public Organizador[] getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(Organizador[] organizadores) {
        this.organizadores = organizadores;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Palestra[] getPalestrasAssociadas() {
        return palestrasAssociadas;
    }

    public void setPalestrasAssociadas(Palestra[] palestrasAssociadas) {
        this.palestrasAssociadas = palestrasAssociadas;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
}
