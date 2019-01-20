package com.basic.movement.movement;

import com.basic.movement.player.Player;
import com.basic.movement.scene.Hud;
import com.basic.movement.world.WorldMap;

public class MovementObserver {
    private WorldMap worldMap;
    private Hud hud;

    public MovementObserver(WorldMap worldMap, Hud hud) {
        this.worldMap = worldMap;
        this.hud = hud;
    }

    public void actOnMovement(Player player) {
        hud.update(player);
    }
}
