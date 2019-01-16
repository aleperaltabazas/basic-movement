package com.basic.movement.player.movement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Player;
import com.basic.movement.utils.PlayerTextureMap;

public class Running implements MovementState {
    static final float SPEED = Walking.SPEED * 2f;

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
        return map.getRunning(direction).getKeyFrame(stateTimer, true);
    }
}
