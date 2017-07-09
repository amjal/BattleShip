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

    List<ChatHistoryPreview> panelList = new ArrayList<>();
    JPanel mainPanel;

    JScrollPane scrollPane;
    int y;

    public ChatHistoryContainer( ){

            super("chat history");
            setSize(220, 600);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            mainPanel = new JPanel();
            mainPanel.setLayout(null);
            mainPanel.setOpaque(true);
            mainPanel.setPreferredSize(new Dimension(220,1600));


        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(0 , 0 , 200 , 600);
        getContentPane().add(scrollPane);
        revalidate();
        paintList();

        setVisible(true);
    }
    public void addPanel(ChatHistoryPreview chatHistoryPreview){
        panelList.add(chatHistoryPreview);
        paintList();
    }

    private void paintList(){
        mainPanel.removeAll();
        y = 60;
        JSeparator separator1 = new JSeparator();
        separator1.setBounds(0 , 55 , 200 , 3);
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


}
