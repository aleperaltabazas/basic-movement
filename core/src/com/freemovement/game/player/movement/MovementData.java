package com.freemovement.game.player.movement;

import com.freemovement.game.player.Direction;
import com.freemovement.game.utils.PlayerTextureMap;
import com.freemovement.game.utils.TextureAtlasAdapter;

public class MovementData {
    PlayerTextureMap textureMap;
    Direction direction;
    float stateTimer;
    MovementState movementState;

    public MovementData(float stateTimer, Direction direction, TextureAtlasAdapter atlas) {
        this.stateTimer = stateTimer;
        this.direction = direction;
        textureMap = new PlayerTextureMap(atlas);
        movementState = new Standing();
    }
}
