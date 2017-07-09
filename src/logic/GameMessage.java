package logic;

import view.Cell;
import view.CellState;

import java.awt.*;
import java.nio.ByteBuffer;

/**
 * Created by amir on 7/9/17.
 */
public class GameMessage extends BaseMessage {
    Cell cell = new Cell();
    public GameMessage(Cell cell){
        this.cell = cell;
    }
    public GameMessage(byte[] serialized){
        this.serialized = serialized;
    }
    @Override
    protected void serialize() {
        int size = 4 + 1 + 4 + 4 + 4;
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.putInt(size);
        buffer.put(MessageTypes.GAME);
        buffer.putInt((int)cell.getLocation().getX());
        buffer.putInt((int)cell.getLocation().getY());
        buffer.putInt(cell.getCellState().ordinal());
        serialized = buffer.array();
    }

    @Override
    protected void deserialize() {
        byte[] x = new byte[4];
        x[0] = serialized[1];
        x[1] = serialized[2];
        x[2] = serialized[3];
        x[3] = serialized[4];
        byte[]y = new byte[4];
        y[0] = serialized[5];
        y[1] = serialized[6];
        y[2] = serialized[7];
        y[3] = serialized[8];
        cell.setLocation(new Point(ByteBuffer.wrap(x).getInt() , ByteBuffer.wrap(y).getInt()));
        byte[]state = new byte[4];
        state[0] = serialized[9];
        state[1] = serialized[10];
        state[2] = serialized[11];
        state[3] = serialized[12];
        cell.setState(CellState.values()[ByteBuffer.wrap(state).getInt()]);
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.GAME;
    }
    public Cell getCell(){
        return cell;
    }
}
