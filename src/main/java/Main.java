import DAO.EventoDAO;
import DAO.LectureDAO;
import DAO.UserDAO;
import Views.MenuView;

public class Main {
    public static void initDatabase() {
        new UserDAO().createUserTable();
        new EventoDAO().createEventoTable();
        new LectureDAO().createLecturesTable();
    }

    public static void startApplication() {
        initDatabase();
        MenuView.start();
    }

    public static void main(String[] args) {
        startApplication();
    }
}
