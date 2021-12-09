package DAO;

import Models.*;
import factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LectureDAO {
    public LectureDAO() {
        createLecturesTable();
    }
    public LectureDAO(String filename) {

    }

    private String tableName = "lectures";
    private Connection connection = new ConnectionFactory().getConnection();
    private LectureUserDAO lectureUserDAO = new LectureUserDAO();
    private LectureEvaluationDAO lectureEvaluationDAO = new LectureEvaluationDAO();

    public void createLecturesTable(){
        String sql = "CREATE SEQUENCE IF NOT EXISTS lectures_id_seq;";

        sql += "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                "lecture_id BIGINT PRIMARY KEY DEFAULT nextval('lectures_id_seq')," +
                "event_id BIGINT NOT NULL, " +
                "name TEXT, " +
                "description TEXT," +
                "lecture_date VARCHAR(10) ," +
                "duration VARCHAR(10)," +
                "status BOOLEAN DEFAULT TRUE, " +
                "CONSTRAINT fk_lecture_event_id " +
                    "FOREIGN KEY (event_id)" +
                    "REFERENCES events(event_id)" +
                    "ON DELETE CASCADE" +
                ");";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.execute();
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        LectureUserDAO lectureUserDAO = new LectureUserDAO();
        lectureUserDAO.createUserLectureTable();
    }

    public Lecture createLectures(Lecture lecture) {
        if (lecture != null){
            String sql = "INSERT INTO " + tableName +
                    "(name, description, date, duration, event_id)" +
                    "VALUES (?, ?, ?, ?, ?)";

            try {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, lecture.getName());
                statement.setString(2, lecture.getDescription());
                statement.setString(3, lecture.getStartDate());
                statement.setString(4, lecture.getDuration());
                statement.setLong(5, lecture.getEvent().getId());
                statement.execute();

                ResultSet resultSet = statement.getGeneratedKeys();

                while(resultSet.next()){
                    lecture.setId(resultSet.getLong("lecture_id"));
                    lecture.setStatus(resultSet.getBoolean("status"));
                }
                return lecture;
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public List<Lecture> listLecture() throws SQLException{
        String sql = "SELECT * FROM " + tableName ;


        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Lecture> listLectures = new ArrayList<>();
        Lecture lecture;

        while(resultSet.next()){
            lecture = new Lecture();
            lecture.setId(resultSet.getLong("lecture_id"));
            lecture.setName(resultSet.getString("name"));
            lecture.setDescription(resultSet.getString("description"));
            lecture.setStartDate(resultSet.getString("date"));
            lecture.setDuration(resultSet.getString("duration"));
            lecture.setStatus(resultSet.getBoolean("status"));
            lecture.setPresenters(lectureUserDAO.getUserPolice(lecture,Roles.PALESTRANTE));
            lecture.setAttendees(lectureUserDAO.getUserPolice(lecture,Roles.CLIENTE));

            listLectures.add(lecture);
        }
        return listLectures;

    }

    public void editSeminar(Lecture lecture) throws SQLException, NullPointerException{
        String sql = "UPDATE " + tableName + " SET name = ?, description = ?, date = ?, duration = ? WHERE lecture_id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(5, lecture.getId());
        statement.setString(1, lecture.getName());
        statement.setString(2, lecture.getDescription());
        statement.setString(3, lecture.getStartDate());
        statement.setString(4, lecture.getDuration());

        statement.executeUpdate();

    }

    public void deleteLecture(Lecture lecture) throws SQLException, NullPointerException{
        String sql = "DELETE FROM " + tableName + " WHERE lecture_id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, lecture.getId());
        statement.execute();

    }

    public void addLectureAttendee(Lecture lecture, User user) throws SQLException{
        lectureUserDAO.addUserPolice(user,lecture,Roles.CLIENTE);
    }

    public void removeLectureAttendee(Lecture lecture, User user) throws SQLException, NullPointerException{
        lectureUserDAO.removeUserPolice(lecture,user,Roles.CLIENTE);
    }

    public List<User> getAttendees(Lecture lecture) throws SQLException{
        return lectureUserDAO.getUserPolice(lecture,Roles.CLIENTE);
    }

    public void addLecturePresenter(Lecture lecture, User presenter) throws SQLException{
        lectureUserDAO.addUserPolice(presenter,lecture,Roles.PALESTRANTE);
    }

    public void removeLecturePresenter(Lecture lecture, User presenter) throws SQLException, NullPointerException{
        lectureUserDAO.removeUserPolice(lecture,presenter,Roles.PALESTRANTE);
    }

    public void evaluateLecture(Lecture lecture, Long user_id, int value)throws SQLException{
        UserDAO user = new UserDAO();
        lectureEvaluationDAO.addLectureEvaluation(user.getUserById(user_id), lecture, value);
    }

    public void editEvaluation(Lecture lecture, Long user_id, int value) throws SQLException, NullPointerException {
        UserDAO user = new UserDAO();
        lectureEvaluationDAO.editLectureEvaluation(user.getUserById(user_id), lecture, value);
    }

    public void removeEvaluation(Lecture lecture, Long user_id) throws SQLException, NullPointerException{
        UserDAO user = new UserDAO();
        lectureEvaluationDAO.removeLectureEvaluation(user.getUserById(user_id),lecture);
    }
    
    public float averageLectureEvaluation(Lecture lecture) throws SQLException{
        return lectureEvaluationDAO.averageLectureEvaluation(lecture);
    }
}
