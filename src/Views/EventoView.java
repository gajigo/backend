package Views;

import Controllers.EventoController;
import Models.Evento;
import Models.Modalidade;

import java.time.Instant;
import java.util.Scanner;

public class EventoView {
    private Evento model;
    private EventoController controller;

    public EventoView(Evento model) {
        this.model = model;
    }

    public EventoView(Evento model, EventoController controller) {
        this.model = model;
        this.controller = controller;
    }

    public void menuEvento() {
        Scanner ler = new Scanner(System.in);
        System.out.println("Crie seu Evento");

        System.out.println("De um nome ao evento");
        String nomeEvento = ler.nextLine();

        System.out.println("Adicione uma descricao do evento");
        String descricao = ler.nextLine();

        System.out.println("Qual a modalidade do evento?");
        Modalidade modalidade = ler.nextLine();

        System.out.println("Quando sera o evento?");
        Instant dataEvento = ler.nextLine();

        controller.cadastrar(nomeEvento,descricao,modalidade,dataEvento);
        System.out.println("Evento " + model.getNomeEvento() + "foi criado!");

    }
}
