package com.basic.movement.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.basic.movement.player.Direction;
import com.basic.movement.player.Gender;

import java.util.HashMap;
import java.util.Map;

public class PlayerTextureMap {
    private Map<String, Animation<TextureRegion>> running;
    private Map<String, Animation<TextureRegion>> walking;
    private Map<String, TextureRegion> standing;

    private final int maleWalkWidth = 14;
    private final int maleWalkHeight = 21;
    private final int maleRunWidth = 15;
    private final int maleRunHeight = 21;

    private final int maleWalkingColumns = 4;
    private final int maleWalkingRows = 1;

    private final int maleRunningColumns = 4;
    private final int maleRunningRows = 1;

    public PlayerTextureMap(TextureAtlasAdapter atlas) {
        standing = new HashMap<>();
        running = new HashMap<>();
        walking = new HashMap<>();

        //TODO: add female

        putWalking(Direction.North, Gender.Male, atlas.getAnimation("walking/north", maleWalkWidth * maleWalkingColumns, maleWalkingColumns, maleWalkHeight * maleWalkingRows, maleWalkingRows));
        putWalking(Direction.South, Gender.Male, atlas.getAnimation("walking/south", maleWalkWidth * maleWalkingColumns, maleWalkingColumns, maleWalkHeight * maleWalkingRows, maleWalkingRows));
        putWalking(Direction.West, Gender.Male, atlas.getAnimation("walking/west", maleWalkWidth * maleWalkingColumns, maleWalkingColumns, maleWalkHeight * maleWalkingRows, maleWalkingRows));
        putWalking(Direction.East, Gender.Male, atlas.getAnimation("walking/east", maleWalkWidth * maleWalkingColumns, maleWalkingColumns, maleWalkHeight * maleWalkingRows, maleWalkingRows));

        putRunning(Direction.South, Gender.Male, atlas.getAnimation("running/south", maleRunWidth * maleRunningColumns, maleRunningColumns, maleRunHeight * maleRunningRows, maleRunningRows));
        putRunning(Direction.North, Gender.Male, atlas.getAnimation("running/north", maleRunWidth * maleRunningColumns, maleRunningColumns, maleRunHeight * maleRunningRows, maleRunningRows));
        putRunning(Direction.West, Gender.Male, atlas.getAnimation("running/west", maleRunWidth * maleRunningColumns, maleRunningColumns, maleRunHeight * maleRunningRows, maleRunningRows));
        putRunning(Direction.East, Gender.Male, atlas.getAnimation("running/east", maleRunWidth * maleRunningColumns, maleRunningColumns, maleRunHeight * maleRunningRows, maleRunningRows));

        putStanding(Direction.South, Gender.Male, atlas.findRegion("standing/south"));
        putStanding(Direction.North, Gender.Male, atlas.findRegion("standing/north"));
        putStanding(Direction.West, Gender.Male, atlas.findRegion("standing/west"));
        putStanding(Direction.East, Gender.Male, atlas.findRegion("standing/east"));
    }

    public void putRunning(Direction direction, Gender gender, Animation<TextureRegion> animation) {
        running.put(direction.toString() + gender.toString(), animation);
    }

    public void putWalking(Direction direction, Gender gender, Animation<TextureRegion> animation) {
        walking.put(direction.toString() + gender.toString(), animation);
    }

    public void putStanding(Direction direction, Gender gender, TextureRegion textureRegion) {
        standing.put(direction.toString() + gender.toString(), textureRegion);
    }

    public Animation<TextureRegion> getRunning(Direction direction, Gender gender) {
        return running.get(direction.toString() + gender.toString());
    }

    public Animation<TextureRegion> getWalking(Direction direction, Gender gender) {
        return walking.get(direction.toString() + gender.toString());
    }

    public TextureRegion getStanding(Direction direction, Gender gender) {
        return standing.get(direction.toString() + gender.toString());
    }
}
