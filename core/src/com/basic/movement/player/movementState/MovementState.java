package com.basic.movement.player.movementState;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Gender;
import com.basic.movement.player.PlayerSprite;
import com.basic.movement.utils.PlayerTextureMap;

public interface MovementState {
    void moveNorth(PlayerSprite playerSprite);

    void moveSouth(PlayerSprite playerSprite);

    void moveEast(PlayerSprite playerSprite);

    void moveWest(PlayerSprite playerSprite);

    TextureRegion getFrame(PlayerTextureMap map, Direction direction, Gender gender, float stateTimer);
}
