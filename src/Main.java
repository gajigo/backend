import Models.Evento;
import Models.Palestra;
import Models.Roles;
import Models.Usuario;

import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Data do Evento
        Calendar dataEvento = Calendar.getInstance();

        // Seta Ano/Mes/Dia
        dataEvento.set(Calendar.YEAR, 2021);
        dataEvento.set(Calendar.MONTH, Calendar.OCTOBER);
        dataEvento.set(Calendar.DAY_OF_MONTH, 1);

        // Reseta Hora/Minuto/Segundo
        dataEvento.set(Calendar.HOUR_OF_DAY, 0);
        dataEvento.set(Calendar.MINUTE, 0);
        dataEvento.set(Calendar.SECOND, 0);

        // Inicializa o Evento
        Evento atual = new Evento(
                "Nasa Space Apps",
                dataEvento.getTime(),
                "NASA is inviting coders, designers, and technologists to come together in a global, virtual hackathon.",
                "Online"
        );

        // Adiciona organizadores
        Usuario organizador1 = new Usuario("Alice", "senhamassa");
        Usuario organizador2 = new Usuario("Bob", "senhalegal");

        // Adiciona o cargo de organizador
        organizador1.addRole(Roles.ORGANIZADOR);
        organizador2.addRole(Roles.ORGANIZADOR);

        // Associa organizadores ao evento
        atual.addOrganizador(organizador1);
        atual.addOrganizador(organizador2);

        System.out.println("Organizadores:");
        for (Usuario organizadorAtual : atual.getOrganizadores()) {
            System.out.println(organizadorAtual.getNome());
        }

        // Adiciona palestra
        Palestra palestra1 = new Palestra("Aula de Java", "Aprenda mais sobre Java");
        Palestra palestra2 = new Palestra("Workshop de Arduino", "Aprenda a trabalhar com Arduinos na pratica");

        atual.addPalestraAssociada(palestra1);
        atual.addPalestraAssociada(palestra2);

        System.out.println("Palestras: ");
        for (Palestra palestraAtual : atual.getPalestrasAssociadas()) {
            System.out.println(palestraAtual.getNome());
        }

        // Printa evento
        System.out.println(atual);
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
