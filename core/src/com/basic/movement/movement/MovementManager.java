package com.basic.movement.movement;

import com.basic.movement.player.ActorPlayer;
import com.basic.movement.player.Direction;
import com.basic.movement.player.PlayerSprite;
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

    public void manage(ActorPlayer actor, WorldMap worldMap) {
        manage(actor.getSprite(), worldMap);
    }

    public void manage(PlayerSprite playerSprite, WorldMap worldMap) {
        boolean west = manager.isMovingWest();
        boolean east = manager.isMovingEast();
        boolean shouldMoveEastWest = (west != east);

        boolean south = manager.isMovingSouth();
        boolean north = manager.isMovingNorth();
        boolean shouldMoveSouthNorth = (south != north);

        boolean running = manager.isRunning();

        boolean interacting = manager.isInteracting();

        if (interacting) {
            float x = playerSprite.getX() + playerSprite.getDirection().getFacingX(), y = playerSprite.getY() + playerSprite.getDirection().getFacingY();

            worldMap.interactWithTile(x, y);
        } else {
            if (!playerSprite.isMoving() && shouldMoveEastWest) {
                playerSprite.setMoving(true);
                playerSprite.setRunning(running);

                if (east) {
                    if (worldMap.isOccupied(playerSprite.getX() + TILE_WIDTH, playerSprite.getY()))
                        playerSprite.walkInPlace(Direction.East);
                    else{
                        playerSprite.setTargetX(playerSprite.getX() + TILE_WIDTH);
                        worldMap.stepOnTile(playerSprite.getX() + TILE_WIDTH, playerSprite.getY());
                    }
                } else if (west) {
                    if (worldMap.isOccupied(playerSprite.getX() - TILE_WIDTH, playerSprite.getY()))
                        playerSprite.walkInPlace(Direction.West);
                    else
                        playerSprite.setTargetX(playerSprite.getX() - TILE_WIDTH);
                }
            } else if (!playerSprite.isMoving() && shouldMoveSouthNorth) {
                playerSprite.setMoving(true);
                playerSprite.setRunning(running);

                if (north) {
                    if (worldMap.isOccupied(playerSprite.getX(), playerSprite.getY() + TILE_HEIGHT))
                        playerSprite.walkInPlace(Direction.North);
                    else
                        playerSprite.setTargetY(playerSprite.getY() + TILE_HEIGHT);
                } else if (south) {
                    if (worldMap.isOccupied(playerSprite.getX(), playerSprite.getY() - TILE_HEIGHT))
                        playerSprite.walkInPlace(Direction.South);
                    else
                        playerSprite.setTargetY(playerSprite.getY() - TILE_HEIGHT);
                }
            } else {
                playerSprite.stopMovement();
            }

            movementObserver.actOnMovement(playerSprite);
        }
    }

    public PlayerInput getInput() {
        return input;
    }
}
