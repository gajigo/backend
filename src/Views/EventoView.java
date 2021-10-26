package Views;

import Controllers.EventoController;
import Models.Evento;

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

        menuModalidade();

        System.out.println("Quando sera o evento?");
        String dataEvento = ler.nextLine();

        controller.cadastrar(nomeEvento, descricao, dataEvento);
        System.out.println("Evento " + model.getNomeEvento() + "foi criado!");

    }

    public void menuModalidade(){
        while (true) {
        System.out.println("Qual a modalidade do seu evento?");
        System.out.println("1 - ONLINE");
        System.out.println("2 - PRESENCIAL");
        System.out.println("3 - HIBRIDO");

        Scanner ler = new Scanner(System.in);
        int escolha = ler.nextInt();

        switch(escolha){
            case 1:
                modOnline();
                break;
            case 2:
                modPresencial();
                break;
            case 3:
                modHibrido();
                break;
            case 0:
                return;
            default:
                System.out.println("Escolha uma modalidade de evento!");
                break;
            }
        }
    }

    public void modOnline(){

    }
    public void modPresencial(){

    }
    public void modHibrido(){

    }
}
