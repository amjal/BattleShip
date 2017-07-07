package view;

/**
 * Created by parsa on 7/7/17.
 */
public class GamePanelInfo {
    private int[][] gamePanelArray;


    public GamePanelInfo(int[][] gamePanelArray) {
        this.gamePanelArray = gamePanelArray;
    }

    public GamePanelInfo() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gamePanelArray[i][j]=3;
            }
        }
    }

    public void setGamePanelArrayij(int i, int j, int status) {
        this.gamePanelArray[i][j] = status;
    }

    public int[][] getGamePanelArray() {
        return gamePanelArray;
    }
}
