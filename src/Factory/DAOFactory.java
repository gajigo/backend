package Factory;

import DAO.FileDAO;
import DAO.UsuarioDAO;
import Models.Evento;
import Models.Palestra;
import Models.Usuario;

public class DAOFactory {
    public static UsuarioDAO getUsuariosDAO() {
       return new UsuarioDAO("usuarios.txt");
    }

    public static FileDAO<Palestra> getPalestrasDAO() {
        return new FileDAO<>("palestras.txt");
    }

    public static FileDAO<Evento> getEventosDAO() {
        return new FileDAO<>("eventos.txt");
    }
}
