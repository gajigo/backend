package Controllers;

import Models.Evento;
import Views.EventoView;

import java.io.*;

public class EventoController {
    private Evento model;
    private EventoView view;

    public EventoController() {
        this.model = new Evento();
        this.view = new EventoView(model, this);
    }

    public void start(){
        load();
        if (model.getNomeEvento() == null) {
            view.menuEvento();
        }
        else {
            System.out.println(model);
        }
        save();
    }
    public void cadastrar(String nomeEvento, String descricao) {
    model.setNomeEvento(nomeEvento);
    model.setDescricao(descricao);
/*    model.setModalidade(modalidade);
    model.setDataEvento(dataEvento); */
    }
    public void save(){
        try {
            FileOutputStream file = new FileOutputStream("evento.txt");
            ObjectOutputStream outfile = new ObjectOutputStream(file);
            outfile.writeObject(model);
            outfile.flush();
            outfile.close();
            file.close();
        } catch(Exception e) {
            System.out.println("nao foi possivel criar um evento");
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            FileInputStream file = new FileInputStream("evento.txt");
            ObjectInputStream infile = new ObjectInputStream(file);
            model = (Evento)  infile.readObject();
            infile.close();
            file.close();
        }   catch (Exception e) {
            System.out.println("nao foi possivel carregar o evento");
        }
    }

    public void clean() {
        File file = new File("evento.txt");
        if(!file.delete()) {
            System.out.println("Erro ao limpar arquivo");
        }
    }
}