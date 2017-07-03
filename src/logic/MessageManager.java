package logic;

import java.util.List;

/**
 * Created by amir on 7/3/17.
 */
public class MessageManager implements ServerSocketHandler.IServerSocketHandlerCallback, NetworkHandler.INetworkHandlerCallback{
    private ServerSocketHandler mServerSocketHandler;
    private List<NetworkHandler> mNetworkHandlerList;
    public MessageManager(int port){

    }
    public MessageManager(String ip, int port){

    }
    @Override
    public void onNewConnectionReceived(NetworkHandler networkHandler) {

    }

    @Override
    public void onMessageReceived(BaseMessage baseMessage) {

    }

    @Override
    public void onSocketClosed() {

    }
}
