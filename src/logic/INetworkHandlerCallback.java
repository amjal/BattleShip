package logic;

/**
 * Created by amir on 7/5/17.
 */
public interface INetworkHandlerCallback{
    void onMessageReceived(BaseMessage baseMessage);
    void onSocketClosed();
}
