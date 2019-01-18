package com.basic.movement.world;

import com.badlogic.gdx.math.Vector2;
import com.basic.movement.player.Player;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private Map<Vector2, InteractiveTile> tileMap;
    private Vector2 playerPosition;

    public WorldMap() {
        tileMap = new HashMap<>();
    }

    public void addTile(float x, float y, InteractiveTile tile) {
        tileMap.put(new Vector2(x, y), tile);
    }

    public boolean isOccupied(float x, float y) {
        if (existsTile(x, y))
            return !tileMap.get(new Vector2(x, y)).canStep();

        return false;

    }

    public void setPlayerPosition(Player player) {
        this.playerPosition = new Vector2(player.getX(), player.getY());
    }

    public void interactWithTile(float x, float y) {
        Vector2 position = new Vector2(x, y);

        if (existsTile(x, y)) {
            tileMap.get(new Vector2(x, y)).interact();
        }
    }

    private boolean existsTile(float x, float y) {
        return tileMap.containsKey(new Vector2(x, y));
    }
}
