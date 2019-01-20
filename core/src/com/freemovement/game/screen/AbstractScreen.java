package com.freemovement.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.World;
import com.freemovement.game.FreeMovementGame;
import com.freemovement.game.utils.TextureAtlasAdapter;

public abstract class AbstractScreen implements Screen {
    protected FreeMovementGame game;
    protected TextureAtlasAdapter atlas;
    protected World world;

    public AbstractScreen(FreeMovementGame game, World world) {
        this.game = game;
        this.atlas = new TextureAtlasAdapter("packed/crono.atlas");
        this.world = world;
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
        atlas.dispose();
        world.dispose();
        game.dispose();
    }

    protected abstract void childDispose();

    public TextureAtlasAdapter getAtlas() {
        return atlas;
    }

    public World getWorld() {
        return world;
    }
}
