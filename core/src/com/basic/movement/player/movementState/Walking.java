package com.basic.movement.player.movementState;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Gender;
import com.basic.movement.player.PlayerSprite;
import com.basic.movement.utils.PlayerTextureMap;

public class Walking implements MovementState {
    static final float SPEED = 50f;

    @Override
    public void moveNorth(PlayerSprite playerSprite) {
        if (playerSprite.getDirection() != Direction.North) {
            playerSprite.reface(Direction.North);
        } else {
            playerSprite.setSpeedY(SPEED);
            playerSprite.setSpeedX(0);
        }
    }

    @Override
    public void moveSouth(PlayerSprite playerSprite) {
        if (playerSprite.getDirection() != Direction.South) {
            playerSprite.reface(Direction.South);
        } else {
            playerSprite.setSpeedX(0);
            playerSprite.setSpeedY(-SPEED);
        }
    }

    @Override
    public void moveEast(PlayerSprite playerSprite) {
        if (playerSprite.getDirection() != Direction.East) {
            playerSprite.reface(Direction.East);
        } else {
            playerSprite.setSpeedX(SPEED);
            playerSprite.setSpeedY(0);
        }
    }

    @Override
    public void moveWest(PlayerSprite playerSprite) {
        if (playerSprite.getDirection() != Direction.West) {
            playerSprite.reface(Direction.West);
        } else {
            playerSprite.setSpeedX(-SPEED);
            playerSprite.setSpeedY(0);
        }
    }

    @Override
    public TextureRegion getFrame(PlayerTextureMap map, Direction direction, Gender gender, float stateTimer) {
        return map.getWalking(direction, gender).getKeyFrame(stateTimer, true);
    }
}
