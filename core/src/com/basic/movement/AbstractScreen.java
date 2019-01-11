package com.basic.movement;

import com.badlogic.gdx.Screen;

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
}
