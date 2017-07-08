package logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;

/**
 * Created by amir on 7/3/17.
 */
public class ServerSocketHandler extends  Thread{
    int port;
    INetworkHandlerCallback iNetworkHandlerCallback;
    IServerSocketHandlerCallback iServerSocketHandlerCallback;
    String name;
    boolean lookingForConnection = true;
    public ServerSocketHandler(int port, INetworkHandlerCallback iNetworkHandlerCallback,
                               IServerSocketHandlerCallback iServerSocketHandlerCallback , String name){
        this.port = port;
        this.iNetworkHandlerCallback = iNetworkHandlerCallback;
        this.iServerSocketHandlerCallback = iServerSocketHandlerCallback;
        this.name = name;
        start();
    }
    @Override
    public void run(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("successfully created server");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(lookingForConnection) {
            try {
                Socket s = serverSocket.accept();
                System.out.println("client connected!");
                iServerSocketHandlerCallback.onNewConnectionReceived(new NetworkHandler(s , iNetworkHandlerCallback , name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void stopSelf(){
        lookingForConnection = false;
    }
}
