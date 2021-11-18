package DAO;

import Models.Evento;
import Models.Modalidade;
import Models.Palestra;
import Models.Usuario;
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
    }

    private String tableName = "eventos";
    private Connection connection = new ConnectionFactory().getConnection();

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
                    evento.setId(resultSet.getInt(1));
                }
                return evento;
            }
            catch (SQLException e){
                return null;
            }
        }
        return null;
    }
}
