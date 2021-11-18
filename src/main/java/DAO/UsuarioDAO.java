package DAO;

import Models.CartaoVisita;
import Models.User;
import factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends FileDAO<User> {
    public UsuarioDAO() {
        super("usuarios.txt");
    }

    public UsuarioDAO(String filename) {
        super(filename);
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

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.execute();
            statement.close();
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

                statement.setString(1, user.getNome());
                statement.setString(2, user.getSenha());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getTelefone());

                statement.execute();

                ResultSet resultSet = statement.getGeneratedKeys();

                while (resultSet.next()) {
                    user.setUserId(resultSet.getInt(1));
                }
                return user;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
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
                user.setId(resultSet.getInt("user_id"));
                user.setNome(resultSet.getString("name"));
                user.setSenha(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setTelefone(resultSet.getString("phone"));

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
                user.setId(resultSet.getInt("user_id"));
                user.setNome(resultSet.getString("name"));
                user.setSenha(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setTelefone(resultSet.getString("phone"));
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
}
