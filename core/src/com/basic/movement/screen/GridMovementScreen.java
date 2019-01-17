package com.basic.movement.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.basic.movement.*;
import com.basic.movement.player.*;
import com.basic.movement.scene.*;
import com.basic.movement.utils.Box2DWorldCreator;
import com.basic.movement.utils.OverworldContactListener;
import com.basic.movement.world.WorldMap;

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
    private ContactListener contactListener;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private WorldMap worldMap;

    public GridMovementScreen(BasicMovementGame game) {
        super(game);
    }

    public TextureAtlas getAtlas() {
        return this.atlas;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, 0), true);
        atlas = new TextureAtlas("output/brendan.atlas");
        player = new Player(this, 56, 21, 60, 21);
        player.setPosition(128, 64);
        player.setTargetPosition(128, 64);
        camera = new OrthographicCamera(Gdx.graphics.getHeight(), Gdx.graphics.getHeight());

        player.getBody().setTransform(128, 64, 0);

        hud = new Hud(game.getBatch());

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/town.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        debugRenderer = new Box2DDebugRenderer();
        debugRenderer.SHAPE_STATIC.set(1, 0, 0, 1);
        debugRenderer.SHAPE_KINEMATIC.set(1, 0, 1, 1);
        debugRenderer.SHAPE_AWAKE.set(0, 0, 1, 1);

        new Box2DWorldCreator(world, map);

        Gdx.input.setInputProcessor(player.getMovementManager().getInput());
        shaper = new ShapeRenderer();

        contactListener = new OverworldContactListener();
        world.setContactListener(contactListener);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        renderMap();
        renderBodies();
        renderBatch();
        renderHUD();

        update(delta);
    }

    private void renderBatch() {
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        player.draw(game.getBatch());
        game.getBatch().end();
    }

    private void renderHUD() {
        hud.getStage().draw();
    }

    private void renderMap() {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    private void renderShapes() {
        shaper.setProjectionMatrix(camera.combined);
        shaper.begin(ShapeRenderer.ShapeType.Line);
        renderAxis();
        shaper.end();
    }

    private void renderBodies() {
        debugRenderer.render(world, camera.combined);
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

        world.step(1 / 60f, 6, 2);

        player.update(delta);
        hud.update(player);
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();


    }

    private void manageKeyboard() {
        player.manageMovement();
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public void dispose() {
        map.dispose();
        world.dispose();
        debugRenderer.dispose();
        hud.dispose();
    }
}
