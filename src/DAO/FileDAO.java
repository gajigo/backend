package DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO<T> {
    private String filename;

    public FileDAO(String filename) {
        this.filename = filename;
    }

    public int save(List<T> models) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream outfile = new ObjectOutputStream(file);
            outfile.writeObject(models);
            outfile.flush();
            outfile.close();
            file.close();
        } catch (Exception e) {
            return -1;
        }

        return 0;
    }

    public List<T> load() {
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream infile = new ObjectInputStream(file);

            // FODA SE
            @SuppressWarnings("unchecked")
            List<T> models = (ArrayList<T>) infile.readObject();

            infile.close();
            file.close();

            return models;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public int clean() {
        File file = new File(filename);

        if (!file.delete()) {
            return -1;
        }

        return 0;
    }
}
