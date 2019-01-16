package com.basic.movement.utils;

import com.basic.movement.player.InputManager;
import com.basic.movement.player.Player;
import com.basic.movement.player.PlayerInput;

public class MovementManager {
    private final float TILE_WIDTH;
    private final float TILE_HEIGHT;

    private PlayerInput input;

    private InputManager manager;

    public MovementManager(float tileWidth, float tileHeight) {
        TILE_WIDTH = tileWidth;
        TILE_HEIGHT = tileHeight;

        manager = new InputManager();
        input = new PlayerInput(manager);
    }

    public void manage(Player player) {

        boolean west = manager.isMovingWest();
        boolean east = manager.isMovingEast();
        boolean souldMoveEastWest = (west != east);

        boolean south = manager.isMovingSouth();
        boolean north = manager.isMovingNorth();
        boolean shouldMoveSouthNorth = (south != north);

        boolean running = manager.isRunning();

        if (!player.isMoving() && souldMoveEastWest) {
            player.setMoving(true);
            player.setRunning(running);

            if (east) {
                player.setTargetX(player.getX() + TILE_WIDTH);
            } else if (west) {
                player.setTargetX(player.getX() - TILE_WIDTH);
            }
        } else if (!player.isMoving() && shouldMoveSouthNorth) {
            player.setMoving(true);
            player.setRunning(running);

            if (north) {
                player.setTargetY(player.getY() + TILE_HEIGHT);
            } else if (south) {
                player.setTargetY(player.getY() - TILE_HEIGHT);
            }
        } else {
            player.stopMovement();
        }

    }

    public PlayerInput getInput() {
        return input;
    }
}
