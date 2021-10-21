package DAO;

import java.io.*;
import java.util.List;

public class FileDAO<T> {
    private String filename;

    public FileDAO(String filename) {
        this.filename = filename;
    }

    public void save(List<T> models) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream outfile = new ObjectOutputStream(file);
            outfile.writeObject(models);
            outfile.flush();
            outfile.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Nao foi possivel salvar usuario");
        }
    }

    public List<T> load() {
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream infile = new ObjectInputStream(file);

            // FODA SE
            @SuppressWarnings("unchecked")
            List<T> models = (List<T>) infile.readObject();

            infile.close();
            file.close();

            return models;
        } catch (Exception e) {
            System.out.println("Nao foi possivel carregar usuarios");
            return null;
        }
    }

    public void clean() {
        File file = new File(filename);

        if (!file.delete()) {
            System.out.println("Erro ao limpar arquivo");
        }
    }
}
