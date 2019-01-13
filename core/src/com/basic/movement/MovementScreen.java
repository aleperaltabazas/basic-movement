package com.basic.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MovementScreen extends AbstractScreen {
    private Brendan brendan;
    private InputManager manager;
    private BrendanInput input;

    private static final int ANCHO = 56;
    private static final int ALTO = 21;

    private TextureAtlas atlas;
    private TextureRegion[] walkingFrames;
    private Animation<TextureRegion> walkingAnimation;

    private TextureRegion brendanRegion;

    private float duration = 0;

    private float x = (float) Gdx.graphics.getWidth() / 2;
    private float y = (float) Gdx.graphics.getHeight() / 2;

    public MovementScreen(BasicMovementGame game) {
        super(game);
    }

    private Animation<TextureRegion> renderFromAtlas(String regionName) {
        TextureRegion region = atlas.findRegion(regionName);
        brendanRegion = new TextureRegion(region, 0, 0, ANCHO, ALTO);

        TextureRegion[][] temp = brendanRegion.split(ANCHO / 4, ALTO);
        walkingFrames = new TextureRegion[temp.length * temp[0].length];

        int index = 0;

        for (TextureRegion[] textureRegions : temp) {
            for (TextureRegion textureRegion : textureRegions) {
                walkingFrames[index++] = textureRegion;
            }
        }

        return new Animation<TextureRegion>(0.16f, walkingFrames);
    }

    @Override
    public void show() {
        atlas = new TextureAtlas("output/atlas2.atlas");

        brendan = new Brendan(this);
        brendan.setPosition(100, 100);

        manager = new InputManager();
        input = new BrendanInput(manager);

        walkingAnimation = renderFromAtlas("front");
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        duration += delta;
        TextureRegion frame = walkingAnimation.getKeyFrame(duration, true);

        game.getBatch().begin();
        game.getBatch().draw(frame, x, y);
        game.getBatch().draw(frame.getTexture(), 407, 56);
        brendan.draw(game.getBatch());
        game.getBatch().end();

        if (manager.isMovingDown() && !manager.isMoving(InputManager.Direction.DOWN)) {
            walkingAnimation = renderFromAtlas("front");
            brendan.moveDown();
        } else if (manager.isMovingLeft() && !manager.isMoving(InputManager.Direction.LEFT)) {
            walkingAnimation = renderFromAtlas("left");
            brendan.moveLeft();
        } else if (manager.isMovingRight() && !manager.isMoving(InputManager.Direction.RIGHT)) {
            walkingAnimation = renderFromAtlas("right");
            brendan.moveRight();
        } else if (manager.isMovingUp() && !manager.isMoving(InputManager.Direction.UP)) {
            walkingAnimation = renderFromAtlas("back");
            brendan.moveUp();
        }

        brendan.update(delta);
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }
}
