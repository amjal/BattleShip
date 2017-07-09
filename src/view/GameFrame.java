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
    GamePanel gamePanel;
    public GameFrame(MessageManager messageManager , Player me) {
        super(me.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        gamePanel = new GamePanel(messageManager , me);
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
        setJMenuBar(jMenuBar);
        FileManager fileManager=new FileManager();
        saveChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileManager.addToJsonObject(gamePanel.getChat());
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
    @Override
    public void dispose(){
        gamePanel.onGameFinished(true);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
