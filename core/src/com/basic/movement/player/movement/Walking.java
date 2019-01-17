package com.basic.movement.player.movement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Player;
import com.basic.movement.utils.PlayerTextureMap;

public class Walking implements MovementState {
    static final float SPEED = 30f;

    @Override
    public void moveNorth(Player player) {
        player.setDirection(Direction.North);
        player.setSpeed(0, SPEED);
    }

    @Override
    public void moveSouth(Player player) {
        player.setDirection(Direction.South);
        player.setSpeed(0, -SPEED);
    }

    @Override
    public void moveEast(Player player) {
        player.setDirection(Direction.East);
        player.setSpeed(SPEED, 0);
    }

    @Override
    public void moveWest(Player player) {
        player.setDirection(Direction.West);
        player.setSpeed(-SPEED, 0);
    }

    @Override
    public TextureRegion getFrame(PlayerTextureMap map, Direction direction, float stateTimer) {
        return map.getWalking(direction).getKeyFrame(stateTimer, true);
    }
}
