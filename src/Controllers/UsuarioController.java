package Controllers;

import DAO.FileDAO;
import Factory.DAOFactory;
import Models.Usuario;
import Views.UsuarioView;

import java.util.ArrayList;
import java.util.List;

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

    public void registrar(String nome, String senha) {
        Usuario novo = new Usuario(nome, senha);
        novo.setUserId(models.size() + 1);

        this.models.add(novo);
    }

    public void save() {
        FileDAO<Usuario> dao = DAOFactory.getUsuariosDAO();
        dao.save(this.models);
    }

    public void load() {
        FileDAO<Usuario> dao = DAOFactory.getUsuariosDAO();
        this.models = dao.load();
    }

    public void clean() {
        FileDAO<Usuario> dao = DAOFactory.getUsuariosDAO();
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
