package com.basic.movement.player.movementState;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Gender;
import com.basic.movement.player.PlayerSprite;
import com.basic.movement.utils.PlayerTextureMap;

public class Standing implements MovementState {
    @Override
    public void moveNorth(PlayerSprite playerSprite) {
        playerSprite.setMovementState(new Walking());
        playerSprite.moveNorth();
    }

    @Override
    public void moveSouth(PlayerSprite playerSprite) {
        playerSprite.setMovementState(new Walking());
        playerSprite.moveSouth();
    }

    @Override
    public void moveEast(PlayerSprite playerSprite) {
        playerSprite.setMovementState(new Walking());
        playerSprite.moveEast();
    }

    @Override
    public void moveWest(PlayerSprite playerSprite) {
        playerSprite.setMovementState(new Walking());
        playerSprite.moveWest();
    }

    @Override
    public TextureRegion getFrame(PlayerTextureMap map, Direction direction, Gender gender, float stateTimer) {
        return map.getStanding(direction, gender);
    }
}
