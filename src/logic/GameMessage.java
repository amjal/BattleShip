package logic;

import view.CellState;
import view.Player;

/**
 * Created by amir on 7/8/17.
 */
public class GameMessage extends BaseMessage {
    CellState[][] states;
    public GameMessage(CellState[][] states){
        this.states = states;
    }
    public GameMessage(byte[] serialized){
        this.serialized = serialized;
    }
    @Override
    protected void serialize() {

    }

    @Override
    protected void deserialize() {

    }

    @Override
    public byte getMessageType() {
        return 0;
    }
    public CellState[][] getStates(){
        return states;
    }
}
