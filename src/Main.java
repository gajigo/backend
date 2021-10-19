import Controllers.UsuarioController;
import Models.*;

import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println("--      Demo Eventos      --");
        System.out.println("----------------------------");
        demoEvento();

        System.out.println();
        System.out.println("----------------------------");
        System.out.println("--      Demo Usuario      --");
        System.out.println("----------------------------");
        demoUsuario();

        System.out.println();
        System.out.println("----------------------------");

        menuUsuario();
    }

    public static void menuUsuario() {
        UsuarioController controller = new UsuarioController();
        controller.start();
    }

    public static void demoEvento() {
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
                "NASA is inviting coders, designers, and technologists to come together in a global, virtual hackathon.",
                Modalidade.ONLINE
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

        atual.addPalestra(palestra1);
        atual.addPalestra(palestra2);

        System.out.println("Palestras: ");
        for (Palestra palestraAtual : atual.getPalestras()) {
            System.out.println(palestraAtual.getNome());
        }

        // Printa evento
        System.out.println(atual);
    }

    public static void demoUsuario() {
        Usuario usuarioTeste = new Usuario("Fabiano", "dQw4w9WgXcQ");

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
    }
}
