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

    //TODO: tiles are 16x16

    private static final int TILE_WIDTH = 2;
    private static final int TILE_HEIGHT = 2;

    private static final int WALKING_WIDTH = 56;
    private static final int WALKING_HEIGHT = 21;

    private static final int RUNNING_WIDTH = 60;
    private static final int RUNNING_HEIGHT = 20;

    private enum Direction {
        North {
            public TextureRegion getKeyFrame(Brendan brendan) {
                return getKeyFrame(brendan, brendan.walkingNorth, brendan.runningNorth, brendan.standingNorth);
            }
        },
        South {
            public TextureRegion getKeyFrame(Brendan brendan) {
                return getKeyFrame(brendan, brendan.walkingSouth, brendan.runningSouth, brendan.standingSouth);
            }
        },
        West {
            public TextureRegion getKeyFrame(Brendan brendan) {
                return getKeyFrame(brendan, brendan.walkingWest, brendan.runningWest, brendan.standingWest);
            }
        },
        East {
            public TextureRegion getKeyFrame(Brendan brendan) {
                return getKeyFrame(brendan, brendan.walkingEast, brendan.runningEast, brendan.standingEast);
            }
        };

        TextureRegion getKeyFrame(Brendan brendan, Animation<TextureRegion> walking, Animation<TextureRegion> running, TextureRegion standing) {
            switch (brendan.state) {
                case Standing:
                    return standing;
                case Walking:
                    return walking.getKeyFrame(brendan.stateTimer, true);
                case Running:
                    return running.getKeyFrame(brendan.stateTimer, true);
                default:
                    throw new RuntimeException("Should not be null");
            }
        }

        public abstract TextureRegion getKeyFrame(Brendan brendan);
    }

    private enum State {
        Standing,
        Walking,
        Running
    }

    private State state;
    private Direction direction;
    private float stateTimer;

    public Brendan(AbstractScreen screen) {
        super(new TextureRegion(new Texture("brendan/standing/south.png")));
        this.screen = screen;

        stateTimer = 0;
        direction = Direction.South;
        state = State.Standing;

        walkingSouth = fillFromAtlas("walking/south", WALKING_WIDTH, 4, WALKING_HEIGHT, 1);
        walkingNorth = fillFromAtlas("walking/north", WALKING_WIDTH, 4, WALKING_HEIGHT, 1);
        walkingWest = fillFromAtlas("walking/west", WALKING_WIDTH, 4, WALKING_HEIGHT, 1);
        walkingEast = fillFromAtlas("walking/east", WALKING_WIDTH, 4, WALKING_HEIGHT, 1);

        runningSouth = fillFromAtlas("running/south", RUNNING_WIDTH, 4, RUNNING_HEIGHT, 1);
        runningNorth = fillFromAtlas("running/north", RUNNING_WIDTH, 4, RUNNING_HEIGHT, 1);
        runningWest = fillFromAtlas("running/west", RUNNING_WIDTH, 4, RUNNING_HEIGHT, 1);
        runningEast = fillFromAtlas("running/east", RUNNING_WIDTH, 4, RUNNING_HEIGHT, 1);

        standingSouth = screen.getAtlas().findRegion("standing/south");
        standingNorth = screen.getAtlas().findRegion("standing/north");
        standingWest = screen.getAtlas().findRegion("standing/west");
        standingEast = screen.getAtlas().findRegion("standing/east");
    }

    private Animation<TextureRegion> fillFromAtlas(String regionName, int width, int columns, int height, int rows) {
        TextureRegion region = screen.getAtlas().findRegion(regionName);
        TextureRegion[][] temp = region.split(width / columns, height / rows);

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
        setSize(getRegionWidth(), getRegionHeight());
    }

    private TextureRegion getFrame(float delta) {
        stateTimer += delta;

        return direction.getKeyFrame(this);
    }

    public void walkNorth() {
        walk();
        this.direction = Direction.North;
        moveNorth(1);
    }

    public void walkEast() {
        walk();
        this.direction = Direction.East;
        moveEast(1);
    }

    public void walkWest() {
        walk();
        this.direction = Direction.West;
        moveWest(1);
    }

    public void walkSouth() {
        walk();
        this.direction = Direction.South;
        moveSouth(1);
    }

    public void runNorth() {
        run();
        this.direction = Direction.North;
        moveNorth(1.5f);
    }

    public void runSouth() {
        run();
        this.direction = Direction.South;
        moveSouth(1.5f);
    }

    public void runEast() {
        run();
        this.direction = Direction.East;
        moveEast(1.5f);
    }

    public void runWest() {
        run();
        this.direction = Direction.West;
        moveWest(1.5f);
    }

    private void walk() {
        this.state = State.Walking;
    }

    private void run() {
        this.state = State.Running;
    }

    public void stop() {
        this.state = State.Standing;
    }

    private void moveSouth(float modifier) {
        setY(getY() - TILE_HEIGHT * modifier);
    }

    private void moveNorth(float modifier) {
        setY(getY() + TILE_HEIGHT * modifier);
    }

    private void moveWest(float modifier) {
        setX(getX() - TILE_WIDTH * modifier);
    }

    private void moveEast(float modifier) {
        setX(getX() + TILE_WIDTH * modifier);
    }
}

