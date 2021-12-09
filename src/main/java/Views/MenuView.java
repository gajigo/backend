package Views;

import Utils.ViewUtils;

import java.util.Scanner;

public class MenuView {
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-Menu Principal-");
            System.out.println("1 - Menu Usuario");
            System.out.println("2 - Menu Evento");
            System.out.println("3 - Menu Palestra");
            System.out.println("0 - Sair");

            int choice = ViewUtils.getChoice(scanner);
            switch (choice) {
                case 0 -> System.exit(0);
                case 1 -> menuUser();
                case 2 -> menuEvent();
                case 3 -> menuLecture();
                default -> System.out.println("Escolha invalida! Tente novamente.");
            }
        }
    }

    public static void menuUser() {
        UserView view = new UserView();
        view.menu();
    }

    public static void menuEvent() {
        EventView view = new EventView();
        view.menu();
    }

    public static void menuLecture(){
        LectureView view = new LectureView();
        view.menu();
    }
}
