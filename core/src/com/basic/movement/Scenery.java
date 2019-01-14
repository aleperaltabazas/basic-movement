package com.basic.movement;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Scenery extends Actor {
    private ShapeRenderer shaper;

    public Scenery() {
        shaper = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        shaper.setProjectionMatrix(batch.getProjectionMatrix());
        shaper.setTransformMatrix(batch.getTransformMatrix());
        shaper.begin(ShapeRenderer.ShapeType.Filled);
        shaper.rect(getX(), getY(), getWidth(), getHeight(),
                Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW);
        shaper.end();

        batch.begin();
    }
}
