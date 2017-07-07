package logic;

import java.net.SocketAddress;

/**
 * Created by amir on 7/5/17.
 */
public interface IServerSocketHandlerCallback{
    void onNewConnectionReceived(NetworkHandler networkHandler);
    void onRequestAccepted(String address);
    void onRequestRejected(String address);
}
