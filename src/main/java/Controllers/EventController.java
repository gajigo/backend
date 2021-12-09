package Controllers;

import DAO.EventDAO;
import Models.Event;
import Models.User;
import Views.EventView;

import java.sql.SQLException;
import java.util.List;

public class EventController {
    private EventDAO dao = new EventDAO();

    public EventController() {
    }

    public Event addEvent(Event event) {
        return dao.createEvento(event);
    }

    public List<Event> getModels() throws SQLException {
        return dao.listEventos();
    }

    public Event getById(Long id) throws SQLException{
        return dao.getById(id);
    }

    public void editEvento(Event event) throws SQLException, NullPointerException{ dao.editEvento(event);}

    public boolean deleteById(Long id) throws SQLException, NullPointerException {
        return dao.deleteById(id);
    }

    public void addEventOrganizer(User user, Event event) throws SQLException { dao.addEventOrganizer(user, event);}

    public void removeEventOrganizer(User user, Event event) throws SQLException, NullPointerException { dao.removeEventOrganizer(user, event);}
}