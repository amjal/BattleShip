package view;

import logic.*;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by parsa on 7/5/17.
 */
public class GamePanel extends JPanel implements ChatListener , ShipReducedListener , GameMessageListener {
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
    Player currentPlayer;
    public GamePanel(MessageManager messageManager , Player me) {
        this.messageManager = messageManager;
        this.me = me;
        enemy = new Player();
        me.setPlayerType(PlayerType.ME);
        messageManager.addChatListener(this);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageManager.onSendMessage(new ChatMessage(me.getName() + ": " + chatMessageField.getText()));
                chatArea.append(me.getName() + ": " + chatMessageField.getText()+"\n");
                chatMessageField.setText("");
            }
        });
        currentPlayer = me;
        currentPlayer.getGamePlace(gamePlace);
        currentPlayer.addShipReducedListener(this);
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
                currentPlayer.reset();
                currentPlayer.setSelectedShipSize(0);
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
                        size2ShipN.getText().equals("0") && size1ShipN.getText().equals("0"))
                    messageManager.onSendMessage(new GameMessage(me.getCellStates()));
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
    public void onGameMessageReceived(CellState[][] states) {
        enemy.setPlayerType(PlayerType.ENEMY);
        enemy.setCellsStates(states);
    }

    class ShipDragHandler extends MouseAdapter{
        int size;
        public ShipDragHandler(int size){
            this.size = size;
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);
           if(currentPlayer.getSelectedShipSize() == size){
               currentPlayer.setSelectedShipSize(0);
           }
           else
               currentPlayer.setSelectedShipSize(size);
        }
    }
}
