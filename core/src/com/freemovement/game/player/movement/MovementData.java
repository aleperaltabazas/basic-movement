package com.freemovement.game.player.movement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.freemovement.game.player.Direction;
import com.freemovement.game.utils.PlayerTextureMap;
import com.freemovement.game.utils.TextureAtlasAdapter;

public class MovementData {
    PlayerTextureMap textureMap;
    Direction direction;
    float stateTimer;
    TextureRegion currentFrame;
    MovementState state;

    public MovementData(float stateTimer, Direction direction, TextureAtlasAdapter atlas) {
        this.stateTimer = stateTimer;
        this.direction = direction;
        textureMap = new PlayerTextureMap(atlas);
        currentFrame = textureMap.getStanding(Direction.South);
    }

    public void update(float delta, MovementState state) {
        stateTimer += delta;
        this.state = state;
        currentFrame = state.getFrame(this);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

}
