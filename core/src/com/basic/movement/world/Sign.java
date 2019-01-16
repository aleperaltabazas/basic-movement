package com.basic.movement.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Sign extends Tile {
    public Sign(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }
}
