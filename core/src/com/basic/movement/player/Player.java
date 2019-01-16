package com.basic.movement.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.utils.KeyboardManager;
import com.basic.movement.utils.PlayerTextureMap;
import com.basic.movement.screen.AbstractScreen;

public class Player extends Sprite {
    private AbstractScreen screen;

    private PlayerTextureMap textureMap;

    private int WALKING_WIDTH;
    private int WALKING_HEIGHT;

    private int RUNNING_WIDTH;
    private int RUNNING_HEIGHT;

    private MovementState movementState;
    private Direction direction;
    private float stateTimer;

    private float speedX = 0;
    private float speedY = 0;
    private boolean moving;

    private static final float WALKING_SPEED = 50f;
    private static final float RUNNING_SPEED = WALKING_SPEED * 2f;

    private float targetX;
    private float targetY;
    private boolean running = false;

    private KeyboardManager keyboardManager;

    public Player(AbstractScreen screen, int walkWidth, int walkHeight, int runWidth, int runHeight) {
        super(screen.getAtlas().findRegion("standing/south"));
        this.screen = screen;

        stateTimer = 0;
        direction = Direction.South;
        movementState = new Standing();

        targetX = getX();
        targetY = getY();

        this.WALKING_WIDTH = walkWidth;
        this.WALKING_HEIGHT = walkHeight;
        this.RUNNING_WIDTH = runWidth;
        this.RUNNING_HEIGHT = runHeight;

        textureMap = new PlayerTextureMap();
        keyboardManager = new KeyboardManager(16, 16);

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

    public Direction getDirection() {
        return this.direction;
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
        move();
        setRegion(getFrame(delta));
        setSize(getRegionWidth(), getRegionHeight());
    }

    public TextureRegion getFrame(float delta) {
        stateTimer += delta;

        return movementState.getFrame(this.textureMap, this.direction, this.stateTimer);
    }

    private void walk() {
        this.movementState = new Walking();
    }

    private void run() {
        this.movementState = new Running();
    }

    public float getStateTimer() {
        return stateTimer;
    }

    public PlayerTextureMap getTextureMap() {
        return textureMap;
    }

    public void stopMovement() {
        if (Math.abs(speedX) > 0.5f)
            speedX *= 0.5f;
        else {
            speedX = 0;
        }

        if (Math.abs(speedY) > 0.5f)
            speedY *= 0.5f;
        else {
            speedY = 0;
        }
    }

    public void move() {
        if ((targetX > getX()) && (targetX - getX() > 1)) {
            if (running)
                run();
            else
                walk();

            moveEast();
        } else if ((targetX < getX()) && (targetX - getX()) < -1) {
            if (running)
                run();
            else
                walk();

            moveWest();
        } else if ((targetY > getY()) && (targetY - getY()) > 1) {
            if (running)
                run();
            else
                walk();

            moveNorth();
        } else if ((targetY < getY()) && (targetY - getY()) < -1) {
            if (running)
                run();
            else
                walk();

            moveSouth();
        } else {
            stop();
        }

        float positionX = getX();
        float positionY = getY();
        float delta = Gdx.graphics.getDeltaTime();
        positionX += speedX * delta;
        positionY += speedY * delta;
        setPosition(positionX, positionY);
    }

    public void stop() {
        setPosition(targetX, targetY);
        moving = false;
        speedX = 0;
        speedY = 0;
        movementState = new Standing();
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

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void manageKeyboard() {
        keyboardManager.manage(this);
    }

    public KeyboardManager getKeyboardManager() {
        return this.keyboardManager;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setMovementState(MovementState movementState) {
        this.movementState = movementState;
    }

    public void moveNorth() {
        movementState.moveNorth(this);
    }

    public void moveSouth() {
        movementState.moveSouth(this);
    }

    public void moveEast() {
        movementState.moveEast(this);
    }

    public void moveWest() {
        movementState.moveWest(this);
    }


}