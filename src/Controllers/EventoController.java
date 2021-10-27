package Controllers;

import DAO.EventoDAO;
import Models.Evento;
import Models.Usuario;
import Views.EventoView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class EventoController {
    private List<Evento> models;
    private EventoView view;

    public EventoController() {
        this.models = new ArrayList<>();
        this.view = new EventoView(this);
    }

    public void start(){
        load();
        view.menu();
        save();
    }
    public Evento cadastrar(String nomeEvento, String descricao, String dataEvento) {
        Evento novoEvento = new Evento(nomeEvento, descricao, dataEvento);
        novoEvento.setId(getNewId());
        this.models.add(novoEvento);
        return novoEvento;
    }

    public long getNewId() {
        // Retorna um id novo que nao esta na lista
        // Percorremos a lista procurando pelo id de valor maximo
        long idMax = 0;
        for (Evento evento : models) {
            idMax = max(idMax, evento.getId());
        }

        // Depois que encontramos o ID maximo, retornamos ele + 1
        return idMax + 1;
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

    public List<Evento> getModels() {
        return this.models;
    }

    public Evento getById(int target) {
        // Recebe um id alvo para procurar na lista, retorna o evento com este id ou null
        // Anda pela lista de eventos, se o id do atual ser o procurado, retorna este evento
        for (Evento evento : models) {
            if (evento.getId() == target) {
                return evento;
            }
        }

        // Se o id nao estiver na lista, retorna null
        return null;
    }

    public boolean deleteById(int id) {
        // Deleta um evento na lista por id, se conseguir, retorna true, se nao, false
        // Procura o evento na lista
        Evento escolhido = getById(id);

        // Se existe, delete e retorna true
        if (escolhido != null) {
            models.remove(escolhido);
            return true;
        }

        // Se nao existe, retorna false
        return false;
    }
}