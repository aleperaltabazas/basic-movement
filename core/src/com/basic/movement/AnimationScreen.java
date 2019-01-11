package com.basic.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationScreen extends AbstractScreen {
    private static final int ANCHO = 56;
    private static final int ALTO = 21;

    private TextureAtlas atlas;
    private TextureRegion[] walkingFrames;
    private Animation<TextureRegion> walkingAnimation;

    private TextureRegion brendanRegion;

    private float duration = 0;

    public AnimationScreen(BasicMovementGame game) {
        super(game);
    }

    @Override
    public void show() {
        atlas = new TextureAtlas("output/atlas.atlas");

        TextureRegion region = atlas.findRegion("front");
        brendanRegion = new TextureRegion(region, 0, 0, ANCHO, ALTO);

        TextureRegion[][] temp = brendanRegion.split(ANCHO / 4, ALTO);
        walkingFrames = new TextureRegion[temp.length * temp[0].length];

        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                walkingFrames[index++] = temp[i][j];
            }
        }

        walkingAnimation = new Animation<TextureRegion>(0.16f, walkingFrames);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        duration += delta;
        TextureRegion frame = walkingAnimation.getKeyFrame(duration, true);

        game.batch.begin();
        game.batch.draw(frame, Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth() / 2);
        game.batch.end();
    }
}
