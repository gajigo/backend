package Views;

import Controllers.LectureController;
import Models.Lecture;

import java.util.List;
import java.util.Scanner;

public class LectureView {
    private LectureController controller;

    public LectureView(LectureController controller) {
        this.controller = controller;
    }

    public void initialMenu() {
        while (true) {
            Scanner reader = new Scanner(System.in);

            System.out.println("1 - Criar Palestra");
            System.out.println("2 - Selecionar Palestra");
            System.out.println("0 - Voltar");

            int escolha = reader.nextInt();
            switch (escolha) {
                case 1:
                    createLecture();
                    break;
                case 2:
                    selectLecture();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Escolha Invalida");
                    break;
            }
        }
    }

    public void createLecture(){
        Scanner reader = new Scanner(System.in);

        System.out.println("Digite o nome da palestra");
        String nome = reader.nextLine();

        System.out.println("Digite a descricao da palestra");
        String descricao = reader.nextLine();

        System.out.println("Digite a data da palestra");
        String data = reader.nextLine();

        System.out.println("Digite a duracao da palestra");
        String duracao = reader.nextLine();

        System.out.println("Digite o idioma da palestra");
        String idioma =  reader.nextLine();

        System.out.println(controller.createLecture(nome,descricao,data,duracao,idioma).getName() + " criada com sucesso");

    }

    public void selectLecture(){
        Scanner reader = new Scanner(System.in);
        List<String> nomePalestra = controller.listLecture();
        int i = 0;

        while (nomePalestra.size() > i){
            System.out.println((i + 1) + " - " + nomePalestra.get(i));
            i = i+1;
        }
        System.out.println("0 - Voltar");
        int escolha = reader.nextInt();

        if (escolha == 0){
            return;
        }
        String selected = controller.lectureIntString(nomePalestra, (escolha - 1));
        if (escolha <= nomePalestra.size()){
            chosenLecture(controller.chosenLecture(selected));
        }
    }

    public void chosenLecture(Lecture lecture){
        Scanner reader = new Scanner(System.in);

        System.out.println("1 - Mostrar");
        System.out.println("2 - Deletar");
        System.out.println("3 - Editar");
        System.out.println("4 - Participar");
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
            case 0:
                return;
            default:
                System.out.println("Escolha invalida");
                break;
        }
    }

    public void showLecture(Lecture lecture){
        Scanner reader = new Scanner(System.in);

        System.out.println("Nome: " + lecture.getName());
        System.out.println("ID: " + lecture.getId());
        System.out.println("Data de Inicio: " + lecture.getInitialDate());
        System.out.println("Duracao: " + lecture.getDuration());
        System.out.println("Idiomas: " + lecture.getLanguage());
        System.out.println("Assuntos: " + lecture.getTopics());
        System.out.println("Palestrantes: " + lecture.getPresenter());
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
        if(!controller.deleteLecture(lecture)){
            System.out.println("Palestra Deletada");
        }else{
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
            controller.editLecture(lecture);
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
        Scanner reader = new Scanner(System.in);

        System.out.println("Digite seu Nome");
        String user = reader.nextLine();

        if(controller.joinLecture(lecture, user)){
            System.out.println("Presenca Contabilizada");
        }else{
            System.out.println("Falha ao adicionar Presenca");
        }
    }

    public void showAttendees(Lecture lecture){
        System.out.println("Participantes:");
        System.out.println(lecture.getAttendees());
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

        System.out.println("Data atual: " + lecture.getInitialDate());
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

        System.out.println("Palestrante atual: " + lecture.getPresenter());
        System.out.println("Deseja ADICIONAR ou REMOVER Palestrante");
        String escolha = reader.nextLine();

        if (escolha.compareToIgnoreCase("ADICIONAR") == 0){
            System.out.println("Insira palestrante novo");
            String novoPalestrante = reader.nextLine();
            controller.addPresenter(lecture,novoPalestrante);
        }

        if (escolha.compareToIgnoreCase("REMOVER") == 0){
            System.out.println("Insira palestrante a ser removido");
            String removePalestrante = reader.nextLine();
            controller.removePresenter(lecture,removePalestrante);
        }
    }
}

