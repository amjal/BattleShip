package logic;

import view.GamePanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.SocketAddress;

/**
 * Created by amir on 7/6/17.
 */
public class Player {
    private String name;
    public JPanel gamePlace=new JPanel();
    private Cell[][] cells = new Cell[10][10];

    public Player(String name){
        this.name = name;
        gamePlace.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setSize(50,50);
                cells[i][j].setBackground(Color.cyan);
                cells[i][j].setOpaque(true);
                cells[i][j].setBorder(new LineBorder(Color.black, 1));
                cells[i][j].setVisible(true);
                gamePlace.add(cells[i][j]);

            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j].addMouseListener(new CellActionHandler());
            }
        }

    }
    public String getName() {
        return name;
    }
    public void setGamePlace(){
        gamePlace.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setSize(50,50);
                cells[i][j].setBackground(Color.cyan);
                cells[i][j].setOpaque(true);
                cells[i][j].setBorder(new LineBorder(Color.black, 1));
                cells[i][j].setVisible(true);

                gamePlace.add(cells[i][j]);

            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j].addMouseListener(new CellActionHandler());
            }
        }
    }
    public void repaintPanel(){
        gamePlace.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setSize(50,50);
                cells[i][j].setOpaque(true);
                cells[i][j].setBorder(new LineBorder(Color.black, 1));
                cells[i][j].setVisible(true);

                gamePlace.add(cells[i][j]);

            }
        }


    }
    public void changeGamePlaceCell(int i , int j ,Cell cell ){
        cells[i][j]=cell;
        repaintPanel();
    }
    class CellActionHandler extends MouseAdapter{

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
        }
    }
}
