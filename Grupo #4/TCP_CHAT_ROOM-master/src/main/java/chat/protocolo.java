package chat;

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
    public static String identificarProtocolo(String comandoingresado){
        protocolo Objprotocolo = null;
        String[] infoProtocolo = comandoingresado.split("/");
        if(infoProtocolo.length ==4){
            String comando = infoProtocolo[1];
            String dato = infoProtocolo[2];
            String emisor = infoProtocolo[3];
            Objprotocolo = new protocolo(comando, dato, emisor);


            return (Objprotocolo.emisor).toString();
        }else{
            return "Comando ingresado no valido";
        }



        //if (comandoingresado.comando == "mensaje")
    }
}
