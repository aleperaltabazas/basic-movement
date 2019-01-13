package com.basic.movement;

public class InputManager {
    private boolean movingLeft;
    private boolean movingRight;
    private boolean movingUp;
    private boolean movingDown;

    private boolean moving = false;

    private Direction direction = Direction.DOWN;

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public boolean isMoving(Direction direction) {
        switch (direction) {
            case DOWN:
                return movingRight || movingUp || movingLeft;
            case UP:
                return movingRight || movingDown || movingLeft;
            case LEFT:
                return movingRight || movingDown || movingUp;
            case RIGHT:
                return movingLeft || movingDown || movingUp;
            default:
                return false;
        }
    }

    public void stopMoving() {
        movingLeft = false;
        movingDown = false;
        movingRight = false;
        movingUp = false;
        moving = false;
    }

    public enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN
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
}
