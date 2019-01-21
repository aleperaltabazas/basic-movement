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

        TextureRegion[] frames = new TextureRegion[cols * rows];
        
        int index = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = temp[i][j];
            }
        }

        return new Animation<TextureRegion>(0.16f, frames);
    }
}
