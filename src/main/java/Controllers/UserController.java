package Controllers;

import DAO.UserDAO;
import Models.Roles;
import Models.User;
import Views.UserView;

import javax.management.relation.Role;
import java.util.List;

public class UserController {
    private UserDAO dao = new UserDAO();

    public UserController() {
    }

    public User addUser(User user) {
        return dao.createUser(user);
    }

    public User editUser(User user) {
        return dao.updateUser(user);
    }

    public List<User> getModels() {
        return dao.listUsers();
    }

    public User getById(Long id) {
        return dao.getUserById(id);
    }

    public boolean deleteById(Long id) {
        return dao.deleteUserById(id);
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
