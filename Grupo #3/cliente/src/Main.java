import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static LinkedList<String> listachats=new LinkedList<>();
    static LinkedList<String> listaUsarios=new LinkedList<>();
    public static void main(String[] args) {
        final int port=8000;
        final String host ="127.0.0.1";
        String nombreUsuario="";
        PrintWriter printer;
        try (Socket cs=new Socket(host,port)) {
            InetAddress iaLocal = InetAddress.getLocalHost();
            String IPlocal=iaLocal.getHostAddress();
            printer=new PrintWriter(cs.getOutputStream(),true);
            listener escuchar=new listener(cs);
            Visor nuevoVisor=new Visor();
            printer.println("usuariosconectados//127.0.0.1\n");
            listaUsarios=new LinkedList<>(escuchar.escucarlinea());
            printer.println("chatscreados//127.0.0.1\n");
            listachats=new LinkedList<>(escuchar.escucarlinea());
            System.out.println("cliente conectado");
            nombreUsuario=nuevoVisor.mostrarregistronombres(listaUsarios,"Usuarios");
            printer.println("registro/"+nombreUsuario+"/"+IPlocal+"\n");
            Scanner input =new Scanner(System.in);
            while (true){
                nuevoVisor.comandos();
                String datosinput=input.nextLine();
                if (datosinput.equals("C")){
                    printer.println("chatscreados//127.0.0.1\n");
                    listachats=new LinkedList<>(escuchar.escucarlinea());
                    String nuevonombrechat=nuevoVisor.mostrarregistronombres(listachats,"Chat");
                    printer.println("creargrupo/"+nuevonombrechat+"/"+IPlocal+"\n");
                    //estamos dentro del grupo
                    System.out.println("entro al la sala de chat");
                    System.out.println("para volver: EXIT");
                    System.out.println("para borrar el grupo: CLOSE");
                    escuchar.start();
                    while (true){
                        String mensaje=input.nextLine();
                        if (mensaje.equals("EXIT")){
                            //salimos del grupo
                            escuchar.terminar();
                            printer.println("salirgrupo/"+nuevonombrechat+"/"+IPlocal+"\n");
                            System.out.println("saliste del chat");
                            break;
                        }
                        if (mensaje.equals("CLOSE")){
                            escuchar.terminar();
                            printer.println("borrargrupo/"+nuevonombrechat+"/"+IPlocal+"\n");
                            System.out.println("saliste del chat");
                            break;
                        }
                        printer.println("mensaje/"+mensaje+"/"+IPlocal+"\n");
                    }
                }
                if (datosinput.equals("E")) {
                    printer.println("chatscreados//127.0.0.1\n");
                    listachats=new LinkedList<>(escuchar.escucarlinea());
                    System.out.println("entro");
                    nuevoVisor.mostrarlista(listachats, "Chat");
                    boolean seguir=true;
                    boolean entrachat=false;
                    String nombrechat="";
                    while (seguir){
                        System.out.println("para volver: EXIT");
                        System.out.println("ingrese nombre del chat valido");
                        nombrechat=input.nextLine();
                        for (String index:listachats){
                            if (index.equals(nombrechat)){
                                seguir=false;
                                entrachat=true;
                                printer.println("entrargrupo/"+nombrechat+"/"+IPlocal+"\n");
                            }
                        }
                        if (nombrechat.equals("EXIT")){
                            seguir=false;
                        }
                    }
                    //estamos dentro del grupo
                    if (entrachat){
                        System.out.println("entro al la sala de chat");
                        System.out.println("para volver: EXIT");
                        System.out.println("para borrar el grupo: CLOSE");
                        escuchar.start();
                    }
                    while (entrachat){
                        String mensaje=input.nextLine();
                        if (mensaje.equals("EXIT")){
                            //salimos del grupo
                            escuchar.terminar();
                            printer.println("salirgrupo/"+nombrechat+"/"+IPlocal+"\n");
                            System.out.println("saliste del chat");
                            seguir=false;
                            entrachat=false;
                            break;
                        }
                        if (mensaje.equals("CLOSE")){
                            escuchar.terminar();
                            printer.println("borrargrupo/"+nombrechat+"/"+IPlocal+"\n");
                            System.out.println("saliste del chat");
                            seguir=false;
                            entrachat=false;
                            break;
                        }
                        printer.println("mensaje/"+mensaje+"/"+IPlocal+"\n");
                    }

                }
                if (datosinput.equals("T")) {
                    printer.println("cerrarconexion//"+IPlocal+"\n");
                    cs.close();
                    break;
                }
                /*printer.println("chatscreados//127.0.0.1\n");
                listachats=new LinkedList<>(escuchar.escucarlinea());*/
            }
        }catch (Exception e){
            System.out.println("error al conectarse con el servidor");
        }
    }
}
