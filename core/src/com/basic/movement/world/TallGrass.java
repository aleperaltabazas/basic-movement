package com.basic.movement.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class TallGrass extends InteractiveTile {
    public TallGrass(WorldMap world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }

    @Override
    public boolean canStep() {
        return true;
    }

    @Override
    public void step() {
        int random = getRandomEncounter();

        if (random > 25) {
            System.out.println("Encounter!");
        }
    }

    private int getRandomEncounter() {
        Random r = new Random();
        return r.nextInt(99);
    }
}
