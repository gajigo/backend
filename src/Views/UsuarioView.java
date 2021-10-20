package Views;

import Controllers.UsuarioController;
import Models.Roles;
import Models.Usuario;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsuarioView {
    private Usuario model;
    private UsuarioController controller;

    public UsuarioView(Usuario model) {
        this.model = model;
    }

    public UsuarioView(Usuario model, UsuarioController controller) {
        this.model = model;
        this.controller = controller;
    }

    public void novoMenu() {
        while (true) {
            System.out.println("-Menu Usuario-");
            System.out.println("1 - Crie um Usuario");
            System.out.println("2 - Edite um Usuario");
            System.out.println("3 - Delete um Usuario");
            System.out.println("4 - Sair");

            Scanner ler = new Scanner(System.in);
            int escolha = ler.nextInt() - 1;

            switch (escolha) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Escolha invalida!");
                    break;
            }
        }
    }

    public void menu() {
        Scanner ler = new Scanner(System.in);

        System.out.println("-Crie seu Usuario-");

        System.out.println("Escreva seu nome:");
        String nome = ler.nextLine();

        System.out.println("Escreva sua senha:");
        String senha = ler.nextLine();

        controller.registrar(nome, senha);
        System.out.println("Bem vindo " + model.getNome() + "!");

        menuCargos();
    }

    public void menuCargos() {
        Scanner ler = new Scanner(System.in);
        while (true) {
            System.out.println("Cargos atuais:");
            System.out.println(model.getRoles());

            System.out.println("Deseja adicionar mais cargos? (s/sim/n/nao)");
            String escolha = ler.nextLine();

            if (escolha.toLowerCase().startsWith("n")) {
                System.out.println("Tchau!");
                System.out.println(model);
                return;
            }

            else if (escolha.toLowerCase().startsWith("s")) {
                List<String> rolesPossiveis = Stream.of(Roles.values()).
                        map(Roles::name).
                        collect(Collectors.toList());

                System.out.println("Adicione um cargo:");
                for (int i = 0; i < rolesPossiveis.toArray().length; i++) {
                    System.out.printf("%d - %s\n", i+1, rolesPossiveis.toArray()[i]);
                }

                int novoCargo = ler.nextInt();
                ler.nextLine();

                if (novoCargo > 0 && novoCargo < 5) {
                    model.addRole(Roles.valueOf(rolesPossiveis.toArray()[novoCargo-1].toString()));
                }
                else {
                    System.out.println("Cargo invalido!");
                }
            }

            else {
                System.out.println("Escolha invalida!");
            }
        }
    }
}
