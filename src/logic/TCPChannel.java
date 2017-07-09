package logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * Created by amir on 7/3/17.
 */
public class TCPChannel {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    public TCPChannel(SocketAddress socketAddress){

    }
    public TCPChannel(Socket socket){
        this.socket = socket;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public byte[] read(final int count){
        byte[] output = new byte[count];
        try {
            inputStream.read(output);
        } catch (IOException e) {
        }
        return output;
    }
    public void write(byte[] data){
        try {
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isConnected(){
        return socket.isConnected();
    }
    public void closeChannel(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public SocketAddress getRemoteAddress(){
        return socket.getRemoteSocketAddress();
    }
}
