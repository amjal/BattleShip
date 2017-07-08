package logic;

import view.ConnectionWaitList;
import view.Player;

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
    ConnectionWaitList connectionWaitList;
    RequestAnswerListener ral;
    ChatListener cl;
    public MessageManager(int port , String name){
        networkHandlerList = new ArrayList<>();
        serverSocketHandler = new ServerSocketHandler(port, this, this , name);
        connectionWaitList = new ConnectionWaitList(this , new Player(name));
    }
    public MessageManager(String ip, int port , String name) throws IOException{
        Socket socket = new Socket(InetAddress.getByName(ip), port);
        networkHandlerList = new ArrayList<>();
        networkHandlerList.add(new NetworkHandler(socket , this , name));
    }
    @Override
    public void onNewConnectionReceived(NetworkHandler networkHandler) {
        networkHandlerList.add(networkHandler);
    }

    @Override
    public void onRequestAccepted(String address) {
        for(NetworkHandler n:networkHandlerList){
            if(n.getRemoteAddress().toString().equals(address)){
                n.sendMessage(new RequestAnswerMessage(true));
            }
        }
        serverSocketHandler.stopSelf();
    }

    @Override
    public void onRequestRejected(String address) {
        for(int i =0 ; i < networkHandlerList.size() ; i++){
            if(networkHandlerList.get(i).getRemoteAddress().toString().equals(address)){
                networkHandlerList.get(i).sendMessage(new RequestAnswerMessage(false));
                networkHandlerList.remove(i);
            }
        }
    }

    @Override
    public void onMessageReceived(BaseMessage baseMessage , NetworkHandler networkHandler) {
        switch (baseMessage.getMessageType()){
            case MessageTypes.GREETING:{
                GreetingMessage m = (GreetingMessage)baseMessage;
                m.deserialize();
                if(serverSocketHandler != null){
                    connectionWaitList.addPanel(m.getName() ,
                            ""+networkHandler.getRemoteAddress());
                }
                break;
            }
            case MessageTypes.REQUEST_ANSWER:{
                RequestAnswerMessage m = (RequestAnswerMessage)baseMessage;
                m.deserialize();
                if(m.isAccepted()){
                    ral.onAccept();
                }
                else{
                    networkHandler.stopSelf();
                    networkHandlerList.remove(networkHandler);
                    ral.onReject();
                }
                break;
            }
            case MessageTypes.CHAT:{
                ChatMessage m = (ChatMessage)baseMessage;
                m.deserialize();
                cl.onChatReceived(m.getText());
                break;
            }
        }
    }

    @Override
    public void onSendMessage(BaseMessage baseMessage) {
        networkHandlerList.get(0).sendMessage(baseMessage);
    }

    @Override
    public void onSocketClosed() {

    }
    public void addRequestAnswerListener(RequestAnswerListener ral){
        this.ral = ral;
    }
    public void addChatListener(ChatListener cl){
        this.cl = cl;
    }
}
