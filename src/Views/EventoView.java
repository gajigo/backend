package Views;

import Controllers.EventoController;
import Models.Evento;
import Models.Modalidade;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventoView {
    private EventoController controller;

    public EventoView(EventoController controller) {
        this.controller = controller;
    }


    public void menuEvento() {
        Scanner ler = new Scanner(System.in);
        System.out.println("Crie seu Evento");

        System.out.println("De um nome ao evento");
        String nomeEvento = ler.nextLine();

        System.out.println("Adicione uma descricao do evento");
        String descricao = ler.nextLine();

        menuModalidade();

        System.out.println("Quando sera o evento?");
        String dataEvento = ler.nextLine();

        Evento novoEvento = controller.cadastrar(nomeEvento, descricao, dataEvento);

        System.out.println("Evento " + novoEvento.getNomeEvento() + "foi criado!");

    }

    public void menuModalidade(){
        Scanner ler = new Scanner(System.in);
        while (true) {
            System.out.println("Qual a modalidade do seu evento?");

            List<String> modalidadesPossiveis = Stream.of(Modalidade.values()).
                    map(Modalidade::name).
                    collect(Collectors.toList());

            for (int i = 0; i < modalidadesPossiveis.size(); i++){
                System.out.println("%d - %s\n", i+1, modalidadesPossiveis.get(i));
            }
            int novaModalidade = ler.nextInt() - 1;
        }
    }


}
