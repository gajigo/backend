package Controllers;

import DAO.PalestraDAO;
import Models.*;
import Views.PalestraView;

import java.util.ArrayList;
import java.util.List;

public class PalestraController {
    private List<Palestra> model;
    private PalestraView view;
    private PalestraDAO dao;

    public PalestraController() {
        this.dao = new PalestraDAO();
        this.model = dao.listSeminars();
        this.view = new PalestraView(this);
    }

    public void start(){
        view.menu();
        if (!dao.save()) {
            System.out.println("Erro ao salvar palestras!");
        }
    }
    public List<Palestra> getModels() {
        return dao.getModels();
    }

    public Palestra criarPalestra(String nome,String descricao, String data, String duracao, String idioma){
        Palestra novaPalestra = new Palestra();
        Idioma novoIdioma = new Idioma();

        novaPalestra.setNome(nome);
        novaPalestra.setDescricao(descricao);
        novaPalestra.setDataInicio(data);
        novaPalestra.setDuracao(duracao);
        novoIdioma.setNome(idioma);
        novaPalestra.getIdioma().add(novoIdioma);

        model.add(novaPalestra);
        return dao.createSeminars(novaPalestra);
    }

    public List<String> listaPalestras(){
        List<String> listaString = new ArrayList<>();

        for (Palestra palestra : model) {
            listaString.add(palestra.getNome());
        }

        return listaString;

    }

    public String palestraIntString(List <String> nomePalestra, int escolha){
        return nomePalestra.get(escolha);
    }

    public Palestra palestraEscolhida(String nomePalestra){
        for (Palestra palestra : model) {
            if (palestra.getNome().compareTo(nomePalestra) == 0) return palestra;
        }

        return null;
    }

    public boolean deletarPalestra(Palestra palestra){
//        palestra.setStatus(false);
//        return palestra.isStatus();
        dao.deleteSeminar(palestra);
        return false;

    }

    public void enviarDuvida(Palestra palestra,String duvida){
        DuvidaPalestra novaDuvida = new DuvidaPalestra();
        novaDuvida.setDuvida(duvida);
        palestra.getDuvidas().add(novaDuvida);
    }

    public boolean participarPalestra(Palestra palestra,String novoUsuario){
        User user = new User();
        user.setName(novoUsuario);
        palestra.getParticipantes().add(user);
        return true;
    }

    public void editaNome(Palestra palestra, String novoNome){
        palestra.setNome(novoNome);
    }

    public void editaDuracao(Palestra palestra, String novaDuracao){
        palestra.setDuracao(novaDuracao);
    }

    public void editaData(Palestra palestra, String novaData){
        palestra.setDataInicio(novaData);
    }

    public void editaDescricao(Palestra palestra, String novaDescricao){
        palestra.setDescricao(novaDescricao);
    }

    public void adicionaIdioma(Palestra palestra, String novoIdioma){
        Idioma idioma = new Idioma(novoIdioma);
        palestra.getIdioma().add(idioma);
    }

    public void removeIdioma(Palestra palestra, String idioma){
        int i = 0;

        while(palestra.getIdioma().size() > i){
            if(palestra.getIdioma().get(i).getNome().compareToIgnoreCase(idioma) != 0){
                palestra.getIdioma().remove(i);
            }
            i = i + 1;
        }
    }

    public void adicionaAssunto(Palestra palestra, String novoAssunto){
        Assunto assunto = new Assunto();
        assunto.setNome(novoAssunto);
        palestra.getAssuntos().add(assunto);
    }

    public void removeAssunto(Palestra palestra, String assunto){
        int i = 0;

        while(palestra.getAssuntos().size() > i){
            if(palestra.getAssuntos().get(i).getNome().compareToIgnoreCase(assunto) != 0){
                palestra.getAssuntos().remove(i);
            }
            i = i + 1;
        }
    }

    public void adicionaPalestrante(Palestra palestra, String novoPalestrante){
        User palestrante = new User();
        palestrante.setName(novoPalestrante);
        palestra.getPalestrantes().add(palestrante);
    }

    public void removePalestrante(Palestra palestra, String palestrante){
        int i = 0;

        while(palestra.getPalestrantes().size() > i){
            if(palestra.getPalestrantes().get(i).getName().compareToIgnoreCase(palestrante) != 0){
                palestra.getPalestrantes().remove(i);
            }
            i = i + 1;
        }
    }

    public void editaPalestra(Palestra palestra){
        dao.editSeminar(palestra);
    }
}
