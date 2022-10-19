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
    private JButton grupoButton;
    private JTextArea Usuario_nick;
    private JTextArea textArea_Chat;
    String name="";
    Usuario  usuario;
    public Cliente(String nick){
        super("Cliente");
        setContentPane(panel1);
        usuario=new Usuario(nick);
        Usuario_nick.setText(nick);
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Socket socket = new Socket("127.0.0.1",9999);
                    DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                    salida.writeUTF(usuario.toString()+": "+textField1.getText());
                    textField1.setText("");
                    salida.close();

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Error : " +ex + " No se pudo realizar la conexión . ");
                }
            }
        });
        grupoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Servidor();

                frame.setSize(500,300);
                frame.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
