package DAO;

import Models.*;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String sql = "CREATE SEQUENCE id_seq;";

        sql += "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                "id BIGINT PRIMARY KEY DEFAULT nextval('id_seq')," +
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

}
