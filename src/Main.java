import view.GamePanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by amir on 6/28/17.
 */
public class Main {
    /**
     *    public void gamePanelMethod() {
     gridPanel = new JPanel();
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
     gamePlace.add(gridPanel);
     }
     * @param args
     */
    public static void main(String[] args) {

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
        GamePanel gamePanel =new GamePanel(gridPanel);
        //gamePanel.setGamePlace(gridPanel);

JFrame frame=new JFrame("test");
frame.setSize(700,500);
frame.setDefaultCloseOperation(3);
//gamePanel.revalidate();


frame.add(gamePanel.getFullPanel());
frame.setVisible(true);

    }
}
