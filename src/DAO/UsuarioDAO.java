package DAO;

import Models.Usuario;

public class UsuarioDAO extends FileDAO<Usuario> {
    public UsuarioDAO() {
        super("usuarios.txt");
    }


    public UsuarioDAO(String filename) {
        super(filename);
    }
}
