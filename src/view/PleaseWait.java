package view;

import logic.MessageManager;
import logic.RequestAnswerListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by parsa on 7/4/17.
 */
public class PleaseWait extends JFrame implements RequestAnswerListener {
    private JButton cancelButton;
    private JPanel panel1;
    private JLabel message;

    public PleaseWait(String ip , int port , String name) {
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
                        MessageManager m = new MessageManager(ip, port , name);
                        m.addRequestAnswerListener(PleaseWait.this);
                        break;
                    } catch (IOException e) {
                    }
                }
                message.setText("found host! waiting to be accepted...");
            }
        });
        t.start();
    }

    @Override
    public void onReject() {
        dispose();
    }

    @Override
    public void onAccept() {
        new GameFrame();
        dispose();
    }
}
