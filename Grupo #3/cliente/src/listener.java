import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;

public class listener extends Thread{
    private Socket cs;
    private boolean ejecutar=true;


    public listener(Socket cs) throws IOException {
        this.cs=cs;
    }
    public void run(){
        try {
            BufferedReader buffer=new BufferedReader(new InputStreamReader(cs.getInputStream()));
            ejecutar=true;
            while (ejecutar){
                String datos=buffer.readLine();
                if (datos!=null){
                    String[] datosProsesados=datos.split("/");
                    System.out.println(datosProsesados[1]);
                }
            }
        }catch (Exception e){
            System.out.println("error al leer mensaje");
        }
    }
    public void terminar(){
        this.ejecutar=false;
    }
    public LinkedList<String> escucarlinea(){
        LinkedList<String> nuevalista=new LinkedList<>();
        try {
            BufferedReader buffer=new BufferedReader(new InputStreamReader(cs.getInputStream()));
            while (true){
                String datos=buffer.readLine();
                if (datos!=null){
                    String[] datosprosesados=datos.split("/");
                    for (String index:datosprosesados){
                        nuevalista.add(index);
                    }
                    return nuevalista;
                }
            }
        }catch (Exception e){
            System.out.println("error al leer el mensage");
            return nuevalista;
        }
    }
}
