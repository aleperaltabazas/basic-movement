package com.freemovement.game.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.freemovement.game.player.Direction;

import java.util.HashMap;
import java.util.Map;

public class PlayerTextureMap {
    private TextureAtlasAdapter atlas;
    private Map<String, Animation<TextureRegion>> walkingMap;
    private Map<String, TextureRegion> standingMap;

    public PlayerTextureMap(TextureAtlasAdapter atlas) {
        this.atlas = atlas;
        walkingMap = new HashMap<String, Animation<TextureRegion>>();
        standingMap = new HashMap<String, TextureRegion>();

        walkingMap.put(Direction.North.toString(), atlas.findAnimation("crono/walking/north", 22, 1, 35, 1));
        walkingMap.put(Direction.South.toString(), atlas.findAnimation("crono/walking/south", 22, 1, 35, 1));
        walkingMap.put(Direction.East.toString(), atlas.findAnimation("crono/walking/east", 22, 1, 35, 1));
        walkingMap.put(Direction.West.toString(), atlas.findAnimation("crono/walking/west", 22, 1, 35, 1));

        standingMap.put(Direction.North.toString(), atlas.findRegion("crono/standing/north"));
        standingMap.put(Direction.South.toString(), atlas.findRegion("crono/standing/south"));
        standingMap.put(Direction.East.toString(), atlas.findRegion("crono/standing/east"));
        standingMap.put(Direction.West.toString(), atlas.findRegion("crono/standing/west"));
    }

    public Animation<TextureRegion> getWalking(Direction direction) {
        return walkingMap.get(direction.toString());
    }

    public TextureRegion getStanding(Direction direction) {
        return standingMap.get(direction.toString());
    }
}
