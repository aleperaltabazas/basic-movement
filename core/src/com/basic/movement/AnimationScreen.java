package com.basic.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationScreen extends GameScreen {
    private Texture brendanTexture;
    private TextureRegion brendanRegion;
    private TextureRegion[] brendanFrames;
    private Animation<TextureRegion> brendanAnimation;
    private int duration = 0;

    private final int ALTO = 21;
    private final int ANCHO = 56;

    public AnimationScreen(BasicMovementGame game) {
        super(game);
    }

    @Override
    public void show() {
        brendanTexture = new Texture("brendan/walking/front.png");
        brendanRegion = new TextureRegion(brendanTexture, ANCHO, ALTO);
        TextureRegion[][] temp = brendanRegion.split(ANCHO / 4, ALTO);

        brendanFrames = new TextureRegion[temp.length * temp[0].length];

        int index = 0;

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                brendanFrames[index++] = temp[i][j];
            }
        }

        brendanAnimation = new Animation<TextureRegion>(1f, brendanFrames);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        duration += delta;
        TextureRegion frame = brendanAnimation.getKeyFrame(duration, true);

        game.batch.begin();
        game.batch.draw(frame, 100, 100);
        game.batch.end();
    }
}
