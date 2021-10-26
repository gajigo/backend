package Views;

import Controllers.UsuarioController;
import Models.Roles;
import Models.Usuario;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsuarioView {
    private UsuarioController controller;

    public UsuarioView(UsuarioController controller) {
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
            int escolha = ler.nextInt();

            switch (escolha) {
                case 1:
                    menuRegistrar();
                    break;
                case 2:
                    menuEditar();
                    break;
                case 3:
                    menuDeletar();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Escolha invalida!");
                    break;
            }
        }
    }

    public void menuRegistrar() {
        Scanner ler = new Scanner(System.in);

        System.out.println("-Criar Usuario-");
        System.out.println("Escreva um nome:");
        String nome = ler.nextLine();

        System.out.println("Escreva uma senha:");
        String senha = ler.nextLine();

        Usuario novoUsuario = controller.registrar(nome, senha);
        System.out.println("Usuario " + nome + " registrado com sucesso!");

        while (true) {
            System.out.println("Cargos: " + novoUsuario.getRoles());
            System.out.println("Deseja adicionar cargos? (s/sim/n/nao):");
            String escolha = ler.nextLine();

            if (escolha.toLowerCase().startsWith("s")) {
                List<String> rolesPossiveis = Stream.of(Roles.values()).
                        map(Roles::name).
                        collect(Collectors.toList());

                System.out.println("Adicione um cargo:");
                for (int i = 0; i < rolesPossiveis.size(); i++) {
                    System.out.printf("%d - %s\n", i+1, rolesPossiveis.get(i));
                }

                int novoCargo = ler.nextInt() - 1;
                ler.nextLine();

                if (novoCargo >= 0 && novoCargo < rolesPossiveis.size()) {
                    novoUsuario.addRole(Roles.valueOf(rolesPossiveis.get(novoCargo)));
                } else {
                    System.out.println("Cargo invalido!");
                }
            } else if (escolha.toLowerCase().startsWith("n")) {
                System.out.println("Cargos confirmados!");
                return;
            } else {
                System.out.println("Nao existe essa opcao, tente novamente.");
            }
        }
    }

    public void menuEditar() {
        Scanner ler = new Scanner(System.in);

        System.out.println("-Editar Usuario-");
        listar();

        System.out.println("Escolha um ID:");
        int id = ler.nextInt();
        ler.nextLine();

        Usuario escolha = controller.getById(id);
        if (escolha == null) {
            System.out.println("Usuario nao encontrado!");
            return;
        }

        System.out.println(escolha);
    }

    public void menuDeletar() {
        Scanner ler = new Scanner(System.in);

        System.out.println("-Deletar Usuario-");
        listar();

        System.out.println("Escolha um ID:");
        int id = ler.nextInt();
        ler.nextLine();

        if (controller.deleteById(id)) {
            System.out.println("Usuario deletado com sucesso!");
        } else {
            System.out.println("Nao foi possivel deletar o Usuario, confirme se escreveu o ID correto.");
        }
    }

    public void listar() {
        for (Usuario usuario: controller.getModels()) {
            System.out.printf("%d - %s\n", usuario.getUserId(), usuario.getNome());
        }
    }
}
