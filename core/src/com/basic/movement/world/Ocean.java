package com.basic.movement.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Ocean extends InteractiveTile {
    public Ocean(WorldMap world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }

    @Override
    public void step() {

    }
}
