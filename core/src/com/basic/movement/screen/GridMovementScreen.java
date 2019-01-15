package com.basic.movement.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.basic.movement.BasicMovementGame;
import com.basic.movement.player.Player;
import com.basic.movement.scene.Hud;

public class GridMovementScreen extends AbstractScreen {

    private TextureAtlas atlas;
    private Player player;
    private OrthographicCamera camera;
    private Hud hud;

    public GridMovementScreen(BasicMovementGame game) {
        super(game);
    }

    public TextureAtlas getAtlas() {
        return this.atlas;
    }

    @Override
    public void show() {
        atlas = new TextureAtlas("output/brendan.atlas");
        player = new Player(this, 56, 21, 60, 21);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player.setPosition(camera.position.x, camera.position.y);

        hud = new Hud(game.getBatch());

        Gdx.input.setInputProcessor(player.getKeyboardManager().getInput());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        game.getBatch().begin();
        player.draw(game.getBatch());
        game.getBatch().end();
        hud.getStage().draw();

        manageKeyboard();

        player.update(delta);
        hud.update(player);
    }

    private void manageKeyboard() {
        player.manageKeyboard();
    }
}
