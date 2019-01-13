package com.basic.movement;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class BrendanInput extends InputAdapter {
    private InputManager manager;

    public BrendanInput(InputManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                manager.setMovingLeft(true);
                return true;
            case Input.Keys.RIGHT:
                manager.setMovingRight(true);
                return true;
            case Input.Keys.UP:
                manager.setMovingUp(true);
                return true;
            case Input.Keys.DOWN:
                manager.setMovingDown(true);
                return true;
            case Input.Keys.ANY_KEY:
                manager.setMovingAtAll(true);
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                manager.setMovingLeft(false);
                return true;
            case Input.Keys.RIGHT:
                manager.setMovingRight(false);
                return true;
            case Input.Keys.UP:
                manager.setMovingUp(false);
                return true;
            case Input.Keys.DOWN:
                manager.setMovingDown(false);
                return true;
            default:
                return false;
        }
    }
}
