package view;

import com.sun.xml.internal.ws.api.message.Message;
import logic.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by parsa on 7/5/17.
 */
public class GamePanel extends JPanel implements ChatListener{
    private JButton button1;
    private JTextField chatMessageField;
    private JSplitPane splitPane1;
    private JPanel gamePlace;
    private JPanel gameInfoPanel;
    private JLabel size4Ship;
    private JLabel size4ShipN;
    private JLabel size3Ship;
    private JLabel size3ShipN;
    private JLabel size2Ship;
    private JLabel size2ShipN;
    private JLabel size1Ship;
    private JLabel size1ShipN;
    private JButton readyButton;
    private JButton resetButton;
    private JLabel chatName;
    private JTextArea chatArea;
    MessageManager messageManager;

    public GamePanel(MessageManager messageManager , Player player) {
        this.messageManager = messageManager;
        messageManager.addChatListener(this);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageManager.onSendMessage(new ChatMessage(player.getName() + ": " + chatMessageField.getText()));
                chatMessageField.setText("");
            }
        });
        player.setGamePlace(gamePlace);
    }
    public JSplitPane getFullPanel() {
        return splitPane1;
    }


    public void setGamePlace(JPanel gamePlace) {
        this.gamePlace = gamePlace;
    }

    public JPanel getGamePlace() {
        return gamePlace;
    }

    public void setButton1(String string) {
        button1.setText(string);
    }


    @Override
    public void onChatReceived(String text) {
        chatArea.append(text+"\n");

    }
}
