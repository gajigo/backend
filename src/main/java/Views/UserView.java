package Views;

import Controllers.UserController;
import Models.Roles;
import Models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserView {
    private UserController controller = new UserController();

    public UserView() {
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-Menu Usuario-");
            System.out.println("1 - Crie um Usuario");
            System.out.println("2 - Edite um Usuario");
            System.out.println("3 - Delete um Usuario");
            System.out.println("4 - Listar Usuarios");
            System.out.println("0 - Sair");

            int choice = ViewUtils.getChoice(scanner);
            if (choice == -1) {
                System.out.println("Escolha invalida!");
                continue;
            }

            switch (choice) {
                case 0:
                    return;
                case 1:
                    menuCreate();
                    break;
                case 2:
                    menuEdit();
                    break;
                case 3:
                    menuDelete();
                    break;
                case 4:
                    System.out.println("-Usuarios Cadastrados-");
                    listUsers();
                    break;
                default:
                    System.out.println("Escolha invalida!");
                    break;
            }
        }
    }

    public void menuCreate() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        System.out.println("-Criar Usuario-");
        System.out.println("Escreva um nome:");
        user.setName(scanner.nextLine());

        System.out.println("Escreva uma senha:");
        user.setNewPassword(scanner.nextLine());

        System.out.println("Escreva um e-mail:");
        user.setEmail(scanner.nextLine());

        menuRoles(user);
        try {
            user = controller.addUser(user);
            System.out.println("Usuario com ID " + user.getId() + " registrado com sucesso!");
        }catch (SQLException e){
            System.out.println("Falha ao registrar Usuario");
        }
    }

    public void menuEdit() {
        try {
            if (controller.getModels().size() == 0) {
                System.out.println("Nao existe usuarios para editar.");
                return;
            }

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("-Editar Usuario-");
                listUsers();

                System.out.println("Escolha um ID:");
                Long id = (long) ViewUtils.getChoice(scanner);
                if (id == -1) {
                    System.out.println("ID Invalido!");
                    continue;
                }

                User user = controller.getById(id);
                if (user == null) {
                    System.out.println("Usuario nao encontrado!");
                    return;
                }

                edit(user);
                return;
            }
        }catch (SQLException e){
            System.out.println("Falha ao Editar Usuario");
        }
    }

    public void menuDelete() {
        try {
            if (controller.getModels().size() == 0) {
                System.out.println("Nao existe usuarios para deletar.");
                return;
            }

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("-Deletar Usuario-");
                listUsers();

                System.out.println("Escolha um ID:");
                Long id = (long) ViewUtils.getChoice(scanner);
                if (id == -1) {
                    System.out.println("ID Invalido!");
                    continue;
                }

                if (!login(controller.getById(id))) {
                    System.out.println("Senha incorreta!");
                    return;
                }

                if (controller.deleteById(id)) {
                    System.out.println("Usuario deletado com sucesso!");
                } else {
                    System.out.println("Nao foi possivel deletar o Usuario, confirme se escreveu o ID correto.");
                }
                return;
            }
        }catch (SQLException e){
            System.out.println("Falha ao Remover Usuario");
        }
    }

    public boolean login(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escreva sua senha:");

        String password = scanner.nextLine();
        return user.checkLogin(password);
    }

    public void edit(User user) {
        if (!login(user)) {
            System.out.println("Senha incorreta!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Informacoes:");
            System.out.printf("1 - Nome: %s\n", user.getName());
            System.out.printf("2 - Senha: %s\n", user.getPassword());
            System.out.printf("3 - Cargos: %s\n", user.getRoles());
            System.out.println("0 - Sair");

            System.out.println("Escolha uma opcao para mudar");
            int choice = ViewUtils.getChoice(scanner);
            if (choice == -1) {
                System.out.println("Escolha invalida!");
                continue;
            }

            try {
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("Escreva um novo nome:");
                        user.setName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Escreva uma nova senha:");
                        user.setNewPassword(scanner.nextLine());
                        break;
                    case 3:
                        menuRoles(user);
                        break;
                    default:
                        System.out.println("Escolha invalida!");
                        break;
                }
                controller.editUser(user);
            }catch (SQLException e){
                System.out.println("Falha ao editar Usuario");
            }
        }
    }

    public void listUsers() {
        try {
            for (User user : controller.getModels()) {
                System.out.printf("%d - %s\n", user.getId(), user.getName());
            }
        }catch (SQLException e){
            System.out.println("Falha ao recuperar Usuarios Cadastrados");
        }
    }

    public void menuRoles(User user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Cargos: " + user.getRoles());
            System.out.println("1 - Adicionar Cargo");
            System.out.println("2 - Remover Cargo");
            System.out.println("3 - Confirmar");
            int choice = ViewUtils.getChoice(scanner);
            if (choice == -1) {
                System.out.println("Escolha invalida!");
                continue;
            }

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

            int selectedRole = ViewUtils.getChoice(scanner);
            if (selectedRole == -1) {
                System.out.println("Escolha invalida!");
                continue;
            }

            selectedRole -= 1;
            if (selectedRole >= 0 && selectedRole < possibleRoles.size()) {
                Roles role = Roles.valueOf(possibleRoles.get(selectedRole));

                if (choice == 1) {
                    try {
                        controller.addRole(user, role);
                    }catch (SQLException e){
                        System.out.println("Falha ao adicionar Cargo");
                    }
                } else {
                    try {
                        controller.removeRole(user, role);
                    }catch (SQLException e){
                        System.out.println("Falha ao remover Cargo");
                    }
                }
            } else {
                System.out.println("Cargo invalido!");
            }
        }
    }
}
