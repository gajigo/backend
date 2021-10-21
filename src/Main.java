import Controllers.EventoController;
import Controllers.UsuarioController;

public class Main {
    public static void main(String[] args) {
        menuEvento();
    }

    public static void menuUsuario() {
        UsuarioController controller = new UsuarioController();
        controller.start();
    }

    public static void menuEvento() {
        EventoController controller = new EventoController();
        controller.start();
    }

}
