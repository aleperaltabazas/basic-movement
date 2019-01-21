package com.basic.movement.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import com.basic.movement.player.*;

public class Hud {
    private Stage stage;
    private Viewport viewport;

    private Float x;
    private Float y;

    private Label xLabel;
    private Label yLabel;

    public Hud(SpriteBatch batch) {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, batch);

        x = 0f;
        y = 0f;

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        xLabel = new Label(String.format("%02f", x), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        yLabel = new Label(String.format("%02f", y), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(xLabel).expandX().padTop(10).padRight(Gdx.graphics.getWidth() - 100);
        table.row();
        table.add(yLabel).expandX().padTop(10).padRight(Gdx.graphics.getWidth() - 100);

        stage.addActor(table);
    }

    public Stage getStage() {
        return stage;
    }

    public void update(PlayerSprite playerSprite) {
        xLabel.setText(String.format("%.2f", x = playerSprite.getX()));
        yLabel.setText(String.format("%.2f", y = playerSprite.getY()));
    }

    public void update(ActorPlayer actor) {
        update(actor.getSprite());
    }
}
