package chat;
import java.io.IOException;
public class protocolo {

    public String comando;
    public String dato;
    public String emisor;

    public protocolo(String comando, String dato, String emisor){
        this.comando = comando;
        this.dato = dato;
        this.emisor = emisor;
    }

    public protocolo(){
        this.comando = null;
        this.dato = null;
        this.emisor = null;
    }

    // falta el lector de protocolos, etiqueta comando dato emisor
    public static String identificarProtocolo(String comandoingresado) throws IOException {
        protocolo Objprotocolo = null;
        String[] infoProtocolo = comandoingresado.split("/");
        if(infoProtocolo.length ==4){ //La cantidad no seria 3???? //es con 4 porque el primer iterador (0) es vacio

            String comando = infoProtocolo[1];
            String dato = infoProtocolo[2];
            String emisor = infoProtocolo[3];
            Objprotocolo = new protocolo(comando, dato, emisor);

            switch (Objprotocolo.comando) {
                case "mensaje":
                    //metodo
                    break;
                case "creargrupo":
                    //metodo
                    break;
                case "borrargrupo":
                    //metodo
                    break;
                case "salirgrupo":
                    //metodo
                    break;
                case "entrargrupo":
                    //metodo
                    break;
                case "cerrarconexion":
                    //metodo
                    //protocolo.cerrarconexion();

                    return "cerrar";


                default:
                    System.out.println("El comando no existe ");
                    break;
            }

            /*if(infoProtocolo[1].equals("mensaje") || infoProtocolo[1].equals("creargrupo") ||
               infoProtocolo[1].equals("borrargrupo") || infoProtocolo[1].equals("salirgrupo ||") ||  // queda un poco mas ordenado con switch
               infoProtocolo[1].equals("entrargrupo") || infoProtocolo[1].equals("cerrarconexion")){
               }else{
                return "Comando incorrecto.";
            }
             */
            return null;
        }else{
            return "linea no valida";
        }


    }
}
