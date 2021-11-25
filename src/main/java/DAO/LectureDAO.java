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

    public Lecture createLectures(Lecture lecture){
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
                statement.execute();

                ResultSet resultSet = statement.getGeneratedKeys();

                while(resultSet.next()){
                    lecture.setId(resultSet.getInt(1));
                    lecture.setStatus(resultSet.getBoolean(6));
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
        UserPoliceDAO userPoliceDAO = new UserPoliceDAO();
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
}
