package logic;

import java.net.SocketAddress;

/**
 * Created by amir on 7/5/17.
 */
public interface INetworkHandlerCallback{
    void onMessageReceived(BaseMessage baseMessage , NetworkHandler networkHandler);
    void onSocketClosed();
}
