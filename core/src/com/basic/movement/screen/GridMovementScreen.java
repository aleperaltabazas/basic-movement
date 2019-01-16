package com.basic.movement.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.*;
import com.basic.movement.BasicMovementGame;
import com.basic.movement.player.Player;
import com.basic.movement.scene.Hud;

public class GridMovementScreen extends AbstractScreen {

    private TextureAtlas atlas;
    private Player player;
    private OrthographicCamera camera;
    private Hud hud;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private ShapeRenderer shaper;
    private Viewport viewport;

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
        player.setPosition(240, 240);
        player.setTargetPosition(240, 240);
        camera = new OrthographicCamera(Gdx.graphics.getHeight(), Gdx.graphics.getHeight());

        hud = new Hud(game.getBatch());

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/town.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        Gdx.input.setInputProcessor(player.getMovementManager().getInput());
        shaper = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        mapRenderer.setView(camera);
        mapRenderer.render();

        shaper.setProjectionMatrix(camera.combined);
        shaper.begin(ShapeRenderer.ShapeType.Line);
        renderAxis();
        shaper.end();

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        player.draw(game.getBatch());
        game.getBatch().end();
        hud.getStage().draw();

        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    private void renderAxis() {
        shaper.line(-1024, 0, 1024, 0);
        shaper.line(0, -1024, 0, 1024);

        for (int i = -1024; i <= 1024; i += 16) {
            shaper.line(-1024, i, 1024, i);
            shaper.line(i, -1024, i, 1024);
        }
    }

    private void update(float delta) {
        manageKeyboard();

        player.update(delta);
        hud.update(player);
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();
    }

    private void manageKeyboard() {
        player.manageMovement();
    }
}
