package com.basic.movement.player.movementState;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Gender;
import com.basic.movement.player.Player;
import com.basic.movement.utils.PlayerTextureMap;

public class Standing implements MovementState {
    @Override
    public void moveNorth(Player player) {
        player.setMovementState(new Walking());
        player.moveNorth();
    }

    @Override
    public void moveSouth(Player player) {
        player.setMovementState(new Walking());
        player.moveSouth();
    }

    @Override
    public void moveEast(Player player) {
        player.setMovementState(new Walking());
        player.moveEast();
    }

    @Override
    public void moveWest(Player player) {
        player.setMovementState(new Walking());
        player.moveWest();
    }

    @Override
    public TextureRegion getFrame(PlayerTextureMap map, Direction direction, Gender gender, float stateTimer) {
        return map.getStanding(direction, gender);
    }
}
