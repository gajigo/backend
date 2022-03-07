package Controllers;

import DAO.UserDAO;
import Models.Roles;
import Models.User;
import Views.UserView;

import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.List;

public class UserController {
    private UserDAO dao = new UserDAO();

    public UserController() {
    }

    public User loginUser(User user) {
        return dao.loginUser(user);
    }

    public User addUser(User user) throws SQLException {
        return dao.createUser(user);
    }

    public User editUser(User user) throws SQLException {
        return dao.updateUser(user);
    }

    public List<User> getModels() throws SQLException{
        return dao.listUsers();
    }

    public User getById(Long id) throws SQLException {
        return dao.getUserById(id);
    }

    public boolean deleteById(Long id) throws SQLException {
        return dao.deleteUserById(id);
    }

    public void addRole(User user, Roles roles) throws SQLException {
        List<Roles> newRoles = user.getRoles();
        newRoles.add(roles);
        dao.insertUpdateRoles(user.getUserId(), newRoles);
    }

    public void removeRole(User user, Roles roles) throws SQLException {
        List<Roles> newRoles = user.getRoles();
        newRoles.remove(roles);
        dao.insertUpdateRoles(user.getUserId(), newRoles);
    }
}
