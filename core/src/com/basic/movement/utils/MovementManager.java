package com.basic.movement.utils;

import com.basic.movement.player.*;
import com.basic.movement.world.WorldMap;

public class MovementManager {
    private final float TILE_WIDTH;
    private final float TILE_HEIGHT;

    private PlayerInput input;
    private InputManager manager;
    private MovementObserver movementObserver;

    public MovementManager(float tileWidth, float tileHeight, MovementObserver movementObserver) {
        TILE_WIDTH = tileWidth;
        TILE_HEIGHT = tileHeight;

        this.manager = new InputManager();
        this.input = new PlayerInput(manager);
        this.movementObserver = movementObserver;
    }

    public void manage(Player player, WorldMap worldMap) {
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
                if (worldMap.isOccupied(player.getX() + TILE_WIDTH, player.getY()))
                    player.walkInPlace(Direction.East);
                else
                    player.setTargetX(player.getX() + TILE_WIDTH);
            } else if (west) {
                if (worldMap.isOccupied(player.getX() - TILE_WIDTH, player.getY()))
                    player.walkInPlace(Direction.West);
                else
                    player.setTargetX(player.getX() - TILE_WIDTH);
            }
        } else if (!player.isMoving() && shouldMoveSouthNorth) {
            player.setMoving(true);
            player.setRunning(running);

            if (north) {
                if (worldMap.isOccupied(player.getX(), player.getY() + TILE_HEIGHT))
                    player.walkInPlace(Direction.North);
                else
                    player.setTargetY(player.getY() + TILE_HEIGHT);
            } else if (south) {
                if (worldMap.isOccupied(player.getX(), player.getY() - TILE_HEIGHT))
                    player.walkInPlace(Direction.South);
                else
                    player.setTargetY(player.getY() - TILE_HEIGHT);
            }
        } else {
            player.stopMovement();
        }

        movementObserver.actOnMovement(player);
    }

    public PlayerInput getInput() {
        return input;
    }
}
