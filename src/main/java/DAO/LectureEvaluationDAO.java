package DAO;

import Models.Lecture;
import Models.User;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LectureEvaluationDAO {
    private String tableName = "lecture_evaluation";
    private Connection connection = new ConnectionFactory().getConnection();

    public void createLectureEvaluationTable() throws SQLException {
        String sql = "CREATE SEQUENCE IF NOT EXISTS lectures_evaluation_id_seq ;";

        sql += "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "evaluation_id BIGINT PRIMARY KEY DEFAULT nextval('lectures_evaluation_id_seq'), " +
                "evaluation_value SMALLINT NOT NULL, " +
                "lecture_id BIGINT NOT NULL, " +
                "user_id BIGINT NOT NULL, " +
                "status BOOLEAN DEFAULT TRUE, " +
                "UNIQUE (lecture_id,user_id), " +
                "CONSTRAINT fk_lecture_evaluation " +
                    "FOREIGN KEY (lecture_id) " +
                    "REFERENCES lectures(lecture_id) " +
                    "ON DELETE CASCADE, " +
                "CONSTRAINT fk_user_evaluation " +
                    "FOREIGN KEY (user_id) " +
                    "REFERENCES users(user_id) " +
                    "ON DELEte CASCADE" +
                ");";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        statement.close();
    }
}

