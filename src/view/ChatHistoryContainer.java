package view;

import jdk.nashorn.api.scripting.JSObject;
import logic.MessageManager;
import logic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.sun.tools.internal.xjc.reader.Ring.add;

/**
 * Created by parsa on 7/9/17.
 */
public class ChatHistoryContainer extends JFrame {

    List<ConnectionRequestPanel> panelList = new ArrayList<>();
    JPanel mainPanel;
    JLabel message;
    JScrollPane scrollPane;
    int y;
    Player player;
    MessageManager messageManager;
    public ChatHistoryContainer(MessageManager messageManager , Player player){
        {
            setSize(220, 600);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        {
            mainPanel = new JPanel();
            mainPanel.setLayout(null);
            mainPanel.setOpaque(true);
            mainPanel.setPreferredSize(new Dimension(220,1600));
        }
        {
            message = new JLabel("Chat History");
            message.setFont(new Font(message.getFont().getName(), Font.ITALIC, 15));
            message.setSize(200, 50);
        }
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(0 , 0 , 200 , 600);
        getContentPane().add(scrollPane);
        revalidate();
        paintList();
        this.messageManager = messageManager;
        this.player = player;
        setVisible(true);
    }
    public void addPanel(String name , String ip){
        panelList.add(new ConnectionRequestPanel(name , ip , this));
        paintList();
    }
    public void removePanel(ConnectionRequestPanel connectionRequestPanel){
        panelList.remove(connectionRequestPanel);
        paintList();
    }
    private void paintList(){
        mainPanel.removeAll();
        y = 60;
        JSeparator separator1 = new JSeparator();
        separator1.setBounds(0 , 55 , 200 , 3);
        mainPanel.add(message);
        mainPanel.add(separator1);
        for(int i =0 ; i < panelList.size() ; i++){
            panelList.get(i).setBounds(0 , y , 200 , 115);
            JSeparator separator = new JSeparator();
            separator.setBounds(0 , y + 118 , 200 , 3);
            mainPanel.add(panelList.get(i));
            mainPanel.add(separator);
            y += 121;
        }
        revalidate();
        repaint();
    }
    public MessageManager getMessageManager(){
        return messageManager;
    }
    public void rejectAll(ConnectionRequestPanel except){
        for(ConnectionRequestPanel c:panelList){
            if(!c.equals(except)){
                c.reject();
            }
        }
    }
    public void makeFrame(){
        new GameFrame(messageManager ,player);
    }
}
