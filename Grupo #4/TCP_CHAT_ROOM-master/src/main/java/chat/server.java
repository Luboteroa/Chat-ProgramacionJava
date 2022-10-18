package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class server implements Runnable {

    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;

    private static ArrayList<cliente> usuarios = new ArrayList<cliente>();
    private boolean done;
    private ExecutorService pool;

    public server(){
        connections = new ArrayList<>();
        done = false;
        grupo.crearGrupos();
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
                usuarios = listaUsuarios.agregarUsuario(cli, usuarios);
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
        for(cliente ch: usuarios){
            if(ch != null){
                ch.handler.sendMessage(message);
            }
        }
    }

    public static void broadcastByIp(String message, String ip){
        for(cliente ch: usuarios){
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
        for(cliente ch: usuarios){
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
              out.println("Por favor ingrese un nombre de usuario: ");
              nickname = in.readLine();
              //falta verificar que el nick que se ingrese este bien
                System.out.println(nickname + " conectado!");
                broadcast(nickname + " se unió al chat");



                String message;
                while((message = in.readLine()) != null){


                    if(message.startsWith("/")){

                        String h = protocolo.identificarProtocolo(message+client.getRemoteSocketAddress());
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
