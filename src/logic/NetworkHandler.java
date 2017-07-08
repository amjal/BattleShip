package logic;

import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by amir on 7/3/17.
 */
public class NetworkHandler{
    private TCPChannel mTCPChannel;
    private boolean go = true;
    private Queue<byte[]> sendQueue;
    private Queue<byte[]> receivedQueue;
    private ReceivedMessageConsumer consumerThread;
    private INetworkHandlerCallback iNetworkHandlerCallback;
    private SocketAddress remoteAddress;
    public NetworkHandler(SocketAddress socketAddress , INetworkHandlerCallback iNetworkHandlerCAllback){

    }
    public NetworkHandler(Socket socket, INetworkHandlerCallback iNetworkHandlerCAllback , String name){
        mTCPChannel = new TCPChannel(socket);
        sendQueue = new PriorityQueue<>();
        receivedQueue = new PriorityQueue<>();
        this.iNetworkHandlerCallback = iNetworkHandlerCAllback;
        sendMessage(new GreetingMessage(name));
        consumerThread = new ReceivedMessageConsumer();
        remoteAddress = mTCPChannel.getRemoteAddress();
        new Sender();
        new Reader();
    }
    public void sendMessage(BaseMessage baseMessage){
        baseMessage.serialize();
        sendQueue.add(baseMessage.getSerialized());
    }
    public void stopSelf(){
        go = false;
        mTCPChannel.closeChannel();
    }
    private byte[] readChannel(int count){
        return mTCPChannel.read(count);
    }
    private int getIncomeMessageSize(){
        return ByteBuffer.wrap(mTCPChannel.read(4)).getInt();
    }
    private class ReceivedMessageConsumer extends Thread{
        ReceivedMessageConsumer(){
            start();
        }
        @Override
        public void run() {
            while (go && mTCPChannel.isConnected()) {
                if (receivedQueue.size() > 0) {
                    switch (receivedQueue.peek()[0]) {
                        case MessageTypes.GREETING: {
                            iNetworkHandlerCallback.onMessageReceived(new GreetingMessage(receivedQueue.poll()),
                                    NetworkHandler.this);
                            break;
                        }
                        case MessageTypes.REQUEST_ANSWER: {
                            iNetworkHandlerCallback.onMessageReceived(new RequestAnswerMessage(receivedQueue.poll()),
                                    NetworkHandler.this);
                            break;
                        }
                        case MessageTypes.CHAT:{
                           iNetworkHandlerCallback.onMessageReceived(new ChatMessage(receivedQueue.poll()),
                                   NetworkHandler.this);
                            break;
                        }
                    }
                }
                else {
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        }
    private class Sender extends Thread{
        Sender(){
            start();
        }
        @Override
        public void run() {
            while(go && mTCPChannel.isConnected()) {
                if(sendQueue.size() >0)
                    mTCPChannel.write(sendQueue.poll());
                else {
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private class Reader extends Thread{
        Reader(){
            start();
        }
        @Override
        public void run() {
            while(go && mTCPChannel.isConnected()){
                byte[] buffer;
                int size = getIncomeMessageSize();
                if(size > 0){
                    buffer = readChannel(size -4);
                    receivedQueue.add(buffer);
                }else{
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public SocketAddress getRemoteAddress(){
        return remoteAddress;
    }
}