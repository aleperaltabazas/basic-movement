package com.basic.movement.world;

import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.basic.movement.player.Player;

public abstract class InteractiveTile {
    protected WorldMap worldMap;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;

    public InteractiveTile(WorldMap world, TiledMap map, Rectangle bounds) {
        this.worldMap = world;
        this.map = map;
        this.bounds = bounds;
    }
}
