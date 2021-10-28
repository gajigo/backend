package Controllers;

import DAO.UsuarioDAO;
import Models.Usuario;
import Views.UsuarioView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class UsuarioController {
    private List<Usuario> models;
    private UsuarioView view;

    public UsuarioController() {
        this.models = new ArrayList<>();
        this.view = new UsuarioView(this);
    }

    public void start() {
        load();
        view.menu();
        save();
    }

    public Usuario registrar(String nome, String senha) {
        // Cria um novo usuario com nome e senha e um ID automatico, salvando na lista
        Usuario novo = new Usuario(nome, senha);
        novo.setUserId(getNewId());

        this.models.add(novo);

        // Retorna o usuario para ele ser modificado se necessario
        return novo;
    }

    public int getNewId() {
        // Retorna um id novo que nao esta na lista
        // Percorremos a lista procurando pelo id de valor maximo
        int idMax = 0;
        for (Usuario usuario : models) {
            idMax = max(idMax, usuario.getUserId());
        }

        // Depois que encontramos o ID maximo, retornamos ele + 1
        return idMax + 1;
    }

    public void save() {
        // Salva a lista de usuarios
        UsuarioDAO dao = new UsuarioDAO();
        if (dao.save(this.models) == -1) {
            System.out.println("Erro ao salvar usuarios!");
        }
    }

    public void load() {
        // Carrega a lista de usuarios
        UsuarioDAO dao = new UsuarioDAO();
        this.models = dao.load();
    }

    public void clean() {
        // Limpa a lista de usuarios
        UsuarioDAO dao = new UsuarioDAO();
        if (dao.clean() == -1) {
            System.out.println("Erro ao limpar arquivo!");
        }
    }

    public List<Usuario> getModels() {
        return this.models;
    }

    public Usuario getById(int target) {
        int low = 0, high = models.size() - 1;

        while (low < high) {
            int med = (low + high) / 2;
            System.out.println(med);
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
            models.remove(escolhido);
            return true;
        }

        // Se nao existe, retorna false
        return false;
    }
}
