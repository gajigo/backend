package DAO;

import Models.Event;
import Models.Modality;
//import Models.Usuario;
import Models.Roles;
import Models.User;
import Factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    public EventDAO() {
    }

    public EventDAO(String filename) {
        createEventoTable();
    }

    private String tableName = "events";
    private Connection connection = new ConnectionFactory().getConnection();
    private EventUserDAO eventUserDAO = new EventUserDAO();

    public void createEventoTable() {
        String sql = "CREATE SEQUENCE IF NOT EXISTS evento_id_seq;";

        sql += "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                "event_id BIGINT PRIMARY KEY DEFAULT nextval('evento_id_seq')," +
                "event_name TEXT NOT NULL," +
                "description TEXT NOT NULL," +
                "modality SMALLINT," +
                "event_date VARCHAR(10)" + ")";

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
                    "event_name, description, modality, event_date)" +
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

    public List<Event> listEventos() throws SQLException{
        String sql = "SELECT * FROM " + tableName;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Event> listEvents = new ArrayList<>();
        while (resultSet.next()) {
            Event novoEvent = new Event();
            novoEvent.setId(resultSet.getLong("event_id"));
            novoEvent.setEventName(resultSet.getString("event_name"));
            novoEvent.setDescription(resultSet.getString("description"));
            novoEvent.setModalidade(Modality.values()[resultSet.getInt("modality")]);
            novoEvent.setDateEvent(resultSet.getString("event_date"));

            listEvents.add(novoEvent);
        }

        return listEvents;
    }

    public void editEvent(Event event) throws SQLException, NullPointerException {
        String sql = "UPDATE " + tableName + " SET event_name = ?, description = ?, modality = 2, event_date = ?" +
                " WHERE event_id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, event.getEventName());
        statement.setString(2, event.getDescription());
//            statement.setInt(3, evento.getModalidade().ordinal());
        statement.setString(3, event.getDateEvent());

        statement.setLong(4, event.getId());

        statement.executeUpdate();
    }

    public Event getById(Long id) throws SQLException {
        String sql = "SELECT * FROM events WHERE event_id = ?";

        Event event = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            event = new Event();
            event.setId(resultSet.getLong("event_id"));
            event.setEventName(resultSet.getString("event_name"));
            event.setDescription(resultSet.getString("description"));
//            evento.setModalidade(resultSet.getInt("modality"));
            event.setDateEvent(resultSet.getString("event_date"));
        }
        return event;
    }

    public boolean deleteById(Long eventoId) throws SQLException, NullPointerException{
        if (eventoId != null) {
            String sql = "DELETE FROM " + tableName + " WHERE event_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, eventoId);

            statement.execute();
            return true;
        }
        return false;
    }

    public void addEventOrganizer(User user, Event event) throws SQLException{
        eventUserDAO.addUserRole(user, event, Roles.ORGANIZADOR);
    }

    public void removeEventOrganizer(User user, Event event) throws SQLException, NullPointerException{
        eventUserDAO.removeUserRole(user, event, Roles.ORGANIZADOR);
    }
}
