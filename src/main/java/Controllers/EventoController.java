package Controllers;

import DAO.EventoDAO;
import Models.Evento;
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

    public Evento cadastrar(String nomeEvento, String descricao, String dataEvento) {
        // Registramos Evento no DAO e retornamos para ser modificado
        Evento novoEvento = new Evento(nomeEvento, descricao, dataEvento);
        return dao.createEvento(novoEvento);

    }

    public List<Evento> getModels() {
        return dao.getModels();
    }

    public Evento getById(long id) {
        return dao.getById(id);
    }

    public boolean deleteById(long id) {
        return dao.deleteById(id);
    }
}