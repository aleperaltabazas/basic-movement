package com.basic.movement.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorPlayer extends Actor {
    private Player sprite;
    private TextureRegion currentFrame;

    public ActorPlayer(Player player) {
        sprite = player;

        currentFrame = new TextureRegion(new Texture("may/standing/south.png"), 14, 20);
        setSize(14, 20);
        setPosition(player.getX(), player.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(currentFrame, getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        currentFrame = sprite.getRegion();
        setSize(currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
    }

    public void walkNorth() {
        sprite.moveNorth();
    }

    public void setTargetPosition(float x, float y) {
        sprite.setTargetPosition(x, y);
    }

    public Player getSprite() {
        return sprite;
    }

    public void update(float delta) {
        sprite.update(delta);
        setPosition(sprite.getX(), sprite.getY());
    }
}
