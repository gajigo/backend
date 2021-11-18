package DAO;

import Models.*;
import factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                "nome TEXT ," +
                "descricao TEXT," +
                "data VARCHAR(10) ," +
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

    public List<Palestra> listSeminars(){
        String sql = "SELECT * FROM " + tableName ;

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<Palestra> listSeminars = new ArrayList<>();
            Palestra seminar ;

            while(resultSet.next()){
                seminar = new Palestra();
                seminar.setId(resultSet.getInt(1));
                seminar.setNome(resultSet.getString(2));
                seminar.setDescricao(resultSet.getString(3));
                seminar.setDataInicio(resultSet.getString(4));
                seminar.setDuracao(resultSet.getString(5));
                seminar.setStatus(resultSet.getBoolean(6));

                listSeminars.add(seminar);
            }
            return listSeminars;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void editSeminar(Palestra seminar){
        String sql = "UPDATE " + tableName + " SET nome = ?, descricao = ?, data = ?, duracao = ? WHERE id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(5, seminar.getId());
            statement.setString(1,seminar.getNome());
            statement.setString(2,seminar.getDescricao());
            statement.setString(3,seminar.getDataInicio());
            statement.setString(4,seminar.getDuracao());

            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void deleteSeminar(Palestra seminar){
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, seminar.getId());
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
