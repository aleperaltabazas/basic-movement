package com.basic.movement;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Shader;

public class BasicMovementGame extends Game {
    private static final int ANCHO = 56;
    private static final int ALTO = 21;
    public SpriteBatch batch;

    private AbstractScreen brendan;

    @Override
    public void create() {
        batch = new SpriteBatch();
        brendan = new MovementScreen(this);
        setScreen(brendan);
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
