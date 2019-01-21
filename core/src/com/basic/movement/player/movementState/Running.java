package com.basic.movement.player.movementState;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Gender;
import com.basic.movement.player.PlayerSprite;
import com.basic.movement.utils.PlayerTextureMap;

public class Running implements MovementState {
    static final float SPEED = Walking.SPEED * 2f;

    @Override
    public void moveNorth(PlayerSprite playerSprite) {
        playerSprite.setSpeedX(0);
        playerSprite.setSpeedY(SPEED);
        playerSprite.setDirection(Direction.North);
    }

    @Override
    public void moveSouth(PlayerSprite playerSprite) {
        playerSprite.setSpeedX(0);
        playerSprite.setSpeedY(-SPEED);
        playerSprite.setDirection(Direction.South);
    }

    @Override
    public void moveEast(PlayerSprite playerSprite) {
        playerSprite.setSpeedX(SPEED);
        playerSprite.setSpeedY(0);
        playerSprite.setDirection(Direction.East);
    }

    @Override
    public void moveWest(PlayerSprite playerSprite) {
        playerSprite.setSpeedX(-SPEED);
        playerSprite.setSpeedY(0);
        playerSprite.setDirection(Direction.West);
    }

    @Override
    public TextureRegion getFrame(PlayerTextureMap map, Direction direction, Gender gender, float stateTimer) {
        return map.getRunning(direction, gender).getKeyFrame(stateTimer, true);
    }
}
