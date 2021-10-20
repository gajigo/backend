import Models.*;
import Controllers.*;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        menuUsuario();
    }

    public static void menuUsuario() {
        UsuarioController controller = new UsuarioController();
        controller.start();
    }
}
