package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class server implements Runnable {

    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;


    private boolean done;
    private ExecutorService pool;

    public server(){
        connections = new ArrayList<>();
        done = false;
        //grupo.crearGrupos();
    }

    public static void messageServer(String message) {
        System.out.println(message);
    }

    @Override
    public void run(){
        try {

            server = new ServerSocket( 9999);
            pool = Executors.newCachedThreadPool();
            while(!done){
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                cliente cli = new cliente(handler, client.getRemoteSocketAddress().toString(), "GLOBAL");
                if(listaUsuarios.getListaU().size() == 0){
                    chat.grupo.crearGrupo("GlobalGroup");
                }
                listaUsuarios.agregarUsuario(cli);


                for(cliente ch: listaUsuarios.getListaU()){
                    if(ch != null){
                        System.out.println(ch.toString());
                    }
                }
                pool.execute(handler);
            }

        } catch (Exception e) {
            try {
                shutDown();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void broadcast(String message){
        for(cliente ch: listaUsuarios.getListaU()){
            if(ch != null){
                ch.handler.sendMessage(message);
            }
        }
    }

    public static void broadcastbygroup(String grupo,String message){

        for(cliente ch: listaUsuarios.getListaU()){
            if(ch.grupo == grupo){
                //ch.handler.sendMessage(message);
            }
        }
    }


    public static void broadcastByIp(String message, String ip){

        for(cliente ch: listaUsuarios.getListaU()){
            if(ch != null){
                ch.handler.sendMessage(message);
            }
        }
    }

    public void shutDown() throws Exception {
        done = true;
        pool.shutdown();
        if(!server.isClosed()){
            server.close();
        }
        for(cliente ch: listaUsuarios.getListaU()){
            ch.handler.shutDownIndividualConnection();
        }
    }

    class ConnectionHandler implements Runnable {
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String nickname;

        public ConnectionHandler(Socket client){
            this.client = client;
        }
        @Override
        public void run(){

            try{
              out = new PrintWriter(client.getOutputStream(), true);
              in = new BufferedReader(new InputStreamReader(client.getInputStream()));
              out.println("Bienvenido");
              int numEspeciales=0;
              for(int i =0; i <= numEspeciales; i++){
                    if(numEspeciales>0){
                        out.println("WARNING: El nombre de usuario contiene caracteres especiale. \n Intente con otro");
                        numEspeciales=0;
                    }
                  //System.out.println(numEspeciales+" --> (1ra) BANDERA, iteracion: "+i);
                  out.println("Ingrese un nick para su usuario: ");
                  nickname = in.readLine();
                  String nickNormalizado = grupo.normalizacionNombre(nickname);
                  //System.out.println("---------> "+nickNormalizado);
                  numEspeciales = grupo.validacionNickname(nickNormalizado, numEspeciales);
                  //System.out.println(numEspeciales+" --> FIN DEL FOR");
                  if(numEspeciales==0){break;}
              }
                out.println("***--->NOMBRE VALIDADO CON EXITO<---***");
                System.out.println(nickname + " conectado!");
                broadcast(nickname + " se unio al chat");


                String message;
                while((message=in.readLine()) != null){

                    if(message.startsWith("/")){

                        protocolo h = protocolo.identificarProtocolo(message+client.getRemoteSocketAddress());
                        System.out.println(h);



                        /*
                        String[] messageSplit = message.split(" ", 2);
                        if(messageSplit.length == 2){
                            broadcast(nickname + " se cambio el nombre a "+ messageSplit[1] );
                            System.out.println(nickname + " se cambio el nombre a "+ messageSplit[1] );
                            nickname = messageSplit[1];
                            out.println("El nombre de usuario fue satisfactoriamente cambiado a: "+ nickname);
                        } else{
                            out.println("No se brindó un nombre de usuario");
                        }*/
                    } else if(message.startsWith("salir")){
                        broadcast(nickname + " salio del chat ):");
                        System.out.println(nickname + " salio del chat ):");
                        //shutDownIndividualConnection(); desconecta el server?
                    } else{
                        broadcast(nickname + ": " +message);
                    }

//                    if(message.startsWith("/listausuarios ")){
//                        String h = listaUsuarios.usuariosRegistrados();
//                        System.out.println(h);
//                    }
                }
            }
            catch (IOException e){
                try {
                    shutDown();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        public void sendMessage(String message){
            out.println(message);
        }

        public void shutDownIndividualConnection() throws IOException {
            in.close();
            out.close();
            if(!client.isClosed()){
                client.close();
            }
        }
    }

    public static void main(String[] args) {
        chat.server server = new server();
        server.run();
    }
}
