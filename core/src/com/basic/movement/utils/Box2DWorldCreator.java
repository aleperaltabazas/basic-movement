package com.basic.movement.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.basic.movement.world.*;

import java.lang.reflect.Constructor;

public class Box2DWorldCreator {
    private World world;
    private TiledMap map;

    private WorldMap worldMap;

    public Box2DWorldCreator(World world, TiledMap map) {
        this.world = world;
        this.map = map;

        createBodies("walls", Wall.class);
        createBodies("ocean", Ocean.class);
        createBodies("signs", Sign.class);
        createBodies("tall grass", TallGrass.class);
        createBodies("doors", Door.class);
    }

    private void createBodies(String objectName, Class<? extends InteractiveTile> entityClass) {
        for (MapObject object : map.getLayers().get(objectName).getObjects().getByType(RectangleMapObject.class)) {
            try {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                Constructor<? extends InteractiveTile> ctor = entityClass.getConstructor(World.class, TiledMap.class, Rectangle.class);
                ctor.newInstance(world, map, rectangle);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getClass().toString());
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
