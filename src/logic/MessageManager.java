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
    public MessageManager(String ip, int port) throws IOException{
        Socket socket = new Socket(ip , port);
        networkHandlerList.add(new NetworkHandler(socket , this));
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
