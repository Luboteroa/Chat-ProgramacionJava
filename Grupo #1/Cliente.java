package Chats;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cliente extends JFrame  {
    private JTextField textField1;
    private JPanel panel1;
    private JButton enviarButton;
    public Cliente(){
        super("Servidor");
        setContentPane(panel1);

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Socket socket = new Socket("127.0.0.1",9999);
                    DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                    salida.writeUTF(textField1.getText());
                    salida.close();

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Error : " +ex + " No se pudo realizar la conexión . ");
                }
            }
        });
    }
}
