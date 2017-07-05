package logic;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by amir on 7/3/17.
 */
public class TCPChannel {
    private Socket mSocket;
    private OutputStream mOUtputStream;
    private InputStream mINputStream;
    public TCPChannel(SocketAddress socketAddress ,int timeout){

    }
    public TCPChannel(Socket Socket , int timeout){

    }
    //public byte[] read(final int count){

    //}
    public void write(byte[] data){

    }
    //public boolean isConnected(){

    //}
    public void closeChannel(){

    }
}
