package logic;

import java.nio.ByteBuffer;

/**
 * Created by amir on 7/7/17.
 */
public class ChatMessage extends BaseMessage{
    private String text;
    public ChatMessage(String text){
        this.text = text;
    }
    public ChatMessage(byte[] serialized){
        this.serialized = serialized;
    }
    @Override
    protected void serialize() {
        byte[] textArray = text.getBytes();
        int size = 4 + 1 + textArray.length;
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.putInt(size);
        buffer.put(MessageTypes.CHAT);
        buffer.put(textArray);
        serialized = buffer.array();
    }

    @Override
    protected void deserialize() {
        text = new String(serialized , 1, serialized.length -1);
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.CHAT;
    }
    public String getText(){
        return text;
    }
}
