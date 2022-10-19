package Chats;
import java.util.ArrayList;
public class Lista_Usuarios {

    ArrayList<Usuario> nicks;


    public void crearArrayList() {

        nicks = new ArrayList();

    }

    public void insertarNick(Usuario x) {

        nicks.add(x);


    }

    public String devolverInformacion() {
        String reporte = "";
        for (int contador = 0; contador < nicks.size(); contador++) {
            reporte += nicks.get(contador).toString() + "\n";
        }
        return reporte;
    }

    public boolean revision(String x) {


        String old;
        String nuevo = x;
        String y = nuevo.replaceAll("\\s+", "");
        boolean supervisor = revisarnumero(y);
        if (supervisor == false) {
            for (int contador = 0; contador < nicks.size(); contador++) {
                old = nicks.get(contador).toString().replaceAll("\\s+", "");
                if (y.equals(old)) {
                    supervisor = true;
                }
            }
        }


        return supervisor;
    }

    public static boolean revisarnumero(String x) {
        boolean aprueba = false;
        char[] arreglo = x.toCharArray();
        String r = String.valueOf(arreglo[0]);
        if (aprueba = (r != null && r.matches("[0-9]+"))) {
            aprueba = true;
        }
        return aprueba;
    }
}