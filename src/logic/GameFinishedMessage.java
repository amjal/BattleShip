package logic;

import java.nio.ByteBuffer;

/**
 * Created by amir on 7/9/17.
 */
public class GameFinishedMessage extends BaseMessage {
    private String name;
    public GameFinishedMessage(String name){
        this.name = name;
    }
    public GameFinishedMessage(byte[] serialized){
        this.serialized = serialized;
    }
    @Override
    protected void serialize() {
        byte[] nameArray = name.getBytes();
        int size = 4 + 1 + nameArray.length;
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.putInt(size);
        buffer.put(MessageTypes.GAME_FINISHED);
        buffer.put(nameArray);
        serialized = buffer.array();
    }

    @Override
    protected void deserialize() {
        name = new String(serialized , 1, serialized.length -1);
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.GAME_FINISHED;
    }
    public String getName(){
        return name;
    }
}
