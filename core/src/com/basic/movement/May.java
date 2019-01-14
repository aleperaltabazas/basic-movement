package com.basic.movement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class May extends Actor {
    private Player sprite;

    public May(AbstractScreen screen) {
        sprite = new Player(screen, 56, 20, 64, 20);
        sprite.setPosition(150, 150);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
