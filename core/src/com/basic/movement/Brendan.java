package com.basic.movement;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Brendan extends Sprite {
    private AbstractScreen screen;

    private Animation<TextureRegion> walkingUp;
    private Animation<TextureRegion> walkingDown;
    private Animation<TextureRegion> walkingLeft;
    private Animation<TextureRegion> walkingRight;

    private static final int TILE_WIDTH = 16;
    private static final int TILE_HEIGHT = 16;

    private static final int WIDTH = 56;
    private static final int HEIGHT = 21;

    private enum Direction {
        Up {

        },
        Down {

        },
        Left {

        },
        Right {

        }

    }

    private Direction direction;
    private float stateTimer;

    public Brendan(AbstractScreen screen) {
        super(new TextureRegion(new Texture("brendan/standing/front.png")));
        this.screen = screen;

        stateTimer = 0;
        direction = Direction.Down;

        walkingDown = fillFromAtlas("front");
        walkingUp = fillFromAtlas("back");
        walkingLeft = fillFromAtlas("left");
        walkingRight = fillFromAtlas("right");
    }

    private Animation<TextureRegion> fillFromAtlas(String regionName) {
        TextureRegion region = screen.getAtlas().findRegion(regionName);
        TextureRegion[][] temp = region.split(WIDTH / 4, HEIGHT);

        TextureRegion[] frames = new TextureRegion[temp.length * temp[0].length];

        int index = 0;

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                frames[index++] = temp[i][j];
            }
        }

        return new Animation<TextureRegion>(0.16f, frames);
    }

    public void update(float delta) {
        setRegion(getFrame(delta));
    }

    private TextureRegion getFrame(float delta) {
        stateTimer += delta;

        switch (direction) {
            case Up:
                return walkingUp.getKeyFrame(stateTimer, true);
            case Down:
                return walkingDown.getKeyFrame(stateTimer, true);
            case Left:
                return walkingLeft.getKeyFrame(stateTimer, true);
            case Right:
                return walkingRight.getKeyFrame(stateTimer, true);
            default:
                throw new RuntimeException("Should not be null");
        }
    }

    public void moveUp() {
        setY(getY() + 1);
        this.direction = Direction.Up;
    }

    public void moveRight() {
        setX(getX() + 1);
        this.direction = Direction.Right;
    }

    public void moveLeft() {
        setX(getX() - 1);
        this.direction = Direction.Left;
    }

    public void moveDown() {
        setY(getY() - 1);
        this.direction = Direction.Down;
    }
}

