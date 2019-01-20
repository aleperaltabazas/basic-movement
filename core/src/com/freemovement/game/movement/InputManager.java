package com.freemovement.game.movement;

import com.badlogic.gdx.InputAdapter;

import static com.freemovement.game.movement.KeyAdapter.*;

public class InputManager extends InputAdapter {
    private boolean movingWest;
    private boolean movingEast;
    private boolean movingNorth;
    private boolean movingSouth;

    private boolean running;

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case NORTH:
                return movingNorth = true;
            case SOUTH:
                return movingSouth = true;
            case EAST:
                return movingEast = true;
            case WEST:
                return movingWest = true;
            case RUNNING:
                return running = true;
            default:
                return false;
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case NORTH:
                return !(movingNorth = false);
            case SOUTH:
                return !(movingSouth = false);
            case EAST:
                return !(movingEast = false);
            case WEST:
                return !(movingWest = false);
            case RUNNING:
                return !(running = false);
            default:
                return false;
        }
    }

    public boolean isMovingWest() {
        return movingWest;
    }

    public boolean isMovingEast() {
        return movingEast;
    }

    public boolean isMovingNorth() {
        return movingNorth;
    }

    public boolean isMovingSouth() {
        return movingSouth;
    }

    public boolean isRunning() {
        return running;
    }
}
