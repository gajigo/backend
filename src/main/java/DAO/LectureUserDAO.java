package DAO;

import Models.Idioma;
import Models.Lecture;
import Models.Roles;
import Models.User;
import factory.ConnectionFactory;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LectureUserDAO {
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

    public List<User>getUserPolice(Lecture lecture, Roles police){
        String sql = "SELECT * FROM  users " +
                "LEFT JOIN " + tableName +
                " USING (user_id) " +
                "WHERE lecture_id = ? " +
                "AND police = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,lecture.getId());
            statement.setInt(2, police.ordinal());

            ResultSet resultSet = statement.executeQuery();
            List<User> usersList = new ArrayList<>();
            User user;

            while (resultSet.next()){
                user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));

                usersList.add(user);
            }
            return usersList;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void removeUserPolice (Lecture lecture, User user, Roles police){
        String sql = "DELETE FROM " + tableName + " WHERE lecture_id = ? AND user_id = ? AND police = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, lecture.getId());
            statement.setLong(2, user.getUserId());
            statement.setInt(3, police.ordinal());
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
