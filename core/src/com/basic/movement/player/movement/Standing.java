package com.basic.movement.player.movement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Player;
import com.basic.movement.utils.PlayerTextureMap;

public class Standing implements MovementState {
    @Override
    public void moveNorth(Player player) {
    }

    @Override
    public void moveSouth(Player player) {
    }

    @Override
    public void moveEast(Player player) {
    }

    @Override
    public void moveWest(Player player) {
    }

    @Override
    public TextureRegion getFrame(PlayerTextureMap map, Direction direction, float stateTimer) {
        return map.getStanding(direction);
    }
}
