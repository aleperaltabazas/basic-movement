package com.basic.movement.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.utils.PlayerTextureMap;

public class Walking implements MovementState {
    private static final float SPEED = 50f;

    @Override
    public void moveNorth(Player player) {
        player.setSpeedX(0);
        player.setSpeedY(SPEED);
        player.setDirection(Direction.North);
    }

    @Override
    public void moveSouth(Player player) {
        player.setSpeedX(0);
        player.setSpeedY(-SPEED);
        player.setDirection(Direction.South);
    }

    @Override
    public void moveEast(Player player) {
        player.setSpeedX(SPEED);
        player.setSpeedY(0);
        player.setDirection(Direction.East);
    }

    @Override
    public void moveWest(Player player) {
        player.setSpeedX(-SPEED);
        player.setSpeedY(0);
        player.setDirection(Direction.West);
    }

    @Override
    public TextureRegion getFrame(PlayerTextureMap map, Direction direction, float stateTimer) {
        return map.getWalking(direction).getKeyFrame(stateTimer, true);
    }
}
