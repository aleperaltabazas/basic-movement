package com.basic.movement.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.utils.PlayerTextureMap;

public enum Direction {
    North {
        public TextureRegion getKeyFrame(Player player) {
            PlayerTextureMap textureMap = player.getTextureMap();
            return getKeyFrame(player, textureMap.getWalking(North), textureMap.getRunning(North), textureMap.getStanding(North));
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

    TextureRegion getKeyFrame(Player player, Animation<TextureRegion> walking, Animation<TextureRegion> running, TextureRegion standing) {
        switch (player.getState()) {
            case Standing:
                return standing;
            case Walking:
                return walking.getKeyFrame(player.getStateTimer(), true);
            case Running:
                return running.getKeyFrame(player.getStateTimer(), true);
            default:
                throw new RuntimeException("Should not be null");
        }
    }

    public abstract TextureRegion getKeyFrame(Player brendan);
}
