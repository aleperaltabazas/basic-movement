package com.basic.movement;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Brendan extends Sprite {
    private AbstractScreen screen;

    private PlayerTextureMap textureMap;

    //TODO: tiles are 16x16

    private static final int TILE_WIDTH = 2;
    private static final int TILE_HEIGHT = 2;

    private static final int WALKING_WIDTH = 56;
    private static final int WALKING_HEIGHT = 21;

    private static final int RUNNING_WIDTH = 60;
    private static final int RUNNING_HEIGHT = 20;

    private State state;
    private Direction direction;
    private float stateTimer;

    public Brendan(AbstractScreen screen) {
        super(new TextureRegion(new Texture("brendan/standing/south.png")));
        this.screen = screen;

        stateTimer = 0;
        direction = Direction.South;
        state = State.Standing;

        textureMap = new PlayerTextureMap();

        textureMap.putWalking(Direction.North, fillFromAtlas("walking/north", WALKING_WIDTH, 4, WALKING_HEIGHT, 1));
        textureMap.putWalking(Direction.South, fillFromAtlas("walking/south", WALKING_WIDTH, 4, WALKING_HEIGHT, 1));
        textureMap.putWalking(Direction.West, fillFromAtlas("walking/west", WALKING_WIDTH, 4, WALKING_HEIGHT, 1));
        textureMap.putWalking(Direction.East, fillFromAtlas("walking/east", WALKING_WIDTH, 4, WALKING_HEIGHT, 1));

        textureMap.putRunning(Direction.South, fillFromAtlas("running/south", RUNNING_WIDTH, 4, RUNNING_HEIGHT, 1));
        textureMap.putRunning(Direction.North, fillFromAtlas("running/north", RUNNING_WIDTH, 4, RUNNING_HEIGHT, 1));
        textureMap.putRunning(Direction.West, fillFromAtlas("running/west", RUNNING_WIDTH, 4, RUNNING_HEIGHT, 1));
        textureMap.putRunning(Direction.East, fillFromAtlas("running/east", RUNNING_WIDTH, 4, RUNNING_HEIGHT, 1));

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

    public State getState() {
        return state;
    }

    public float getStateTimer() {
        return stateTimer;
    }

    public PlayerTextureMap getTextureMap() {
        return textureMap;
    }
}