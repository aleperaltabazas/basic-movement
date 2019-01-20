package com.freemovement.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.freemovement.game.screen.AbstractScreen;

public class FreeMovementGame extends Game {
	private SpriteBatch batch;
	private AbstractScreen screen;

	@Override
	public void create () {
		batch = new SpriteBatch();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
