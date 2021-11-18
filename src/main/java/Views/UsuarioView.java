package Views;

import Controllers.UsuarioController;
import Models.Roles;
import Models.User;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsuarioView {
    // salvar coisas foreign como fkID ao invez de objeto
    private UsuarioController controller;

    public UsuarioView(UsuarioController controller) {
        this.controller = controller;
    }

    public void menu() {
        Scanner ler = new Scanner(System.in);
        while (true) {
            System.out.println("-Menu Usuario-");
            System.out.println("1 - Crie um Usuario");
            System.out.println("2 - Edite um Usuario");
            System.out.println("3 - Delete um Usuario");
            System.out.println("4 - Listar Usuarios");
            System.out.println("0 - Sair");

            int escolha = ler.nextInt();

            switch (escolha) {
                case 0:
                    return;
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
                    System.out.println("-Usuarios Cadastrados-");
                    listar();
                    break;
                default:
                    System.out.println("Escolha invalida!");
                    break;
            }
        }
    }

    public void menuRegistrar() {
        Scanner ler = new Scanner(System.in);

        User user = new User();

        System.out.println("-Criar Usuario-");
        System.out.println("Escreva um nome:");
        user.setNome(ler.nextLine());

        System.out.println("Escreva uma senha:");
        user.setSenha(ler.nextLine());

        System.out.println("Escreva um e-mail:");
        user.setEmail(ler.nextLine());

        User novoUser = controller.registrar(user);
        menuCargos(novoUser);

        System.out.println("Usuario " + novoUser.getId() + " registrado com sucesso!");
    }

    public void menuEditar() {
        if (controller.getModels().size() == 0) {
            System.out.println("Nao existe usuarios para editar.");
            return;
        }

        Scanner ler = new Scanner(System.in);

        System.out.println("-Editar Usuario-");
        listar();

        System.out.println("Escolha um ID:");
        int id = ler.nextInt();
        ler.nextLine();

        User escolha = controller.getById(id);
        if (escolha == null) {
            System.out.println("Usuario nao encontrado!");
            return;
        }

        edite(escolha);
    }

    public void menuDeletar() {
        if (controller.getModels().size() == 0) {
            System.out.println("Nao existe usuarios para deletar.");
            return;
        }

        Scanner ler = new Scanner(System.in);

        System.out.println("-Deletar Usuario-");
        listar();

        System.out.println("Escolha um ID:");
        Long id = ler.nextLong();
        ler.nextLine();

        if (controller.deleteById(id)) {
            System.out.println("Usuario deletado com sucesso!");
        } else {
            System.out.println("Nao foi possivel deletar o Usuario, confirme se escreveu o ID correto.");
        }
    }

    public boolean login(User user) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Escreva sua senha:");

        String senha = ler.nextLine();
        return user.getSenha().equals(senha);
    }

    public void edite(User user) {
        Scanner ler = new Scanner(System.in);

        if (!login(user)) {
            System.out.println("Senha incorreta!");
            return;
        }

        while (true) {
            System.out.println("Informacoes:");
            System.out.printf("1 - %s: %s\n", "Nome", user.getNome());
            System.out.printf("2 - %s: %s\n", "Senha", user.getSenha());
            System.out.printf("3 - %s: %s\n", "Cargos", user.getRoles());
            System.out.println("0 - Sair");

            System.out.println("Escolha uma opcao para mudar");
            int escolha = ler.nextInt();
            ler.nextLine();

            switch (escolha) {
                case 0:
                    return;
                case 1:
                    System.out.println("Escreva um novo nome:");
                    user.setNome(ler.nextLine());
                    break;
                case 2:
                    System.out.println("Escreva uma nova senha:");
                    user.setSenha(ler.nextLine());
                    break;
                case 3:
                    menuCargos(user);
                    break;
                default:
                    System.out.println("Escolha invalida!");
                    break;
            }
        }
    }

    public void listar() {
        for (User user : controller.getModels()) {
            System.out.printf("%d - %s\n", user.getId(), user.getNome());
        }
    }

    public void menuCargos(User user) {
        Scanner ler = new Scanner(System.in);
        while (true) {
            System.out.println("Cargos: " + user.getRoles());
            System.out.println("1 - Adicionar Cargo");
            System.out.println("2 - Remover Cargo");
            System.out.println("3 - Confirmar");
            int escolha = ler.nextInt();

            if (escolha == 3) {
                return;
            } else if (escolha < 1 || escolha > 3) {
                System.out.println("Escolha invalida!");
                continue;
            }

            List<String> rolesPossiveis = Stream.of(Roles.values()).
                    map(Roles::name).
                    collect(Collectors.toList());

            System.out.println("Escolha um cargo:");
            for (int i = 0; i < rolesPossiveis.size(); i++) {
                System.out.printf("%d - %s\n", i+1, rolesPossiveis.get(i));
            }

            int cargoEscolhido = ler.nextInt() - 1;
            ler.nextLine();

            if (cargoEscolhido >= 0 && cargoEscolhido < rolesPossiveis.size()) {
                Roles cargo = Roles.valueOf(rolesPossiveis.get(cargoEscolhido));

                if (escolha == 1) {
                    user.addRole(cargo);
                } else {
                    user.removeRole(cargo);
                }
            } else {
                System.out.println("Cargo invalido!");
            }
        }
    }
}
