package DAO;

import Models.Event;
import Models.Roles;
import Models.User;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventUserDAO {
        private String tableName = "event_user";
        private Connection connection = new ConnectionFactory().getConnection();

        public void createEventUserTable(){
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                    "user_id BIGINT, " +
                    "event_id BIGINT, " +
                    "role SMALLINT, " +
                    "PRIMARY KEY (user_id, event_id, role)," +
                    "CONSTRAINT fk_user_event_user_id " +
                        "FOREIGN KEY (user_id)" +
                        "REFERENCES users(user_id), " +
                    "CONSTRAINT fk_user_event_event_id " +
                        "FOREIGN KEY (event_id) " +
                        "REFERENCES eventos(eventoId) " +
                    ");";
            try{
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.execute();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    public void addUserRole(User user, Event event, Roles roles){

            if(user != null && event != null && roles != null){
                String sql = "INSERT INTO " + tableName +
                        "(user_id, event_id, role)" +
                        "VALUES (?, ?, ?)";

                try{
                    PreparedStatement statement = connection.prepareStatement(sql);

                    statement.setLong(1, user.getUserId());
                    statement.setLong(2, event.getId());
                    statement.setLong(3, roles.ordinal());
                    statement.execute();
                } catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }

    }

    public List<User> getUserRole(Event event, Roles roles){
            String sql = "SELECT * FROM users " +
                    "LEFT JOIN " + tableName +
                    " ON users.user_id = event_user.user_id " +
                    "WHERE event_id = ? " +
                    "AND role = ?";

            try{
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1, event.getId());
                statement.setInt(2, roles.ordinal());

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
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
    }

    public void removeUserRole (User user, Event event, Roles roles){
            String sql = "DELETE FROM " + tableName + " WHERE event_id = ? AND user_id = ? AND role = ?";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1, event.getId());
                statement.setLong(2, user.getUserId());
                statement.setInt(3, roles.ordinal());
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}



/*
* responsavel por gerenciar os organizadores e participantes do evento
* */