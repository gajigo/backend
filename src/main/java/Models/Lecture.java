package Models;

import DAO.DAOUser;

import java.util.ArrayList;
import java.util.List;

public class Lecture implements DAOUser {

    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private long id;
    private String initialDate;
    private String duration;
    private List<Idioma> language = new ArrayList<>();
    private List<Assunto> topics = new ArrayList<>();
    private List<Avaliacao> evaluation = new ArrayList<>();
    private List<User> presenter = new ArrayList<>();
    private List<User> attendees = new ArrayList<>();
    private List<DuvidaPalestra> lectureQuestions = new ArrayList<>();
    private boolean status;

    public Lecture() {
    }

    public Lecture(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Palestra{" +
                "nome='" + name + '\'' +
                ", descricao='" + description + '\'' +
                ", id=" + id +
                ", data de Inicio=" + initialDate +
                ", duracao=" + duration +
                ", idioma=" + language +
                ", assuntos=" + topics +
                ", avaliacoes=" + evaluation +
                ", palestrantes=" + presenter +
                ", participantes=" + attendees +
                ", duvidas=" + lectureQuestions +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Idioma> getLanguage() {
        return language;
    }

    public void setLanguage(List<Idioma> language) {
        this.language = language;
    }

    public List<Assunto> getTopics() {
        return topics;
    }

    public void setTopics(List<Assunto> topics) {
        this.topics = topics;
    }

    public List<Avaliacao> getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(List<Avaliacao> evaluation) {
        this.evaluation = evaluation;
    }

    public List<User> getPresenter() {
        return presenter;
    }

    public void setPresenter(List<User> presenter) {
        this.presenter = presenter;
    }

    public List<DuvidaPalestra> getLectureQuestions() {
        return lectureQuestions;
    }

    public void setLectureQuestions(List<DuvidaPalestra> lectureQuestions) {
        this.lectureQuestions = lectureQuestions;
    }

    public void addTopic(Assunto assunto) {
        this.topics.add(assunto);
    }

    public void removeTopic(Assunto assunto) {
        this.topics.remove(assunto);
    }

    public void addEvaluation(Avaliacao avaliacao) {
        this.evaluation.add(avaliacao);
    }

    public void removeEvaluation(Avaliacao avaliacao) {
        this.evaluation.remove(avaliacao);
    }

    public void addPresenter(User presenter) {
        this.presenter.add(presenter);
    }

    public void removePresenter(User presenter) {
        this.presenter.remove(presenter);
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }

    public void addLectureQuestion(DuvidaPalestra lectureQuestion) {
        this.lectureQuestions.add(lectureQuestion);
    }

    public void removeLectureQuestion(DuvidaPalestra lectureQuestion) {
        this.lectureQuestions.remove(lectureQuestion);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
