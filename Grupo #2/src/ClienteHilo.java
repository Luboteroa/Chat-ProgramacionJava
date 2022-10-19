import javax.imageio.IIOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteHilo extends Thread{

    private DataInputStream in;
    private DataOutputStream out;

    public ClienteHilo(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }
    @Override
    public void run(){
        Scanner sn = new Scanner(System.in);
        sn.useDelimiter("\n");
        String mensaje;
        int opcion=0;
        boolean salir = false;

        while(!salir){
            try {
                System.out.println("1. Crear un grupo de chat");
                System.out.println("2. Borrar grupo de chat");
                System.out.println("3. Entrar a un grupo de chat");
                System.out.println("4. Lista de grupos");
                System.out.println("5. Salir");

                opcion = sn.nextInt();
                out.writeInt(opcion);
                switch (opcion) {
                    case 1://Agregar grupos
                        String NombreDeGrupo= in.readUTF();
                        System.out.println(NombreDeGrupo);
                        String Grupo = sn.next();
                        out.writeUTF(Grupo);
                        break;
                    case 2://ELiminar grupos
                        String RemoveGrupo= in.readUTF();
                        System.out.println(RemoveGrupo);
                        String GrupoR = sn.next();
                        out.writeUTF(GrupoR);
                        break;
                    case 3:
                        break;
                    case 4://MostrarLista de grupos
                        String ListaDeGrupos=in.readUTF();
                        System.out.println(ListaDeGrupos);
                        break;
                    case 5:
                        salir=true;
                        break;
                    default:
                        mensaje = in.readUTF();
                        System.out.println(mensaje);

                }
            } catch(IOException ex) {
                Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE,null, ex);
            }

        }

    }
}
