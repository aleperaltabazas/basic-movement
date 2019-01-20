package com.freemovement.game.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.freemovement.game.FreeMovementGame;
import com.freemovement.game.player.Player;

public class FreeMovementScreen extends AbstractScreen {
    private Player player;

    public FreeMovementScreen(FreeMovementGame game, AssetManager assetManager) {
        super(game, assetManager);
    }

    @Override
    public void show() {
        player = new Player(this);
    }

    @Override
    public void render(float delta) {
        game.getBatch().begin();
        game.getBatch().end();
    }

    @Override
    protected void childDispose() {

    }
}
