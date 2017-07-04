package logic;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.Queue;

/**
 * Created by amir on 7/3/17.
 */``
public class NetworkHandler extends Thread {
    private TCPChannel mTCPChannel;
    private Queue<byte[]> mSendQueue;
    private Queue<byte[]> mRecievedQueue;
    private ReceivedMessageConsumer mConsumerThread;
    public NetworkHandler(SocketAddress socketAddress , INetworkHandlerCallback iNetworkHandlerCAllback){

    }
    public NetworkHandler(Socket socket, INetworkHandlerCallback iNetworkHandlerCAllback){

    }
    public void sendMessage(BaseMessage baseMessage){

    }
    @Override public void run(){

    }
    public void stopSelf(){

    }
    private byte[] readChannel(){

    }
    private class ReceivedMessageConsumer extends Thread{
        @Override public void run(){

        }
    }
    public interface INetworkHandlerCallback{
        void onMessageReceived(BaseMessage baseMessage);
        void onSocketClosed();
    }
}