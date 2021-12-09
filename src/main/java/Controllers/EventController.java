package Controllers;

import DAO.EventDAO;
import Models.Event;
import Models.User;
import Views.EventView;

import java.util.List;

public class EventController {
    private EventDAO dao;

    public EventController() {
        this.dao = new EventDAO();
    }

    public Event cadastrar(String nomeEvento, String descricao, String dataEvento) {
        // Registramos Evento no DAO e retornamos para ser modificado
        Event novoEvent = new Event(nomeEvento, descricao, dataEvento);
        return dao.createEvento(novoEvent);

    }

    public List<Event> getModels() {
        return dao.listEventos();
    }

    public Event getById(Long id) {
        return dao.getById(id);
    }

    public void editEvento(Event event){ dao.editEvento(event);}

    public boolean deleteById(Long id) {
        return dao.deleteById(id);
    }

    public void addEventOrganizer(User user, Event event) { dao.addEventOrganizer(user, event);}

    public void removeEventOrganizer(User user, Event event) { dao.removeEventOrganizer(user, event);}
}