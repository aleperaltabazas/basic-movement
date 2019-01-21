package com.freemovement.game.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.freemovement.game.FreeMovementGame;
import com.freemovement.game.player.movement.MovementData;
import com.freemovement.game.player.movement.MovementState;
import com.freemovement.game.player.movement.Standing;
import com.freemovement.game.player.movement.Walking;
import com.freemovement.game.screen.AbstractScreen;
import com.freemovement.game.utils.PlayerTextureMap;

import static com.freemovement.game.player.Direction.*;

public class Player extends Sprite {
    private AbstractScreen screen;

    private PlayerTextureMap textureMap;

    private Body body;
    private MovementData movementData;
    private MovementState movementState;
    private TextureRegion currentFrame;

    public Player(AbstractScreen screen) {
        super(new TextureRegion(screen.getAtlas().findRegion("crono/standing/south")));
        this.screen = screen;
        this.movementData = new MovementData(0, South, screen.getAtlas());
        this.currentFrame = movementData.getCurrentFrame();
        this.movementState = new Standing();

        createBody();
    }

    private void createBody() {
        Vector2 center = new Vector2(getX() + FreeMovementGame.TILE_WIDTH / 2, getY() + FreeMovementGame.TILE_HEIGHT / 2);
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(center);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = screen.getWorld().createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(center.x, center.y, center, 0);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

    public void moveNorth() {
        movementData.setDirection(North);
        movementState.moveNorth(this);
    }

    public void moveSouth() {
        movementData.setDirection(South);
        movementState.moveSouth(this);
    }

    public void moveEast() {
        movementData.setDirection(East);
        movementState.moveEast(this);
    }

    public void moveWest() {
        movementData.setDirection(West);
        movementState.moveWest(this);
    }

    public void stopX() {
        body.setLinearVelocity(0, body.getLinearVelocity().y);
    }

    public void stopY() {
        body.setLinearVelocity(body.getLinearVelocity().x, 0);
    }

    public void walk() {
        movementState = new Walking();
    }

    public void stop() {
        stopX();
        stopY();
        movementState = new Standing();
    }

    public void update(float delta) {
        updateData(delta);
        updatePosition(delta);
    }

    private void updateData(float delta) {
        movementData.update(delta, movementState);
        setRegion(currentFrame = movementData.getCurrentFrame());
        setSize(getRegionWidth(), getRegionHeight());
    }

    private void updatePosition(float delta) {
        setPosition(body.getPosition().x, body.getPosition().y);
    }

    public Body getBody() {
        return this.body;
    }

    public void setSpeed(float speedX, float speedY) {
        body.setLinearVelocity(speedX, speedY);
    }

    public float getSpeedX() {
        return body.getLinearVelocity().x;
    }

    public float getSpeedY() {
        return body.getLinearVelocity().y;
    }
}
