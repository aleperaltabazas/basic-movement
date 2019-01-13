package com.basic.movement;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;

public class Brendan extends Sprite {
    private float x;
    private float y;
    private Texture texture;

    private enum State {
        WALKING,
        RUNNING,
        STANDING
    }

    private enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    private State state;
    private Direction direction;

    private Animation<TextureRegion> walking;
    private Animation<TextureRegion> running;
    private Animation<TextureRegion> standing;

    TextureAtlas atlas = new TextureAtlas("output/atlas.atlas");

    public Brendan(AbstractScreen screen) {
        //TODO: change front to walking_front, etc.
        super(screen.getAtlas().findRegion("front"));

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), i * 14, 0, 14, i * 21));
        }

        walking = new Animation<TextureRegion>(0.16f, frames);
        state = State.STANDING;
        direction = Direction.DOWN;
    }

    public void update(float delta) {
        setRegion(getFrame(delta));
    }

    private TextureRegion getFrame(float delta) {
        switch (state) {
            case WALKING:
                return walking.getKeyFrame(delta, true);
            case RUNNING:
                return running.getKeyFrame(delta, true);
            case STANDING:
                return standing.getKeyFrame(delta, true);
        }
        return null;
    }

    public void moveDown() {
        setY(getY() - 1);
    }

    public void moveUp() {
        setY(getY() + 1);
    }

    public void moveLeft() {
        setX(getX() - 1);
    }

    public void moveRight() {
        setX(getX() + 1);
        this.setTexture(new TextureRegion(new Texture("brendan/standing/front.png")).getTexture());
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, getX(), getY());
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }
}

