package view;

import javax.swing.*;

/**
 * Created by parsa on 7/9/17.
 */
public class PastChat extends JFrame{

    public JPanel panel1;
    public JTextArea textArea1;

    public PastChat(String str) {
        super();
        setSize(220,500);
        add(panel1);
        setVisible(true);
     textArea1.setText(str);
     textArea1.setVisible(true);
     panel1.setVisible(true);
    }

}
