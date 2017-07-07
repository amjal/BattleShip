package logic;

import java.nio.ByteBuffer;

/**
 * Created by amir on 7/6/17.
 */
public class GreetingMessage extends BaseMessage{
    String name;
    public GreetingMessage(String name){
        this.name = name;
    }
    public GreetingMessage(byte[] serialized){
        this.serialized = serialized;
    }
    @Override
    protected void serialize() {
        byte[] serializedName = name.getBytes();
        int size = 4+1+serializedName.length;
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.putInt(size);
        buffer.put(MessageTypes.GREETING);
        buffer.put(serializedName);
        serialized = buffer.array();
    }
    @Override
    protected void deserialize() {
        name = new String(serialized , 1 , serialized.length -1);
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.GREETING;
    }
    public String getName(){
        return name;
    }
}
