import java.util.LinkedList;
import java.util.Scanner;

public class Visor {
    public void mostrarlista(LinkedList<String> lista,String mensaje){
        System.out.println(mensaje);
        for (int i=0;i<lista.size();i++){
            System.out.println(lista.get(i));
        }
    }
    public String mostrarregistronombres(LinkedList<String> lista, String mensaje){
        Scanner input=new Scanner(System.in);
        System.out.println("ingrese nombre de "+mensaje);
        boolean nombreAcceptable=true;
        while (true){
            String nombreUsuario=input.nextLine();
            String nombreUsuariomodificado=nombreUsuario.toUpperCase();
            nombreUsuariomodificado=nombreUsuariomodificado.replace(" ","");
            if (!esunnumero(nombreUsuariomodificado.toCharArray()[0])){
                for (String index:lista){
                    String indexmodificado=index.toUpperCase();
                    indexmodificado=indexmodificado.replace(" ", "");
                    if (indexmodificado.equals(nombreUsuariomodificado)){
                        nombreAcceptable=false;
                    }
                }
            }
            if (nombreAcceptable){
                return nombreUsuario;
            }else {
                System.out.println("ingrese un nombre de "+mensaje+" valido");
            }
        }


    }
    public void comandos(){
        System.out.println("crear chat: C");
        System.out.println("entrar grupo: E");
        System.out.println("terminar conexion: T");
    }

    public void mostrarchat(String nombrechat){
        System.out.println("ha entrado en el grupo de chat "+nombrechat);
    }
    public boolean esunnumero(char dato){
        char[] numero={'1','2','3','4','5','6','7','8','9'};
        for (int i=0;i<9;i++){
            if (dato==numero[i]){
                return true;
            }
        }
        return false;
    }
}
