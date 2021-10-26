package Controllers;

import DAO.FileDAO;
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
        view.novoMenu();
        save();
    }

    public Usuario registrar(String nome, String senha) {
        Usuario novo = new Usuario(nome, senha);
        novo.setUserId(getNewId());

        this.models.add(novo);
        return novo;
    }

    public int getNewId() {
        int idMax = 0;
        for (Usuario usuario : models) {
            idMax = max(idMax, usuario.getUserId());
        }

        return idMax + 1;
    }

    public void save() {
        UsuarioDAO dao = new UsuarioDAO();
        dao.save(this.models);
    }

    public void load() {
        UsuarioDAO dao = new UsuarioDAO();
        this.models = dao.load();
    }

    public void clean() {
        UsuarioDAO dao = new UsuarioDAO();
        dao.clean();
    }

    public List<Usuario> getModels() {
        return this.models;
    }

    public Usuario getById(int id) {
        for (Usuario usuario : models) {
            if (usuario.getUserId() == id) {
                return usuario;
            }
        }

        return null;
    }

    public boolean deleteById(int id) {
        for (Usuario usuario : models) {
            if (usuario.getUserId() == id) {
                models.remove(usuario);
                return true;
            }
        }
        return false;
    }
}
