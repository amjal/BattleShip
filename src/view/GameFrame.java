package view;

import logic.MessageManager;
import model.FileManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by parsa on 7/5/17.
 */
public class GameFrame extends JFrame {
   // @TODO inja ye method mikham textarea ro stringesho bede
String chat="chato inja bezan";
    public GameFrame(MessageManager messageManager , Player me) {
        super("battle ship");
        setSize(1000, 700);
        setDefaultCloseOperation(3);
        GamePanel gamePanel = new GamePanel(messageManager , me);
        add(gamePanel.getFullPanel());
        JMenuBar jMenuBar =new JMenuBar();

        JMenu file=new JMenu("file");
        JMenu help=new JMenu("help");

        JMenuItem saveChat=new JMenuItem("save chat");
        JMenuItem loadCHat=new JMenuItem("load chats");
        jMenuBar.add(file);
        jMenuBar.add(help);
        file.add(saveChat);
        file.add(loadCHat);
        FileManager fileManager=new FileManager();
        saveChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileManager.addToJsonObject(chat);

            }
        });
        loadCHat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileManager.loadHistory();
            }
        });
        setVisible(true);
    }


}
