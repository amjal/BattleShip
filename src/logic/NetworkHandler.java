package logic;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
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
    private ReceivedMessageConsumer mConsumerThread;
    private INetworkHandlerCallback iNetworkHandlerCallback;
    public NetworkHandler(SocketAddress socketAddress , INetworkHandlerCallback iNetworkHandlerCAllback){

    }
    public NetworkHandler(Socket socket, INetworkHandlerCallback iNetworkHandlerCAllback){
        mTCPChannel = new TCPChannel(socket);
        this.iNetworkHandlerCallback = iNetworkHandlerCAllback;
    }
    public void sendMessage(BaseMessage baseMessage){
        sendQueue.add(baseMessage.getSerialized());
    }
    @Override
    public void run(){
        while(go && mTCPChannel.isConnected()){
            for(int i =0 ; i < sendQueue.size() ; i ++) {
                mTCPChannel.write(sendQueue.poll());
            }
            byte[] buffer;
            do{
                buffer = readChannel();
                receivedQueue.add(buffer);
            }while(buffer[3] != 0);
        }
    }
    public void stopSelf(){
        go = false;
    }
    private byte[] readChannel(){
        return mTCPChannel.read(4);
    }
    private class ReceivedMessageConsumer extends Thread{
        @Override
        public void run(){
            for(int i =0 ; i < receivedQueue.size() ; i++){
                //iNetworkHandlerCallback.onMessageReceived();
            }
        }
    }
}