package com.freemovement.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.freemovement.game.FreeMovementGame;
import com.freemovement.game.movement.MovementManager;
import com.freemovement.game.player.Player;

public class FreeMovementScreen extends AbstractScreen {
    private Player player;
    private MovementManager manager;

    private OrthographicCamera camera;

    private Box2DDebugRenderer debugRenderer;

    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private TmxMapLoader mapLoader;

    public FreeMovementScreen(FreeMovementGame game) {
        super(game, new World(new Vector2(0, 0), true));
    }

    @Override
    public void show() {
        initializeMap();
        initializePlayer();
        initializeMovement();
        initializePhysics();
        initializeCamera();
    }

    private void initializeMap() {
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/town.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        createBodies("tall grass");
        createBodies("walls");
        createBodies("ocean");
        createBodies("signs");
        createBodies("doors");
    }

    private void initializeCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void initializePhysics() {
        debugRenderer = new Box2DDebugRenderer();
        debugRenderer.SHAPE_KINEMATIC.set(Color.RED);
        debugRenderer.SHAPE_STATIC.set(Color.BLUE);
    }

    private void initializePlayer() {
        player = new Player(this);
        player.getBody().setTransform(150, 150, 0);
    }

    private void initializeMovement() {
        manager = new MovementManager();
        Gdx.input.setInputProcessor(manager.getInputManager());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        renderMap();
        renderPhysics();
        renderBatch();

        update(delta);
    }

    private void renderMap() {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    private void renderPhysics() {
        debugRenderer.render(world, camera.combined);
    }

    private void renderBatch() {
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        player.draw(game.getBatch());
        game.getBatch().end();
    }

    private void update(float delta) {
        world.step(1 / 60f, 6, 2);
        manager.manage(player);
        player.update(delta);

        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();
    }

    @Override
    protected void childDispose() {

    }

    private void createBodies(String objectName) {
        for (MapObject object : map.getLayers().get(objectName).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            BodyDef bodyDef = new BodyDef();
            PolygonShape shape = new PolygonShape();
            FixtureDef fixtureDef = new FixtureDef();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);

            Body body = world.createBody(bodyDef);

            shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
    }
}
