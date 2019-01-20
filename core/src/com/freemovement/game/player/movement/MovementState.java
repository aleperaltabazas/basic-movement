package com.freemovement.game.player.movement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface MovementState {
    TextureRegion getFrame(MovementData data);
}
