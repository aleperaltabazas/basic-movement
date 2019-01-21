package com.basic.movement.player.movementState;

public abstract class Moving implements MovementState {
    final float SPEED;

    public Moving(float speed) {
        SPEED = speed;
    }
}
