package Models;
import java.util.ArrayList;

public class Organizador {
    private ArrayList<Evento> eventoAssociados;

    public Organizador() {
    }

    public Organizador(ArrayList<Evento> eventoAssociados) {
        this.eventoAssociados = eventoAssociados;
    }

    @Override
    public String toString() {
        return "Organizador{" +
                "eventosAssociados=" + eventoAssociados +
                '}';
    }

    public ArrayList<Evento> getEventosAssociados() {
        return eventoAssociados;
    }

    public void setEventosAssociados(ArrayList<Evento> eventoAssociados) {
        this.eventoAssociados = eventoAssociados;
    }

    public void addEventoAssociado(Evento novoEvento) {
        this.eventoAssociados.add(novoEvento);
    }

    public void removeEventoAssociado(Evento evento) {
        this.eventoAssociados.remove(evento);
    }
}
