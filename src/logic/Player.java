package logic;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.SocketAddress;

/**
 * Created by amir on 7/6/17.
 */
public class Player {
    private JPanel gamePlace;
    public Player(){
        gamePlace.setLayout(new GridLayout(10, 10));
        Cell[][] cells = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setSize(50,50);
                cells[i][j].setBackground(Color.RED);
                cells[i][j].setBorder(new LineBorder(Color.black, 1));

                cells[i][j].setVisible(true);
                gamePlace.add(cells[i][j]);
            }

        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);


                    }
                });
            }
        }
    }
    private SocketAddress IP;

    public SocketAddress getIP() {
        return IP;
    }

    public String getName() {
        return name;
    }

    private String name;
    public Player(SocketAddress IP , String name){
        this.IP = IP;
        this.name = name;
    }
}
