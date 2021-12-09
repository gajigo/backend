package DAO;

import Models.Evento;
import Models.Modalidade;
//import Models.Usuario;
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

    private String tableName = "events";
    private Connection connection = new ConnectionFactory().getConnection();

    public void createEventoTable() {
        String sql = "CREATE SEQUENCE IF NOT EXISTS evento_id_seq;";

        sql += "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                "evento_id BIGINT PRIMARY KEY DEFAULT nextval('evento_id_seq')," +
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

    public Evento createEvento(Evento evento) {
        if (evento != null) {
            String sql = "INSERT INTO " + tableName + " (" +
                    "event_name, description, modality, event_date)" +
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
                novoEvento.setId(resultSet.getLong("event_id"));
                novoEvento.setNomeEvento(resultSet.getString("event_name"));
                novoEvento.setDescricao(resultSet.getString("description"));
                novoEvento.setModalidade(Modalidade.values()[resultSet.getInt("modality")]);
                novoEvento.setDataEvento(resultSet.getString("event_date"));

                listEventos.add(novoEvento);
            }

            return listEventos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editEvento(Evento evento) {
        String sql = "UPDATE " + tableName + " SET event_name = ?, description = ?, modality = 2, event_date = ?" +
                " WHERE event_id = ?";

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
        String sql = "SELECT * FROM events WHERE event_id = ?";

        Evento evento = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                evento = new Evento();
                evento.setId(resultSet.getLong("event_id"));
                evento.setNomeEvento(resultSet.getString("event_name"));
                evento.setDescricao(resultSet.getString("description"));
//            evento.setModalidade(resultSet.getInt("modality"));
                evento.setDataEvento(resultSet.getString("event_date"));
            }
        }   catch (SQLException e){
            throw new RuntimeException(e);
        }
        return evento;


    }

    public boolean deleteById(Long eventoId) {
        if (eventoId != null) {
            String sql = "DELETE FROM " + tableName + " WHERE event_id = ?";

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
}
