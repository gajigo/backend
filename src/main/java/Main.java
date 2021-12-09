import DAO.*;
import Views.LoginPanelView;
import Views.MenuView;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void initDatabase() {
        try {
            new EventDAO().createEventoTable();
            new UserDAO().createUserTable();
            new LectureDAO().createLecturesTable();
            new LectureEvaluationDAO().createLectureEvaluationTable();
            new EventUserDAO().createEventUserTable();
        }catch (SQLException e){
            System.out.println("Falha ao criar tabelas");
            throw new RuntimeException(e);
        }
    }

    public static void startApplication() {
        initDatabase();
        new LoginPanelView();
    }

    public static void main(String[] args) {
        startApplication();
    }
}
