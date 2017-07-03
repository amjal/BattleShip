package logic;

/**
 * Created by amir on 7/3/17.
 */
public class ServerSocketHandler extends  Thread{
    public ServerSocketHandler(int port, NetworkHandler.INetworkHandlerCallback iNetworkHandlerCallback,
                               IServerSocketHandlerCallback iServerSocketHandlerCallback){

    }
    @Override public void run(){

    }
    public void stopSelf(){

    }
    public interface IServerSocketHandlerCallback{
        void onNewConnectionReceived(NetworkHandler networkHandler);
    }
}
