package Chat_grupal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Servidor{

    public static void main(String[] args) {

        VentanaServidor mimarco = new VentanaServidor();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class VentanaServidor extends JFrame implements Runnable{

    private JTextArea areaTexto;
    private JButton BotonCliente;

    public VentanaServidor(){

        setBounds(1200,300,280,350);

        JPanel milamina = new JPanel();

        milamina.setLayout(new BorderLayout());

        areaTexto = new JTextArea();

        milamina.add(areaTexto, BorderLayout.CENTER);

        add(milamina);

        setVisible(true);

        Thread mihilo = new Thread(this);

        mihilo.start();
    }

    @Override
    public void run() {

        try{
            ServerSocket servidor = new ServerSocket(9999);

            String Nick, Grupo, Mensaje;

            Chat_grupal.PaqueteEnvio PaqueteRecibido;

            LinkedList<Usuarios> Grupos = new LinkedList<Usuarios>();

            while(true){
                Socket misocket = servidor.accept();

                ObjectInputStream Paquete_datos = new ObjectInputStream(misocket.getInputStream());

                PaqueteRecibido =(Chat_grupal.PaqueteEnvio) Paquete_datos.readObject();

                Nick = PaqueteRecibido.getNick();

                Grupo = PaqueteRecibido.getGrupo();

                Mensaje = PaqueteRecibido.getMensaje();

                if (!Mensaje.equals("/Nuevo Grupo")) {

                    areaTexto.append("\n " + Nick + ": " + Mensaje + " Para: " + Grupo);

                    for (Usuarios Elgrupo : Grupos)
                    {
                        Elgrupo.ConsultaGrupo(Grupo);

                        Socket EnvioDestinatario = new Socket(Elgrupo.Direccion, 9090);

                        ObjectOutputStream PaqueteReenvio = new ObjectOutputStream(EnvioDestinatario.getOutputStream());

                        PaqueteReenvio.writeObject(PaqueteRecibido);

                        PaqueteReenvio.close();

                        EnvioDestinatario.close();

                        misocket.close();
                    }


                }else {
                    //----------------------Crear Grupos--------------------------------------------

                    InetAddress Localizacion = misocket.getInetAddress();

                    String IpRemota = Localizacion.getHostAddress();

                    System.out.println("Online: "+ IpRemota);

                    Grupos.add(new Usuarios(Grupo, IpRemota));
                    //---------------------------------------------

                }
            }

        }catch(IOException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error : " +e);
        }

    }
}