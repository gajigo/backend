package DAO;

import Models.*;
import factory.ConnectionFactory;

import java.sql.*;

public class PalestraDAO  extends FileDAO<Palestra> {
    public PalestraDAO() {
        super("palestras.txt");
    }
    public PalestraDAO(String filename) {
        super(filename);
    }

    private String tableName = "seminars";
    private Connection connection = new ConnectionFactory().getConnection();

    public void createSeminarsTable(){
        String sql = "CREATE SEQUENCE IF NOT EXISTS seminars_id_seq;";

        sql += "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                "id BIGINT PRIMARY KEY DEFAULT nextval('seminars_id_seq')," +
                "nome TEXT NOT NULL," +
                "descricao TEXT," +
                "data VARCHAR(10) NOT NULL," +
                "duracao VARCHAR(10)," +
                "status BOOLEAN DEFAULT TRUE );";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.execute();
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Palestra createSeminars(Palestra seminar){
        if (seminar != null){
            String sql = "INSERT INTO " + tableName +
                    "(nome, descricao, data, duracao)" +
                    "VALUES (?, ?, ?, ?)";

            try {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, seminar.getNome());
                statement.setString(2, seminar.getDescricao());
                statement.setString(3, seminar.getDataInicio());
                statement.setString(4, seminar.getDuracao());

                statement.execute();

                ResultSet resultSet = statement.getGeneratedKeys();

                while(resultSet.next()){
                    seminar.setId(resultSet.getInt(1));
                    seminar.setStatus(resultSet.getBoolean(6));
                }
                return seminar;
            }catch (SQLException e){
                return null;
            }
        }
        return null;
    }
}
