package DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class FileDAO<T extends DAOUser> {
    private String filename;
    private List<T> models;

    public FileDAO(String filename) {
        this.filename = filename;
    }

    public boolean save() {
        // Salva a lista de modelos para um arquivo
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream outfile = new ObjectOutputStream(file);
            outfile.writeObject(models);

            outfile.flush();
            outfile.close();
            file.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean clean() {
        // Limpa o arquivo de usuarios
        // e recarrega a lista para jogar fora os dados
        File file = new File(filename);

        return file.delete();
    }
}
