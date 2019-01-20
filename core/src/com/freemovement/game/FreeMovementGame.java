package com.freemovement.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.freemovement.game.screen.AbstractScreen;
import com.freemovement.game.screen.FreeMovementScreen;

public class FreeMovementGame extends Game {
    public static final float TILE_WIDTH = 16;
    public static final float TILE_HEIGHT = 16;

    private SpriteBatch batch;
    private AbstractScreen screen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        screen = new FreeMovementScreen(this);
        setScreen(screen);
    }

    @Override
    public void dispose() {
        batch.dispose();
        screen.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
