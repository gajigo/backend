package DAO;

import Models.Event;
import Models.Modality;
//import Models.Usuario;
import Models.Roles;
import Models.User;
import factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO extends FileDAO<Event> {
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

    public Event createEvento(Event event) {
        if (event != null) {
            String sql = "INSERT INTO " + tableName + " (" +
                    "nomeEvento, descricao, modalidade, dataEvento)" +
                    "VALUES (?, ?, ?, ?)";

            try {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, event.getEventName());
                statement.setString(2, event.getDescription());
                if (event.getModalidade() != null) {
                    statement.setInt(3, event.getModalidade().ordinal());
                    } else { statement.setNull(3, Types.NULL); }

                statement.setString(4, event.getDateEvent());

                statement.execute();

                ResultSet resultSet = statement.getGeneratedKeys();

                while (resultSet.next()) {
                    event.setId(resultSet.getLong(1));
                }
                return event;
            }
            catch (SQLException e){
                return null;
            }
        }
        return null;
    }

    public List<Event> listEventos() {
        String sql = "SELECT * FROM " + tableName;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<Event> listEvents = new ArrayList<>();
            while (resultSet.next()) {
                Event novoEvent = new Event();
                novoEvent.setId(resultSet.getLong("eventoid"));
                novoEvent.setEventName(resultSet.getString("nomeevento"));
                novoEvent.setDescription(resultSet.getString("descricao"));
                novoEvent.setModalidade(Modality.values()[resultSet.getInt("modalidade")]);
                novoEvent.setDateEvent(resultSet.getString("dataevento"));

                listEvents.add(novoEvent);
            }

            return listEvents;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editEvento(Event event) {
        String sql = "UPDATE " + tableName + " SET nomeEvento = ?, descricao = ?, modalidade = 2, dataEvento = ?" +
                " WHERE eventoId = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, event.getEventName());
            statement.setString(2, event.getDescription());
//            statement.setInt(3, evento.getModalidade().ordinal());
            statement.setString(3, event.getDateEvent());

            statement.setLong(4, event.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Event getById(Long id) {
        String sql = "SELECT * FROM eventos WHERE eventoid = ?";

        Event event = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getLong("eventoid"));
                event.setEventName(resultSet.getString("nomeevento"));
                event.setDescription(resultSet.getString("descricao"));
//            evento.setModalidade(resultSet.getInt("modalidade"));
                event.setDateEvent(resultSet.getString("dataevento"));
            }
        }   catch (SQLException e){
            throw new RuntimeException(e);
        }
        return event;


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

    public void addEventOrganizer(User user, Event event){
        eventUserDAO.addUserRole(user, event, Roles.ORGANIZADOR);
    }
}
