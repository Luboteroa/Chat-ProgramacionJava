package chat;

import java.util.ArrayList;

public class grupo {

    public static ArrayList<String> grupos = new ArrayList<String>();

    /*
    public static void crearGrupos() {
        grupos.add("GLOBAL");
    }
    */

    public static void crearGrupo(String nombre) {
       grupos.add(nombre);
   }

    public static ArrayList<String> mostrarGrupos() {

        return grupos;
    }
//    public static

    /*
    public String validacionNombre(String nombre){
        String nombreSinEspacios = nombre.replaceAll("\\s", "");
        String nombreMayuscula = nombreSinEspacios.toUpperCase();
        
    }

     */
}
