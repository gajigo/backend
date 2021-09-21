package Models;

public class GestorPalestra {
    private Palestra palestraAssociada;

    public Palestra getPalestraAssociada() {
        return palestraAssociada;
    }

    public void setPalestraAssociada(Palestra palestraAssociada) {
        this.palestraAssociada = palestraAssociada;
    }

    public void addParticipante(Usuario participante) {
        palestraAssociada.addParticipante(participante);
    }

}
