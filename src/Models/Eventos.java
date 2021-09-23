package Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Eventos {
    private String nomeEvento;
    private Date dataEvento;
    private ArrayList<Usuario> organizadores;
    private String descricao;
    private ArrayList<Palestra> palestrasAssociadas;
    private String modalidade;

    public static void main(String[] args){
        // Data do Evento
        Calendar dataEvento = Calendar.getInstance();

        // Seta Ano/Mes/Dia
        dataEvento.set(Calendar.YEAR, 2021);
        dataEvento.set(Calendar.MONTH, Calendar.OCTOBER);
        dataEvento.set(Calendar.DAY_OF_MONTH, 1);

        // Reseta Hora/Minuto/Segundo
        dataEvento.set(Calendar.HOUR_OF_DAY, 0);
        dataEvento.set(Calendar.MINUTE, 0);
        dataEvento.set(Calendar.SECOND, 0);

        // Inicializa o Evento
        Eventos atual = new Eventos(
                "Nasa Space Apps",
                dataEvento.getTime(),
                "NASA is inviting coders, designers, and technologists to come together in a global, virtual hackathon.",
                "Online"
        );

        // Adiciona organizadores
        Usuario organizador1 = new Usuario("Alice", "senhamassa");
        Usuario organizador2 = new Usuario("Bob", "senhalegal");

        // Adiciona o cargo de organizador
        organizador1.addRole(Roles.ORGANIZADOR);
        organizador2.addRole(Roles.ORGANIZADOR);

        // Associa organizadores ao evento
        atual.addOrganizador(organizador1);
        atual.addOrganizador(organizador2);

        System.out.println("Organizadores:");
        System.out.println(atual.getOrganizadores());

        // Adiciona palestra
        Palestra palestra1 = new Palestra("Aula de Java", "Aprenda mais sobre Java");
        Palestra palestra2 = new Palestra("Workshop de Arduino", "Aprenda a trabalhar com Arduinos na pratica");

        atual.addPalestraAssociada(palestra1);
        atual.addPalestraAssociada(palestra2);

        System.out.println("Palestras: ");
        System.out.println(atual.getPalestrasAssociadas());

        // Printa evento
        System.out.println(atual);
    }

    public Eventos() {
        this.palestrasAssociadas = new ArrayList<>();
        this.organizadores = new ArrayList<>();
    }

    public Eventos(String nomeEvento, Date dataEvento, String descricao, String modalidade) {
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.modalidade = modalidade;
        this.palestrasAssociadas = new ArrayList<>();
        this.organizadores = new ArrayList<>();
    }

    public Eventos(String nomeEvento, Date dataEvento, ArrayList<Usuario> organizadores, String descricao, ArrayList<Palestra> palestrasAssociadas, String modalidade) {
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
