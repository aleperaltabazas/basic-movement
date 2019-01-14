package com.basic.movement;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum Direction {
    North {
        public TextureRegion getKeyFrame(Player brendan) {
            PlayerTextureMap textureMap = brendan.getTextureMap();
            return getKeyFrame(brendan, textureMap.getWalking(North), textureMap.getRunning(North), textureMap.getStanding(North));
        }
    },
    South {
        public TextureRegion getKeyFrame(Player brendan) {
            PlayerTextureMap textureMap = brendan.getTextureMap();
            return getKeyFrame(brendan, textureMap.getWalking(South), textureMap.getRunning(South), textureMap.getStanding(South));
        }
    },
    West {
        public TextureRegion getKeyFrame(Player brendan) {
            PlayerTextureMap textureMap = brendan.getTextureMap();
            return getKeyFrame(brendan, textureMap.getWalking(West), textureMap.getRunning(West), textureMap.getStanding(West));
        }
    },
    East {
        public TextureRegion getKeyFrame(Player brendan) {
            PlayerTextureMap textureMap = brendan.getTextureMap();
            return getKeyFrame(brendan, textureMap.getWalking(East), textureMap.getRunning(East), textureMap.getStanding(East));
        }
    };

    TextureRegion getKeyFrame(Player brendan, Animation<TextureRegion> walking, Animation<TextureRegion> running, TextureRegion standing) {
        switch (brendan.getState()) {
            case Standing:
                return standing;
            case Walking:
                return walking.getKeyFrame(brendan.getStateTimer(), true);
            case Running:
                return running.getKeyFrame(brendan.getStateTimer(), true);
            default:
                throw new RuntimeException("Should not be null");
        }
    }

    public abstract TextureRegion getKeyFrame(Player brendan);
}
