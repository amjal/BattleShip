package view;

import logic.CellClickedOnListener;
import logic.CellHoveredOnListener;
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
public class Player implements CellHoveredOnListener, CellClickedOnListener , Serializable{
    private String name;
    private int selectedShipSize;
    private int currentShipDirection = SwingConstants.HORIZONTAL;
    private ShipReducedListener shipReducedListener;
    private Cell[][] cells = new Cell[10][10];
    private int[] shipsLeft = {0,4,3,2,1};
    private PlayerType playerType;
    public Player(String name){
        this.name = name;
    }
    public Player(){

    }
    public String getName() {
        return name;
    }
    public void getGamePlace(JPanel gamePlace){
        gamePlace.removeAll();
        gamePlace.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell(new Point(i , j));
                cells[i][j].addCellHoveredOnListener(this);
                cells[i][j].addCellClickedOnListener(this);
                gamePlace.add(cells[i][j]);
            }
        }
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
        if(action == 1)//right click
        {
            if(currentShipDirection == SwingConstants.HORIZONTAL)
                currentShipDirection = SwingConstants.VERTICAL;
            else currentShipDirection = SwingConstants.HORIZONTAL;
            clearHovered();
            drawHovered(cell);
        }
        else if(action == 0){// left click
            for(int i =0 ; i< 10 ;  i++){
                for(int j =0 ; j < 10 ; j ++){
                    if((cells[i][j].getCellState() == CellState.HOVERD_OVER ||
                            cells[i][j].getCellState() == CellState.FOLLOWING_HOVERED_OVER)){
                        try{
                            if(cells[i-1][j-1].getCellState() == CellState.SHIP) return;
                            if(cells[i][j-1].getCellState() == CellState.SHIP) return;
                            if(cells[i+1][j-1].getCellState() == CellState.SHIP) return;
                            if(cells[i-1][j].getCellState() == CellState.SHIP) return;
                            if(cells[i+1][j].getCellState() == CellState.SHIP) return;
                            if(cells[i-1][j+1].getCellState() == CellState.SHIP) return;
                            if(cells[i][j+1].getCellState() == CellState.SHIP) return;
                            if(cells[i+1][j+1].getCellState() == CellState.SHIP) return;
                        }catch(ArrayIndexOutOfBoundsException e){

                        }
                    }
                }
            }
            for(int i =0 ; i<10 ; i ++){
                for(int j =0 ; j < 10 ; j ++){
                    if(cells[i][j].getCellState() == CellState.HOVERD_OVER ||
                            cells[i][j].getCellState() == CellState.FOLLOWING_HOVERED_OVER){
                        if(cells[i][j].getCellState() == CellState.HOVERD_OVER) {
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
    private void clearHovered(){
        for(int i =0 ; i < 10 ; i ++){
            for(int j =0 ; j < 10 ; j ++){
                if(cells[i][j].getCellState() == CellState.FOLLOWING_HOVERED_OVER ||
                        cells[i][j].getCellState() == CellState.HOVERD_OVER){
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
                            cells[(int) cell.getLocation().getX() + i][(int) cell.getLocation().getY()].setState(CellState.HOVERD_OVER);
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
                            cells[(int) cell.getLocation().getX()][(int) cell.getLocation().getY() + j].setState(CellState.HOVERD_OVER);
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
    public void addShipReducedListener(ShipReducedListener shipReducedListener){
        this.shipReducedListener = shipReducedListener;
    }
    public PlayerType getPlayerType(){
        return playerType;
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
}
