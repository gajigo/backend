package DAO;

import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventPromoterDAO {
    private String tableName = "user_lecture";
    private Connection connection = new ConnectionFactory().getConnection();

    public void createUserLectureTable(){

        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "user_id BIGINT, " +
                "lecture_id BIGINT, " +
                "PRIMARY KEY (user_id,lecture_id), " +
                "CONSTRAINT fk_user_lecture_user_id " +
                    "FOREIGN KEY (user_id) " +
                    "REFERENCES users(user_id), " +
                "CONSTRAINT fk_user_lecture_lecture_id " +
                    "FOREIGN KEY (lecture_id) " +
                    "REFERENCES lectures(lecture_id) " +
                ");";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.execute();
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }




}
