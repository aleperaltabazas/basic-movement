package com.basic.movement.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.basic.movement.player.movementState.MovementState;
import com.basic.movement.player.movementState.Running;
import com.basic.movement.player.movementState.Standing;
import com.basic.movement.player.movementState.Walking;
import com.basic.movement.screen.AbstractScreen;
import com.basic.movement.utils.PlayerTextureMap;

public class PlayerSprite extends Sprite {
    private AbstractScreen screen;

    private PlayerTextureMap textureMap;

    private int WALKING_WIDTH;
    private int WALKING_HEIGHT;

    private int RUNNING_WIDTH;
    private int RUNNING_HEIGHT;

    private MovementState movementState;
    private Direction direction;
    private Gender gender;

    private float stateTimer;

    private float speedX = 0;
    private float speedY = 0;
    private boolean moving;

    private float targetX;
    private float targetY;
    private boolean running = false;

    private TextureRegion currentTexture;

    private float virtualSpeed = 50f;
    private float virtualPosition = 0;
    private float virtualTargetPosition;
    private boolean virtualMovement = false;

    private Vector2 position;

    public PlayerSprite(AbstractScreen screen, int walkWidth, int walkHeight, int runWidth, int runHeight) {
        super(screen.getAtlas().findRegion("standing/south"));
        this.screen = screen;

        stateTimer = 0;
        direction = Direction.South;
        movementState = new Standing();
        gender = Gender.Male;

        targetX = getX();
        targetY = getY();

        position = new Vector2(getX(), getY());

        this.WALKING_WIDTH = walkWidth;
        this.WALKING_HEIGHT = walkHeight;
        this.RUNNING_WIDTH = runWidth;
        this.RUNNING_HEIGHT = runHeight;

        textureMap = new PlayerTextureMap(screen.getAtlas());
        currentTexture = getFrame(0);
    }

    public void update(float delta) {
        move();
        setRegion(getFrame(delta));
        setSize(getRegionWidth(), getRegionHeight());
    }

    public TextureRegion getFrame(float delta) {
        stateTimer += delta;

        return currentTexture = movementState.getFrame(this.textureMap, this.direction, this.gender, this.stateTimer);
    }

    private void walk() {
        this.movementState = new Walking();
    }

    private void run() {
        this.movementState = new Running();
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
        if (virtualMovement) {
            walkInPlace(this.direction);
        } else {
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

    public void stop() {
        setPosition(targetX, targetY);
        moving = false;
        speedX = 0;
        speedY = 0;
        movementState = new Standing();
    }

    public void setTargetPosition(float targetX, float targetY) {
        setTargetX(targetX);
        setTargetY(targetY);
    }

    public void setTargetX(float x) {
        this.targetX = x;
    }

    public void setTargetY(float y) {
        this.targetY = y;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setSpeed(float speedX, float speedY) {
        setSpeedX(speedX);
        setSpeedY(speedY);
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

    public TextureRegion getRegion() {
        return currentTexture;
    }

    public void walkInPlace(Direction direction) {
        if (!moving) {
            moving = true;
        }

        if (!virtualMovement) {
            this.movementState = new Walking();
            virtualPosition = 0;
            virtualTargetPosition = 16;
            virtualMovement = true;
            this.direction = direction;
        }

        float delta = Gdx.graphics.getDeltaTime();
        virtualPosition += virtualSpeed * delta;

        if (virtualPosition + 1 > virtualTargetPosition) {
            moving = false;
            virtualPosition = 0;
            virtualTargetPosition = 0;
            virtualMovement = false;
        }
    }

    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        position.set(x, y);
    }

    public void reface(Direction direction) {
        if (!virtualMovement) {
            virtualMovement = true;
            this.movementState = new Walking();
            virtualPosition = 0;
            virtualTargetPosition = 3;
            this.direction = direction;
            setTargetPosition(getX(), getY());
        }

        float delta = Gdx.graphics.getDeltaTime();
        virtualPosition += virtualSpeed * delta;

        if (virtualPosition + 1 > virtualTargetPosition) {
            moving = false;
            virtualTargetPosition = 0;
            virtualPosition = 0;
            virtualMovement = false;
        }
    }
}