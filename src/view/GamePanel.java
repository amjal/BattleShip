package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;
import java.awt.*;

import static com.sun.tools.internal.xjc.reader.Ring.add;

/**
 * Created by parsa on 7/5/17.
 */
public class GamePanel extends JFrame {
    private JLabel label;
    private JSplitPane splitPane1;
    private JButton button1;
    private JTextArea textArea1;
    private JTextField textField1;
    private JPanel gamePlace;
    private JButton leaveButton;
    private JPanel gridPanel;
    public GamePanel() {
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
        setSize(1000,1000);
        setDefaultCloseOperation(3);
        setResizable(true);
        setLayout(null);
        gridPanel.setSize(500,500);
        add(splitPane1);
        setVisible(true);
    }
    }



