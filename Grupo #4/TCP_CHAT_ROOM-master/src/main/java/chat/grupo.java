package chat;

import java.util.ArrayList;

public class grupo {

    private static ArrayList<String> grupos = new ArrayList<String>();

    public static void crearGrupos() {
        grupos.add("GLOBAL");
    }

//    String crearGrupo(String nombre) {
//        grupos.add(nombre);
//        return "Grupo Creado Con Exito!!!";
//    }
//
    public static String mostrarGrupos() {
        return String.join(", ", grupos);
    }
//    public static

    /*
    public String validacionNombre(String nombre){
        String nombreSinEspacios = nombre.replaceAll("\\s", "");
        String nombreMayuscula = nombreSinEspacios.toUpperCase();
        
    }

     */
}
