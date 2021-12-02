package DAO;

import Models.*;
import factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LectureDAO extends FileDAO<Lecture> {
    public LectureDAO() {
        super("lectures.txt");
        createLecturesTable();
    }
    public LectureDAO(String filename) {
        super(filename);
    }

    private String tableName = "lectures";
    private Connection connection = new ConnectionFactory().getConnection();
    private UserPoliceDAO userPoliceDAO = new UserPoliceDAO();

    public void createLecturesTable(){
        String sql = "CREATE SEQUENCE IF NOT EXISTS lectures_id_seq;";

        sql += "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                "lecture_id BIGINT PRIMARY KEY DEFAULT nextval('lectures_id_seq')," +
                "event_id BIGINT NOT NULL, " +
                "name TEXT, " +
                "description TEXT," +
                "date VARCHAR(10) ," +
                "duration VARCHAR(10)," +
                "status BOOLEAN DEFAULT TRUE, " +
                "CONSTRAINT fk_lecture_event_id " +
                    "FOREIGN KEY (event_id)" +
                    "REFERENCES eventos(eventoId)" +
                ");";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.execute();
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        UserPoliceDAO userPoliceDAO = new UserPoliceDAO();
        userPoliceDAO.createUserLectureTable();
    }

    public Lecture createLectures(Lecture lecture) throws SQLException, NullPointerException {
        if (lecture != null){
            String sql = "INSERT INTO " + tableName +
                    "(name, description, date, duration, event_id)" +
                    "VALUES (?, ?, ?, ?, ?)";

            try {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, lecture.getName());
                statement.setString(2, lecture.getDescription());
                statement.setString(3, lecture.getInitialDate());
                statement.setString(4, lecture.getDuration());
                statement.setLong(5, lecture.getEvent().getId());
                statement.execute();

                ResultSet resultSet = statement.getGeneratedKeys();

                while(resultSet.next()){
                    lecture.setId(resultSet.getInt("lecture_id"));
                    lecture.setStatus(resultSet.getBoolean("status"));
                }
                return lecture;
            }catch (SQLException e){
                return null;
            }
        }
        return null;
    }

    public List<Lecture> listLecture(){
        String sql = "SELECT * FROM " + tableName ;

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<Lecture> listLectures = new ArrayList<>();
            Lecture lecture;

            while(resultSet.next()){
                lecture = new Lecture();
                lecture.setId(resultSet.getInt("lecture_id"));
                lecture.setName(resultSet.getString("name"));
                lecture.setDescription(resultSet.getString("description"));
                lecture.setInitialDate(resultSet.getString("date"));
                lecture.setDuration(resultSet.getString("duration"));
                lecture.setStatus(resultSet.getBoolean("status"));
                lecture.setPresenter(userPoliceDAO.getUserPolice(lecture,Roles.PALESTRANTE));
                lecture.setAttendees(userPoliceDAO.getUserPolice(lecture,Roles.CLIENTE));

                listLectures.add(lecture);
            }
            return listLectures;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void editSeminar(Lecture lecture){
        String sql = "UPDATE " + tableName + " SET name = ?, description = ?, date = ?, duration = ? WHERE lecture_id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(5, lecture.getId());
            statement.setString(1, lecture.getName());
            statement.setString(2, lecture.getDescription());
            statement.setString(3, lecture.getInitialDate());
            statement.setString(4, lecture.getDuration());

            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void deleteLecture(Lecture lecture){
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, lecture.getId());
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void addLectureAttendee(Lecture lecture, User user){
        userPoliceDAO.addUserPolice(user,lecture,Roles.CLIENTE);
    }
    public void removeLectureAttendee(Lecture lecture, User user){
        userPoliceDAO.removeUserPolice(lecture,user,Roles.CLIENTE);
    }

    public List<User> getAttendees(Lecture lecture){
        return userPoliceDAO.getUserPolice(lecture,Roles.CLIENTE);
    }

    public void addLecturePresenter(Lecture lecture, User presenter){
        userPoliceDAO.addUserPolice(presenter,lecture,Roles.PALESTRANTE);
    }
    public void removeLecturePresenter(Lecture lecture, User presenter){
        userPoliceDAO.removeUserPolice(lecture,presenter,Roles.PALESTRANTE);
    }
}
