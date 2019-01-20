package com.freemovement.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.freemovement.game.FreeMovementGame;
import com.freemovement.game.utils.TextureAtlasAdapter;

public abstract class AbstractScreen implements Screen {
    protected FreeMovementGame game;
    protected TextureAtlasAdapter atlas;
    protected AssetManager assetManager;

    public AbstractScreen(FreeMovementGame game, AssetManager assetManager) {
        this.game = game;
        this.atlas = new TextureAtlasAdapter("packed/crono.atlas");
        this.assetManager = assetManager;

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
        atlas.dispose();
    }

    protected abstract void childDispose();

    public TextureAtlasAdapter getAtlas() {
        return atlas;
    }
}
