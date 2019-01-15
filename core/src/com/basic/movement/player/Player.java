package com.basic.movement.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.utils.PlayerTextureMap;
import com.basic.movement.screen.AbstractScreen;

public class Player extends Sprite {
    private AbstractScreen screen;

    private PlayerTextureMap textureMap;

    //TODO: tiles are 16x16

    private static final int TILE_WIDTH = 2;
    private static final int TILE_HEIGHT = 2;

    private int WALKING_WIDTH;
    private int WALKING_HEIGHT;

    private int RUNNING_WIDTH;
    private int RUNNING_HEIGHT;

    private State state;
    private Direction direction;
    private float stateTimer;

    private float speed;
    private boolean moving;

    private float targetX;
    private float targetY;

    private boolean movingX;
    private boolean movingY;

    public Player(AbstractScreen screen, int walkWidth, int walkHeight, int runWidth, int runHeight) {
        super(screen.getAtlas().findRegion("standing/south"));
        this.screen = screen;

        stateTimer = 0;
        direction = Direction.South;
        state = State.Standing;

        this.WALKING_WIDTH = walkWidth;
        this.WALKING_HEIGHT = walkHeight;
        this.RUNNING_WIDTH = runWidth;
        this.RUNNING_HEIGHT = runHeight;

        textureMap = new PlayerTextureMap();

        textureMap.putWalking(Direction.North, fillFromAtlas("walking/north", this.WALKING_WIDTH, 4, this.WALKING_HEIGHT, 1));
        textureMap.putWalking(Direction.South, fillFromAtlas("walking/south", this.WALKING_WIDTH, 4, this.WALKING_HEIGHT, 1));
        textureMap.putWalking(Direction.West, fillFromAtlas("walking/west", this.WALKING_WIDTH, 4, this.WALKING_HEIGHT, 1));
        textureMap.putWalking(Direction.East, fillFromAtlas("walking/east", this.WALKING_WIDTH, 4, this.WALKING_HEIGHT, 1));

        textureMap.putRunning(Direction.South, fillFromAtlas("running/south", this.RUNNING_WIDTH, 4, this.RUNNING_HEIGHT, 1));
        textureMap.putRunning(Direction.North, fillFromAtlas("running/north", this.RUNNING_WIDTH, 4, this.RUNNING_HEIGHT, 1));
        textureMap.putRunning(Direction.West, fillFromAtlas("running/west", this.RUNNING_WIDTH, 4, this.RUNNING_HEIGHT, 1));
        textureMap.putRunning(Direction.East, fillFromAtlas("running/east", this.RUNNING_WIDTH, 4, this.RUNNING_HEIGHT, 1));

        textureMap.putStanding(Direction.South, screen.getAtlas().findRegion("standing/south"));
        textureMap.putStanding(Direction.North, screen.getAtlas().findRegion("standing/north"));
        textureMap.putStanding(Direction.West, screen.getAtlas().findRegion("standing/west"));
        textureMap.putStanding(Direction.East, screen.getAtlas().findRegion("standing/east"));
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

    public TextureRegion getFrame(float delta) {
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

    public State getState() {
        return state;
    }

    public float getStateTimer() {
        return stateTimer;
    }

    public PlayerTextureMap getTextureMap() {
        return textureMap;
    }

    public void stop2() {
        if (Math.abs(speed) > 0.5f)
            speed *= 0.5f;
        else {
            speed = 0;
        }
    }

    public void caminarOeste() {
        if (speed != -50f)
            speed = -50f;
    }

    public void caminarEste() {
        if (speed != 50f)
            speed = 50f;
    }

    public void updatePosition() {
        if ((targetX > getX()) && (targetX - getX() > 1)) {
            caminarEste();
        } else if ((targetX < getX()) && (targetX - getX()) < -1) {
            caminarOeste();
        } else {
            fullStop();
        }

        float position = getX();
        float delta = Gdx.graphics.getDeltaTime();
        position += speed * delta;
        setX(position);
    }

    private void fullStop() {
        targetX = getX();
        targetY = getY();
        moving = false;
        speed = 0;
    }

    public void setTargetX(float x) {
        this.targetX = x;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setTargetY(float y) {
        this.targetY = y;
    }
}