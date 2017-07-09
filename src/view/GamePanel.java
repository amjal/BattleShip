package view;

import logic.*;

import javax.swing.*;
import java.awt.event.*;

import static java.lang.Thread.sleep;

/**
 * Created by parsa on 7/5/17.
 */
public class GamePanel extends JPanel implements ChatListener , ShipReducedListener , ReadyMessageListener,
        GameMessageListener ,MoveMadeListener , GameFinishedListener, GameFinishedMessageListener{
    private JButton sendButton;
    private JTextField chatMessageField;
    private JSplitPane splitPane1;
    private JPanel gamePlace;
    private JPanel gameInfoPanel;
    private JLabel size4Ship;
    private JLabel size4ShipN;
    private JLabel size3Ship;
    private JLabel size3ShipN;
    private JLabel size2Ship;
    private JLabel size2ShipN;
    private JLabel size1Ship;
    private JLabel size1ShipN;
    private JButton readyButton;
    private JButton resetButton;
    private JLabel chatName;
    private JTextArea chatArea;
    private JPanel leftPanel;
    MessageManager messageManager;
    Player me;
    Player enemy;
    PlayerType turn = null;
    public GamePanel(MessageManager messageManager , Player me) {
        this.messageManager = messageManager;
        this.me = me;
        this.me.setPlayerType(PlayerType.ME);
        this.me.cellsInit();
        this.me.addGameFinishedListener(this);
        enemy = new Player(PlayerType.ENEMY);
        enemy.cellsInit();
        enemy.addMoveMadeListener(this);
        enemy.addGameFinishedListener(this);
        messageManager.addChatListener(this);
        messageManager.addReadyMessageListener(this);
        messageManager.addGameMessageListener(this);
        messageManager.addGameFinishedMessageListener(this);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageManager.onSendMessage(new ChatMessage(me.getName() + ": " + chatMessageField.getText()));
                chatArea.append(me.getName() + ": " + chatMessageField.getText()+"\n");
                chatMessageField.setText("");
            }
        });
        me.getGamePlace(gamePlace);
        me.addShipReducedListener(this);
        myTableInit();
    }
    void myTableInit(){
        size1Ship.addMouseListener(new ShipDragHandler(1));
        size2Ship.addMouseListener(new ShipDragHandler(2));
        size3Ship.addMouseListener(new ShipDragHandler(3));
        size4Ship.addMouseListener(new ShipDragHandler(4));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                me.reset();
                me.setSelectedShipSize(0);
                size1ShipN.setText(4+"");
                size2ShipN.setText(3+"");
                size3ShipN.setText(2+"");
                size4ShipN.setText(1+"");
            }
        });
        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(size4ShipN.getText().equals("0") && size3ShipN.getText().equals("0") &&
                        size2ShipN.getText().equals("0") && size1ShipN.getText().equals("0")) {
                    messageManager.onSendMessage(new ReadyMessage(me.getCellStates()));
                    if(turn == null){// we are ready but the enemy is not so we can't show enemy's grid we have to wait
                        turn = PlayerType.ME;
                        String message = "***"+me.getName()+" IS READY!***";
                        chatArea.append(message);
                        messageManager.onSendMessage(new ChatMessage(message));
                    }
                    else if (turn == PlayerType.ENEMY)// we are ready but after the enemy so our grid is shown
                    {
                        me.getGamePlace(gamePlace);
                    }
                readyButton.setEnabled(false);
                resetButton.setEnabled(false);
                }
            }
        });
    }
    public JSplitPane getFullPanel(){
        return splitPane1;
    }
    @Override
    public void onChatReceived(String text) {
        chatArea.append(text+"\n");
    }

    @Override
    public void shipReduced(int size) {
        switch (size){
            case 1:{
                size1ShipN.setText((Integer.parseInt(size1ShipN.getText())-1)+"");
                break;
            }
            case 2:{
                size2ShipN.setText((Integer.parseInt(size2ShipN.getText())-1)+"");
                break;
            }
            case 3:{
                size3ShipN.setText((Integer.parseInt(size3ShipN.getText())-1)+"");
                break;
            }
            case 4:{
                size4ShipN.setText((Integer.parseInt(size4ShipN.getText())-1)+"");
                break;
            }

        }
    }

    @Override
    public void onReadyMessageReceived(CellState[][] cellStates) {
        enemy.setCellsStates(cellStates);
        if(turn == null) // it's the beginning and we are not ready but the enemy is ready
        {
            turn = PlayerType.ENEMY;
        }
        else if (turn == PlayerType.ME) //it's the beginning of the game and we were ready before enemy
        {
            enemy.getGamePlace(gamePlace);
        }

    }

    @Override
    public void onGameMessageReceived(Cell cell) {
        me.setCellState(cell.getLocation() , cell.getCellState());
        if(cell.getCellState() == CellState.MISSED) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            turn = PlayerType.ME;
            enemy.getGamePlace(gamePlace);
        }
    }

    @Override
    public void onMoveMade(Cell cell) {
        messageManager.onSendMessage(new GameMessage(cell));
        if(cell.getCellState() == CellState.MISSED) {
            gamePlace.updateUI();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            turn = PlayerType.ENEMY;
            me.getGamePlace(gamePlace);
        }
    }

    @Override
    public void onGameFinished() {
        messageManager.onSendMessage(new GameFinishedMessage(me.getName()));
        chatArea.append("***"+me.getName()+" WON!***");
        gamePlace.setEnabled(false);
    }

    @Override
    public void onGameFinishedMessageReceived(String name) {
        chatArea.append("***"+name+" WON!***");
        gamePlace.setEnabled(false);
    }

    class ShipDragHandler extends MouseAdapter{
        int size;
        public ShipDragHandler(int size){
            this.size = size;
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);
           if(me.getSelectedShipSize() == size){
               me.setSelectedShipSize(0);
           }
           else
               me.setSelectedShipSize(size);
        }
    }
    public String getChat(){
        return chatArea.getText();
    }

}
