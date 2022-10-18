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
        //Le quita los espacios a la cadena
        String nombreSinEspacios = nombre.replaceAll("\\s", "");
        boolean esValido = true;
        for(int i=0; i<nombreSinEspacios.length(); i++){
            //Evalua cada caracter de la cadena y lo pone en mayuscula
            char caracter = nombreSinEspacios.toUpperCase().charAt(i);
            //Se obtiene el valor en codigo ASCII de cada caracter
            int valorASCII = (int)caracter;
            //Valida si el primer caracter es un numero, si asi es, se sale del ciclo
            if(i==0 && (valorASCII >= 48 || valorASCII <= 64)){
                System.out.println("El primer caracter no puede ser un numero");
                esValido = false;
                break;
            }
            if(i > 0 && (valorASCII > 57 || valorASCII < 65) && valorASCII != 165 && (valorASCII < 48 || valorASCII > 90)){
                esValido = false;
            }
        }
        if(!esValido){
            System.out.println("El nombre de usuario NO puede contener caracteres especiales");
        }
        String nombreValidado = nombreSinEspacios.toUpperCase();
        return nombreValidado;
    }

     */
}
