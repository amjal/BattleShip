package view;

import logic.MessageManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by parsa on 7/4/17.
 */
public class PleaseWait extends JFrame{
    private JButton cancelButton;
    private JPanel panel1;
    private JLabel message;

    public PleaseWait(String ip , int port) {
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(panel1);
        setVisible(true);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        new MessageManager(ip, port);
                        break;
                    } catch (IOException e) {
                        System.out.println("ok");
                    }
                }
            }
        });
        t.start();
    }
}
