import Models.Roles;
import Models.Usuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Usuario usuarioTeste = new Usuario("Fabiano", "dQw4w9WgXcQ");
        System.out.println("----------------------------");
        // Testa que cargos iniciam vazios
        System.out.println("Cargos Iniciais: ");
        System.out.println(usuarioTeste.getRoles());

        // Testa adicionar novos cargos
        usuarioTeste.addRole(Roles.ORGANIZADOR);
        usuarioTeste.addRole(Roles.PALESTRANTE);

        System.out.println("Cargos apos adicionar: ");
        System.out.println(usuarioTeste.getRoles());

        // Testa se ignoramos cargos duplicados
        usuarioTeste.addRole(Roles.ORGANIZADOR);

        System.out.println("Cargos apos adicionar duplicado: ");
        System.out.println(usuarioTeste.getRoles());

        // Printa informacoes do usuario
        System.out.println(usuarioTeste);
        System.out.println("----------------------------");

        Scanner ler = new Scanner(System.in);

        System.out.println("Crie seu Usuario:");
        System.out.println("Escreva um nome:");
        String nome = ler.nextLine();

        System.out.println("Escreva uma senha:");
        String senha = ler.nextLine();

        Usuario usuario = new Usuario(nome, senha);

        System.out.println("Bem vindo " + usuario.getNome() + "!");

        while (true) {
            System.out.println("Roles atuais:");
            System.out.println(usuario.getRoles());
            System.out.println("Deseja adicionar roles? (s/sim/n/nao)");
            String input = ler.nextLine();

            if (input.startsWith("n")) {
                System.out.println("Tchau!");
                break;
            }
            if (input.startsWith("s")) {
                System.out.println("Escolha um role: ");
                System.out.println("1 - Organizador");
                System.out.println("2 - Admin");
                System.out.println("3 - Palestrante");
                System.out.println("4 - Cliente");

                int choice = ler.nextInt();
                ler.nextLine();

                Roles[] rolesPossiveis = Roles.values();

                if (choice > 0 && choice < 5) {
                    usuario.addRole(rolesPossiveis[choice-1]);
                }
            }
        }

    }
}
