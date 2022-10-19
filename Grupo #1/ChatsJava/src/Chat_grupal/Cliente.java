package Chat_grupal;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) {

        VentanaCliente mimarco = new VentanaCliente();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class VentanaCliente extends JFrame{

    public VentanaCliente(){

        setBounds(600,300,280,350);

        LaminaVentanaCliente milamina = new LaminaVentanaCliente();

        add(milamina);

        setVisible(true);
    }

}

class LaminaVentanaCliente extends JPanel implements Runnable{

    private JTextField Campo1;
    private JLabel Nick;
    private JComboBox Grupo;
    private JButton BotonEnviar, BotonNuevoGrupo, BotonUnirGrupo;
    private JTextArea AreaChat;

    public LaminaVentanaCliente(){

        String Nick_Usuario = JOptionPane.showInputDialog("Nick: ");

        JLabel n_Nick = new JLabel("Nick: ");

        add(n_Nick);

        Nick = new JLabel();

        Nick.setText(Nick_Usuario);

        add(Nick);

        JLabel Texto = new JLabel("Grupos: ");

        add(Texto);

        Grupo = new JComboBox();

        Grupo.addItem("Chatjava");

        add(Grupo);

        AreaChat = new JTextArea(12,20);

        add(AreaChat);

        Campo1 = new JTextField(20);

        add(Campo1);

        BotonEnviar = new JButton("Enviar");

        EnviaTexto mievento= new EnviaTexto();

        BotonEnviar.addActionListener(mievento);

        add(BotonEnviar);

        BotonNuevoGrupo = new JButton("Crear Grupo");

        NuevoGrupo CrearGrupo= new NuevoGrupo();

        BotonNuevoGrupo.addActionListener(CrearGrupo);

        add(BotonNuevoGrupo);

        Thread mihilo = new Thread(this);

        mihilo.start();

    }

    @Override
    public void run() {

        try{

            ServerSocket ServidorCliente = new ServerSocket(9090);

            Socket Cliente;

            PaqueteEnvio PaqueteRecibido;

            while (true){

                Cliente = ServidorCliente.accept();

                ObjectInputStream Entrada = new ObjectInputStream(Cliente.getInputStream());

                PaqueteRecibido = (PaqueteEnvio) Entrada.readObject();

                AreaChat.append("\n" + PaqueteRecibido.getNick() + ": " + PaqueteRecibido.getMensaje());

            }

        }catch (Exception e){

            System.out.println(e.getMessage());
        }

    }

   private class EnviaTexto implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {

            AreaChat.append("\n" + "Me: " + Campo1.getText());

            try {

                Socket socket = new Socket("192.168.1.52",9999);

                PaqueteEnvio datos = new PaqueteEnvio();

                datos.setNick(Nick.getText());

                datos.setGrupo(Grupo.getSelectedItem().toString());

                datos.setMensaje(Campo1.getText());

                ObjectOutputStream Paquete_datos = new ObjectOutputStream(socket.getOutputStream());

                Paquete_datos.writeObject(datos);

                socket.close();

                Campo1.setText(" ");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Error : " +ex + " No se pudo realizar la conexión . ");
            }

        }
    }


    //-------------------------CREAR GRUPO----------------------------------
    private class NuevoGrupo implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {

            String N_Grupo = JOptionPane.showInputDialog("Nombre del grupo: ");

            try {

                Socket socket = new Socket("127.0.0.1",9999);

                PaqueteEnvio datos = new PaqueteEnvio();

                datos.setMensaje("/Nuevo Grupo");
                datos.setGrupo(N_Grupo);

                ObjectOutputStream Paquete_datos = new ObjectOutputStream(socket.getOutputStream());

                Paquete_datos.writeObject(datos);

                socket.close();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Error : " +ex + " No se pudo realizar la conexión . ");
            }

        }
    }
//----------------------------------------------------------------------------------------------------------
}

class PaqueteEnvio implements Serializable {

    private String Nick;
    private String Grupo;
    private String Mensaje;

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String grupo) {
        Grupo = grupo;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

}