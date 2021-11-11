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
        this.models = load();
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

    public List<T> load() {
        // Retorna a lista de modelos carregadas de um arquivo
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream infile = new ObjectInputStream(file);

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
        // Limpa o arquivo de usuarios
        // e recarrega a lista para jogar fora os dados
        File file = new File(filename);
        this.models = load();

        return file.delete();
    }

    public List<T> getModels() {
        // Adiciona modelo a lista
        return this.models;
    }

    public T addModel(T model) {
        // Registra um novo modelo para lista com ID automatico
        if (model.getId() == 0) {
            model.setId(getNewId());
        }

        this.models.add(model);

        // Retornamos o modelo para poder ser modificado depois
        return model;
    }

    public void removeModel(T model) {
        // Remove modelo da lista
        this.models.remove(model);
    }

    public long getNewId() {
        // Retorna um id novo que nao esta na lista
        // Percorremos a lista procurando pelo id de valor maximo
        long idMax = 0;
        for (T model : models) {
            idMax = max(idMax, model.getId());
        }

        // Depois que encontramos o ID maximo, retornamos ele + 1
        return idMax + 1;
    }

    public T getById(long id) {
        // Algoritmo: Binary Search
        // Pesquisa mais rapida (ao invez de checar todos elementos, checa log2(tamanho) na media)
        // Basicamente funciona dividindo o espaco pra procurar por 2 toda vez que testa uma posicao

        // low e high comecam apontando pro inicio e final da lista
        int low = 0, high = models.size() - 1;

        while (low < high) {
            // Pegamos o modelo no ponto medio da lista
            int med = (low + high) / 2;
            T found = models.get(med);

            if (found.getId() < id) {
                // Se o ID desse modelo for MENOR do que a gente procura
                // Procuramos novamente na parte SUPERIOR da lista (que vai ter IDs MAIORES)
                low = med + 1;
            } else if (found.getId() > id) {
                // Se o ID desse modelo for MAIOR do que a gente procura
                // Procuramos novamente na parte INFERIOR da lista (que vai ter IDs MENORES)
                high = med - 1;
            } else {
                // No caso (raro) do ponto medio ser o que a gente quer, retornamos ele direto.
                return found;
            }
        }

        // Se nao retornamos ja o modelo, precisamos testar se a posicao realmente contem o mesmo ID
        // Pois esse algoritmo na verdade retorna a posicao que PODE estar o que a gente procura.
        if (models.get(low).getId() == id) {
            return models.get(low);
        }

        // Se o ID da posicao nao for o que estamos procurando, o ID nao existe na lista,
        // entao, retornamos nulo para avisar quem estiver usando.
        return null;
    }

    public boolean deleteById(long id) {
        // Deleta um modelo na lista por id
        // Se conseguir, retorna true, se nao, false
        // Primeiro procuramos o elemento por ID
        T chosen = getById(id);

        // Se ele existir (nao for nulo), removemos e retornamos true
        if (chosen != null) {
            removeModel(chosen);
            return true;
        }

        // Se nao, retornamos false
        return false;
    }
}
