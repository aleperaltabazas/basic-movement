package com.freemovement.game.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureAtlasAdapter extends TextureAtlas {
    public TextureAtlasAdapter(String atlasName) {
        super(atlasName);
    }

    public Animation<TextureRegion> findAnimation(String regionName, int width, int cols, int height, int rows) {
        TextureRegion region = findRegion(regionName);
        TextureRegion[][] temp = region.split(width / cols, height / rows);

        TextureRegion[] frames = new TextureRegion[temp.length * temp[0].length];
        int index = 0;

        for (TextureRegion[] textureRegions : temp) {
            for (TextureRegion textureRegion : textureRegions) {
                frames[index++] = textureRegion;
            }
        }

        return new Animation<TextureRegion>(0.16f, frames);
    }
}
