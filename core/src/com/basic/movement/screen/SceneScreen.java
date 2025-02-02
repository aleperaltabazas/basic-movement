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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.basic.movement.BasicMovementGame;
import com.basic.movement.movement.MovementManager;
import com.basic.movement.movement.MovementObserver;
import com.basic.movement.player.ActorPlayer;
import com.basic.movement.player.PlayerSprite;
import com.basic.movement.scene.Hud;
import com.basic.movement.world.Readable;
import com.basic.movement.world.*;

import java.lang.reflect.Constructor;

public class SceneScreen extends AbstractScreen {
    private PlayerSprite playerSprite;
    private OrthographicCamera camera;
    private Hud hud;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private ShapeRenderer shaper;
    private Viewport viewport;
    private WorldMap worldMap;

    private MovementManager movementManager;

    private ActorPlayer actorPlayer;
    private Stage stage;

    public SceneScreen(BasicMovementGame game) {
        super(game, "packed/brendan.atlas");
    }

    @Override
    public void show() {
        loadMap();
        loadHUD();
        loadMovement();
        loadPlayer();
        loadCamera();
        loadActors();
    }

    private void loadActors() {
        actorPlayer = new ActorPlayer(playerSprite);
        stage = new Stage(viewport);

        stage.addActor(actorPlayer);
    }

    private void loadHUD() {
        hud = new Hud(game.getBatch());
    }

    private void loadMovement() {
        movementManager = new MovementManager(16, 16, new MovementObserver(worldMap, hud));
        Gdx.input.setInputProcessor(movementManager.getInput());
    }

    private void loadPlayer() {
        playerSprite = new PlayerSprite(this, 56, 21, 60, 21);
        playerSprite.setPosition(128, 64);
        playerSprite.setTargetPosition(128, 64);
    }

    private void loadCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getHeight(), Gdx.graphics.getHeight());
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        shaper = new ShapeRenderer();
    }

    private void loadMap() {
        worldMap = new WorldMap(playerSprite);

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
        renderHUD();
        stage.draw();
        stage.act();

        update(delta);
    }

    private void renderBatch() {
        game.getBatch().setProjectionMatrix(stage.getCamera().combined);
        game.getBatch().begin();
        playerSprite.draw(game.getBatch());
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
        shaper.setProjectionMatrix(stage.getCamera().combined);
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
        movementManager.manage(actorPlayer, worldMap);

        hud.update(playerSprite);
        stage.getCamera().position.set(actorPlayer.getX(), actorPlayer.getY(), 0);
        stage.getCamera().update();
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
