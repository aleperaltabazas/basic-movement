package com.basic.movement;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.basic.movement.screen.AbstractScreen;
import com.basic.movement.screen.SceneScreen;

public class BasicMovementGame extends Game {
    public SpriteBatch batch;

    private AbstractScreen brendan;
    private AbstractScreen may;

    @Override
    public void create() {
        batch = new SpriteBatch();
        /*brendan = new MovementScreen(this, "output/brendan.atlas");
        setScreen(brendan);*/
        may = new SceneScreen(this);
        setScreen(may);
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
