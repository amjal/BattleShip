package logic;

import view.ConnectionWaitList;

import java.io.IOException;
import java.net.InetAddress;
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
    public MessageManager(int port , ConnectionWaitList connectionWaitList , String name){
        networkHandlerList = new ArrayList<>();
        serverSocketHandler = new ServerSocketHandler(port, this, this , name);
        this.connectionWaitList = connectionWaitList;
    }
    public MessageManager(String ip, int port , String name) throws IOException{
        networkHandlerList = new ArrayList<>();
        Socket socket = new Socket(InetAddress.getByName(ip), port);
        networkHandlerList.add(new NetworkHandler(socket , this , name));
    }
    @Override
    public void onNewConnectionReceived(NetworkHandler networkHandler) {
        networkHandlerList.add(networkHandler);
    }

    @Override
    public void onMessageReceived(BaseMessage baseMessage , NetworkHandler networkHandler) {
        switch (baseMessage.getMessageType()){
            case MessageTypes.GREETING:{
                if(serverSocketHandler != null){
                    GreetingMessage m = (GreetingMessage)baseMessage;
                    m.deserialize();
                    connectionWaitList.addPanel(m.getName() ,
                            ""+networkHandler.getRemoteAddress());
                }
                break;
            }
        }
    }

    @Override
    public void onSocketClosed() {

    }
}
