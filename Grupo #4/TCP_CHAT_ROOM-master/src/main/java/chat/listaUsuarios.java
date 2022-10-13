package chat;
import java.util.ArrayList;
public class listaUsuarios {

    static ArrayList<String> nicknames;


    public static void crearlistaUsuarios() {
        nicknames = new ArrayList();
    }

    public static void agregarUsuario(String nickname) {
        nicknames.add(nickname);
    }

    public static String usuariosRegistrados() {
        String usuariosRegistrados = "";
        for (int i = 0; i < nicknames.size(); i++) {
            usuariosRegistrados += nicknames.get(i).toString() + "\n";
        }
        return usuariosRegistrados;
    }

}
