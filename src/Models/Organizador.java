package Models;
import java.util.ArrayList;

public class Organizador {
    private ArrayList<Eventos> eventosAssociados;

    public Organizador() {
    }

    public Organizador(ArrayList<Eventos> eventosAssociados) {
        this.eventosAssociados = eventosAssociados;
    }

    @Override
    public String toString() {
        return "Organizador{" +
                "eventosAssociados=" + eventosAssociados +
                '}';
    }

    public ArrayList<Eventos> getEventosAssociados() {
        return eventosAssociados;
    }

    public void setEventosAssociados(ArrayList<Eventos> eventosAssociados) {
        this.eventosAssociados = eventosAssociados;
    }

    public void addEventoAssociado(Eventos novoEvento) {
        this.eventosAssociados.add(novoEvento);
    }

    public void removeEventoAssociado(Eventos evento) {
        this.eventosAssociados.remove(evento);
    }
}
