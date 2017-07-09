package view;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by parsa on 7/9/17.
 */
public class ChatHistoryPreview extends  JPanel{

    public ChatHistoryPreview(JSONObject jsonObject){
        setLayout(null);
        JLabel nameLabel = new JLabel(jsonObject.getString("last chat"));
        nameLabel.setBounds(0 ,0 , 200 , 20);
        JLabel date = new JLabel(jsonObject.getString("date and time"));
        date.setBounds(0 , 25, 200 , 20);
        JButton view = new JButton("view");
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                JFrame jFrame=new JFrame();
                JTextArea textArea=new JTextArea();

                jFrame.setSize(200,600);
                textArea.setSize(1000,1000);
                textArea.append(jsonObject.getString("chat"));
                textArea.repaint();
               jFrame.add(textArea);
               textArea.setEditable(false);
               textArea.revalidate();
               textArea.repaint();
                jFrame.revalidate();
                jFrame.setVisible(true);
                textArea.setVisible(true);






            }
        });
        view.setBounds(0,60 , 98 , 50);

        add(nameLabel);
        add(date);
        add(view);


    }
}
