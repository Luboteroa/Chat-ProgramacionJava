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
    public void identificarProtocolo(String comandoingresado){

        String[] infoProtocolo = comandoingresado.split("/");
        this.comando = infoProtocolo[0];
        this.dato = infoProtocolo[1];
        //this.emisor = infoProtocolo[2];
        //if (comandoingresado.comando == "mensaje")
    }
}
