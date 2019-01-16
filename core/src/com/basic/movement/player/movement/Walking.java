package com.basic.movement.player.movement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Player;
import com.basic.movement.utils.PlayerTextureMap;

public class Walking implements MovementState {
    static final float SPEED = 50f;

    @Override
    public void moveNorth(Player player) {
        /*player.setSpeedX(0);
        player.setSpeedY(SPEED);*/
        player.setDirection(Direction.North);
        if (player.getBody().getLinearVelocity().y <= 1)
            player.getBody().applyLinearImpulse(new Vector2(0, 1f), player.getBody().getWorldCenter(), true);
    }

    @Override
    public void moveSouth(Player player) {
        /*player.setSpeedX(0);
        player.setSpeedY(-SPEED);*/
        player.setDirection(Direction.South);
        if (player.getBody().getLinearVelocity().y <= -1)
            player.getBody().applyLinearImpulse(new Vector2(0, -1f), player.getBody().getWorldCenter(), true);
    }

    @Override
    public void moveEast(Player player) {
        /*player.setSpeedX(SPEED);
        player.setSpeedY(0);*/
        player.setDirection(Direction.East);
        if (player.getBody().getLinearVelocity().x <= 1)
            player.getBody().applyLinearImpulse(new Vector2(1f, 0), player.getBody().getWorldCenter(), true);
    }

    @Override
    public void moveWest(Player player) {
        /*player.setSpeedX(-SPEED);
        player.setSpeedY(0);*/
        player.setDirection(Direction.West);
        if (player.getBody().getLinearVelocity().x <= -1)
            player.getBody().applyLinearImpulse(new Vector2(1f, 0), player.getBody().getWorldCenter(), true);
    }

    @Override
    public TextureRegion getFrame(PlayerTextureMap map, Direction direction, float stateTimer) {
        return map.getWalking(direction).getKeyFrame(stateTimer, true);
    }
}
