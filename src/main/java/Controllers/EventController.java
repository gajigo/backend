package Controllers;

import DAO.EventDAO;
import Models.Event;
import Models.User;

import java.util.List;

public class EventController {
    private EventDAO dao = new EventDAO();

    public EventController() {
    }

    public Event addEvent(Event event) {
        return dao.createEvento(event);
    }

    public boolean removeEvent(Long id) {
        return dao.deleteById(id);
    }

    public boolean removeEvent(Event event) {
        return removeEvent(event.getId());
    }

    public List<Event> getModels() {
        return dao.listEventos();
    }

    public Event getById(Long id) {
        return dao.getById(id);
    }

    public void editEvent(Event event){ dao.editEvent(event);}

    public void addEventOrganizer(User user, Event event) { dao.addEventOrganizer(user, event);}

    public void removeEventOrganizer(User user, Event event) { dao.removeEventOrganizer(user, event);}
}