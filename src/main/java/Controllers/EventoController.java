package Controllers;

import DAO.EventoDAO;
import Models.Event;
import Models.User;
import Views.EventoView;

import java.util.List;

public class EventoController {
    private EventoDAO dao;
    private EventoView view;

    public EventoController() {
        this.dao = new EventoDAO();
        this.view = new EventoView(this);
    }

    public void start(){
        view.menu();
        if (!dao.save()) {
            System.out.println("Erro ao salvar eventos!");
        }
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

}