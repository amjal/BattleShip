package logic;

import java.nio.ByteBuffer;

/**
 * Created by amir on 7/9/17.
 */
public class GameFinishedMessage extends BaseMessage {
    private String name;
    private boolean left;
    public GameFinishedMessage(String name , boolean left){
        this.name = name;
        this.left = left;
    }
    public GameFinishedMessage(byte[] serialized){
        this.serialized = serialized;
    }
    @Override
    protected void serialize() {
        byte[] nameArray = name.getBytes();
        int size = 4 + 1 + nameArray.length+1;
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.putInt(size);
        buffer.put(MessageTypes.GAME_FINISHED);
        buffer.put(nameArray);
        byte l;
        if(left) l = 1;
        else l = 0;
        buffer.put(l);
        serialized = buffer.array();
    }

    @Override
    protected void deserialize() {
        name = new String(serialized , 1, serialized.length -2);
        if(serialized[serialized.length-1] == 1){
            left = true;
        }
        else left = false;
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.GAME_FINISHED;
    }
    public String getName(){
        return name;
    }
    public boolean hasLeft(){
        return left;
    }
}
