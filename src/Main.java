
import view.Player;
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
       try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
        e.printStackTrace();
        }
        new SelectConnectionMode();



    }
}
