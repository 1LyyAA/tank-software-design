package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.Directions;
import ru.mipt.bit.platformer.Tank;

public class KeyboardListener implements com.badlogic.gdx.InputProcessor {
    

    private Tank tank;
    public KeyboardListener(Tank tank) {
        this.tank = tank;
    }
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                tank.tryMove(Directions.UP);
                break;
            case Input.Keys.S:
                tank.tryMove(Directions.DOWN);
                break;
            case Input.Keys.A:
                tank.tryMove(Directions.LEFT);
                break;
            case Input.Keys.D:
                tank.tryMove(Directions.RIGHT);
                break;
        }
        return true;
    }

    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char c) { return false; }
    @Override public boolean touchDown(int x, int y, int p, int b) { return false; }
    @Override public boolean touchUp(int x, int y, int p, int b) { return false; }
    @Override public boolean touchDragged(int x, int y, int p) { return false; }
    @Override public boolean mouseMoved(int x, int y) { return false; }
    @Override public boolean scrolled(int x) { return false; }
}
