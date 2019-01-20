package com.freemovement.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.freemovement.game.FreeMovementGame;
import com.freemovement.game.movement.MovementManager;
import com.freemovement.game.player.Player;

public class FreeMovementScreen extends AbstractScreen {
    private Player player;
    private MovementManager manager;

    public FreeMovementScreen(FreeMovementGame game) {
        super(game, new World(new Vector2(0, 0), true));
    }

    @Override
    public void show() {
        initializePlayer();
        initializeMovement();
    }

    private void initializePlayer() {
        player = new Player(this);
        player.setPosition(150, 150);
    }

    private void initializeMovement() {
        manager = new MovementManager();
        Gdx.input.setInputProcessor(manager.getInputManager());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        game.getBatch().begin();
        player.draw(game.getBatch());
        game.getBatch().end();

        update(delta);
    }

    private void update(float delta) {
        player.update(delta);
        manager.manage(player);
    }

    @Override
    protected void childDispose() {

    }
}
