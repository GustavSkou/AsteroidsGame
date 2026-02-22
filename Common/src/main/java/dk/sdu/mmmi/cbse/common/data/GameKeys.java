package dk.sdu.mmmi.cbse.common.data;

public class GameKeys {

    private static boolean[] keyState;

    private static final int NUM_KEYS = 4;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int SPACE = 3;

    public GameKeys() {
        keyState = new boolean[NUM_KEYS];
    }

    public void update() { }

    public void setKey(int k, boolean b) {
        keyState[k] = b;
    }

    public boolean isDown(int k) {
        return keyState[k];
    }
}
