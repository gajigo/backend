package Controllers;

import DAO.EventoDAO;
import Models.Evento;
import Views.EventoView;

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
    public Evento cadastrar(String nomeEvento, String descricao, String dataEvento) {
        Evento novoEvento = new Evento(nomeEvento, descricao, dataEvento);
        this.models.add(novoEvento);
        return novoEvento;
    }
    public void save(){
        EventoDAO dao = new EventoDAO();
        if (dao.save(this.models) == -1) {
            System.out.println("Erro ao salvar eventos");
        }
    }

    public void load() {
        EventoDAO dao = new EventoDAO();
        this.models = dao.load();
    }

    public void clean() {
        EventoDAO dao = new EventoDAO();
        if (dao.clean() == -1) {
            System.out.println("erro ao limpar arquivo!");
        }
    }
}