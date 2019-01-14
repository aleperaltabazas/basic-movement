package com.basic.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class SceneScreen extends AbstractScreen {
    private TextureAtlas atlas;
    private May may;

    public SceneScreen(BasicMovementGame game) {
        super(game);
        atlas = new TextureAtlas("output/may.atlas");
        may = new May(this);
    }

    @Override
    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        may.draw(game.getBatch(), 0);
        game.getBatch().end();
    }
}
