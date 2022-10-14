package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class cliente implements Runnable{

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;


    @Override
    public void run() {
        try{
            String ip = "127.0.0.1";
            int puerto = 9999;
            client = new Socket(ip, puerto);

            out = new PrintWriter(client.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inHandler = new InputHandler();
            Thread t = new Thread(inHandler);
            t.start();

            String inMessage;
            while((inMessage = in.readLine()) != null){
                System.out.println(inMessage);
            }
        } catch (IOException e){
            //TODO: agregar para server inactivo
            shutdown();
        }
    }

    public void shutdown(){
         done = true;
         try {
             in.close();
             out.close();
             if(!client.isClosed()){
                 client.close();
             }
         } catch (IOException e){
             //TODO: make custom exception here
            e.printStackTrace();
         }
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {
            try{
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while(!done){

                    String message = inReader.readLine();
                    if(message.startsWith("/")){
                        String h = protocolo.identificarProtocolo(message+client.getRemoteSocketAddress());
                        
                        if(h == "cerrar"){
                            out.println(message);
                            inReader.close();
                            client.close();
                        }

                        //shutdown();
                    } else {
                        out.println(message);
                    }
                }
            } catch (IOException e){
                shutdown();
            }
        }
    }

    public static void main(String[] args){
        cliente cliente = new cliente();
        cliente.run();
    }
}
