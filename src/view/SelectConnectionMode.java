package view;

import logic.MessageManager;
import logic.ServerSocketHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by amir on 7/4/17.
 */
public class SelectConnectionMode extends JFrame {

    private JPanel connectionSelection;
    private JTextField name;
    private JTextField hostPort;
    private JRadioButton hostRadioButton1;
    private JRadioButton guestRadioButton;
    private JTextField hostIP;
    private JLabel hostPortLabel;
    private JButton startButton;
    private JButton exitButton;
    private JPanel innerConnectionSelection;
    private JTextField guestPort;
    private JLabel message;

    private enum ConnectionType{
        HOST, CLIENT;
    }
    private ConnectionType connectionType;
    public SelectConnectionMode(){
        setSize(450 , 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(connectionSelection);
        setVisible(true);

        hostRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(hostRadioButton1.isSelected()){
                    connectionType = ConnectionType.HOST;
                    guestPort.setEditable(false);
                    hostIP.setEditable(false);
                    hostPort.setEditable(true);
                    guestRadioButton.setSelected(false);
                }
                else {
                    connectionType = null;
                    guestPort.setEditable(true);
                    hostIP.setEditable(true);
                }
            }
        });

        guestRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(guestRadioButton.isSelected()){
                    connectionType = ConnectionType.CLIENT;
                    hostPort.setEditable(false);
                    hostIP.setEditable(true);
                    guestPort.setEditable(true);
                    hostRadioButton1.setSelected(false);
                }
                else {
                    connectionType = null;
                    hostPort.setEditable(true);
                }
            }
        });
        startButton.addActionListener(new ActionListener() {
            public void checkForFields() throws NullPointerException{
                if(name.getText().length() ==0 || hostIP.getText().length() ==0) throw new NullPointerException();
            }
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    checkForFields();
                    switch (connectionType) {
                        case HOST:{
                            new MessageManager(Integer.parseInt(hostPort.getText()));
                            break;
                        }
                        case CLIENT:{
                            new MessageManager(hostIP.getText() , Integer.parseInt(guestPort.getText()));
                            break;
                        }
                    }
                }catch(NullPointerException e){
                    if(!guestRadioButton.isSelected() && !hostRadioButton1.isSelected()){
                        message.setText("Please select a mode!");
                    }
                    else if(name.getText().length() ==0){
                        message.setText("Please enter your name!");
                    }
                    else if(hostIP.getText().length() ==0){
                        message.setText("Please enter IP address!");
                    }
                }catch(NumberFormatException e){
                    message.setText("Please enter a valid port number!");
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
