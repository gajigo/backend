package Models;


import java.util.Locale;

public class LectureQuestion {
    private Long id;
    private User user;
    private String question;

    public LectureQuestion() {
    }

    public LectureQuestion(User user, String question) {
        setUser(user);
        setQuestion(question);
    }

    @Override
    public String toString() {
        return "LectureQuestion{" +
                "id=" + id +
                ", user=" + user +
                ", question='" + question + '\'' +
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
        this.question = question.toUpperCase(Locale.ROOT);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
