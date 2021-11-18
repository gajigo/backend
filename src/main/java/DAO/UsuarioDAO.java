package DAO;

import Models.CartaoVisita;
import Models.Usuario;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO extends FileDAO<Usuario> {
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
                "nome TEXT NOT NULL," +
                "senha TEXT NOT NULL," +
                "email VARCHAR(50) UNIQUE," +
                "telefone VARCHAR(14));";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
