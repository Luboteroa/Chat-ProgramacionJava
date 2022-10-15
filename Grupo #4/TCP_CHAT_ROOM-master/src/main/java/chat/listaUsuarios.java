package chat;
import java.util.ArrayList;
public class listaUsuarios {

    static ArrayList<cliente> listUsuarios;


    public static void crearlistaUsuarios() {
        listUsuarios = new ArrayList();
    }

    public static void agregarUsuario(cliente cli) {
        listUsuarios.add(cli);
    }

//    public static String usuariosRegistrados() {
//        String usuariosRegistrados = "";
//        for (int i = 0; i < cli.size(); i++) {
//            usuariosRegistrados += cli.get(i).toString() + "\n";
//        }
//        return usuariosRegistrados;
//    }



}
