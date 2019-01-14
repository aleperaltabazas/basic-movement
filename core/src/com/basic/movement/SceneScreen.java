package com.basic.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SceneScreen extends AbstractScreen {
    private TextureAtlas atlas;
    private May may;
    private Stage stage;
    private Scenery scenery;

    public SceneScreen(BasicMovementGame game) {
        super(game);
        atlas = new TextureAtlas("output/may.atlas");

        stage = new Stage(new FillViewport(640, 480));

        scenery = new Scenery();
        scenery.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(scenery);

        may = new May(this);
        may.setColor(Color.RED);
        may.setPosition(100, 100);

        stage.addActor(may);
    }

    @Override
    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act();

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            may.addAction(Actions.moveBy(0, 16, 1));
            may.walkNorth();
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
