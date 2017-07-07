package logic;

/**
 * Created by amir on 7/7/17.
 */
public class RequestAnswerMessage extends BaseMessage{
    boolean accepted;
    public RequestAnswerMessage(boolean accepted){
        this.accepted = accepted;
    }
    public RequestAnswerMessage(byte[] buffer){
        serialized = buffer;
    }
    @Override
    protected void serialize() {
        serialized = new byte[6];
        serialized[0] = 0;
        serialized[1] = 0;
        serialized[2] = 0;
        serialized[3] = 6;
        serialized[4] = MessageTypes.REQUEST_ANSWER;
        if (accepted)
            serialized[5] = 1;
        else
            serialized[5] = 0;
    }

    @Override
    protected void deserialize() {
        if(serialized[1] == 1)
            accepted = true;
        else accepted = false;
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.REQUEST_ANSWER;
    }
    public boolean isAccepted(){
        return accepted;
    }
}
