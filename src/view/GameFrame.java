package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by parsa on 7/5/17.
 */
public class GameFrame extends JFrame {

    public GameFrame(){
        super("battle ship");
        setSize(1000,700);
        setVisible(true);
    }
    public void gamePanelMethod() {
       JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(10, 10));
        JLabel[][] cells = new JLabel[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new JLabel();
                cells[i][j].setSize(50,50);
                cells[i][j].setBackground(Color.RED);
                cells[i][j].setBorder(new LineBorder(Color.black, 1));

                cells[i][j].setVisible(true);
                gridPanel.add(cells[i][j]);
            }

        }
        cells[5][5].setForeground(Color.cyan);
        gridPanel.setSize(500,500);

    }

}
