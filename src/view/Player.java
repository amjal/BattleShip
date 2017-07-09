package view;

import logic.CellClickedOnListener;
import logic.CellHoveredOnListener;
import logic.MoveMadeListener;
import logic.ShipReducedListener;
import view.Cell;
import view.CellState;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Created by amir on 7/6/17.
 */
public class Player implements CellHoveredOnListener, CellClickedOnListener{
    private String name;
    private int selectedShipSize;
    private int currentShipDirection = SwingConstants.HORIZONTAL;
    private ShipReducedListener shipReducedListener;
    private Cell[][] cells = new Cell[10][10];
    private int[] shipsLeft = {0,4,3,2,1};
    private PlayerType playerType;
    private MoveMadeListener moveMadeListener;
    public Player(String name){
        this.name = name;
    }
    public Player(PlayerType playerType){
        this.playerType = playerType;
    }
    public void cellsInit(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(playerType == PlayerType.ENEMY)
                    cells[i][j] = new Cell(new Point(i , j) , CellType.ENEMY_CELL);
                else cells[i][j] = new Cell(new Point(i,j) , CellType.ME_CELL);
                cells[i][j].addCellHoveredOnListener(this);
                cells[i][j].addCellClickedOnListener(this);
            }
        }
    }
    public String getName() {
        return name;
    }
    public void getGamePlace(JPanel gamePlace){
        gamePlace.removeAll();
        gamePlace.setLayout(new GridLayout(10, 10));
        for(int i=0 ; i < 10 ; i ++){
            for(int j =0 ; j < 10 ; j ++){
                gamePlace.add(cells[i][j]);
            }
        }
        gamePlace.repaint();
        gamePlace.revalidate();
    }

    @Override
    public void cellHoveredOn(Cell cell , int action) {
        switch (action){
            case MouseEvent.MOUSE_ENTERED: {
                if(shipsLeft[selectedShipSize] >0)
                    drawHovered(cell);
                break;
            }
            case MouseEvent.MOUSE_EXITED: {
                clearHovered();
                break;
            }
        }
    }
    @Override
    public void cellClickedOn(Cell cell, int action) {
        if(cell.getCellType() == CellType.ME_CELL && cell.getCellState() == CellState.HOVERED_OVER) {
            if (action == 1)//right click on initialize
            {
                if (currentShipDirection == SwingConstants.HORIZONTAL)
                    currentShipDirection = SwingConstants.VERTICAL;
                else currentShipDirection = SwingConstants.HORIZONTAL;
                clearHovered();
                drawHovered(cell);
            } else if (action == 0) {// left click on initialize
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if ((cells[i][j].getCellState() == CellState.HOVERED_OVER ||
                                cells[i][j].getCellState() == CellState.FOLLOWING_HOVERED_OVER)) {
                            if (i >0 && j >0 &&cells[i - 1][j - 1].getCellState() == CellState.SHIP) return;
                            if (j >0 && cells[i][j - 1].getCellState() == CellState.SHIP) return;
                            if (i <9 && j >0 && cells[i + 1][j - 1].getCellState() == CellState.SHIP) return;
                            if (i >0 && cells[i - 1][j].getCellState() == CellState.SHIP) return;
                            if (i <9 && cells[i + 1][j].getCellState() == CellState.SHIP) return;
                            if (i >0 && j <9 &&cells[i - 1][j + 1].getCellState() == CellState.SHIP) return;
                            if (j <9 && cells[i][j + 1].getCellState() == CellState.SHIP) return;
                            if (i <9 && j <9 &&cells[i + 1][j + 1].getCellState() == CellState.SHIP) return;
                        }
                    }
                }
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (cells[i][j].getCellState() == CellState.HOVERED_OVER ||
                                cells[i][j].getCellState() == CellState.FOLLOWING_HOVERED_OVER) {
                            if (cells[i][j].getCellState() == CellState.HOVERED_OVER) {
                                shipsLeft[selectedShipSize]--;
                                shipReducedListener.shipReduced(selectedShipSize);
                            }
                            cells[i][j].setState(CellState.SHIP);
                            cells[i][j].paintCell();
                        }
                    }
                }
            }
        }
        else if (cell.getCellType() == CellType.ENEMY_CELL){
            if(cell.getCellState() == CellState.SHIP){
                cell.setState(CellState.HIT);
            }
            else if (cell.getCellState() == CellState.WATER){
                cell.setState(CellState.MISSED);
            }
            cell.paintCell();
            moveMadeListener.onMoveMade(cell);
        }
    }
    private void clearHovered(){
        for(int i =0 ; i < 10 ; i ++){
            for(int j =0 ; j < 10 ; j ++){
                if(cells[i][j].getCellState() == CellState.FOLLOWING_HOVERED_OVER ||
                        cells[i][j].getCellState() == CellState.HOVERED_OVER){
                    cells[i][j].setState(CellState.WATER);
                    cells[i][j].paintCell();
                }
            }
        }
    }
    private void drawHovered(Cell cell){
        switch (currentShipDirection) {
            case SwingConstants.VERTICAL: {
                if (cell.getLocation().getX() + selectedShipSize - 1 < 10) {
                    for (int i = 0; i < selectedShipSize; i++) {
                        if (cells[(int) cell.getLocation().getX() + i][(int) cell.getLocation().getY()].getCellState() == CellState.SHIP) {
                            clearHovered();
                            break;
                        }
                        if (i == 0) {
                            cells[(int) cell.getLocation().getX() + i][(int) cell.getLocation().getY()].setState(CellState.HOVERED_OVER);
                        } else {
                            cells[(int) cell.getLocation().getX() + i][(int) cell.getLocation().getY()].setState(CellState.FOLLOWING_HOVERED_OVER);
                        }
                        cells[(int) cell.getLocation().getX() + i][(int) cell.getLocation().getY()].paintCell();
                    }
                }
                break;
            }
            case SwingConstants.HORIZONTAL: {
                if (cell.getLocation().getY() + selectedShipSize - 1 < 10) {
                    for (int j = 0; j < selectedShipSize; j++) {
                        if(cells[(int) cell.getLocation().getX()][(int) cell.getLocation().getY() + j].getCellState() == CellState.SHIP){
                            clearHovered();
                            break;
                        }
                        if(j ==0) {
                            cells[(int) cell.getLocation().getX()][(int) cell.getLocation().getY() + j].setState(CellState.HOVERED_OVER);
                        }
                        else {
                            cells[(int) cell.getLocation().getX()][(int) cell.getLocation().getY() + j].setState(CellState.FOLLOWING_HOVERED_OVER);
                        }
                        cells[(int) cell.getLocation().getX()][(int) cell.getLocation().getY() + j].paintCell();
                    }
                }
                break;
            }
        }
    }
    public void setSelectedShipSize(int value){
        selectedShipSize = value;
    }
    public int getSelectedShipSize(){
        return selectedShipSize;
    }
    public void setCellState(Point location , CellState state){
        cells[(int)location.getX()][(int)location.getY()].setState(state);
        cells[(int)location.getX()][(int)location.getY()].paintCell();
    }
    public void addShipReducedListener(ShipReducedListener shipReducedListener){
        this.shipReducedListener = shipReducedListener;
    }
    public void setPlayerType(PlayerType playerType){
        this.playerType = playerType;
    }
    public void reset(){
        for(int i =0 ; i < 10 ; i++){
            for(int j =0 ; j < 10 ; j ++){
                shipsLeft[1] = 4;
                shipsLeft[2] = 3;
                shipsLeft[3] = 2;
                shipsLeft[4] = 1;
                cells[i][j].setState(CellState.WATER);
                cells[i][j].paintCell();
            }
        }
    }
    public void setCellsStates(CellState[][] states){
        for(int i =0 ; i < 10 ; i ++){
            for (int j =0 ; j < 10 ; j ++){
                cells[i][j].setState(states[i][j]);
                cells[i][j].paintCell();
            }
        }
    }
    public CellState[][] getCellStates(){
        CellState[][] output = new CellState[10][10];
        for(int i =0 ; i < 10 ; i ++){
            for (int j =0 ; j < 10 ; j ++){
                output[i][j] = cells[i][j].getCellState();
            }
        }
        return output;
    }
    public void addMoveMadeListener(MoveMadeListener moveMadeListener){
        this.moveMadeListener = moveMadeListener;
    }
}
