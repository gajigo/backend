package Views;

import Controllers.EventoController;
import Controllers.UsuarioController;

import java.util.Scanner;

public class MenuView {
    public static void start() {
        Scanner ler = new Scanner(System.in);
        while (true) {
            System.out.println("-Menu Principal-");
            System.out.println("1 - Menu Usuario");
            System.out.println("2 - Menu Evento");
            System.out.println("3 - Sair");

            int escolha = ler.nextInt();
            switch (escolha) {
                case 1:
                    menuUsuario();
                    break;
                case 2:
                    menuEvento();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Escolha invalida! Tente novamente.");
                    break;
            }
        }
    }

    public static void menuUsuario() {
        UsuarioController controller = new UsuarioController();
        controller.start();
    }

    public static void menuEvento() {
        EventoController controller = new EventoController();
        controller.start();
    }

}
