package com.basic.movement.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.*;
import com.basic.movement.*;
import com.basic.movement.player.*;
import com.basic.movement.scene.*;
import com.basic.movement.utils.*;
import com.basic.movement.world.*;
import com.basic.movement.world.Readable;

import java.lang.reflect.Constructor;

public class SceneScreen extends AbstractScreen {

    private TextureAtlas atlas;
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

    private ActorPlayer actorPlayer;
    private Stage stage;

    public SceneScreen(BasicMovementGame game) {
        super(game);
    }

    public TextureAtlas getAtlas() {
        return this.atlas;
    }

    @Override
    public void show() {
        atlas = new TextureAtlas("output/brendan.atlas");

        initializeMap();
        initializeHUD();
        initializeMovement();
        initializePlayer();
        initializeCamera();
        initializeActors();
    }

    private void initializeActors() {
        actorPlayer = new ActorPlayer(player);
        stage = new Stage(viewport);

        stage.addActor(actorPlayer);
    }

    private void initializeHUD() {
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
        worldMap = new WorldMap();

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

        hud.update(player);
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
