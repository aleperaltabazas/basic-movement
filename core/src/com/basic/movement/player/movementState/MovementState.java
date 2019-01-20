package com.basic.movement.player.movementState;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Gender;
import com.basic.movement.player.Player;
import com.basic.movement.utils.PlayerTextureMap;

public interface MovementState {
    public void moveNorth(Player player);

    public void moveSouth(Player player);

    public void moveEast(Player player);

    public void moveWest(Player player);

    public TextureRegion getFrame(PlayerTextureMap map, Direction direction, Gender gender, float stateTimer);
}
