package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by parsa on 7/5/17.
 */
public class GameFrame extends JFrame {

    public GameFrame() {
        super("battle ship");
        setSize(1000, 700);
        setDefaultCloseOperation(3);
        GamePanel gamePanel = new GamePanel();
        add(gamePanel.getFullPanel());
        setVisible(true);
    }


}
