package Controllers;

import Models.Usuario;
import Views.UsuarioView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UsuarioController {
    private Usuario model;
    private UsuarioView view;

    public UsuarioController() {
        this.model = new Usuario();
        this.view = new UsuarioView(model, this);
    }

    public void start() {
        load();
        if (model.getNome() == null) {
            view.menu();
        }
        else {
            System.out.println(model);
        }
        save();
        clean();
    }

    public void updateUsuario(String nome, String senha) {
        model.setNome(nome);
        model.setSenha(senha);
    }

    public void save() {
        File file = new File("usuario.txt");

        try {
            if (!file.createNewFile()) {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter pw = new PrintWriter(file);

            pw.println(model.getUserId());
            pw.println(model.getNome());
            pw.println(model.getSenha());
            pw.println(model.getStatusLogin());
            pw.println(model.getRoles());

            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(file.getAbsolutePath());
    }

    public void load() {
        File file = new File("usuario.txt");

        if (!file.exists()) {
            return;
        }

        // Em teoria nao precisaria do try/catch mas a IDE reclama :(
        model = new Usuario();
        try {
            Scanner ler = new Scanner(file);

            model.setUserId(ler.nextInt());
            ler.nextLine();

            model.setNome(ler.nextLine());
            model.setSenha(ler.nextLine());
            model.setStatusLogin(ler.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clean() {
        File file = new File("usuario.txt");

        if (!file.delete()) {
            System.out.println("Erro ao limpar arquivo");
        }
    }
}
