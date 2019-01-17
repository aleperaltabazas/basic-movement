package com.basic.movement.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.basic.movement.BasicMovementGame;
import com.basic.movement.player.movement.*;
import com.basic.movement.screen.*;
import com.basic.movement.utils.*;
import com.sun.javafx.geom.Edge;

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

    private boolean moving;

    private float targetX;
    private float targetY;
    private boolean running = false;

    private MovementManager movementManager;
    private TextureRegion currentTexture;

    private Body body;

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
        movementManager = new MovementManager(16, 16);

        defineBody();

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

    private void defineBody() {
        BodyDef bodyDef = new BodyDef();
        Vector2 center = new Vector2(getX() + BasicMovementGame.TILE_WIDTH / 2, getY() + BasicMovementGame.TILE_HEIGHT / 2);
        bodyDef.position.set(center);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = screen.getWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape playerBox = new PolygonShape();
        playerBox.setAsBox(center.x, center.y, center, 0);
        fixtureDef.shape = playerBox;
        body.createFixture(fixtureDef);

        defineEdges();
    }

    private void defineEdges() {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;

        EdgeShape north = new EdgeShape();
        north.set(new Vector2(getX() - 1, getY() + BasicMovementGame.TILE_HEIGHT), new Vector2(getX() + BasicMovementGame.TILE_WIDTH + 1, getY() + BasicMovementGame.TILE_HEIGHT));
        fixtureDef.shape = north;

        body.createFixture(fixtureDef).setUserData("north");

        EdgeShape south = new EdgeShape();
        north.set(new Vector2(getX() + 1, getY()), new Vector2(getX() + BasicMovementGame.TILE_WIDTH - 1, getY()));
        fixtureDef.shape = south;

        body.createFixture(fixtureDef).setUserData("south");

        EdgeShape east = new EdgeShape();
        east.set(new Vector2(getX() + BasicMovementGame.TILE_WIDTH, getY() + 1), new Vector2(getX() + BasicMovementGame.TILE_WIDTH, getY() + BasicMovementGame.TILE_HEIGHT - 1));
        fixtureDef.shape = east;

        body.createFixture(fixtureDef).setUserData("east");

        EdgeShape west = new EdgeShape();
        west.set(new Vector2(getX(), getY() + 1), new Vector2(getX(), getY() + BasicMovementGame.TILE_HEIGHT - 1));
        fixtureDef.shape = west;

        body.createFixture(fixtureDef).setUserData("west");
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

        return currentTexture = movementState.getFrame(this.textureMap, this.direction, this.stateTimer);
    }

    private void walk() {
        this.movementState = new Walking();
    }

    private void run() {
        this.movementState = new Running();
    }

    public void stopMovement() {
        if (Math.abs(body.getLinearVelocity().x) > 0.5f) {
            float newSpeedX = body.getLinearVelocity().x * 0.5f;
            float speedY = body.getLinearVelocity().y;
            body.setLinearVelocity(newSpeedX, speedY);
        } else {
            float speedY = body.getLinearVelocity().y;
            body.setLinearVelocity(0, speedY);
        }

        if (Math.abs(body.getLinearVelocity().x) > 0.5f) {
            float speedX = body.getLinearVelocity().x;
            float newSpeedY = body.getLinearVelocity().y * 0.5f;
            body.setLinearVelocity(speedX, newSpeedY);
        } else {
            float speedX = body.getLinearVelocity().x;
            body.setLinearVelocity(speedX, 0);
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

        float positionX = body.getPosition().x;
        float positionY = body.getPosition().y;
        float delta = Gdx.graphics.getDeltaTime();
        positionX += body.getLinearVelocity().x * delta;
        positionY += body.getLinearVelocity().y * delta;
        setPosition(positionX, positionY);
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
        setSpeed(0, 0);
        moving = false;
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

    public void manageMovement() {
        movementManager.manage(this);
    }

    public MovementManager getMovementManager() {
        return this.movementManager;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setSpeed(float speedX, float speedY) {
        body.setLinearVelocity(speedX, speedY);
    }

    public TextureRegion getRegion() {
        return currentTexture;
    }

    public Body getBody() {
        return this.body;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        body.setTransform(x, y, 0);
    }
}