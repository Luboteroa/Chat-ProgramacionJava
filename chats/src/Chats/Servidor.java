package Chats;

import java.io.IOException;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.*;

public class Servidor extends JFrame{
    private JPanel panel1;
    private JTextArea area;


    public Servidor(){
        super("Cliente");
        setContentPane(panel1);
        Thread mihilo = new Thread(this::run);
        mihilo.start();
    }



    public void run() {
        try{
            ServerSocket servidor = new ServerSocket(9999);


            while(true){
                Socket misocket = servidor.accept();

                DataInputStream entrada = new DataInputStream(misocket.getInputStream());
                String mensaje = entrada.readUTF();

                area.append("\n " + mensaje);
                misocket.close();
            }

        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error : " +e);
        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
