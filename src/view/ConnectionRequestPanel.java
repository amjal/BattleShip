package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by amir on 7/6/17.
 */
public class ConnectionRequestPanel extends JPanel {
    ConnectionWaitList container;
    String ip;
    public ConnectionRequestPanel( String name , String ip , ConnectionWaitList container){
        setLayout(null);
        JLabel nameLabel = new JLabel(name);
        nameLabel.setBounds(0 ,0 , 200 , 20);
        JLabel ipLable = new JLabel(ip);
        ipLable.setBounds(0 , 25, 200 , 20);
        JButton accept = new JButton("accept");
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                container.getMessageManager().onRequestAccepted(ip);
                container.rejectAll(ConnectionRequestPanel.this);
                container.makeFrame();
                container.dispose();
            }
        });
        accept.setBounds(0,60 , 98 , 50);
        JButton reject = new JButton("reject");
        reject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                container.removePanel(ConnectionRequestPanel.this);
                container.getMessageManager().onRequestRejected(ip);
            }
        });
        reject.setBounds(100 , 60 , 98 , 50);
        add(nameLabel);
        add(ipLable);
        add(accept);
        add(reject);
        this.container = container;
        this.ip = ip;
    }
    public void reject(){
        container.getMessageManager().onRequestRejected(ip);
    }
}
