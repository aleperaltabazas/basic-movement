package com.basic.movement;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class AnimationScreen extends AbstractScreen {
    private static final int ANCHO = 56;
    private static final int ALTO = 21;

    private TextureAtlas atlas;
    private TextureRegion[] walkingFrames;
    private Animation<TextureRegion> walkingAnimation;

    private TextureRegion brendanRegion;

    private float duration = 0;

    private float x = (float) Gdx.graphics.getWidth() / 2;
    private float y = (float) Gdx.graphics.getHeight() / 2;

    public AnimationScreen(BasicMovementGame game) {
        super(game);
    }

    @Override
    public void show() {
        walkingAnimation = renderFromAtlas("front");
    }

    private Animation<TextureRegion> renderFromAtlas(String regionName) {
        atlas = new TextureAtlas("output/atlas.atlas");
        TextureRegion region = atlas.findRegion(regionName);
        brendanRegion = new TextureRegion(region, 0, 0, ANCHO, ALTO);

        TextureRegion[][] temp = brendanRegion.split(ANCHO / 4, ALTO);
        walkingFrames = new TextureRegion[temp.length * temp[0].length];

        int index = 0;

        for (TextureRegion[] textureRegions : temp) {
            for (TextureRegion textureRegion : textureRegions) {
                walkingFrames[index++] = textureRegion;
            }
        }

        return new Animation<TextureRegion>(0.16f, walkingFrames);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        duration += delta;
        TextureRegion frame = walkingAnimation.getKeyFrame(duration, true);
        game.batch.begin();
        game.batch.draw(frame, x, y);
        manageInputs();
        game.batch.end();
    }

    private void manageInputs() {
        boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        if (up && !down && !left && !right) {
            walkingAnimation = renderFromAtlas("back");
            y += 1;
        } else if (!up && down && !left && !right) {
            walkingAnimation = renderFromAtlas("front");
            y -= 1;
        } else if (!up && !down && left && !right) {
            walkingAnimation = renderFromAtlas("left");
            x -= 1;
        } else if (!up && !down && !left && right) {
            walkingAnimation = renderFromAtlas("right");
            x += 1;
        }
    }
}
