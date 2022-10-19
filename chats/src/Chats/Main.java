package Chats;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Servidor();
                frame.setSize(500,300);
                frame.setVisible(true);

                JFrame frameCliente = new Cliente("Robot");
                frameCliente.setSize(400,300);
                frameCliente.setVisible(true);

            }
        });
    }

}
