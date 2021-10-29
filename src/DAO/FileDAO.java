package DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO<T> {
    private String filename;
    private List<T> models;

    public FileDAO(String filename) {
        this.filename = filename;
        this.models = load();
    }

    public boolean save() {
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

    public boolean clean() {
        File file = new File(filename);
        return file.delete();
    }

    public List<T> getModels() {
        return this.models;
    }

    public void addModel(T model) {
        this.models.add(model);
    }

    public void removeModel(T model) {
        this.models.remove(model);
    }
}
