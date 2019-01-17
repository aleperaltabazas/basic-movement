package com.basic.movement.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Door extends InteractiveTile {
    public Door(WorldMap worldMap, TiledMap map, Rectangle bounds) {
        super(worldMap, map, bounds);
    }
}
