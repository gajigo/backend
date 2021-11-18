package DAO;

import Models.CartaoVisita;
import Models.User;
import factory.ConnectionFactory;

import java.sql.*;

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
                "userId BIGINT PRIMARY KEY DEFAULT nextval('user_id_seq')," +
                "name TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "email VARCHAR(50) UNIQUE," +
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
}
