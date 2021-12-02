import DAO.EventoDAO;
import DAO.LectureDAO;
import DAO.LectureEvaluationDAO;
import DAO.UserDAO;
import Views.MenuView;

import java.sql.SQLException;

public class Main {
    public static void initDatabase() {
        try {
            new UserDAO().createUserTable();
            new EventoDAO().createEventoTable();
            new LectureDAO().createLecturesTable();
            new LectureEvaluationDAO().createLectureEvaluationTable();
        }catch (SQLException e){
            System.out.println("Falha ao criar tabelas");
            throw new RuntimeException(e);
        }
    }

    public static void startApplication() {
        initDatabase();
        MenuView.start();
    }

    public static void main(String[] args) {
        startApplication();
    }
}
