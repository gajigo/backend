package Models;


public class LectureQuestion {
    private Long id;
    private String question;
    private User user;

    public LectureQuestion() {
    }

    public LectureQuestion(String question, User user) {
        this.question = question;
        this.user = user;
    }

    @Override
    public String toString() {
        return "LectureQuestion{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
