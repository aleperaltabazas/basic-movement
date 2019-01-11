package com.basic.movement;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BasicMovementGame extends Game {
    private static final int ANCHO = 56;
    private static final int ALTO = 21;
    public SpriteBatch batch;

    private Texture brendanTexture;
    private Sprite brendanSprite;

    private int tileSize;

    private Screen brendan;
    private TextureRegion brendanRegion;
    private TextureRegion[] brendanFrames;
    private Animation<TextureRegion> brendanAnimation;

    @Override
    public void create() {
        batch = new SpriteBatch();

        /*brendanTexture = new Texture("brendan/standing/front.png");

        brendanSprite = new Sprite(brendanTexture);
        brendanSprite.setPosition(50, 50);
        tileSize = 5;
        */

        //brendan = new AnimationScreen(this);
        //setScreen(brendan);

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

    private float delta = 0;

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.6f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        delta += 0.5;
        batch.draw(brendanAnimation.getKeyFrame(delta, true), 100, 100);
        batch.end();
    }

    private void renderAssets() {
        Gdx.gl.glClearColor(0.6f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        brendanSprite.draw(batch);
        batch.end();
    }

    private void manageInputs() {
        boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);

        boolean bButton = Gdx.input.isKeyPressed(Input.Keys.X);

        if (bButton) {
            run(up, down, right, left);
        } else {
            walk(up, down, right, left);
        }
    }

    private void walk(boolean up, boolean down, boolean right, boolean left) {
        move(up, down, right, left, 1, "walking");
    }

    private void run(boolean up, boolean down, boolean right, boolean left) {
        move(up, down, right, left, 1.5f, "running");
    }

    private void move(boolean up, boolean down, boolean right, boolean left, float modifier, String mode) {
        float x = brendanSprite.getX(), y = brendanSprite.getY();

        if (up && !down && !left && !right) {
            y += tileSize * modifier;
        } else if (!up && down && !left && !right) {
            y -= tileSize * modifier;
        } else if (!up && !down && left && !right) {
            x -= tileSize * modifier;
        } else if (!up && !down && !left && right) {
            x += tileSize * modifier;
        }

        brendanSprite.setPosition(x, y);
    }

    @Override
    public void dispose() {
        batch.dispose();

        brendanTexture.dispose();
    }
}
