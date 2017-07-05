package logic;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Created by amir on 7/3/17.
 */
public class MessageManager implements IServerSocketHandlerCallback, INetworkHandlerCallback{
    private ServerSocketHandler serverSocketHandler;
    private List<NetworkHandler> networkHandlerList;
    public MessageManager(int port){
        serverSocketHandler = new ServerSocketHandler(port, this, this);
    }
    public MessageManager(String ip, int port){
        try {
            networkHandlerList.add(new NetworkHandler(new Socket(ip , port) , this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onNewConnectionReceived(NetworkHandler networkHandler) {
        networkHandlerList.add(networkHandler);
    }

    @Override
    public void onMessageReceived(BaseMessage baseMessage) {

    }

    @Override
    public void onSocketClosed() {

    }
}
