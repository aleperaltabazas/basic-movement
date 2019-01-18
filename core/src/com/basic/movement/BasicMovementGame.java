package com.basic.movement;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.basic.movement.screen.AbstractScreen;
import com.basic.movement.screen.GridMovementScreen;
import com.basic.movement.screen.SceneScreen;

public class BasicMovementGame extends Game {
    public static final float WIDTH = 240;
    public static final float HEIGHT = 160;

    public SpriteBatch batch;

    private AbstractScreen gridScreen;
    private AbstractScreen sceneScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();

        gridScreen = new GridMovementScreen(this);
        setScreen(gridScreen);
        sceneScreen = new SceneScreen(this);
        setScreen(sceneScreen);
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
