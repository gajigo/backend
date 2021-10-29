package Controllers;

import DAO.UsuarioDAO;
import Models.Usuario;
import Views.UsuarioView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class UsuarioController {
    private UsuarioDAO dao;
    private UsuarioView view;

    public UsuarioController() {
        this.dao = new UsuarioDAO();
        this.view = new UsuarioView(this);
    }

    public void start() {
        view.menu();
        if (!dao.save()) {
            System.out.println("Erro ao salvar usuarios!");
        }
    }

    public Usuario registrar(String nome, String senha) {
        // Cria um novo usuario com nome e senha e um ID automatico, salvando na lista
        Usuario novo = new Usuario(nome, senha);
        novo.setUserId(getNewId());

        dao.addModel(novo);

        // Retorna o usuario para ele ser modificado se necessario
        return novo;
    }

    public int getNewId() {
        // Retorna um id novo que nao esta na lista
        // Percorremos a lista procurando pelo id de valor maximo
        int idMax = 0;
        for (Usuario usuario : dao.getModels()) {
            idMax = max(idMax, usuario.getUserId());
        }

        // Depois que encontramos o ID maximo, retornamos ele + 1
        return idMax + 1;
    }

    public List<Usuario> getModels() {
        return dao.getModels();
    }

    public Usuario getById(int target) {
        List<Usuario> models = dao.getModels();

        int low = 0, high = models.size() - 1;
        while (low < high) {
            int med = (low + high) / 2;
            Usuario usuario = models.get(med);

            if (usuario.getUserId() < target) {
                low = med + 1;
            } else if (usuario.getUserId() > target) {
                high = med - 1;
            } else {
                low = med;
                break;
            }
        }

        if (models.get(low).getUserId() == target) {
            return models.get(low);
        }

        return null;
    }

    public boolean deleteById(int id) {
        // Deleta um usuario na lista por id, se conseguir, retorna true, se nao, false
        // Procura o usuario na lista
        Usuario escolhido = getById(id);

        // Se existe, delete e retorna true
        if (escolhido != null) {
            dao.removeModel(escolhido);
            return true;
        }

        // Se nao existe, retorna false
        return false;
    }
}
