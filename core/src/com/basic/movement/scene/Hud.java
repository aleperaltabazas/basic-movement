package com.basic.movement.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.*;

public class Hud {
    private Stage stage;
    private Viewport viewport;

    private Integer x;
    private Integer y;

    private Label xLabel;
    private Label yLabel;

    public Hud(SpriteBatch batch) {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, batch);

        x = 0;
        y = 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        xLabel = new Label(String.format("%02d", x), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        yLabel = new Label(String.format("%02d", y), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(xLabel).expandX().padTop(10);
        table.row();
        table.add(yLabel).expandX().padTop(10);

        stage.addActor(table);
    }
}
