package chat;
import java.util.ArrayList;
public class listaUsuarios {

    public static ArrayList<cliente> listaU = new ArrayList<cliente>();
    public static void agregarUsuario(cliente cli) {
        listaU.add(cli);
    }

    public static ArrayList<cliente> getListaU(){
        return listaU;
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
