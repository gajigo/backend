package Views;

import Controllers.EventController;
import Controllers.LectureController;
import Controllers.UserController;
import Models.Event;
import Models.Language;
import Models.Lecture;
import Models.User;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class LectureView {
    private LectureController controller = new LectureController();

    public LectureView() {
    }

    public void menu() {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("1 - Criar Palestra");
            System.out.println("2 - Selecionar Palestra");
            System.out.println("0 - Sair");

            int choice = ViewUtils.getChoice(scanner);
            switch (choice) {
                case 0:
                    return;
                case 1:
                    menuCreate();
                    break;
                case 2:
                    selectLecture();
                    break;
                default:
                    System.out.println("Escolha Invalida");
                    break;
            }
        }
    }

    public void menuCreate(){
        Scanner scanner = new Scanner(System.in);

        EventController eventController = new EventController();
        EventView eventView = new EventView();

        Lecture lecture = new Lecture();

        System.out.println("Digite o nome da palestra");
        lecture.setName(scanner.nextLine());

        System.out.println("Digite a descricao da palestra");
        lecture.setDescription(scanner.nextLine());

        System.out.println("Digite a data da palestra");
        lecture.setStartDate(scanner.nextLine());

        System.out.println("Digite a duracao da palestra");
        lecture.setDuration(scanner.nextLine());

        System.out.println("Digite o idioma da palestra");
        lecture.getLanguage().add(new Language(scanner.nextLine()));

        while (true) {
            System.out.println("Escolha o Evento");
            eventView.listEvents();
            Long evento = (long) ViewUtils.getChoice(scanner);

            try {
                Event event = eventController.getById(evento);
                if (event == null) {
                    System.out.println("ID Invalido!");
                    continue;
                }

                lecture.setEvent(event);
                lecture = controller.createLecture(lecture);
                System.out.println(lecture.getName() + " criada com sucesso");
            } catch (SQLException e) {
                System.out.println("deu ruim");
            } catch (NullPointerException e) {
                throw new RuntimeException(e);
            }
            return;
        }
    }

    public void selectLecture(){
        Scanner reader = new Scanner(System.in);
        List<String> nomePalestra;
        try {
            nomePalestra = controller.listLectureNames();
        }catch (SQLException e){
            System.out.println("Falha ao recuperar a lista de palestras");
            return;
        }
        int i = 0;

        while (nomePalestra.size() > i){
            System.out.println((i + 1) + " - " + nomePalestra.get(i));
            i = i+1;
        }
        System.out.println("0 - Voltar");
        int escolha = reader.nextInt();

        if (escolha == 0 || escolha > nomePalestra.size()){
            return;
        }
        String selected = controller.lectureIntString(nomePalestra, (escolha - 1));
        if (escolha <= nomePalestra.size()){
            try {
                chosenLecture(controller.chosenLecture(selected));
            }catch (SQLException e){
                System.out.println("Falha ao escolher palestra");
            }
        }
    }

    public void chosenLecture(Lecture lecture){
        Scanner reader = new Scanner(System.in);

        System.out.println("1 - Mostrar");
        System.out.println("2 - Deletar");
        System.out.println("3 - Editar");
        System.out.println("4 - Participar");
        System.out.println("5 - Avaliar");
        System.out.println("6 - Editar Avaliacao");
        System.out.println("7 - Remover Avaliacao");
        System.out.println("0 - Voltar");

        int escolha = reader.nextInt();
        switch (escolha){
            case 1:
                showLecture(lecture);
                break;
            case 2:
                deleteLecture(lecture);
                break;
            case 3:
                editMenu(lecture);
                break;
            case 4:
                joinLecture(lecture);
                break;
            case 5:
                reviewLecture(lecture);
                break;
            case 6:
                changeReview(lecture);
                break;
            case 7:
                removeEvaluation(lecture);
                break;
            case 0:
                return;
            default:
                System.out.println("Escolha invalida");
                break;
        }
    }

    public void showLecture(Lecture lecture){
        Scanner reader = new Scanner(System.in);
        Float evaluationValue = 0.0F;
        DecimalFormat format = new DecimalFormat("#.#");

        try{
            evaluationValue = controller.averageLectureEvaluation(lecture);
        }catch (SQLException e){
            System.out.println("Falha ao Recuperar Avaliacoes");
        }

        System.out.println("Nome: " + lecture.getName());
        System.out.println("ID: " + lecture.getId());
        System.out.println("Data de Inicio: " + lecture.getStartDate());
        System.out.println("Duracao: " + lecture.getDuration());
        System.out.println("Idiomas: " + lecture.getLanguage());
        System.out.println("Assuntos: " + lecture.getTopics());
        System.out.println("Palestrantes: " + lecture.getPresenters());
        System.out.println("Avaliacao da Palestra: " + format.format(evaluationValue) );
        System.out.println();
        System.out.println("1 - Listar Duvidas");
        System.out.println("2 - Listar Participantes");
        System.out.println("0 - Voltar");

        int escolha = reader.nextInt();

        switch (escolha){
            case 1:
                showLectureQuestion(lecture);
                break;
            case 2:
                showAttendees(lecture);
                break;
            case 0:
                return;
            default:
                System.out.println("Escolha Invalida");
                break;
        }
    }

    public void deleteLecture(Lecture lecture){
       try {
           controller.deleteLecture(lecture);
           System.out.println("Palestra Deletada");
        }catch (SQLException e){
            System.out.println("Erro ao Deletar");
       }
    }

    public void editMenu(Lecture lecture){
        while (true){

            System.out.println("1 - Nome");
            System.out.println("2 - Duracao");
            System.out.println("3 - Data");
            System.out.println("4 - Descricao");
            System.out.println("5 - Idiomas");
            System.out.println("6 - Assunto");
            System.out.println("7 - Palestrante");
            System.out.println("0 - Voltar");

            Scanner reader = new Scanner(System.in);
            int escolha = reader.nextInt();

            switch (escolha){
                case 1:
                    editName(lecture);
                    break;
                case 2:
                    editDuration(lecture);
                    break;
                case 3:
                    editDate(lecture);
                    break;
                case 4:
                    editDescription(lecture);
                    break;
                case 5:
                    editLanguage(lecture);
                    break;
                case 6:
                    editTopic(lecture);
                    break;
                case 7:
                    editPresenter(lecture);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Escolha invalida");
                    break;
            }
            try {
                controller.editLecture(lecture);
                System.out.println("Editado com Sucesso");
            }catch (SQLException e){
                System.out.println("Falha ao Editar Palestra");
            }
        }
    }

    public void sendLectureQuestion(Lecture lecture){
        Scanner reader = new Scanner(System.in);

        System.out.println("Digite sua duvida:");
        String question = reader.nextLine();

        controller.sendLectureQuestion(lecture, question);
    }

    public void showLectureQuestion(Lecture lecture){
        Scanner reader = new Scanner(System.in);

        System.out.println("Duvidas:");
        System.out.println(lecture.getLectureQuestions());

        System.out.println("1 - Enviar Duvida");
        System.out.println("0 - Voltar");
        int escolha = reader.nextInt();

        switch (escolha){
            case 1:
                sendLectureQuestion(lecture);
                break;
            case 0:
                return;
            default:
                System.out.println("Escolha invalida");
        }
    }

    public void joinLecture(Lecture lecture){
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();
        UserController userController = new UserController();

        while (true) {
            userView.listUsers();
            System.out.println("Escolha seu Usuario");
            Long id = (long) ViewUtils.getChoice(scanner);

            try {
                User user = userController.getById(id);
                if (user == null) {
                    System.out.println("ID Invalido");
                    continue;
                }

                controller.joinLecture(lecture, user);
                System.out.println("Presenca Contabilizada");
            } catch (SQLException e) {
                System.out.println("Falha ao adicionar Presenca");
            }
            return;
        }
    }

    public void reviewLecture(Lecture lecture){
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();
        UserController userController = new UserController();

        User user;
        while (true) {
            userView.listUsers();
            System.out.println("Escolha seu Usuario");
            Long id = (long) ViewUtils.getChoice(scanner);

            try {
                user = userController.getById(id);
                if (user == null) {
                    System.out.println("ID Invalido");
                    continue;
                }
            } catch (SQLException e) {
                System.out.println("Falha ao resgatar usuario");
                continue;
            }
            break;
        }

        int rating;
        while (true) {
            System.out.println("Digite a nota da palestra (Entre 1 e 5)");
            rating = ViewUtils.getChoice(scanner);
            if (rating < 1 || rating > 5) {
                System.out.println("Nota invalida!");
                continue;
            }
            break;
        }

        try {
            controller.reviewLecture(lecture, user, rating);
            System.out.println("Obrigado pela avaliacao");
        } catch (SQLException e) {
            System.out.println("Falha ao enviar avaliacao");
        }
    }

    public void changeReview(Lecture lecture){
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();

        userView.listUsers();
        System.out.println("Escolha Seu Usuario");
        Long user_id = scanner.nextLong();

        System.out.println("Digite a nota da palestra (Entre 1 e 5)");
        int value = scanner.nextInt();

        try {
            controller.editEvaluation(lecture, user_id, value);
            System.out.println("Avaliacao Editada");
        }catch (SQLException | NullPointerException e){
            System.out.println("Falha ao Editar Avaliacao");
        }
    }

    public void removeEvaluation(Lecture lecture){
        Scanner reader = new Scanner(System.in);
        UserView userView = new UserView();

        userView.listUsers();
        System.out.println("Escolha Seu Usuario");
        Long user_id = reader.nextLong();

        try {
            controller.removeEvaluation(lecture, user_id);
            System.out.println("Avaliacao Removida");
        }catch (SQLException | NullPointerException e){
            System.out.println("Falha ao Remover Avaliacao");
        }
    }

    public void showAttendees(Lecture lecture){
        try {
            System.out.println("Participantes:");
            System.out.println(controller.getAttendees(lecture));
        }catch (SQLException e){
            System.out.println("Falha ao exibir participantes");
        }
    }

    public void editName(Lecture lecture){
        Scanner reader = new Scanner(System.in);

        System.out.println("Nome atual: " + lecture.getName());
        System.out.println("Insira nome novo:");
        String newName = reader.nextLine();
        controller.editName(lecture,newName);

    }

    public void editDuration(Lecture lecture){
        Scanner reader = new Scanner(System.in);

        System.out.println("Duracao atual: " + lecture.getDuration());
        System.out.println("Insira duracao nova:");
        String novaDuracao = reader.nextLine();
        controller.editDuration(lecture,novaDuracao);
    }

    public void editDate(Lecture lecture){
        Scanner reader = new Scanner(System.in);

        System.out.println("Data atual: " + lecture.getStartDate());
        System.out.println("Insira data nova:");
        String novaData = reader.nextLine();
        controller.editDate(lecture,novaData);
    }

    public void editDescription(Lecture lecture){
        Scanner reader = new Scanner(System.in);

        System.out.println("Descricao atual: " + lecture.getDescription());
        System.out.println("Insira descricao nova");
        String novaDescricao = reader.nextLine();
        controller.editDescription(lecture,novaDescricao);
    }

    public void editLanguage(Lecture lecture){
        Scanner reader = new Scanner(System.in);
        System.out.println("Idioma atual: " + lecture.getLanguage());
        System.out.println("Deseja ADICIONAR ou REMOVER Idioma:");
        String escolha = reader.nextLine();

        if (escolha.compareToIgnoreCase("ADICIONAR") == 0){
            System.out.println("Insira idioma novo");
            String novoIdioma = reader.nextLine();
            controller.addLanguage(lecture,novoIdioma);
        }

        if (escolha.compareToIgnoreCase("REMOVER") == 0){
            System.out.println("Insira idioma a ser removido");
            String removeIdioma = reader.nextLine();
            controller.removeLanguage(lecture,removeIdioma);
        }
    }

    public void editTopic(Lecture lecture){
        Scanner reader = new Scanner(System.in);

        System.out.println("Assunto atual: " + lecture.getTopics());
        System.out.println("Deseja ADICIONAR ou REMOVER Assunto");
        String escolha = reader.nextLine();

        if (escolha.compareToIgnoreCase("ADICIONAR") == 0){
            System.out.println("Insira assunto novo");
            String novoAssunto = reader.nextLine();
            controller.addTopic(lecture,novoAssunto);
        }

        if (escolha.compareToIgnoreCase("REMOVER") == 0){
            System.out.println("Insira assunto a ser removido");
            String removeAssunto = reader.nextLine();
            controller.removeTopic(lecture,removeAssunto);
        }
    }

    public void editPresenter(Lecture lecture){
        Scanner reader = new Scanner(System.in);
        UserView userView = new UserView();
        UserController userController = new UserController();

        System.out.println("Palestrante atual: " + lecture.getPresenters());
        System.out.println("Deseja ADICIONAR ou REMOVER Palestrante");
        String escolha = reader.nextLine();

        if (escolha.compareToIgnoreCase("ADICIONAR") == 0){
            userView.listUsers();
            System.out.println("Escolha um usuario:");
            int id = reader.nextInt();
            try {
                User userEscolhido = userController.getById((long) id);
                controller.addPresenter(lecture, userEscolhido);
                System.out.println("Palestrante Adicionado");
            }catch (SQLException e){
                System.out.println("Falha ao adicionar Palestrante");
            }
        }

        if (escolha.compareToIgnoreCase("REMOVER") == 0){
            userView.listUsers();
            System.out.println("Escolha um usuario:");
            int id = reader.nextInt();
            try {
                User userEscolhido = userController.getById((long) id);
                controller.removePresenter(lecture, userEscolhido);
                System.out.println("Palestrante Removido");
            }catch (SQLException e) {
                System.out.println("Falha ao remover Palestrante");
            }
        }
    }

}

