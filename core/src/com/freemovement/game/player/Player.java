package com.freemovement.game.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.freemovement.game.screen.AbstractScreen;
import com.freemovement.game.utils.PlayerTextureMap;

import static com.freemovement.game.player.Direction.*;

public class Player extends Sprite {
    private AbstractScreen screen;

    private PlayerTextureMap textureMap;

    private Body body;
    private float stateTimer;
    private Direction direction;

    public Player(AbstractScreen screen) {
        this.screen = screen;
        this.textureMap = new PlayerTextureMap(screen.getAtlas());

        this.direction = South;
        this.stateTimer = 0;
    }
}
