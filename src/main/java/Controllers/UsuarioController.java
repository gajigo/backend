package Controllers;

import DAO.UserDAO;
import Models.User;
import Views.UsuarioView;

import java.util.List;

public class UsuarioController {
    private UserDAO dao;
    private UsuarioView view;

    public UsuarioController() {
        this.dao = new UserDAO();
        this.view = new UsuarioView(this);
    }

    public void start() {
        view.menu();
        if (!dao.save()) {
            System.out.println("Erro ao salvar usuarios!");
        }
    }

    public User registrar(User user) {
        // Registramos o usuario com nome e senha na DAO
        // e retornamos para o usuario modificar.
        return dao.createUser(user);
    }

    public User editUser(User user) {
        if (user != null) {
            return dao.updateUser(user);
        }
        return null;
    }

    public List<User> getModels() {
        return dao.listUsers();
    }

    public User getById(long id) {
        return dao.getUserById(id);
    }

    public boolean deleteById(Long id) {
        return dao.deleteUserById(id);
    }
}
