package Controllers;

import DAO.UsuarioDAO;
import Models.User;
import Views.UsuarioView;

import java.util.List;

public class UsuarioController {
    private UsuarioDAO dao;
    private UsuarioView view;

    public UsuarioController() {
        this.dao = new UsuarioDAO();
        this.view = new UsuarioView(this);
        this.dao.createUserTable();
    }

    public void start() {
        view.menu();
        if (!dao.save()) {
            System.out.println("Erro ao salvar usuarios!");
        }
    }

    public User registrar(String nome, String senha) {
        // Registramos o usuario com nome e senha na DAO
        // e retornamos para o usuario modificar.
        User user = new User(nome, senha);
        user.setEmail("teste@email.com");
        user.setTelefone("45999999999");
        return dao.createUser(user);
    }

    public List<User> getModels() {
        return dao.listUsers();
    }

    public User getById(long id) {
        return dao.getById(id);
    }

    public boolean deleteById(Long id) {
        return dao.deleteUserById(id);
    }
}
