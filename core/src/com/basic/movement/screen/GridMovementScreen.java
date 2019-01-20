package com.basic.movement.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.basic.movement.BasicMovementGame;
import com.basic.movement.movement.MovementManager;
import com.basic.movement.movement.MovementObserver;
import com.basic.movement.player.Player;
import com.basic.movement.scene.Hud;
import com.basic.movement.world.Readable;
import com.basic.movement.world.*;

import java.lang.reflect.Constructor;

public class GridMovementScreen extends AbstractScreen {
    private Player player;
    private OrthographicCamera camera;
    private Hud hud;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private ShapeRenderer shaper;
    private Viewport viewport;
    private WorldMap worldMap;

    private MovementManager movementManager;

    public GridMovementScreen(BasicMovementGame game) {
        super(game, "output/brendan.atlas");
    }

    @Override
    public void show() {
        initializeMap();
        initializeActors();
        initializeMovement();
        initializeCamera();
        initializePlayer();
    }

    private void initializeActors() {
        hud = new Hud(game.getBatch());
    }

    private void initializeMovement() {
        movementManager = new MovementManager(16, 16, new MovementObserver(worldMap, hud));
        Gdx.input.setInputProcessor(movementManager.getInput());
    }

    private void initializePlayer() {
        player = new Player(this, 56, 21, 60, 21);
        player.setPosition(128, 64);
        player.setTargetPosition(128, 64);
    }

    private void initializeCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getHeight(), Gdx.graphics.getHeight());
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        shaper = new ShapeRenderer();
    }

    private void initializeMap() {
        worldMap = new WorldMap(player);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/town.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        createBodies("walls", Wall.class);
        createBodies("ocean", Ocean.class);
        createBodies("signs", Readable.class);
        createBodies("tall grass", TallGrass.class);
        createBodies("doors", Door.class);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        renderMap();
        renderShapes();
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
        shaper.end();
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
        movementManager.manage(player, worldMap);

        player.update(delta);
        hud.update(player);
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();
    }

    private void createBodies(String objectName, Class<? extends InteractiveTile> entityClass) {
        for (MapObject object : map.getLayers().get(objectName).getObjects().getByType(RectangleMapObject.class)) {
            try {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                Constructor<? extends InteractiveTile> ctor = entityClass.getConstructor(WorldMap.class, TiledMap.class, Rectangle.class);
                ctor.newInstance(worldMap, map, rectangle);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getClass().toString());
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
