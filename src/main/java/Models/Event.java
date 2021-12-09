package Models;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private Long id;
    private String eventName;
    private String dateEvent;
    private String description;
    private Modality modality;
    private List<User> organizers = new ArrayList<>();
    private List<Lecture> lectures = new ArrayList<>();

    public Event() {
    }

    public Event(String eventName, String description, String dateEvent) {
        this.eventName = eventName;
        this.description = description;
        this.dateEvent = dateEvent;
    }

    @Override
    public String toString() {
        return "Evento{" +
                " id=" + id +
                ", nomeEvento='" + eventName + '\'' +
                ", descricao='" + description + '\'' +
                ", modalidade=" + modality +
                ", dataEvento=" + dateEvent +
                ", organizadores=" + organizers +
                ", palestras=" + lectures +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Modality getModalidade() {
        return modality;
    }

    public void setModalidade(Modality modality) {
        this.modality = modality;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public List<User> getOrganizers() {
        return organizers;
    }

    public void setOrganizers(List<User> organizers) {
        this.organizers = organizers;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }
}
