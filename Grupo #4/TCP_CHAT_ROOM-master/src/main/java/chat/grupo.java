package chat;

import java.util.ArrayList;

public class grupo {
    static ArrayList<String> grupos;


    public static void crearListaGrupos() {
        grupos = new ArrayList();
        grupos.add("GLOBAL");
        System.out.println(grupos.get(0));
    }

    public static String crearGrupo(String nombre) {
        grupos.add(nombre);
        return "Grupo Creado Con Exito!!!";
    }

    public static String mostrarGrupos() {
        StringBuilder sb = new StringBuilder();
        for (String g : grupos)
        {
            sb.append(g);
            sb.append("\t");
        }
        return sb.toString();
    }

    /*
    public String validacionNombre(String nombre){
        String nombreSinEspacios = nombre.replaceAll("\\s", "");
        String nombreMayuscula = nombreSinEspacios.toUpperCase();
        
    }

     */
}
