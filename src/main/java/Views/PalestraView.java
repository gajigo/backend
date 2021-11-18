package Views;

import Controllers.PalestraController;
import Models.Palestra;

import java.util.List;
import java.util.Scanner;

public class PalestraView {
    private PalestraController controller;

    public PalestraView(PalestraController controller) {
        this.controller = controller;
    }

    public void menu() {
        while (true) {
            Scanner ler = new Scanner(System.in);

            System.out.println("1 - Criar Palestra");
            System.out.println("2 - Selecionar Palestra");
            System.out.println("0 - Voltar");

            int escolha = ler.nextInt();
            switch (escolha) {
                case 1:
                    criarPalestra();
                    break;
                case 2:
                    selecionarPalestra();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Escolha Invalida");
                    break;
            }
        }
    }

    public void criarPalestra(){
        Scanner ler = new Scanner(System.in);

        System.out.println("Digite o nome da palestra");
        String nome = ler.nextLine();

        System.out.println("Digite a descricao da palestra");
        String descricao = ler.nextLine();

        System.out.println("Digite a data da palestra");
        String data = ler.nextLine();

        System.out.println("Digite a duracao da palestra");
        String duracao = ler.nextLine();

        System.out.println("Digite o idioma da palestra");
        String idioma =  ler.nextLine();

        System.out.println(controller.criarPalestra(nome,descricao,data,duracao,idioma).getNome() + "criada com sucesso");

    }

    public void selecionarPalestra(){
        Scanner ler = new Scanner(System.in);
        List<String> nomePalestra = controller.listaPalestras();
        int i = 0;

        while (nomePalestra.size() > i){
            System.out.println((i + 1) + " - " + nomePalestra.get(i));
            i = i+1;
        }
        System.out.println("0 - Voltar");
        int escolha = ler.nextInt();

        if (escolha == 0){
            return;
        }
        String escolhida = controller.palestraIntString(nomePalestra, (escolha - 1));
        if (escolha <= nomePalestra.size()){
            palestraEscolhida(controller.palestraEscolhida(escolhida));
        }
    }

    public void palestraEscolhida(Palestra palestra){
        Scanner ler = new Scanner(System.in);

        System.out.println("1 - Mostrar");
        System.out.println("2 - Deletar");
        System.out.println("3 - Editar");
        System.out.println("4 - Participar");
        System.out.println("0 - Voltar");

        int escolha = ler.nextInt();
        switch (escolha){
            case 1:
                mostraPalestra(palestra);
                break;
            case 2:
                deletarPalestra(palestra);
                break;
            case 3:
                menuEdita(palestra);
                break;
            case 4:
                participarPalestra(palestra);
                break;
            case 0:
                return;
            default:
                System.out.println("Escolha invalida");
                break;
        }
    }

    public void mostraPalestra(Palestra palestra){
        Scanner ler = new Scanner(System.in);

        System.out.println("Nome: " + palestra.getNome());
        System.out.println("ID: " + palestra.getId());
        System.out.println("Data de Inicio: " + palestra.getDataInicio());
        System.out.println("Duracao: " + palestra.getDuracao());
        System.out.println("Idiomas: " + palestra.getIdioma());
        System.out.println("Assuntos: " + palestra.getAssuntos());
        System.out.println("Palestrantes: " + palestra.getPalestrantes());
        System.out.println();
        System.out.println("1 - Listar Duvidas");
        System.out.println("2 - Listar Participantes");
        System.out.println("0 - Voltar");

        int escolha = ler.nextInt();

        switch (escolha){
            case 1:
                listarDuvida(palestra);
                break;
            case 2:
                listarParticipantes(palestra);
                break;
            case 0:
                return;
            default:
                System.out.println("Escolha Invalida");
                break;
        }
    }

    public void deletarPalestra(Palestra palestra){
        if(!controller.deletarPalestra(palestra)){
            System.out.println("Palestra Deletada");
        }else{
            System.out.println("Erro ao Deletar");
        }
    }

    public void menuEdita(Palestra palestra){
        while (true){

            System.out.println("1 - Nome");
            System.out.println("2 - Duracao");
            System.out.println("3 - Data");
            System.out.println("4 - Descricao");
            System.out.println("5 - Idiomas");
            System.out.println("6 - Assunto");
            System.out.println("7 - Palestrante");
            System.out.println("0 - Voltar");

            Scanner ler = new Scanner(System.in);
            int escolha = ler.nextInt();

            switch (escolha){
                case 1:
                    editaNome(palestra);
                    break;
                case 2:
                    editaDuracao(palestra);
                    break;
                case 3:
                    editaData(palestra);
                    break;
                case 4:
                    editaDescricao(palestra);
                    break;
                case 5:
                    editaIdioma(palestra);
                    break;
                case 6:
                    editaAssunto(palestra);
                    break;
                case 7:
                    editaPalestrante(palestra);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Escolha invalida");
                    break;
            }
        }
    }

    public void enviarDuvida(Palestra palestra){
        Scanner ler = new Scanner(System.in);

        System.out.println("Digite sua duvida:");
        String duvida = ler.nextLine();

        controller.enviarDuvida(palestra, duvida);
    }

    public void listarDuvida(Palestra palestra){
        Scanner ler = new Scanner(System.in);

        System.out.println("Duvidas:");
        System.out.println(palestra.getDuvidas());

        int escolha = ler.nextInt();

        switch (escolha){
            case 1:
                enviarDuvida(palestra);
                break;
            case 0:
                return;
            default:
                System.out.println("Escolha invalida");
        }
    }

    public void participarPalestra(Palestra palestra){
        Scanner ler = new Scanner(System.in);

        System.out.println("Digite seu Nome");
        String usuario = ler.nextLine();

        if(controller.participarPalestra(palestra, usuario)){
            System.out.println("Presenca Contabilizada");
        }else{
            System.out.println("Falha ao adicionar Presenca");
        }
    }

    public void listarParticipantes(Palestra palestra){
        System.out.println("Participantes:");
        System.out.println(palestra.getParticipantes());
    }

    public void editaNome(Palestra palestra){
        Scanner ler = new Scanner(System.in);

        System.out.println("Nome atual: " + palestra.getNome());
        System.out.println("Insira nome novo:");
        String novoNome = ler.nextLine();
        controller.editaNome(palestra,novoNome);

    }

    public void editaDuracao(Palestra palestra){
        Scanner ler = new Scanner(System.in);

        System.out.println("Duracao atual: " + palestra.getDuracao());
        System.out.println("Insira duracao nova:");
        String novaDuracao = ler.nextLine();
        controller.editaDuracao(palestra,novaDuracao);
    }

    public void editaData(Palestra palestra){
        Scanner ler = new Scanner(System.in);

        System.out.println("Data atual: " + palestra.getDataInicio());
        System.out.println("Insira data nova:");
        String novaData = ler.nextLine();
        controller.editaData(palestra,novaData);
    }

    public void editaDescricao(Palestra palestra){
        Scanner ler = new Scanner(System.in);

        System.out.println("Descricao atual: " + palestra.getDescricao());
        System.out.println("Insira descricao nova");
        String novaDescricao = ler.nextLine();
        controller.editaDescricao(palestra,novaDescricao);
    }

    public void editaIdioma(Palestra palestra){
        Scanner ler = new Scanner(System.in);
        System.out.println("Idioma atual: " + palestra.getIdioma());
        System.out.println("Deseja ADICIONAR ou REMOVER Idioma:");
        String escolha = ler.nextLine();

        if (escolha.compareToIgnoreCase("ADICIONAR") == 0){
            System.out.println("Insira idioma novo");
            String novoIdioma = ler.nextLine();
            controller.adicionaIdioma(palestra,novoIdioma);
        }

        if (escolha.compareToIgnoreCase("REMOVER") == 0){
            System.out.println("Insira idioma a ser removido");
            String removeIdioma = ler.nextLine();
            controller.removeIdioma(palestra,removeIdioma);
        }
    }

    public void editaAssunto(Palestra palestra){
        Scanner ler = new Scanner(System.in);

        System.out.println("Assunto atual: " + palestra.getAssuntos());
        System.out.println("Deseja ADICIONAR ou REMOVER Assunto");
        String escolha = ler.nextLine();

        if (escolha.compareToIgnoreCase("ADICIONAR") == 0){
            System.out.println("Insira assunto novo");
            String novoAssunto = ler.nextLine();
            controller.adicionaAssunto(palestra,novoAssunto);
        }

        if (escolha.compareToIgnoreCase("REMOVER") == 0){
            System.out.println("Insira assunto a ser removido");
            String removeAssunto = ler.nextLine();
            controller.removeAssunto(palestra,removeAssunto);
        }
    }
    public void editaPalestrante(Palestra palestra){
        Scanner ler = new Scanner(System.in);

        System.out.println("Palestrante atual: " + palestra.getPalestrantes());
        System.out.println("Deseja ADICIONAR ou REMOVER Palestrante");
        String escolha = ler.nextLine();

        if (escolha.compareToIgnoreCase("ADICIONAR") == 0){
            System.out.println("Insira palestrante novo");
            String novoPalestrante = ler.nextLine();
            controller.adicionaPalestrante(palestra,novoPalestrante);
        }

        if (escolha.compareToIgnoreCase("REMOVER") == 0){
            System.out.println("Insira palestrante a ser removido");
            String removePalestrante = ler.nextLine();
            controller.removePalestrante(palestra,removePalestrante);
        }
    }
}

