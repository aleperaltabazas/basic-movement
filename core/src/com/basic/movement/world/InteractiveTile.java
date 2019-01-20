package com.basic.movement.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class InteractiveTile extends Actor {
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    private Vector2 position;

    public InteractiveTile(WorldMap world, TiledMap map, Rectangle bounds) {
        this.map = map;
        this.bounds = bounds;
        this.position = new Vector2(bounds.x, bounds.y);

        world.addTile(bounds.x, bounds.y, this);
    }

    public boolean canStep() {
        return false;
    }

    public void interact() {

    }
}
