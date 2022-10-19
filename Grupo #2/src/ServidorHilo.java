import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorHilo extends Thread{
    private DataInputStream in;
    private Socket sc;
    private DataOutputStream out;
    private String nombreCliente;
    String NombreDelGrupo;

    public static ArrayList<String> ListaDeGrupos= new ArrayList<>();
    String GrupoNombre;

    public ServidorHilo(Socket sc, DataInputStream in, DataOutputStream out, String nombreCliente) {
        this.in = in;
        this.sc=sc;
        this.out = out;
        this.nombreCliente = nombreCliente;
    }
    @Override
    public void run(){
        int opcion;
        String mensaje;
        boolean salir=false;

        while(!salir){

            try {
                opcion = in.readInt();

                switch (opcion){
                    case 1:
                        out.writeUTF("Ingrese el nombre del grupo: ");
                        GrupoNombre=(in.readUTF());
                        ListaDeGrupos.add(GrupoNombre);
                        System.out.println("El nombre del grupo: " +GrupoNombre);
                        break;
                    case 2:
                        out.writeUTF("Ingrese nombre del grupo que desea eliminar: ");
                        NombreDelGrupo=in.readUTF();
                        ListaDeGrupos.remove(NombreDelGrupo);
                        break;
                    case 3:
                        break;
                    case 4:
                        System.out.println("Lista de grupos: "+ListaDeGrupos);
                        out.writeUTF("Lista: "+ListaDeGrupos);
                        break;
                    case 5:
                        salir=true;
                        break;
                    default:
                        out.writeUTF("Solo numeros del 1 al 6");

                }

            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        try {
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE,null,ex);
        }
        System.out.println("Conexion cerrada con el cliente "+nombreCliente);
    }
}
