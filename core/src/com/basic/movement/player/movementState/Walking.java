package com.basic.movement.player.movementState;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Gender;
import com.basic.movement.player.Player;
import com.basic.movement.utils.PlayerTextureMap;

public class Walking implements MovementState {
    static final float SPEED = 50f;

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
    public TextureRegion getFrame(PlayerTextureMap map, Direction direction, Gender gender, float stateTimer) {
        return map.getWalking(direction, gender).getKeyFrame(stateTimer, true);
    }
}
