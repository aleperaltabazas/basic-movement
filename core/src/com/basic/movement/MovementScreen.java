package com.basic.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MovementScreen extends AbstractScreen {
    private String atlasName;
    private Player brendan;
    private InputManager manager;
    private BrendanInput input;

    private TextureAtlas atlas;

    private OrthographicCamera camera;
    private ShapeRenderer shaper;

    public MovementScreen(BasicMovementGame game, String atlasName) {
        super(game);
        this.atlasName = atlasName;
    }

    @Override
    public void show() {
        atlas = new TextureAtlas(atlasName);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shaper = new ShapeRenderer();

        brendan = new Player(this, 56, 21, 60, 21);
        brendan.setPosition(camera.position.x, camera.position.y);

        manager = new InputManager();
        input = new BrendanInput(manager);

        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        renderAxis();

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        brendan.draw(game.getBatch());
        game.getBatch().end();

        if (manager.isMovingDown()) {
            if (manager.isRunning())
                brendan.runSouth();
            else
                brendan.walkSouth();
        } else if (manager.isMovingLeft()) {
            if (manager.isRunning())
                brendan.runWest();
            else
                brendan.walkWest();
        } else if (manager.isMovingRight()) {
            if (manager.isRunning())
                brendan.runEast();
            else
                brendan.walkEast();
        } else if (manager.isMovingUp()) {
            if (manager.isRunning())
                brendan.runNorth();
            else
                brendan.walkNorth();
        } else {
            brendan.stop();
        }

        brendan.update(delta);
        camera.position.set(brendan.getX(), brendan.getY(), 0);
        camera.update();
    }

    private void renderAxis() {
        shaper.setProjectionMatrix(camera.combined);
        shaper.begin(ShapeRenderer.ShapeType.Line);
        shaper.line(-1024, 0, 1024, 0, Color.RED, Color.RED);
        shaper.line(0, -1024, 0, 1024, Color.RED, Color.RED);
        for (int i = -1024; i <= 1024; i += 16) {
            if (i == 0) continue;
            shaper.line(-1024, i, 1024, i, Color.GREEN, Color.GREEN);
            shaper.line(i, -1024, i, 1024, Color.GREEN, Color.GREEN);
        }
        shaper.end();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }
}
