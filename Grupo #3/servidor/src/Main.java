import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.chrono.IsoEra;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static LinkedList<String> listausuarios=new LinkedList<>();
    static LinkedList<chats> listachats=new LinkedList<>();
    static LinkedList<conexiones> listaConexiones=new LinkedList<>();
    public static void main(String[] args) {

        final int port =8000;

        try(ServerSocket socketservidor=new ServerSocket(port)){
            System.out.println("servidor iniciado");
            while (true){
                Socket socket =socketservidor.accept();
                conexiones nuevaConexion=new conexiones(socket,listausuarios,listachats,listaConexiones);
                nuevaConexion.start();
                listaConexiones.add(nuevaConexion);
                System.out.println(listaConexiones.size());
            }
        }catch (Exception e){
            System.out.println("error al inicar puerto servidor");
        }

    }
}
