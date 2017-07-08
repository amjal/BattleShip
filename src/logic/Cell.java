package logic;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by parsa on 7/7/17.
 */
public class Cell extends JLabel {
  public   JLabel label=new JLabel();
  // 0 -> hit
    //1-> miss
    //2->ship
    //3->water

 public    int status;


public Cell(){
    label.setText("");
    label.setBackground(Color.cyan);
    label.setBorder(new LineBorder(Color.black, 1));
}


    public void CellType(int type){
        switch (type){
            //hit
            case 0:
                label.setText("x");
                label.setForeground(Color.red);
                label.setBackground(Color.blue);
                label.setBorder(new LineBorder(Color.red, 1));
                break;
                //miss
            case 1:
                label.setText(".");
                label.setForeground(Color.red);
                label.setBackground(Color.blue);
                label.setBorder(new LineBorder(Color.black, 1));
                break;
                //default
            case 2:
                label.setText("");
                label.setBackground(Color.black);
                label.setBorder(new LineBorder(Color.white, 1));
                break;
            case 3:
                label.setText("");
                label.setForeground(Color.red);
                label.setBackground(Color.cyan);
                label.setBorder(new LineBorder(Color.black, 1));
            default:
                label.setText("");
                label.setForeground(Color.red);
                label.setBackground(Color.cyan);
                label.setBorder(new LineBorder(Color.black, 1));



        }
    }
}
