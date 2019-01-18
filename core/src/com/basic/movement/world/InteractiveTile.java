package com.basic.movement.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class InteractiveTile extends Actor {
    protected WorldMap worldMap;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;

    public InteractiveTile(WorldMap world, TiledMap map, Rectangle bounds) {
        this.worldMap = world;
        this.map = map;
        this.bounds = bounds;

        worldMap.addTile(bounds.x, bounds.y, this);
    }

    public boolean canStep() {
        return false;
    }
}
