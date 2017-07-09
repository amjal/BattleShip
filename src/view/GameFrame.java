package view;

import logic.MessageManager;

import javax.swing.*;

/**
 * Created by parsa on 7/5/17.
 */
public class GameFrame extends JFrame {

    public GameFrame(MessageManager messageManager , Player me) {
        super("battle ship");
        setSize(1000, 700);
        setDefaultCloseOperation(3);
        GamePanel gamePanel = new GamePanel(messageManager , me);
        add(gamePanel.getFullPanel());

        setVisible(true);
    }


}
