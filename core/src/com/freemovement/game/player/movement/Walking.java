package com.freemovement.game.player.movement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.freemovement.game.player.Player;

public class Walking implements MovementState {
    private final float walkSpeed = 50f;

    @Override
    public TextureRegion getFrame(MovementData data) {
        return data.textureMap.getStanding(data.direction);
    }

    @Override
    public void moveNorth(Player player) {
        player.setSpeed(player.getSpeedX(), walkSpeed);
    }

    @Override
    public void moveSouth(Player player) {
        player.getBody().setLinearVelocity(player.getSpeedX(), -walkSpeed);
    }

    @Override
    public void moveEast(Player player) {
        player.getBody().setLinearVelocity(walkSpeed, player.getSpeedY());
    }

    @Override
    public void moveWest(Player player) {
        player.getBody().setLinearVelocity(-walkSpeed, player.getSpeedY());
    }
}
