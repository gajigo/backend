package Api.Services;

import Api.Models.AttendanceRequest;
import DAO.LectureDAO;
import Models.Lecture;
import Models.User;

import java.sql.SQLException;

public class LectureService {
    public void addLectureAttendee(AttendanceRequest request) throws SQLException {
        LectureDAO lectureDAO = new LectureDAO();
        User user = new User();
        user.setUserId(request.user_id);
        Lecture lecture = new Lecture();
        lecture.setId(request.lecture_id);

        lectureDAO.addLectureAttendee(lecture, user);
    }
}
