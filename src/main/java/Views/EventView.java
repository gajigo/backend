package Views;

import Controllers.EventController;
import Controllers.UserController;
import Models.Event;
import Models.Modality;
import Models.User;
import org.postgresql.gss.GSSOutputStream;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventView {
    private EventController controller;

    public EventView(EventController controller) {
        this.controller = controller;
    }

    public EventView() {
        controller = new EventController();
    }

    public void menu() {
        Scanner ler = new Scanner(System.in);

        while (true) {
            System.out.println("-Menu Evento-");
            System.out.println("1 - Crie um Evento");
            System.out.println("2 - Edite um Evento");
            System.out.println("3 - Delete um Evento");
            System.out.println("4 - Listar Eventos");
            System.out.println("0 - Sair");

            int escolha = ler.nextInt();
            ler.nextLine();

            switch (escolha) {
                case 0:
                    return;
                case 1:
                    menuRegistrar();
                    break;
                case 2:
                    menuEditar();
                    break;
                case 3:
                    menuDeletar();
                    break;
                case 4:
                    System.out.println("-Eventos Cadastrados-");
                    listar();
                    break;
                default:
                    System.out.println("Escolha invalida!");
                    break;
            }
        }
    }

    public void menuRegistrar() {
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

    public void menuModalidade(Event event){
        Scanner ler = new Scanner(System.in);
        while (true) {
            System.out.println("Qual a modalidade do seu evento?");

            List<String> modalidadesPossiveis = Stream.of(Modality.values()).
                    map(Modality::name).
                    collect(Collectors.toList());

            for (int i = 0; i < modalidadesPossiveis.size(); i++){
                System.out.printf("%d - %s\n", i+1, modalidadesPossiveis.get(i));
            }

            int novaModalidade = ler.nextInt() - 1;
            ler.nextLine();

            if (novaModalidade >= 0 && novaModalidade < modalidadesPossiveis.size()) {
                event.setModalidade(Modality.valueOf(modalidadesPossiveis.get(novaModalidade)));
                return;
            } else {
                System.out.println("Modalidade invalida!");
            }

        }
    }

    public void menuDeletar() {
        try {
            if (controller.getModels().size() == 0) {
                System.out.println("Nao existe eventos para deletar.");
                return;
            }

            Scanner ler = new Scanner(System.in);

            System.out.println("-Deletar Evento-");
            listar();

            System.out.println("Escolha um ID:");
            Long id = ler.nextLong();
            ler.nextLine();

            try{
                controller.deleteById(id);
                System.out.println("Evento deletado com sucesso!");
            }catch (SQLException e){
                System.out.println("Falha ao Deletar Evento");
            }catch (NullPointerException e){
                System.out.println("Falha ao Deletar Evento");
            }
        }catch (SQLException e){
            System.out.println("Falha ao Deletar Evento");
        }
    }

    public void menuEditar() {
        try {
            if (controller.getModels().size() == 0) {
                System.out.println("Nao existe eventos para editar.");
                return;
            }

            Scanner ler = new Scanner(System.in);

            System.out.println("-Editar Evento-");
            listar();

            System.out.println("Escolha um ID:");
            Long id = ler.nextLong();
            ler.nextLine();

            Event escolha = controller.getById(id);
            if (escolha == null) {
                System.out.println("Evento nao encontrado!");
                return;
            }

            edite(escolha);
        }catch (SQLException e){
            System.out.println("Falha ao editar Evento");
        }
    }

    public void edite(Event event) {
        Scanner ler = new Scanner(System.in);

        while (true) {
            System.out.println("Informacoes:");
            System.out.printf("1 - %s: %s\n", "Nome", event.getEventName());
            System.out.printf("2 - %s: %s\n", "Descricao", event.getDescription());
            System.out.printf("3 - %s: %s\n", "Data", event.getDateEvent());
            System.out.printf("4 - %s: %s\n", "Modalidade", event.getModalidade());
            System.out.printf("5 - %s: %s\n", "Organizadores", event.getOrganizers());
            System.out.printf("6 - %s: %s\n", "Palestras", event.getLectures());
            System.out.println("0 - Sair");

            System.out.println("Escolha uma opcao para mudar");
            int escolha = ler.nextInt();
            ler.nextLine();

            switch (escolha) {
                case 0:
                    return;
                case 1:
                    System.out.println("Escreva um novo nome:");
                    event.setEventName(ler.nextLine());
                    break;
                case 2:
                    System.out.println("Escreva uma nova descricao:");
                    event.setDescription(ler.nextLine());
                    break;
                case 3:
                    System.out.println("Escreva uma nova data:");
                    event.setDateEvent(ler.nextLine());
                    break;
                case 4:
                    menuModalidade(event);
                    break;
                case 5:
                    menuOrganizadores(event);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Escolha invalida!");
                    break;

            }
            try {
                controller.editEvento(event);
                System.out.println("Evento Editado com Sucesso");
            }catch (SQLException e){
                System.out.println("Falha ao Salvar Edicao");
            }catch (NullPointerException e){
                System.out.println("Falha ao Salvar Edicao");
            }
        }
    }

    public void listar() {
        try {
            for (Event event : controller.getModels()) {
                System.out.printf("%d - %s - %s\n", event.getId(), event.getEventName(), event.getModalidade());
            }
        }catch (SQLException e){
            System.out.println("Falha ao Listar Arquivos");
        }
    }

    public void menuOrganizadores(Event event) {
        UserController usuarios = new UserController();
        UserView userView = new UserView();

        Scanner ler = new Scanner(System.in);
        while (true) {
            try {
                if (usuarios.getModels().size() == 0) {
                    System.out.println("Nao existe usuarios cadastrados!");
                    return;
                }


                System.out.println("Organizadores: " + event.getOrganizers());
                System.out.println("1 - Adicionar Organizador");
                System.out.println("2 - Remover Organizador");
                System.out.println("3 - Confirmar");

                int escolha = ler.nextInt();
                ler.nextLine();

                if (escolha == 3) {
                    return;
                } else if (escolha < 1 || escolha > 3) {
                    System.out.println("Escolha invalida!");
                    continue;
                }

                userView.list();
                System.out.println("Escolha um usuario:");
                int id = ler.nextInt();
                ler.nextLine();

                User userEscolhido = usuarios.getById((long) id);
                if (userEscolhido == null) {
                    System.out.println("ID invalido!");
                    continue;
                }

                if (escolha == 1) {
                    try {
                        controller.addEventOrganizer(usuarios.getById((long) id), event);  // coloca aqui a funçao pra add no sql
                    }catch (SQLException e){
                        System.out.println("Falha ao Adicionar Organizador");
                    }
                } else {
                    controller.removeEventOrganizer(usuarios.getById((long) id), event);
                }
            }catch (SQLException e){
                System.out.println("Falha ao recuperar Usuarios Cadastrados");
            }
        }
    }
}
