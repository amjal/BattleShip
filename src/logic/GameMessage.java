package logic;

import view.Cell;

/**
 * Created by amir on 7/9/17.
 */
public class GameMessage extends BaseMessage {
    Cell cell;
    public GameMessage(Cell cell){
        this.cell = cell;
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
    public Cell getCell(){
        return cell;
    }
}
