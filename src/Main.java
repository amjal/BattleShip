
import logic.Player;
import model.FileManager;
import view.GameFrame;
import view.SelectConnectionMode;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by amir on 6/28/17.
 */
public class Main {
    public static void main(String[] args) {

        //new SelectConnectionMode();
        FileManager fileManager = new FileManager("hello \n whats \n up");
        try {
            String str  =fileManager.readFile("file.txt");
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
