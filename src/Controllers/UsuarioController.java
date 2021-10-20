package Controllers;

import Models.Usuario;
import Views.UsuarioView;

import java.io.*;
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
    }

    public void registrar(String nome, String senha) {
        model.setNome(nome);
        model.setSenha(senha);
    }

    public void save() {
        try {
            FileOutputStream file = new FileOutputStream("usuario.txt");
            ObjectOutputStream outfile = new ObjectOutputStream(file);
            outfile.writeObject(model);
            outfile.flush();
            outfile.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Nao foi possivel salvar usuario");
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            FileInputStream file = new FileInputStream("usuario.txt");
            ObjectInputStream infile = new ObjectInputStream(file);
            model = (Usuario) infile.readObject();
            infile.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Nao foi possivel carregar usuario");
        }
    }

    public void clean() {
        File file = new File("usuario.txt");

        if (!file.delete()) {
            System.out.println("Erro ao limpar arquivo");
        }
    }
}
