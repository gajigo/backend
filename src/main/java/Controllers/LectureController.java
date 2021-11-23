package Controllers;

import DAO.LectureDAO;
import Models.*;
import Views.LectureView;

import java.util.ArrayList;
import java.util.List;

public class LectureController {
    private List<Lecture> model;
    private LectureView view;
    private LectureDAO dao;

    public LectureController() {
        this.dao = new LectureDAO();
        this.model = dao.listLecture();
        this.view = new LectureView(this);
    }

    public void start(){
        view.initialMenu();
        if (!dao.save()) {
            System.out.println("Erro ao salvar palestras!");
        }
    }
    public List<Lecture> getModels() {
        return dao.listLecture();
    }

    public Lecture createLecture(String name, String description, String date, String duration, String language){
        Lecture newLecture = new Lecture();
        Idioma newLanguage = new Idioma();

        newLecture.setName(name);
        newLecture.setDescription(description);
        newLecture.setInitialDate(date);
        newLecture.setDuration(duration);
        newLanguage.setNome(language);
        newLecture.getLanguage().add(newLanguage);

        model.add(newLecture);
        return dao.createLectures(newLecture);
    }

    public List<String> listLecture(){
        List<String> listString = new ArrayList<>();

        for (Lecture lecture : model) {
            listString.add(lecture.getName());
        }

        return listString;

    }

    public String lectureIntString(List <String> lectureName, int selected){
        return lectureName.get(selected);
    }

    public Lecture chosenLecture(String lectureName){
        for (Lecture lecture : model) {
            if (lecture.getName().compareTo(lectureName) == 0) return lecture;
        }

        return null;
    }

    public boolean deleteLecture(Lecture lecture){
        dao.deleteLecture(lecture);
        return false;

    }

    public void sendLectureQuestion(Lecture lecture, String lectureQuestion){
        DuvidaPalestra newQuestion = new DuvidaPalestra();
        newQuestion.setDuvida(lectureQuestion);
        lecture.getLectureQuestions().add(newQuestion);
    }

    public boolean joinLecture(Lecture lecture, String newUser){
        User user = new User();
        user.setName(newUser);
        lecture.getAttendees().add(user);
        return true;
    }

    public void editName(Lecture lecture, String newName){
        lecture.setName(newName);
    }

    public void editDuration(Lecture lecture, String newDuration){
        lecture.setDuration(newDuration);
    }

    public void editDate(Lecture lecture, String newDate){
        lecture.setInitialDate(newDate);
    }

    public void editDescription(Lecture lecture, String newDescription){
        lecture.setDescription(newDescription);
    }

    public void addLanguage(Lecture lecture, String newLanguage){
        Idioma language = new Idioma(newLanguage);
        lecture.getLanguage().add(language);
    }

    public void removeLanguage(Lecture lecture, String language){
        int i = 0;

        while(lecture.getLanguage().size() > i){
            if(lecture.getLanguage().get(i).getNome().compareToIgnoreCase(language) != 0){
                lecture.getLanguage().remove(i);
            }
            i = i + 1;
        }
    }

    public void addTopic(Lecture lecture, String newTopic){
        Assunto assunto = new Assunto();
        assunto.setNome(newTopic);
        lecture.getTopics().add(assunto);
    }

    public void removeTopic(Lecture lecture, String topic){
        int i = 0;

        while(lecture.getTopics().size() > i){
            if(lecture.getTopics().get(i).getNome().compareToIgnoreCase(topic) != 0){
                lecture.getTopics().remove(i);
            }
            i = i + 1;
        }
    }

    public void addPresenter(Lecture lecture, String newPresenter){
        User presenter = new User();
        presenter.setName(newPresenter);
        lecture.getPresenter().add(presenter);
    }

    public void removePresenter(Lecture lecture, String presenter){
        int i = 0;

        while(lecture.getPresenter().size() > i){
            if(lecture.getPresenter().get(i).getName().compareToIgnoreCase(presenter) != 0){
                lecture.getPresenter().remove(i);
            }
            i = i + 1;
        }
    }

    public void editLecture(Lecture lecture){
        dao.editSeminar(lecture);
    }
}
