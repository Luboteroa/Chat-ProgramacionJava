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
    public static String normalizacionNombre(String nombre) {
        String nombSinEspacios = nombre.replaceAll(" ", "");
        String nombreNormalizado = nombSinEspacios.toUpperCase();
        return nombreNormalizado;
    }
    public static int validacionNickname(String nickNormalizado, int caracterEspecial){
        int valorASCII=0;
        System.out.println("-----------*****-----------");
        for(int j=0; j < nickNormalizado.length(); j++){
            char caracter = nickNormalizado.charAt(j);
            valorASCII = (int)caracter;
            //System.out.println("posición: "+ j+ " Valor ASCII "+valorASCII);
            if(j == 0 && (valorASCII < 65 || valorASCII > 90)){
                System.out.println("entra al 1er if en la iteracion #"+j);
                caracterEspecial++;
            }
            if(j>0 && (valorASCII < 48 || valorASCII > 90) || (valorASCII > 57 && valorASCII < 65)){
                System.out.println("entra al 2do if en la iteracion #"+j);
                caracterEspecial++;
            }
        }
        return caracterEspecial;
    }
}
