package logic;

/**
 * Created by amir on 6/28/17.
 */
public abstract class BaseMessage {
    protected byte[] serialized;
    protected abstract void serialize();
    protected abstract void deserialize();
    public abstract byte getMessageType();
    public byte[] getSerialized(){
        return serialized;
    }
}
