package view;

import logic.CellClickedOnListener;
import logic.CellHoveredOnListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Created by parsa on 7/7/17.
 */
public class Cell extends JLabel implements Serializable{
    private CellHoveredOnListener chol;
    private CellClickedOnListener ccol;
    private Point location;
    private CellState cellState;
    private CellType celltype;
    public Cell(Point location , CellType celltype){
        this.location = location;
        this.celltype = celltype;
        cellState = CellState.WATER;
        setSize(50,50);
        setOpaque(true);
        setBorder(new LineBorder(Color.black, 1));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(SwingUtilities.isRightMouseButton(mouseEvent)){
                    ccol.cellClickedOn(Cell.this , 1);
                }
                else ccol.cellClickedOn(Cell.this , 0);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                chol.cellHoveredOn(Cell.this , MouseEvent.MOUSE_ENTERED );
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                chol.cellHoveredOn(Cell.this , MouseEvent.MOUSE_EXITED );
            }
        });
        paintCell();
    }
    public Cell(){

    }
    public void addCellHoveredOnListener(CellHoveredOnListener chol){
        this.chol = chol;
    }
    public void addCellClickedOnListener(CellClickedOnListener ccol){
        this.ccol = ccol;
    }
    public Point getLocation(){
        return location;
    }
    public CellState getCellState(){
        return cellState;
    }
    public CellType getCellType(){
        return celltype;
    }
    public void setState(CellState state){
        cellState = state;
    }
    public void paintCell(){
        switch (cellState){
            case HIT: {
                setBackground(Color.red);
                break;
            }
            case SHIP:{
                if(celltype == CellType.ME_CELL)
                    setBackground(Color.black);
                else setBackground(Color.cyan);
                break;
            }
            case FOLLOWING_HOVERED_OVER:{
                setBackground(Color.blue);
                break;
            }
            case HOVERED_OVER:{
                setBackground(Color.blue);
                break;
            }
            case WATER:{
                setBackground(Color.cyan);
                break;
            }
            case MISSED:{
                setBackground(Color.gray);
                break;
            }
        }
    }
    public void setLocation(Point point){
        this.location = point;
    }
}
