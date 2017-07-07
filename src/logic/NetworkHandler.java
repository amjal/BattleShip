package logic;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by amir on 7/3/17.
 */
public class NetworkHandler extends Thread {
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
        start();
    }
    public void sendMessage(BaseMessage baseMessage){
        baseMessage.serialize();
        sendQueue.add(baseMessage.getSerialized());
    }
    @Override
    public void run(){
        while(go && mTCPChannel.isConnected()){
            for(int i =0 ; i < sendQueue.size() ; i ++) {
                /*for(int j =0 ; j < sendQueue.peek().length ; j++){
                    System.out.print(sendQueue.peek()[j]);
                }*/
                System.out.println();
                mTCPChannel.write(sendQueue.poll());
            }
            byte[] buffer;
            int size = getIncomeMessageSize();
            if(size > 0){
                buffer = readChannel(size -4);
                receivedQueue.add(buffer);
                consumerThread.start();
            }
        }
    }
    public void stopSelf(){
        go = false;
    }
    private byte[] readChannel(int count){
        return mTCPChannel.read(count);
    }
    private int getIncomeMessageSize(){
        return ByteBuffer.wrap(mTCPChannel.read(4)).getInt();
    }
    private class ReceivedMessageConsumer extends Thread{
        @Override
        public void run(){
            while (receivedQueue.size()>0){
                switch (receivedQueue.peek()[0]){
                    case MessageTypes.GREETING: {
                        /*for(int i =0 ; i < receivedQueue.peek().length ; i ++){
                            System.out.print(receivedQueue.peek()[i]);
                        }*/
                        iNetworkHandlerCallback.onMessageReceived(new GreetingMessage(receivedQueue.poll()) ,
                                NetworkHandler.this);
                        break;
                    }
                }
            }
        }
    }
    public SocketAddress getRemoteAddress(){
        return remoteAddress;
    }
}