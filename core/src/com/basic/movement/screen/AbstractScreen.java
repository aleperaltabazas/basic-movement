package com.basic.movement.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;
import com.basic.movement.BasicMovementGame;

public abstract class AbstractScreen implements Screen {
    protected BasicMovementGame game;

    public AbstractScreen(BasicMovementGame game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public abstract TextureAtlas getAtlas();

    public World getWorld() {
        return null;
    }
}
