package com.basic.movement.movement;

import com.basic.movement.player.ActorPlayer;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Player;
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

    public void manage(Player player, WorldMap worldMap) {
        boolean west = manager.isMovingWest();
        boolean east = manager.isMovingEast();
        boolean shouldMoveEastWest = (west != east);

        boolean south = manager.isMovingSouth();
        boolean north = manager.isMovingNorth();
        boolean shouldMoveSouthNorth = (south != north);

        boolean running = manager.isRunning();

        boolean interacting = manager.isInteracting();

        if (interacting) {
            float x = player.getX() + player.getDirection().getFacingX(), y = player.getY() + player.getDirection().getFacingY();

            worldMap.interactWithTile(x, y);
        } else {
            if (!player.isMoving() && shouldMoveEastWest) {
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
    }

    public PlayerInput getInput() {
        return input;
    }
}
