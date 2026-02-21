package dk.sdu.mmmi.cbse.common.data;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameData {

    /*private int displayWidth  = 800 ;
    private int displayHeight = 800;*/
    private final GameKeys keys = new GameKeys();
    private Pane pane;

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public GameKeys getKeys() {
        return keys;
    }

    /*public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }*/

    public int getDisplayWidth() {
        return (int)pane.getWidth();
    }

    /*public void setDisplayHeight(int height) {
        return
    }*/

    public int getDisplayHeight() {
        return (int)pane.getHeight();
    }
}