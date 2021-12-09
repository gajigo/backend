package Views;

import Controllers.EventController;
import Controllers.UserController;

import java.util.Scanner;

public class MenuView {
    public static void start() {
        Scanner ler = new Scanner(System.in);
        while (true) {
            System.out.println("-Menu Principal-");
            System.out.println("1 - Menu Usuario");
            System.out.println("2 - Menu Evento");
            System.out.println("3 - Menu Palestra");
            System.out.println("0 - Sair");

            int escolha = ler.nextInt();
            switch (escolha) {
                case 0:
                    return;
                case 1:
                    menuUsuario();
                    break;
                case 2:
                    menuEvento();
                    break;
                case 3:
                    menuPalestra();
                    break;
                default:
                    System.out.println("Escolha invalida! Tente novamente.");
                    break;
            }
        }
    }

    public static void menuUsuario() {
        UserView view = new UserView();
        view.menu();
    }

    public static void menuEvento() {
        EventView view = new EventView();
        view.menu();
    }

    public static void menuPalestra(){
        LectureView view = new LectureView();
        view.initialMenu();
    }
}
