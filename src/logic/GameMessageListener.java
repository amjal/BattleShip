package logic;

import view.Cell;

/**
 * Created by amir on 7/9/17.
 */
public interface GameMessageListener {
    void onGameMessageReceived(Cell cell);
}
