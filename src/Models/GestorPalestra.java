package Models;

public class GestorPalestra {
    private Palestra palestraAssociada;

    public GestorPalestra() {
    }

    public GestorPalestra(Palestra palestraAssociada) {
        this.palestraAssociada = palestraAssociada;
    }

    @Override
    public String toString() {
        return "GestorPalestra{" +
                "palestraAssociada=" + palestraAssociada +
                '}';
    }

    public Palestra getPalestraAssociada() {
        return palestraAssociada;
    }

    public void setPalestraAssociada(Palestra palestraAssociada) {
        this.palestraAssociada = palestraAssociada;
    }

}
