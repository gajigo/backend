package Factory;

import DAO.FileDAO;
import Models.Evento;
import Models.Palestra;
import Models.Usuario;

public class DAOFactory {
    public FileDAO<Usuario> getUsuariosDAO() {
       return new FileDAO<>("usuarios.txt");
    }

    public FileDAO<Palestra> getPalestrasDAO() {
        return new FileDAO<>("palestras.txt");
    }

    public FileDAO<Evento> getEventosDAO() {
        return new FileDAO<>("eventos.txt");
    }
}
