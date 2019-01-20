package com.freemovement.game.movement;

import com.freemovement.game.player.Player;

public class MovementManager {
    private InputManager manager;

    public MovementManager() {
        this.manager = new InputManager();
    }

    public void manage(Player player) {
        boolean east = manager.isMovingEast();
        boolean west = manager.isMovingWest();
        boolean shouldMoveEastWest = east != west;

        boolean north = manager.isMovingNorth();
        boolean south = manager.isMovingSouth();
        boolean shouldMoveNorthSouth = north != south;

        if (shouldMoveEastWest) {
            if (east)
                player.moveEast();
            else if (west)
                player.moveWest();

            player.walk();
        } else
            player.stopX();

        if (shouldMoveNorthSouth) {
            if (north)
                player.moveNorth();
            else if (south)
                player.moveSouth();

            player.walk();
        } else
            player.stopY();

        if (!shouldMoveEastWest && !shouldMoveNorthSouth)
            player.stop();
    }

    public InputManager getInputManager() {
        return manager;
    }
}
