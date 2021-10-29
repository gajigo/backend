package Views;

import Controllers.EventoController;
import Controllers.UsuarioController;
import Models.Evento;
import Models.Modalidade;
import Models.Usuario;


import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventoView {
    private EventoController controller;

    public EventoView(EventoController controller) {
        this.controller = controller;
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
        Scanner ler = new Scanner(System.in);
        System.out.println("Crie seu Evento");

        System.out.println("De um nome ao evento");
        String nomeEvento = ler.nextLine();

        System.out.println("Adicione uma descricao do evento");
        String descricao = ler.nextLine();

        System.out.println("Quando sera o evento?");
        String dataEvento = ler.nextLine();

        Evento novoEvento = controller.cadastrar(nomeEvento, descricao, dataEvento);

        menuModalidade(novoEvento);

        System.out.println("Evento " + novoEvento.getNomeEvento() + " foi criado!");
    }

    public void menuModalidade(Evento evento){
        Scanner ler = new Scanner(System.in);
        while (true) {
            System.out.println("Qual a modalidade do seu evento?");

            List<String> modalidadesPossiveis = Stream.of(Modalidade.values()).
                    map(Modalidade::name).
                    collect(Collectors.toList());

            for (int i = 0; i < modalidadesPossiveis.size(); i++){
                System.out.printf("%d - %s\n", i+1, modalidadesPossiveis.get(i));
            }

            int novaModalidade = ler.nextInt() - 1;
            ler.nextLine();

            if (novaModalidade >= 0 && novaModalidade < modalidadesPossiveis.size()) {
                evento.setModalidade(Modalidade.valueOf(modalidadesPossiveis.get(novaModalidade)));
                return;
            } else {
                System.out.println("Modalidade invalida!");
            }
        }
    }

    public void menuDeletar() {
        if (controller.getModels().size() == 0) {
            System.out.println("Nao existe eventos para deletar.");
            return;
        }

        Scanner ler = new Scanner(System.in);

        System.out.println("-Deletar Evento-");
        listar();

        System.out.println("Escolha um ID:");
        int id = ler.nextInt();
        ler.nextLine();

        if (controller.deleteById(id)) {
            System.out.println("Evento deletado com sucesso!");
        } else {
            System.out.println("Nao foi possivel deletar o Evento, confirme se escreveu o ID correto.");
        }
    }

    public void menuEditar() {
        if (controller.getModels().size() == 0) {
            System.out.println("Nao existe usuarios para editar.");
            return;
        }

        Scanner ler = new Scanner(System.in);

        System.out.println("-Editar Evento-");
        listar();

        System.out.println("Escolha um ID:");
        int id = ler.nextInt();
        ler.nextLine();

        Evento escolha = controller.getById(id);
        if (escolha == null) {
            System.out.println("Evento nao encontrado!");
            return;
        }

        edite(escolha);
    }

    public void edite(Evento evento) {
        Scanner ler = new Scanner(System.in);

        while (true) {
            System.out.println("Informacoes:");
            System.out.printf("1 - %s: %s\n", "Nome", evento.getNomeEvento());
            System.out.printf("2 - %s: %s\n", "Descricao", evento.getDescricao());
            System.out.printf("3 - %s: %s\n", "Data", evento.getDataEvento());
            System.out.printf("4 - %s: %s\n", "Modalidade", evento.getModalidade());
            System.out.printf("5 - %s: %s\n", "Organizadores", evento.getOrganizadores());
            System.out.printf("6 - %s: %s\n", "Palestras", evento.getPalestras());
            System.out.println("0 - Sair");

            System.out.println("Escolha uma opcao para mudar");
            int escolha = ler.nextInt();
            ler.nextLine();

            switch (escolha) {
                case 0:
                    return;
                case 1:
                    System.out.println("Escreva um novo nome:");
                    evento.setNomeEvento(ler.nextLine());
                    break;
                case 2:
                    System.out.println("Escreva uma nova descricao:");
                    evento.setDescricao(ler.nextLine());
                    break;
                case 3:
                    System.out.println("Escreva uma nova data:");
                    evento.setDataEvento(ler.nextLine());
                    break;
                case 4:
                    menuModalidade(evento);
                    break;
                case 5:
                    menuOrganizadores(evento);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Escolha invalida!");
                    break;
            }
        }
    }

    public void listar() {
        for (Evento evento: controller.getModels()) {
            System.out.printf("%d - %s - %s\n", evento.getId(), evento.getNomeEvento(), evento.getModalidade());
        }
    }

    public void menuOrganizadores(Evento evento) {
        UsuarioController usuarios = new UsuarioController();
        UsuarioView usuarioView = new UsuarioView(usuarios);

        Scanner ler = new Scanner(System.in);
        while (true) {
            if (usuarios.getModels().size() == 0) {
                System.out.println("Nao existe usuarios cadastrados!");
                return;
            }

            System.out.println("Organizadores: " + evento.getOrganizadores());
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

            usuarioView.listar();
            System.out.println("Escolha um usuario:");
            int id = ler.nextInt();
            ler.nextLine();

            Usuario usuarioEscolhido = usuarios.getById(id);
            if (usuarioEscolhido == null) {
                System.out.println("ID invalido!");
                continue;
            }

            if (escolha == 1) {
                evento.addOrganizador(usuarioEscolhido);
            } else {
                evento.removeOrganizador(usuarioEscolhido);
            }
        }
    }
}
