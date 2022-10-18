import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

public class conexiones extends Thread{
    private Socket cs;
    private String direccionip="";
    private LinkedList<String> listausuarios;
    private LinkedList<chats> listachats;
    private LinkedList<conexiones> listaconexiones;
    //private static PrintWriter responder;
    public conexiones(Socket cs, LinkedList<String> listausuarios,LinkedList<chats> listachats,LinkedList<conexiones> listaconexiones){
        this.cs=cs;
        this.listausuarios= listausuarios;
        this.listachats=listachats;
        this.listaconexiones=listaconexiones;
        /*try {
            this.responder=new PrintWriter(cs.getOutputStream(),true);
        }catch (Exception e){
            System.out.println("error creando Print writer");
        }*/

    }

    public void run() {
        try {
            BufferedReader buffer=new BufferedReader(new InputStreamReader(cs.getInputStream()));
            while (true) {
                String datos = buffer.readLine();
                if (datos != null){
                    //System.out.println(direccionip);
                    String[] datosseparados= datos.split("/");
                    if (datosseparados[0].equals("mensaje")){
                        for(chats index: listachats){
                            if (index.estaenelchat(direccionip)){
                                index.send(datosseparados[1],direccionip,listaconexiones);
                            }
                        }
                    }else if (datosseparados[0].equals("creargrupo")){
                        chats nuevochat=new chats(datosseparados[1],datosseparados[2]);
                        listachats.add(nuevochat);
                        System.out.println("chat creado");
                        //nuevochat.mostrarusuarios();

                    }else if (datosseparados[0].equals("borrargrupo")){
                        //comprobar si esta solo en el chat - pendiente
                        for (int i=0;i<listachats.size();i++){
                            if (listachats.get(i).getNombre().equals(datosseparados[1])){
                                listachats.remove(i);
                                System.out.println("se borro el grupo"+datosseparados[1]);
                            }
                        }
                    }else if (datosseparados[0].equals("salirgrupo")){
                        for (chats index:listachats){
                            if (index.getNombre().equals(datosseparados[1])){
                                index.salirchat(datosseparados[2]);
                                System.out.println(datosseparados[2]+" salio de un grupo");
                                //index.mostrarusuarios();
                            }
                        }
                    }else if (datosseparados[0].equals("entrargrupo")){
                        for (chats index:listachats){
                            if (index.getNombre().equals(datosseparados[1])){
                                index.entrarchat(datosseparados[2]);
                                System.out.println(datosseparados[2]+" entro de un grupo");
                                //index.mostrarusuarios();
                            }
                        }
                    }else if (datosseparados[0].equals("cerrarconexion")){
                        cs.close();
                        System.out.println("se a cerrado la conecicon con "+direccionip);
                        break;
                    }else if (datosseparados[0].equals("usuariosconectados")){
                        String datosLstausuario=codificar(listausuarios,"listausuarios");
                        enviarlista(datosLstausuario);
                    }else if (datosseparados[0].equals("chatscreados")){
                        LinkedList<String> listachatsnombres=nombresChats();
                        String datoschats=codificar(listachatsnombres,"listachats");
                        enviarlista(datoschats);
                    }else if (datosseparados[0].equals("registro")){
                        if(!siEsta(listausuarios,datosseparados[1])){
                            listausuarios.add(datosseparados[1]);
                            direccionip=datosseparados[2];
                            System.out.println(datosseparados[1]+" se registro");
                        }
                    }
                    //System.out.println(datosseparados[0]);
                }
            }
            System.out.println("se ha terminado la conexion");
        }catch (Exception e ){
            System.out.println("error al leer el mensaje");
        }


    }
    public boolean siEsta(LinkedList<String> lista, String objetivo){
        for (String index:lista){
            if (index.equals(objetivo)){
                return true;
            }
        }
        return false;
    }
    public String codificar(LinkedList<String> lista,String comando){
        String datos= comando;
        for(String index:lista){
            datos=datos+"/"+index;
        }
        return datos+"\n";
    }
    public String getDireccionip(){
        return direccionip;
    }
    public void envarMensaje(String mensaje){
        try{
            PrintWriter responder=new PrintWriter(this.cs.getOutputStream(),true);
            responder.println("mensaje/"+mensaje);
            //responder.close();
            System.out.println("mensaje enviado");
        }catch (Exception e){
            System.out.println("error creando Print writer");
        }
    }
    public void enviarlista(String nombrelista){
        try{
            PrintWriter responder=new PrintWriter(this.cs.getOutputStream(),true);
            responder.println(nombrelista);
            System.out.println("lista enviada");
        }catch (Exception e){
            System.out.println("error creando Print writer");
        }
    }
    public LinkedList<String> nombresChats(){
        LinkedList<String> datos=new LinkedList<>();
        for (chats index : listachats){
            datos.add(index.getNombre());
        }
        return datos;
    }
}
