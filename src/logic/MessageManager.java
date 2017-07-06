package logic;

import view.ConnectionWaitList;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amir on 7/3/17.
 */
public class MessageManager implements IServerSocketHandlerCallback, INetworkHandlerCallback{
    private ServerSocketHandler serverSocketHandler;
    private List<NetworkHandler> networkHandlerList;
    private ConnectionWaitList connectionWaitList;
    public MessageManager(int port , ConnectionWaitList connectionWaitList){
        networkHandlerList = new ArrayList<>();
        serverSocketHandler = new ServerSocketHandler(port, this, this);
        this.connectionWaitList = connectionWaitList;
    }
    public MessageManager(String ip, int port) throws IOException{
        networkHandlerList = new ArrayList<>();
        Socket socket = new Socket(ip , port);
        networkHandlerList.add(new NetworkHandler(socket , this));
    }
    @Override
    public void onNewConnectionReceived(NetworkHandler networkHandler) {
        networkHandlerList.add(networkHandler);
        connectionWaitList.addPanel(networkHandler.getPlayer().getName() , ""+networkHandler.getPlayer().getIP());
    }

    @Override
    public void onMessageReceived(BaseMessage baseMessage) {

    }

    @Override
    public void onSocketClosed() {

    }
}
