package Controllers;

import DAO.UserDAO;
import Models.Roles;
import Models.User;
import Views.UserView;

import javax.management.relation.Role;
import java.util.List;

public class UserController {
    private UserDAO dao;
    private UserView view;

    public UserController() {
        this.dao = new UserDAO();
        this.view = new UserView(this);
    }

    public void start() {
        view.menu();
        if (!this.dao.save()) {
            System.out.println("Erro ao salvar usuarios!");
        }
    }

    public User register(User user) {
        // Registramos o usuario com nome e senha na DAO
        // e retornamos para o usuario modificar.
        return this.dao.createUser(user);
    }

    public User editUser(User user) {
        if (user != null) {
            return this.dao.updateUser(user);
        }
        return null;
    }

    public List<User> getModels() {
        return this.dao.listUsers();
    }

    public User getById(Long id) {
        return this.dao.getUserById(id);
    }

    public boolean deleteById(Long id) {
        return this.dao.deleteUserById(id);
    }

    public void addRole(User user, Roles roles) {
        List<Roles> newRoles = user.getRoles();
        newRoles.add(roles);
        dao.insertUpdateRoles(user.getUserId(), newRoles);
    }

    public void removeRole(User user, Roles roles) {
        List<Roles> newRoles = user.getRoles();
        newRoles.remove(roles);
        dao.insertUpdateRoles(user.getUserId(), newRoles);
    }
}
