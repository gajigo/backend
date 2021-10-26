package Controllers;

import DAO.EventoDAO;
import Models.Evento;
import Views.EventoView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoController {
    private List<Evento> models;
    private EventoView view;

    public EventoController() {
        this.models = new ArrayList<>();
        this.view = new EventoView(this);
    }

    public void start(){
        load();
        System.out.println(models);
        view.menuEvento();
        save();
    }
    public void cadastrar(String nomeEvento, String descricao, String dataEvento) {
        Evento novoEvento = new Evento(nomeEvento, descricao, dataEvento);
    }
    public void save(){
        EventoDAO dao = new EventoDAO();
        dao.save(this.models)
    }

    public void load() {
        EventoDAO dao = new EventoDAO();
        this.models = dao.load();
    }

    public void clean() {
        EventoDAO dao = new EventoDAO();
        dao.clean();
    }
}