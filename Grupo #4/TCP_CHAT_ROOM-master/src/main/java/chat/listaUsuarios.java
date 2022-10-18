package chat;
import java.util.ArrayList;
public class listaUsuarios {
    public  static ArrayList<cliente> agregarUsuario(cliente cli, ArrayList<cliente> usuarios) {
        usuarios.add(cli);
        return usuarios;
    }

//    public ArrayList<cliente> getListaUsuarios() {
//
//        return usuarios;
//    }

//    public static String usuariosRegistrados() {
//        String usuariosRegistrados = "";
//        for (int i = 0; i < cli.size(); i++) {
//            usuariosRegistrados += cli.get(i).toString() + "\n";
//        }
//        return usuariosRegistrados;
//    }



}
