package Views;

import Controllers.EventController;
import Controllers.UserController;
import Models.Event;
import Models.Modality;
import Models.User;
import Utils.ViewUtils;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventView {
    private EventController controller = new EventController();

    public EventView() {
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-Menu Evento-");
            System.out.println("1 - Crie um Evento");
            System.out.println("2 - Edite um Evento");
            System.out.println("3 - Delete um Evento");
            System.out.println("4 - Listar Eventos");
            System.out.println("0 - Sair");

            int choice = ViewUtils.getChoice(scanner);
            switch (choice) {
                case 0:
                    return;
                case 1:
                    menuCreate();
                    break;
                case 2:
                    menuEdit();
                    break;
                case 3:
                    menuDelete();
                    break;
                case 4:
                    System.out.println("-Eventos Cadastrados-");
                    listEvents();
                    break;
                default:
                    System.out.println("Escolha invalida!");
                    break;
            }
        }
    }

    public void menuCreate() {
        Scanner scanner = new Scanner(System.in);

        Event event = new Event();
        System.out.println("Crie seu Evento");

        System.out.println("De um nome ao evento");
        event.setEventName(scanner.nextLine());

        System.out.println("Adicione uma descricao do evento");
        event.setDescription(scanner.nextLine());

        System.out.println("Quando sera o evento?");
        event.setDateEvent(scanner.nextLine());

        controller.addEvent(event);
    }

    public void menuModality(Event event){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Qual a modalidade do seu evento?");

            List<String> modalidadesPossiveis = Stream.of(Modality.values()).
                    map(Modality::name).
                    collect(Collectors.toList());

            for (int i = 0; i < modalidadesPossiveis.size(); i++){
                System.out.printf("%d - %s\n", i+1, modalidadesPossiveis.get(i));
            }

            int newModality = ViewUtils.getChoice(scanner) - 1;

            if (newModality >= 0 && newModality < modalidadesPossiveis.size()) {
                event.setModalidade(Modality.valueOf(modalidadesPossiveis.get(newModality)));
                return;
            } else {
                System.out.println("Modalidade invalida!");
            }

        }
    }

    public void menuDelete() {
        try {
            if (controller.getModels().size() == 0) {
                System.out.println("Nao existe eventos para deletar.");
                return;
            }

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("-Deletar Evento-");
                listEvents();

                System.out.println("Escolha um ID:");
                Long id = (long) ViewUtils.getChoice(scanner);
                if (id == -1) {
                    System.out.println("ID Invalido!");
                    continue;
                }

                try {
                    controller.removeEvent(id);
                    System.out.println("Evento deletado com sucesso!");
                } catch (SQLException | NullPointerException e) {
                    System.out.println("Falha ao Deletar Evento");
                    continue;
                }
                return;
            }
        }catch (SQLException e){
            System.out.println("Falha ao Deletar Evento");
        }
    }

    public void menuEdit() {
        try {
            if (controller.getModels().size() == 0) {
                System.out.println("Nao existe eventos para editar.");
                return;
            }

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("-Editar Evento-");
                listEvents();

                System.out.println("Escolha um ID:");
                Long id = (long) ViewUtils.getChoice(scanner);
                if (id == -1) {
                    System.out.println("ID Invalido!");
                    continue;
                }

                Event event = controller.getById(id);
                if (event == null) {
                    System.out.println("Evento nao encontrado!");
                    return;
                }

                edit(event);
                return;
            }
        } catch (SQLException e) {
            System.out.println("Falha ao editar Evento");
        }
    }

    public void edit(Event event) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Informacoes:");
            System.out.printf("1 - Nome: %s\n", event.getEventName());
            System.out.printf("2 - Descricao: %s\n", event.getDescription());
            System.out.printf("3 - Data: %s\n", event.getDateEvent());
            System.out.printf("4 - Modalidade: %s\n", event.getModalidade());
            System.out.printf("5 - Organizadores: %s\n", event.getOrganizers());
            System.out.printf("6 - Palestras: %s\n", event.getLectures());
            System.out.println("0 - Sair");

            System.out.println("Escolha uma opcao para mudar");
            int choice = ViewUtils.getChoice(scanner);

            switch (choice) {
                case 0:
                    try {
                        controller.editEvent(event);
                        System.out.println("Evento Editado com Sucesso");
                        return;
                    } catch (SQLException | NullPointerException e) {
                        System.out.println("Falha ao Salvar Edicao");
                    }
                case 1:
                    System.out.println("Escreva um novo nome:");
                    event.setEventName(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Escreva uma nova descricao:");
                    event.setDescription(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("Escreva uma nova data:");
                    event.setDateEvent(scanner.nextLine());
                    break;
                case 4:
                    menuModality(event);
                    break;
                case 5:
                    menuOrganizers(event);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Escolha invalida!");
                    break;

            }
        }
    }

    public void listEvents() {
        try {
            for (Event event : controller.getModels()) {
                System.out.printf("%d - %s - %s\n", event.getId(), event.getEventName(), event.getModalidade());
            }
        }catch (SQLException e){
            System.out.println("Falha ao Listar Arquivos");
        }
    }

    public void menuOrganizers(Event event) {
        UserController userController = new UserController();
        UserView userView = new UserView();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                if (userController.getModels().size() == 0) {
                    System.out.println("Nao existe usuarios cadastrados!");
                    return;
                }

                System.out.println("Organizadores: " + event.getOrganizers());
                System.out.println("1 - Adicionar Organizador");
                System.out.println("2 - Remover Organizador");
                System.out.println("3 - Confirmar");

                int choice = ViewUtils.getChoice(scanner);
                if (choice == 3) {
                    return;
                } else if (choice < 1 || choice > 3) {
                    System.out.println("Escolha invalida!");
                    continue;
                }

                userView.listUsers();
                System.out.println("Escolha um usuario:");
                Long id = (long) ViewUtils.getChoice(scanner);

                User userEscolhido = userController.getById(id);
                if (userEscolhido == null) {
                    System.out.println("ID invalido!");
                    continue;
                }

                if (choice == 1) {
                    try {
                        controller.addEventOrganizer(userController.getById(id), event);  // coloca aqui a fun??ao pra add no sql
                    }catch (SQLException e){
                        System.out.println("Falha ao Adicionar Organizador");
                    }
                } else {
                    controller.removeEventOrganizer(userController.getById(id), event);
                }
            }catch (SQLException e){
                System.out.println("Falha ao recuperar Usuarios Cadastrados");
            }
        }
    }
}
