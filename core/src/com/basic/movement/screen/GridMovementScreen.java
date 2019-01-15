package com.basic.movement.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.basic.movement.BasicMovementGame;
import com.basic.movement.player.Player;

public class GridMovementScreen extends AbstractScreen {
    private static final float TILE_WIDTH = 16;
    private static final float TILE_HEIGHT = 16;

    private TextureAtlas atlas;
    private Player player;
    private OrthographicCamera camera;

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
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        game.getBatch().begin();
        player.draw(game.getBatch());
        game.getBatch().end();

        manageKeyboard();

        player.update(delta);
    }

    private void manageKeyboard() {
        boolean west = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean east = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean souldMoveEastWest = (west != east);

        boolean south = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean north = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean shouldMoveSouthNorth = (south != north);

        boolean running = Gdx.input.isKeyPressed(Input.Keys.X);

        if (!player.isMoving() && souldMoveEastWest) {
            player.setMoving(true);
            player.setRunning(running);

            if (east) {
                player.setTargetX(player.getX() + TILE_WIDTH);
            } else if (west) {
                player.setTargetX(player.getX() - TILE_WIDTH);
            }
        } else if (!player.isMoving() && shouldMoveSouthNorth) {
            player.setMoving(true);
            player.setRunning(running);

            if (north) {
                player.setTargetY(player.getY() + TILE_HEIGHT);
            } else if (south) {
                player.setTargetY(player.getY() - TILE_HEIGHT);
            }
        } else {
            player.stopMovement();
        }

        player.move();
    }
}
