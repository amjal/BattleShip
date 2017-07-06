import view.GamePanel;

import javax.swing.*;

/**
 * Created by amir on 6/28/17.
 */
public class Main {
    public static void main(String[] args) {



        JFrame frame = new JFrame("test");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(3);
        GamePanel gamePanel=new GamePanel();

        frame.add(gamePanel.getFullPanel());
        frame.setVisible(true);

    }
}
