package dk.sdu.mmmi.cbse.common.data;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameData {
    private final GameKeys keys = new GameKeys();
    private Pane pane;
    private double deltaTime;
    private double timeNow = 0;
    private double timePre = 0;
    private double score = 0;

    public double getDeltaTime() {
        return deltaTime;
    }

    public void updateDeltaTime(double timeNow) {

        this.timeNow = timeNow / 100000000; // time in seconds
        this.deltaTime = this.timeNow - this.timePre; // calculate time between frames
        this.timePre = this.timeNow;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public GameKeys getKeys() {
        return keys;
    }

    public int getDisplayWidth() {
        return (int)pane.getWidth();
    }

    public int getDisplayHeight() {
        return (int)pane.getHeight();
    }
}