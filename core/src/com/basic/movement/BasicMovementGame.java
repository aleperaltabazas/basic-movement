package com.basic.movement;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.basic.movement.screen.AbstractScreen;
import com.basic.movement.screen.GridMovementScreen;

public class BasicMovementGame extends Game {
    public static final float WIDTH = 240;
    public static final float HEIGHT = 160;

    public SpriteBatch batch;

    private AbstractScreen brendan;
    private AbstractScreen may;
    private AbstractScreen gridScene;

    @Override
    public void create() {
        batch = new SpriteBatch();
        /*brendan = new MovementScreen(this, "output/brendan.atlas");
        setScreen(brendan);*/
        /*may = new SceneScreen(this);
        setScreen(may);*/
        gridScene = new GridMovementScreen(this);
        setScreen(gridScene);
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
