package chat;

import java.util.ArrayList;

public class grupo {
    static ArrayList<String> grupos;

    public String validacionNombre(String nombre){
        String nombreSinEspacios = nombre.replaceAll("\\s", "");
        String nombreMayuscula = nombreSinEspacios.toUpperCase();
        
    }
}
