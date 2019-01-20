package com.freemovement.game.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.freemovement.game.player.movement.MovementData;
import com.freemovement.game.screen.AbstractScreen;
import com.freemovement.game.utils.PlayerTextureMap;

import static com.freemovement.game.player.Direction.*;

public class Player extends Sprite {
    private AbstractScreen screen;

    private PlayerTextureMap textureMap;

    private Body body;
    private MovementData movementData;

    public Player(AbstractScreen screen) {
        this.screen = screen;
        this.movementData = new MovementData(0, South, screen.getAtlas());
    }
}
