package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by parsa on 7/5/17.
 */
/**
 * Created by parsa on 7/5/17.
 */
public class GamePanel extends JFrame {
    private JButton button1;
    private JTextArea textArea1;
    private JTextField textField1;
    private JSplitPane splitPane1;
    private JPanel gamePlace;
    private JButton leaveButton;
    private JPanel gameInfoPanel;


    public GamePanel() {

        JPanel gridPanel = new JPanel();
        gamePlace.setLayout(new GridLayout(10, 10));
        JLabel[][] cells = new JLabel[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new JLabel();
                cells[i][j].setSize(50,50);
                cells[i][j].setBackground(Color.RED);
                cells[i][j].setBorder(new LineBorder(Color.black, 1));

                cells[i][j].setVisible(true);
                gamePlace.add(cells[i][j]);
            }

        }
        cells[5][5].setForeground(Color.cyan);
        //setGamePlace(gridPanel);
        this.gamePlace=gamePlace;

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
}