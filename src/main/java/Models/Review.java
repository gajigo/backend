package Models;


public class Review {
    private Long id;
    private float rating;
    private Lecture lecture;
    private User reviewer;
    private boolean active;

    public Review(float rating, Lecture lecture, User reviewer) {
        this.rating = rating;
        this.lecture = lecture;
        this.reviewer = reviewer;
        this.active = true;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", lecture=" + lecture +
                ", reviewer=" + reviewer +
                ", active=" + active +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
