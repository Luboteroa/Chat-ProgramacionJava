import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

//Conexión TCP
public class Servidor {
    public static ArrayList<String> PersonasConectadas= new ArrayList<>();
    public static ArrayList<String> ListaDeGrupos= new ArrayList<>();

    public static Boolean verificarNombre(String mensaje){


        boolean check= mensaje.charAt(0) != '1' && mensaje.charAt(0) != '2' && mensaje.charAt(0) != '3' && mensaje.charAt(0) != '4'
                && mensaje.charAt(0) != '5' && mensaje.charAt(0) != '6' && mensaje.charAt(0) != '7'
                && mensaje.charAt(0) != '8' && mensaje.charAt(0) != '9' && mensaje.charAt(0) != '0';

        for (int i=0;i<PersonasConectadas.size();i++){

            if (mensaje.equalsIgnoreCase(PersonasConectadas.get(i))){
                check=false;

            }

        }

        return check;
    }

    public static void main(String[] args) {


        try {
            ServerSocket server = new ServerSocket(5000);
            Socket sc;

            System.out.println("Servidor iniciado");
            while (true){

                sc= server.accept();
                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out= new DataOutputStream(sc.getOutputStream());


                boolean stop= false;
                do{// Pido al cliente el nombre
                    out.writeUTF("Indica tu nombre");
                    String nombreCliente = in.readUTF().replace(" ","").toUpperCase();

                    if (verificarNombre(nombreCliente)) {

                        // Inicio el hilo
                        ServidorHilo hilo = new ServidorHilo(sc,in, out, nombreCliente);
                        hilo.start();

                        System.out.println("Creada la conexion con el cliente " + nombreCliente);

                        stop=true;
                        out.writeBoolean(true);

                        PersonasConectadas.add(nombreCliente);
                        ListaDeGrupos.add(hilo.GrupoNombre);

                        System.out.println("Personas conectadas : "+ Arrays.toString(PersonasConectadas.toArray()));
                        System.out.println("Lista de grupos: "+Arrays.toString(ListaDeGrupos.toArray()));
                    } else {
                        out.writeBoolean(false);
                    }
                }while (!stop);

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}