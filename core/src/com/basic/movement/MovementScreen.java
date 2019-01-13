package com.basic.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx .graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MovementScreen extends AbstractScreen {
    private String atlasName;
    private Brendan brendan;
    private InputManager manager;
    private BrendanInput input;

    private TextureAtlas atlas;

    public MovementScreen(BasicMovementGame game, String atlasName) {
        super(game);
        this.atlasName = atlasName;
    }

    @Override
    public void show() {
        atlas = new TextureAtlas(atlasName);

        brendan = new Brendan(this);
        brendan.setPosition(100, 100);

        manager = new InputManager();
        input = new BrendanInput(manager);

        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        game.getBatch().begin();
        brendan.draw(game.getBatch());
        game.getBatch().end();

        if (manager.isMovingDown() && !manager.isMoving(InputManager.Direction.DOWN)) {
            brendan.moveDown();
        } else if (manager.isMovingLeft() && !manager.isMoving(InputManager.Direction.LEFT)) {
            brendan.moveLeft();
        } else if (manager.isMovingRight() && !manager.isMoving(InputManager.Direction.RIGHT)) {
            brendan.moveRight();
        } else if (manager.isMovingUp() && !manager.isMoving(InputManager.Direction.UP)) {
            brendan.moveUp();
        }

        brendan.update(delta);
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }
}
