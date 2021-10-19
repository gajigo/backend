package Views;

import Controllers.UsuarioController;
import Models.Roles;
import Models.Usuario;

import java.util.Locale;
import java.util.Scanner;

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


    public void menu() {
        Scanner ler = new Scanner(System.in);

        System.out.println("-Crie seu Usuario-");

        System.out.println("Escreva seu nome:");
        String nome = ler.nextLine();

        System.out.println("Escreva sua senha:");
        String senha = ler.nextLine();

        controller.updateUsuario(nome, senha);
        System.out.println("Bem vindo " + model.getNome() + "!");
        System.out.println(model);

        while (true) {
            System.out.println("Cargos atuais:");
            System.out.println(model.getRoles());

            System.out.println("Deseja adicionar mais cargos? (s/sim/n/nao)");
            String escolha = ler.nextLine();

            if (escolha.toLowerCase().startsWith("n")) {
                System.out.println("Tchau!");
                System.out.println(model);
                break;
            }
            if (escolha.toLowerCase().startsWith("s")) {
                System.out.println("Adicione um cargo:");
                System.out.println("1 - Organizador");
                System.out.println("2 - Admin");
                System.out.println("3 - Palestrante");
                System.out.println("4 - Cliente");

                int novoCargo = ler.nextInt();
                ler.nextLine();

                Roles[] rolesPossiveis = Roles.values();
                if (novoCargo > 0 && novoCargo < 5) {
                    model.addRole(rolesPossiveis[novoCargo-1]);
                }
                else {
                    System.out.println("Cargo invalido!");
                }
            }
        }
    }
}
