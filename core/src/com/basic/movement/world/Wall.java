package com.basic.movement.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Wall extends InteractiveTile {
    public Wall(WorldMap world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }
}
