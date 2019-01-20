package com.freemovement.game.player.movement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Standing implements MovementState {
    @Override
    public TextureRegion getFrame(MovementData data) {
        return data.textureMap.getStanding(data.direction);
    }
}
