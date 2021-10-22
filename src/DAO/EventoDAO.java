package DAO;

import Models.Evento;

public class EventoDAO extends FileDAO<Evento> {
    public EventoDAO() {
        super("eventos.txt");
    }

    public EventoDAO(String filename) {
        super(filename);
    }
}
