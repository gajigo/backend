package Views;

import Controllers.EventoController;
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
            System.out.println("5 - Sair");

            int escolha = ler.nextInt();
            ler.nextLine();

            switch (escolha) {
                case 1:
                    menuRegistrar();
                    break;
                case 2:
                    break;
                case 3:
                    menuDeletar();
                    break;
                case 4:
                    System.out.println("-Eventos Cadastrados-");
                    listar();
                    break;
                case 5:
                    return;
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

        System.out.println("Evento " + novoEvento.getNomeEvento() + "foi criado!");
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

    public void listar() {
        for (Evento evento: controller.getModels()) {
            System.out.printf("%d - %s\n", evento.getId(), evento.getNomeEvento());
        }
    }
}
