package com.basic.movement.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureAtlasAdapter extends TextureAtlas {
    public TextureAtlasAdapter(String atlasName) {
        super(atlasName);
    }

    public Animation<TextureRegion> getAnimation(String regionName, int width, int columns, int height, int rows) {
        TextureRegion region = findRegion(regionName);
        TextureRegion[][] temp = region.split(width / columns, height / rows);

        TextureRegion[] frames = new TextureRegion[temp.length * temp[0].length];

        int index = 0;

        for (TextureRegion[] textureRegions : temp) {
            for (TextureRegion textureRegion : textureRegions) {
                frames[index++] = textureRegion;
            }
        }

        return new Animation<>(0.16f, frames);
    }
}
