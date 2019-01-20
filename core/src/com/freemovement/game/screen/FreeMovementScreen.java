package com.freemovement.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.freemovement.game.FreeMovementGame;
import com.freemovement.game.movement.MovementManager;
import com.freemovement.game.player.Player;

public class FreeMovementScreen extends AbstractScreen {
    private Player player;
    private MovementManager manager;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;

    public FreeMovementScreen(FreeMovementGame game) {
        super(game, new World(new Vector2(0, 0), true));
    }

    @Override
    public void show() {
        initializePlayer();
        initializeMovement();
        initializePhysics();
        initializeCamera();
    }

    private void initializeCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void initializePhysics() {
        debugRenderer = new Box2DDebugRenderer();
        debugRenderer.SHAPE_KINEMATIC.set(Color.RED);
    }

    private void initializePlayer() {
        player = new Player(this);
    }

    private void initializeMovement() {
        manager = new MovementManager();
        Gdx.input.setInputProcessor(manager.getInputManager());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        debugRenderer.render(world, camera.combined);

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        player.draw(game.getBatch());
        game.getBatch().end();

        update(delta);
    }

    private void update(float delta) {
        world.step(1 / 60f, 6, 2);
        manager.manage(player);
        player.update(delta);
    }

    @Override
    protected void childDispose() {

    }
}
