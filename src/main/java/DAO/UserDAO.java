package DAO;

import Models.Roles;
import Models.User;
import factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private LoggerDAO logger = new LoggerDAO("usuario.txt");

    public UserDAO() {

    }

    public UserDAO(String filename) {
        createUserTable();
    }

    private String tableName = "users";
    private Connection connection = new ConnectionFactory().getConnection();

    public void createUserTable() {
        String sql = "CREATE SEQUENCE IF NOT EXISTS user_id_seq;";

        sql += "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                "user_id BIGINT PRIMARY KEY DEFAULT nextval('user_id_seq')," +
                "name TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "email VARCHAR(50) UNIQUE NOT NULL," +
                "phone VARCHAR(14));";

        sql += "CREATE TABLE IF NOT EXISTS roles (" +
                "user_id BIGINT," +
                "role SMALLINT," +
                "PRIMARY KEY (user_id, role)," +
                "CONSTRAINT fk_user_id " +
                    "FOREIGN KEY (user_id) " +
                    "REFERENCES users(user_id) " +
                    "ON DELETE CASCADE);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertUpdateRoles(Long userId, List<Roles> roles) {
        String sql = "DELETE FROM roles WHERE user_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);

            statement.execute();

            for (Roles role : roles) {
                sql = "INSERT INTO roles " +
                        "(user_id, role)" +
                        "VALUES (?, ?)";

                statement = connection.prepareStatement(sql);

                statement.setLong(1, userId);
                if (role != null) {
                    statement.setInt(2, role.ordinal());
                } else {
                    statement.setNull(2, Types.NULL);
                }

                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User createUser(User user) {
        if (user != null) {
            String sql = "INSERT INTO " + tableName +
                    "(name, password, email, phone)" +
                    "VALUES (?, ?, ?, ?)";

            try {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, user.getName());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPhone());

                statement.execute();

                ResultSet resultSet = statement.getGeneratedKeys();

                while (resultSet.next()) {
                    user.setUserId(resultSet.getLong(1));
                }

                insertUpdateRoles(user.getUserId(), user.getRoles());

                logger.logOperation("INSERÇÃO", user.toString());

                return user;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public User loginUser(User user) {
        User authenticatedUser = null;
        String sql = "SELECT * FROM " + tableName + " " +
                "WHERE email = ? " +
                "AND password = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                authenticatedUser = new User();
                authenticatedUser.setId(resultSet.getLong("user_id"));
                authenticatedUser.setName(resultSet.getString("name"));
                authenticatedUser.setPassword(resultSet.getString("password"));
                authenticatedUser.setEmail(resultSet.getString("email"));
                authenticatedUser.setPhone(resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authenticatedUser;
    }

    public List<User> listUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        try {
            User user;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public User getUserById(Long userId) {
        User user = null;
        String sql = "SELECT * FROM " + tableName + " WHERE user_id = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean deleteUserById(Long userId) {
        if (userId != null) {
            String sql = "DELETE FROM " + tableName + " WHERE user_id = ?";

            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1, userId);

                statement.execute();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public User updateUser(User user) {
        if (user != null) {
            String sql = "UPDATE " + tableName + " SET " +
                    "name = ?, password = ?, email = ?, phone = ? " +
                    "WHERE user_id = ?";

            try {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, user.getName());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPhone());
                statement.setLong(5, user.getId());

                statement.execute();

                insertUpdateRoles(user.getUserId(), user.getRoles());

                return user;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
