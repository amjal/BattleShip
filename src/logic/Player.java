package logic;

import java.net.InetAddress;
import java.net.SocketAddress;

/**
 * Created by amir on 7/6/17.
 */
public class Player {
    private SocketAddress IP;

    public SocketAddress getIP() {
        return IP;
    }

    public String getName() {
        return name;
    }

    private String name;
    public Player(SocketAddress IP , String name){
        this.IP = IP;
        this.name = name;
    }
}
