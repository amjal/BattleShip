package logic;

import view.CellState;
import view.Player;

/**
 * Created by amir on 7/8/17.
 */
public interface GameMessageListener {
    void onGameMessageReceived(CellState[][] states);
}
