package com.basic.movement.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.player.Direction;

import java.util.HashMap;
import java.util.Map;

public class PlayerTextureMap {
    private Map<String, Animation<TextureRegion>> running = new HashMap<String, Animation<TextureRegion>>();
    private Map<String, Animation<TextureRegion>> walking = new HashMap<String, Animation<TextureRegion>>();
    private Map<String, TextureRegion> standing = new HashMap<String, TextureRegion>();

    public void putRunning(Direction direction, Animation<TextureRegion> animation) {
        running.put(direction.toString(), animation);
    }

    public void putWalking(Direction direction, Animation<TextureRegion> animation) {
        walking.put(direction.toString(), animation);
    }

    public void putStanding(Direction direction, TextureRegion textureRegion) {
        standing.put(direction.toString(), textureRegion);
    }

    public Animation<TextureRegion> getRunning(Direction direction) {
        return running.get(direction.toString());
    }

    public Animation<TextureRegion> getWalking(Direction direction) {
        return walking.get(direction.toString());
    }

    public TextureRegion getStanding(Direction direction) {
        return standing.get(direction.toString());
    }
}
