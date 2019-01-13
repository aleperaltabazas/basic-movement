package com.basic.movement;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Brendan extends Sprite {
    private AbstractScreen screen;

    private Animation<TextureRegion> walkingNorth;
    private Animation<TextureRegion> walkingSouth;
    private Animation<TextureRegion> walkingEast;
    private Animation<TextureRegion> walkingWest;

    private Animation<TextureRegion> runningNorth;
    private Animation<TextureRegion> runningSouth;
    private Animation<TextureRegion> runningEast;
    private Animation<TextureRegion> runningWest;

    private TextureRegion standingNorth;
    private TextureRegion standingSouth;
    private TextureRegion standingEast;
    private TextureRegion standingWest;

    private static final int TILE_WIDTH = 16;
    private static final int TILE_HEIGHT = 16;

    private static final int WIDTH = 56;
    private static final int HEIGHT = 21;

    private enum Direction {
        North {
            public TextureRegion getKeyFrame(Brendan brendan) {
                return brendan.walkingNorth.getKeyFrame(brendan.stateTimer, true);
            }
        },
        South {
            public TextureRegion getKeyFrame(Brendan brendan) {
                return brendan.walkingSouth.getKeyFrame(brendan.stateTimer, true);
            }
        },
        West {
            public TextureRegion getKeyFrame(Brendan brendan) {
                return brendan.walkingWest.getKeyFrame(brendan.stateTimer, true);
            }
        },
        East {
            public TextureRegion getKeyFrame(Brendan brendan) {
                return brendan.walkingEast.getKeyFrame(brendan.stateTimer, true);
            }
        };

        public abstract TextureRegion getKeyFrame(Brendan brendan);
    }

    private enum State {
        Standing,
        Walking,
        Running
    }

    private Direction direction;
    private float stateTimer;

    public Brendan(AbstractScreen screen) {
        super(new TextureRegion(new Texture("brendan/standing/south.png")));
        this.screen = screen;

        stateTimer = 0;
        direction = Direction.South;

        walkingSouth = fillFromAtlas("south");
        walkingNorth = fillFromAtlas("north");
        walkingWest = fillFromAtlas("west");
        walkingEast = fillFromAtlas("east");
    }

    private Animation<TextureRegion> fillFromAtlas(String regionName) {
        TextureRegion region = screen.getAtlas().findRegion(regionName);
        TextureRegion[][] temp = region.split(WIDTH / 4, HEIGHT);

        TextureRegion[] frames = new TextureRegion[temp.length * temp[0].length];

        int index = 0;

        for (TextureRegion[] textureRegions : temp) {
            for (TextureRegion textureRegion : textureRegions) {
                frames[index++] = textureRegion;
            }
        }

        return new Animation<TextureRegion>(0.16f, frames);
    }

    public void update(float delta) {
        setRegion(getFrame(delta));
    }

    private TextureRegion getFrame(float delta) {
        stateTimer += delta;

        return direction.getKeyFrame(this);
    }

    public void moveUp() {
        setY(getY() + 1);
        this.direction = Direction.North;
    }

    public void moveRight() {
        setX(getX() + 1);
        this.direction = Direction.East;
    }

    public void moveLeft() {
        setX(getX() - 1);
        this.direction = Direction.West;
    }

    public void moveDown() {
        setY(getY() - 1);
        this.direction = Direction.South;
    }
}

