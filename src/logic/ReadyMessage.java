package logic;

import view.CellState;

import java.nio.ByteBuffer;

/**
 * Created by amir on 7/8/17.
 */
public class ReadyMessage extends BaseMessage {
    CellState[][] states = new CellState[10][10];
    public ReadyMessage(CellState[][] states){
        this.states = states;
    }
    public ReadyMessage(byte[] serialized){
        this.serialized = serialized;
    }
    @Override
    protected void serialize() {
        int size = 4 + 1 + 400;
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.putInt(size);
        buffer.put(MessageTypes.READY);
        for(int i =0 ; i < 10 ; i ++){
            for(int j=0 ; j < 10 ; j ++){
                buffer.putInt(states[i][j].ordinal());
            }
        }
        serialized = buffer.array();
    }

    @Override
    protected void deserialize() {
        for (int index=1 ; index<serialized.length ; index+=4){
            byte[] buffer = new byte[4];
            buffer[0] = serialized[index];
            buffer[1] = serialized[index+1];
            buffer[2] = serialized[index+2];
            buffer[3] = serialized[index+3];
            states[(index - 1)/4/10][((index-1)/4)%10] = CellState.values()[ByteBuffer.wrap(buffer).getInt()];
        }
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.READY;
    }
    public CellState[][] getStates(){
        return states;
    }
}
