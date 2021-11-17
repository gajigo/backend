package Controllers;

import DAO.UsuarioDAO;
import Models.Usuario;
import Views.UsuarioView;

import java.util.List;

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
        // Registramos o usuario com nome e senha na DAO
        // e retornamos para o usuario modificar.
        return dao.addModel(new Usuario(nome, senha));
    }

    public List<Usuario> getModels() {
        return dao.getModels();
    }

    public Usuario getById(long id) {
        return dao.getById(id);
    }

    public boolean deleteById(long id) {
        return dao.deleteById(id);
    }
}
