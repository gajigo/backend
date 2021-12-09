package Controllers;

import DAO.LectureDAO;
import Models.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LectureController {
    private LectureDAO dao = new LectureDAO();

    public LectureController() {
    }

    public List<Lecture> getModels(){
        return dao.listLecture();
    }

    public Lecture createLecture(String name, String description, String date, String duration, String language, long event) throws SQLException {
        Lecture newLecture = new Lecture();
        Language newLanguage = new Language();
        EventoController newEvent = new EventoController();

        newLecture.setName(name);
        newLecture.setDescription(description);
        newLecture.setStartDate(date);
        newLecture.setDuration(duration);
        newLanguage.setName(language);
        newLecture.getLanguage().add(newLanguage);
        newLecture.setEvent(newEvent.getById(event));

        return dao.createLectures(newLecture);
    }

    public List<String> listLectureNames() {
        List<String> listString = new ArrayList<>();
        List<Lecture> lectures = getModels();

        for (Lecture lecture : lectures) {
            listString.add(lecture.getName());
        }

        return listString;

    }

    public String lectureIntString(List <String> lectureName, int selected){
        return lectureName.get(selected);
    }

    public Lecture chosenLecture(String lectureName){
        List<Lecture> lectures = getModels();

        for (Lecture lecture : lectures) {
            if (lecture.getName().compareTo(lectureName) == 0) return lecture;
        }

        return null;
    }

    public boolean deleteLecture(Lecture lecture)throws SQLException, NullPointerException{
        dao.deleteLecture(lecture);
        return false;

    }

    public void sendLectureQuestion(Lecture lecture, String lectureQuestion){
        LectureQuestion newQuestion = new LectureQuestion();
        newQuestion.setQuestion(lectureQuestion);
        lecture.getLectureQuestions().add(newQuestion);
    }

    public void joinLecture(Lecture lecture, int user_id) throws SQLException{
        UserController userController = new UserController();
        User user = userController.getById(user_id);
        dao.addLectureAttendee(lecture,user);
    }

    public void editName(Lecture lecture, String newName){
        lecture.setName(newName);
    }

    public void editDuration(Lecture lecture, String newDuration){
        lecture.setDuration(newDuration);
    }

    public void editDate(Lecture lecture, String newDate){
        lecture.setStartDate(newDate);
    }

    public void editDescription(Lecture lecture, String newDescription){
        lecture.setDescription(newDescription);
    }

    public void addLanguage(Lecture lecture, String newLanguage){
        Language language = new Language(newLanguage);
        lecture.getLanguage().add(language);
    }

    public void removeLanguage(Lecture lecture, String language){
        int i = 0;

        while(lecture.getLanguage().size() > i){
            if(lecture.getLanguage().get(i).getName().compareToIgnoreCase(language) != 0){
                lecture.getLanguage().remove(i);
            }
            i = i + 1;
        }
    }

    public void addTopic(Lecture lecture, String newTopic){
        Topic topic = new Topic();
        topic.setName(newTopic);
        lecture.getTopics().add(topic);
    }

    public void removeTopic(Lecture lecture, String topic){
        int i = 0;

        while(lecture.getTopics().size() > i){
            if(lecture.getTopics().get(i).getName().compareToIgnoreCase(topic) != 0){
                lecture.getTopics().remove(i);
            }
            i = i + 1;
        }
    }

    public void addPresenter(Lecture lecture, User newPresenter) throws SQLException{
        dao.addLecturePresenter(lecture,newPresenter);
    }

    public void removePresenter(Lecture lecture, User presenter) throws SQLException{
        dao.removeLecturePresenter(lecture,presenter);
    }

    public void editLecture(Lecture lecture) throws SQLException, NullPointerException{
        dao.editSeminar(lecture);
    }

    public List<User>getAttendees(Lecture lecture){
        return dao.getAttendees(lecture);
    }

    public void evaluateLecture(Lecture lecture, long user_id, int value){
        dao.evaluateLecture(lecture,user_id,value);
    }
    
    public void editEvaluation(Lecture lecture, long user_id, int value){
        dao.editEvaluation(lecture,user_id,value);
    }

    public void removeEvaluation(Lecture lecture, long user_id) {
        dao.removeEvaluation(lecture,user_id);
    }

    public float averageLectureEvaluation(Lecture lecture){
        return dao.averageLectureEvaluation(lecture);
    }
}
