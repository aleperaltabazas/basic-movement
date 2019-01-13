package com.basic.movement;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BasicMovementGame extends Game {
    public SpriteBatch batch;

    private AbstractScreen brendan;

    @Override
    public void create() {
        batch = new SpriteBatch();
        brendan = new MovementScreen(this, "output/brendan.atlas");
        setScreen(brendan);
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
