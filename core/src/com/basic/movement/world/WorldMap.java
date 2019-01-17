package com.basic.movement.world;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private Map<Vector2, InteractiveTile> tileMap;

    public WorldMap() {
        tileMap = new HashMap<>();
    }

    public void addTile(float x, float y, InteractiveTile tile) {
        tileMap.put(new Vector2(x, y), tile);
    }

    public boolean isOccupied(float x, float y) {
        if (tileMap.containsKey(new Vector2(x, y))) {
            return true;
        }

        return tileMap.get(new Vector2(x, y)).canStep();
    }
}
