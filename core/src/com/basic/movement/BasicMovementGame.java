package com.basic.movement;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BasicMovementGame extends ApplicationAdapter {
    private SpriteBatch batch;

    private Texture standingTexture;
    private Texture runningTexture;

    private Sprite brendanSprite;

    private int tileSize;

    @Override
    public void create() {
        batch = new SpriteBatch();

        standingTexture = new Texture("brendan/standing/front.png");
        runningTexture = new Texture("brendan/running/front 0.png");

        brendanSprite = new Sprite(runningTexture);
        brendanSprite.setPosition(50, 50);
        tileSize = 5;
    }

    @Override
    public void render() {
        renderAssets();

        manageInputs();
    }

    private void renderAssets() {
        Gdx.gl.glClearColor(0.6f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        brendanSprite.draw(batch);
        batch.end();
    }

    private void manageInputs() {
        float x = brendanSprite.getX(), y = brendanSprite.getY();

        boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);

        boolean bButton = Gdx.input.isKeyPressed(Input.Keys.X);

        if (bButton) {
            brendanSprite.setTexture(runningTexture);

            if (up && !down && !left && !right) {
                y += tileSize * 1.5;
            } else if (!up && down && !left && !right) {
                y -= tileSize * 1.5;
            } else if (!up && !down && left && !right) {
                x -= tileSize * 1.5;
            } else if (!up && !down && !left && right) {
                x += tileSize * 1.5;
            }
        } else {
            brendanSprite.setTexture(standingTexture);

            if (up && !down && !left && !right) {
                y += tileSize;
            } else if (!up && down && !left && !right) {
                y -= tileSize;
            } else if (!up && !down && left && !right) {
                x -= tileSize;
            } else if (!up && !down && !left && right) {
                x += tileSize;
            }
        }

        brendanSprite.setPosition(x, y);
    }

    @Override
    public void dispose() {
        batch.dispose();
        standingTexture.dispose();
        runningTexture.dispose();
    }
}
