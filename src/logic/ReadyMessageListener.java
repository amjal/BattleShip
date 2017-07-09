package logic;

import view.CellState;

/**
 * Created by amir on 7/8/17.
 */
public interface ReadyMessageListener {
    void onReadyMessageReceived(CellState[][] cellStates);
}
