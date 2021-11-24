package DAO;

import Models.Lecture;
import Models.Roles;
import Models.User;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserPoliceDAO {
    private String tableName = "user_lecture";
    private Connection connection = new ConnectionFactory().getConnection();

    public void createUserLectureTable(){

        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "user_id BIGINT, " +
                "lecture_id BIGINT, " +
                "police SMALLINT, " +
                "PRIMARY KEY (user_id,lecture_id,police), " +
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

    public void addUserPolice(User user, Lecture lecture, Roles police){
        if(user != null && lecture != null && police != null){
            String sql = "INSERT INTO " + tableName +
                    "(user_id,lecture_id,police)" +
                    "VALUES (?, ?, ?)";
            try{
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setLong(1,user.getUserId());
                statement.setLong(2,lecture.getId());
                statement.setInt(3,police.ordinal());
                statement.execute();

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }


}
