package com.basic.movement.movement;

public class InputManager {
    private boolean movingLeft;
    private boolean movingRight;
    private boolean movingUp;
    private boolean movingDown;

    private boolean running;
    private boolean interacting ;

    public boolean isMovingWest() {
        return movingLeft;
    }

    public boolean isMovingEast() {
        return movingRight;
    }

    public boolean isMovingNorth() {
        return movingUp;
    }

    public boolean isMovingSouth() {
        return movingDown;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public void setInteracting(boolean interacting) {
        this.interacting = interacting;
    }

    public boolean isInteracting() {
        return interacting;
    }
}
