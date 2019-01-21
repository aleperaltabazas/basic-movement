package com.freemovement.game.player.movement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.freemovement.game.player.Player;

public class Standing implements MovementState {
    @Override
    public TextureRegion getFrame(MovementData data) {
        return data.textureMap.getStanding(data.direction);
    }

    @Override
    public void moveNorth(Player player) {

    }

    @Override
    public void moveSouth(Player player) {

    }

    @Override
    public void moveEast(Player player) {

    }

    @Override
    public void moveWest(Player player) {

    }
}
