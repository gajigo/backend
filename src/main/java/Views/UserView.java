package Views;

import Controllers.UserController;
import Models.Roles;
import Models.User;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserView {
    // salvar coisas foreign como fkID ao invez de objeto
    private UserController controller;

    public UserView(UserController controller) {
        this.controller = controller;
    }

    public void menu() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("-Menu Usuario-");
            System.out.println("1 - Crie um Usuario");
            System.out.println("2 - Edite um Usuario");
            System.out.println("3 - Delete um Usuario");
            System.out.println("4 - Listar Usuarios");
            System.out.println("0 - Sair");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 0:
                    return;
                case 1:
                    registrationMenu();
                    break;
                case 2:
                    editMenu();
                    break;
                case 3:
                    deleteMenu();
                    break;
                case 4:
                    System.out.println("-Usuarios Cadastrados-");
                    list();
                    break;
                default:
                    System.out.println("Escolha invalida!");
                    break;
            }
        }
    }

    public void registrationMenu() {
        Scanner input = new Scanner(System.in);
        User user = new User();

        System.out.println("-Criar Usuario-");
        System.out.println("Escreva um nome:");
        user.setName(input.nextLine());

        System.out.println("Escreva uma senha:");
        user.setNewPassword(input.nextLine());

        System.out.println("Escreva um e-mail:");
        user.setEmail(input.nextLine());

        rolesMenu(user);
        User novoUser = controller.register(user);

        System.out.println("Usuario " + novoUser.getId() + " registrado com sucesso!");
    }

    public void editMenu() {
        if (controller.getModels().size() == 0) {
            System.out.println("Nao existe usuarios para editar.");
            return;
        }

        Scanner input = new Scanner(System.in);

        System.out.println("-Editar Usuario-");
        list();

        System.out.println("Escolha um ID:");
        int id = input.nextInt();
        input.nextLine();

        User choice = controller.getById(id);
        if (choice == null) {
            System.out.println("Usuario nao encontrado!");
            return;
        }

        edit(choice);
    }

    public void deleteMenu() {
        Scanner input = new Scanner(System.in);

        if (controller.getModels().size() == 0) {
            System.out.println("Nao existe usuarios para deletar.");
            return;
        }

        System.out.println("-Deletar Usuario-");
        list();

        System.out.println("Escolha um ID:");
        Long id = input.nextLong();
        input.nextLine();

        if (controller.deleteById(id)) {
            System.out.println("Usuario deletado com sucesso!");
        } else {
            System.out.println("Nao foi possivel deletar o Usuario, confirme se escreveu o ID correto.");
        }
    }

    public boolean login(User user) {
        Scanner input = new Scanner(System.in);
        System.out.println("Escreva sua senha:");

        String password = input.nextLine();
        return user.checkLogin(password);
    }

    public void edit(User user) {
        if (!login(user)) {
            System.out.println("Senha incorreta!");
            return;
        }

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Informacoes:");
            System.out.printf("1 - %s: %s\n", "Nome", user.getName());
            System.out.printf("2 - %s: %s\n", "Senha", user.getPassword());
            System.out.printf("3 - %s: %s\n", "Cargos", user.getRoles());
            System.out.println("0 - Sair");

            System.out.println("Escolha uma opcao para mudar");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 0:
                    return;
                case 1:
                    System.out.println("Escreva um novo nome:");
                    user.setName(input.nextLine());
                    controller.editUser(user);
                    break;
                case 2:
                    System.out.println("Escreva uma nova senha:");
                    user.setNewPassword(input.nextLine());
                    controller.editUser(user);
                    break;
                case 3:
                    rolesMenu(user);
                    controller.editUser(user);
                    break;
                default:
                    System.out.println("Escolha invalida!");
                    break;
            }
        }
    }

    public void list() {
        for (User user : controller.getModels()) {
            System.out.printf("%d - %s\n", user.getId(), user.getName());
        }
    }

    public void rolesMenu(User user) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Cargos: " + user.getRoles());
            System.out.println("1 - Adicionar Cargo");
            System.out.println("2 - Remover Cargo");
            System.out.println("3 - Confirmar");
            int choice = input.nextInt();

            if (choice == 3) {
                return;
            } else if (choice < 1 || choice > 3) {
                System.out.println("Escolha invalida!");
                continue;
            }

            List<String> possibleRoles = Stream.of(Roles.values()).
                    map(Roles::name).
                    collect(Collectors.toList());

            System.out.println("Escolha um cargo:");
            for (int i = 0; i < possibleRoles.size(); i++) {
                System.out.printf("%d - %s\n", i+1, possibleRoles.get(i));
            }

            int selectedRole = input.nextInt() - 1;
            input.nextLine();

            if (selectedRole >= 0 && selectedRole < possibleRoles.size()) {
                Roles role = Roles.valueOf(possibleRoles.get(selectedRole));

                if (choice == 1) {
                    user.addRole(role);
                } else {
                    user.removeRole(role);
                }
            } else {
                System.out.println("Cargo invalido!");
            }
        }
    }
}
