package DAO;

import Models.Evento;
import Models.Modalidade;
//import Models.Usuario;
import Models.Roles;
import Models.User;
import factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO extends FileDAO<Evento> {
    public EventoDAO() {
        super("eventos.txt");
    }

    public EventoDAO(String filename) {
        super(filename);
        createEventoTable();
    }

    private String tableName = "eventos";
    private Connection connection = new ConnectionFactory().getConnection();
    private EventUserDAO eventUserDAO = new EventUserDAO();

    public void createEventoTable() {
        String sql = "CREATE SEQUENCE IF NOT EXISTS evento_id_seq;";

        sql += "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                "eventoId BIGINT PRIMARY KEY DEFAULT nextval('evento_id_seq')," +
                "nomeEvento TEXT NOT NULL," +
                "descricao TEXT NOT NULL," +
                "modalidade SMALLINT," +
                "dataEvento VARCHAR(10)" + ")";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.execute();
            statement.close();
        }   catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Evento createEvento(Evento evento) {
        if (evento != null) {
            String sql = "INSERT INTO " + tableName + " (" +
                    "nomeEvento, descricao, modalidade, dataEvento)" +
                    "VALUES (?, ?, ?, ?)";

            try {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, evento.getNomeEvento());
                statement.setString(2, evento.getDescricao());
                if (evento.getModalidade() != null) {
                    statement.setInt(3, evento.getModalidade().ordinal());
                    } else { statement.setNull(3, Types.NULL); }

                statement.setString(4, evento.getDataEvento());

                statement.execute();

                ResultSet resultSet = statement.getGeneratedKeys();

                while (resultSet.next()) {
                    evento.setId(resultSet.getLong(1));
                }
                return evento;
            }
            catch (SQLException e){
                return null;
            }
        }
        return null;
    }

    public List<Evento> listEventos() {
        String sql = "SELECT * FROM " + tableName;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<Evento> listEventos = new ArrayList<>();
            while (resultSet.next()) {
                Evento novoEvento = new Evento();
                novoEvento.setId(resultSet.getLong("eventoid"));
                novoEvento.setNomeEvento(resultSet.getString("nomeevento"));
                novoEvento.setDescricao(resultSet.getString("descricao"));
                novoEvento.setModalidade(Modalidade.values()[resultSet.getInt("modalidade")]);
                novoEvento.setDataEvento(resultSet.getString("dataevento"));

                listEventos.add(novoEvento);
            }

            return listEventos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editEvento(Evento evento) {
        String sql = "UPDATE " + tableName + " SET nomeEvento = ?, descricao = ?, modalidade = 2, dataEvento = ?" +
                " WHERE eventoId = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, evento.getNomeEvento());
            statement.setString(2, evento.getDescricao());
//            statement.setInt(3, evento.getModalidade().ordinal());
            statement.setString(3, evento.getDataEvento());

            statement.setLong(4, evento.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Evento getById(Long id) {
        String sql = "SELECT * FROM eventos WHERE eventoid = ?";

        Evento evento = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                evento = new Evento();
                evento.setId(resultSet.getLong("eventoid"));
                evento.setNomeEvento(resultSet.getString("nomeevento"));
                evento.setDescricao(resultSet.getString("descricao"));
//            evento.setModalidade(resultSet.getInt("modalidade"));
                evento.setDataEvento(resultSet.getString("dataevento"));
            }
        }   catch (SQLException e){
            throw new RuntimeException(e);
        }
        return evento;


    }

    public boolean deleteById(Long eventoId) {
        if (eventoId != null) {
            String sql = "DELETE FROM " + tableName + " WHERE eventoid = ?";

            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1, eventoId);

                statement.execute();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public void addEventOrganizer(User user, Evento evento){
        eventUserDAO.addUserRole(user,evento, Roles.ORGANIZADOR);
    }
}
