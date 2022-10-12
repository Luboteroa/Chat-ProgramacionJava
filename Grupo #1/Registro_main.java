package Chats;

import javax.swing.*;

public class Registro_main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame frame = new Ingresar();
                frame.setSize(500,500);
                frame.setVisible(true);

            }
        });
    }
}
