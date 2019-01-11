package com.basic.movement;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class BasicMovementGame extends Game {
    private static final int ANCHO = 56;
    private static final int ALTO = 21;
    public SpriteBatch batch;

    private AbstractScreen brendan;

    @Override
    public void create() {
        batch = new SpriteBatch();
        brendan = new AnimationScreen(this);
        setScreen(brendan);
    }
}
