package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Lecture {
    private Long id;
    private String name;
    private String description;
    private String startDate;
    private String duration;
    private Event event;
    private List<Language> language = new ArrayList<>();
    private List<Topic> topics = new ArrayList<>();
    private List<Review> evaluation = new ArrayList<>();
    private List<User> presenters = new ArrayList<>();
    private List<User> attendees = new ArrayList<>();
    private List<LectureQuestion> lectureQuestions = new ArrayList<>();
    private boolean status;

    public Lecture() {
    }

    public Lecture(String name, String description) {
        setName(name);
        setDescription(description);
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", duration='" + duration + '\'' +
                ", event=" + event +
                ", language=" + language +
                ", topics=" + topics +
                ", evaluation=" + evaluation +
                ", presenters=" + presenters +
                ", attendees=" + attendees +
                ", lectureQuestions=" + lectureQuestions +
                ", status=" + status +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase(Locale.ROOT);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.toUpperCase(Locale.ROOT);
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate.toUpperCase(Locale.ROOT);
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration.toUpperCase(Locale.ROOT);
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Language> getLanguage() {
        return language;
    }

    public void setLanguage(List<Language> language) {
        this.language = language;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Review> getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(List<Review> evaluation) {
        this.evaluation = evaluation;
    }

    public List<User> getPresenters() {
        return presenters;
    }

    public void setPresenters(List<User> presenters) {
        this.presenters = presenters;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }

    public List<LectureQuestion> getLectureQuestions() {
        return lectureQuestions;
    }

    public void setLectureQuestions(List<LectureQuestion> lectureQuestions) {
        this.lectureQuestions = lectureQuestions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
