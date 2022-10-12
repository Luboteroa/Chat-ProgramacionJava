package Chats;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ingresar extends JFrame{
    private JTextArea jta_mostrar;
    private JButton btn_ver;
    private JButton btn_ingresar;
    private JPanel panelRegistro;
    Lista_Usuarios lista_Usuarios;

    public Ingresar(){
        super("Registro");
        setContentPane(panelRegistro);
        lista_Usuarios = new Lista_Usuarios();
        lista_Usuarios.crearArrayList();
        btn_ver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta_mostrar.setText("");
                jta_mostrar.setText(lista_Usuarios.devolverInformacion());
            }
        });
        btn_ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nick = JOptionPane.showInputDialog("Digite su nick");
                String str=nick.toUpperCase();
                boolean aprueba = lista_Usuarios.revision(str);
                while (aprueba == true)
                {
                    JOptionPane.showMessageDialog(null,"nombre ocupado");
                    nick = JOptionPane.showInputDialog("Digite su nick");
                    aprueba = lista_Usuarios.revision(nick.toUpperCase());
                }
                JOptionPane.showMessageDialog(null,"nombre libre");
                Usuario usuario = new Usuario(nick.toUpperCase());
                lista_Usuarios.insertarNick(usuario);
                JOptionPane.showMessageDialog(null,"Usuario Registrado");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
