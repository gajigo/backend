package Api.Models;

public class AttendanceRequest {
    public Long user_id;
    public Long lecture_id;

    @Override
    public String toString() {
        return "AttendanceRequest{" +
                "user_id=" + user_id +
                "lecture_id=" + lecture_id +
                '}';
    }
}
