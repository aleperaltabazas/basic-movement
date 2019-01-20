package com.basic.movement.screen;

import com.badlogic.gdx.Screen;
import com.basic.movement.BasicMovementGame;
import com.basic.movement.utils.TextureAtlasAdapter;

public abstract class AbstractScreen implements Screen {
    protected BasicMovementGame game;
    private TextureAtlasAdapter atlas;

    public AbstractScreen(BasicMovementGame game, String atlasName) {
        this.game = game;
        this.atlas = new TextureAtlasAdapter(atlasName);
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

    public TextureAtlasAdapter getAtlas() {
        return atlas;
    }

}
