package com.basic.movement.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorPlayer extends Actor {
    private PlayerSprite sprite;
    private TextureRegion currentFrame;

    public ActorPlayer(PlayerSprite playerSprite) {
        sprite = playerSprite;

        currentFrame = new TextureRegion(playerSprite.getRegion());
        setSize(14, 20);
        setPosition(playerSprite.getX(), playerSprite.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(sprite.getRegion(), getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updatePosition(delta);
        updateFrame();
    }

    private void updateFrame() {
        currentFrame = sprite.getRegion();
        setSize(currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        setOrigin(sprite.getOriginX(), sprite.getOriginY());
        setScale(sprite.getScaleX(), sprite.getScaleY());
        setRotation(sprite.getRotation());
    }

    private void updatePosition(float delta) {
        sprite.update(delta);
        setPosition(sprite.getX(), sprite.getY());
    }

    public void setTargetPosition(float x, float y) {
        sprite.setTargetPosition(x, y);
    }

    public PlayerSprite getSprite() {
        return sprite;
    }
}
