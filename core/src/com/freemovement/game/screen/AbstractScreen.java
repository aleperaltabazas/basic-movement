package com.freemovement.game.screen;

import com.badlogic.gdx.Screen;
import com.freemovement.game.FreeMovementGame;
import com.freemovement.game.utils.TextureAtlasAdapter;

public abstract class AbstractScreen implements Screen {
    protected FreeMovementGame game;
    protected TextureAtlasAdapter atlas;

    public AbstractScreen(FreeMovementGame game) {
        this.game = game;
        this.atlas = new TextureAtlasAdapter("crono.atlas");
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
        game.dispose();
    }
}
